package com.kevdn.airbnb.domain.payment.service;

import com.kevdn.airbnb.domain.common.constant.Currency;
import com.kevdn.airbnb.domain.common.constant.Locale;
import com.kevdn.airbnb.domain.payment.constant.VNPayParams;
import com.kevdn.airbnb.domain.payment.dto.request.InitPaymentRequest;
import com.kevdn.airbnb.domain.payment.dto.response.InitPaymentResponse;
import com.kevdn.airbnb.infrastructure.constant.Symbol;
import com.kevdn.airbnb.infrastructure.service.CryptoService;
import com.kevdn.airbnb.infrastructure.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class VNPayService implements PaymentService {

    public static final String VERSION = "2.1.0";
    public static final String COMMAND = "pay";
    public static final String ORDER_TYPE = "190000";
    public static final long DEFAULT_MULTIPLIER = 100L;

    @Value("${payment.vnpay.tmn-code}")
    private String tmnCode;

    @Value("${payment.vnpay.init-payment-url}")
    private String initPaymentPrefixUrl;

    @Value("${payment.vnpay.return-url}")
    private String returnUrlFormat;

    @Value("${payment.vnpay.timeout}")
    private Integer paymentTimeout;

    private final CryptoService cryptoService;


    public InitPaymentResponse init(InitPaymentRequest request) {
        var amount = request.getAmount() * DEFAULT_MULTIPLIER;  // 1. amount * 100
        var txnRef = request.getTxnRef();                       // 2. bookingId
        var returnUrl = buildReturnUrl(txnRef);                 // 3. FE redirect by returnUrl
        var vnCalendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        var createdDate = DateUtil.formatVnTime(vnCalendar);
        vnCalendar.add(Calendar.MINUTE, paymentTimeout);
        var expiredDate = DateUtil.formatVnTime(vnCalendar);    // 4. expiredDate for secure

        var ipAddress = request.getIpAddress();
        var orderInfo = buildPaymentDetail(request);
        var requestId = request.getRequestId();

        Map<String, String> params = new HashMap<>();

        params.put(VNPayParams.VERSION, VERSION);
        params.put(VNPayParams.COMMAND, COMMAND);

        params.put(VNPayParams.TMN_CODE, tmnCode);
        params.put(VNPayParams.AMOUNT, String.valueOf(amount));
        params.put(VNPayParams.CURRENCY, Currency.VND.getValue());

        params.put(VNPayParams.TXN_REF, txnRef);
        params.put(VNPayParams.RETURN_URL, returnUrl);

        params.put(VNPayParams.CREATED_DATE, createdDate);
        params.put(VNPayParams.EXPIRE_DATE, expiredDate);

        params.put(VNPayParams.IP_ADDRESS, ipAddress);
        params.put(VNPayParams.LOCALE, Locale.VIETNAM.getCode());

        params.put(VNPayParams.ORDER_INFO, orderInfo);
        params.put(VNPayParams.ORDER_TYPE, ORDER_TYPE);

        var initPaymentUrl = buildInitPaymentUrl(params);
        log.debug("[request_id={}] Init payment url: {}", requestId, initPaymentUrl);
        return InitPaymentResponse.builder()
                .vnpUrl(initPaymentUrl)
                .build();
    }

    public boolean verifyIpn(Map<String, String> params) {
        var reqSecureHash = params.get(VNPayParams.SECURE_HASH);
        params.remove(VNPayParams.SECURE_HASH);
        params.remove(VNPayParams.SECURE_HASH_TYPE);
        var hashPayload = new StringBuilder();
        var fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        var itr = fieldNames.iterator();
        while (itr.hasNext()) {
            var fieldName = itr.next();
            var fieldValue = params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                //Build hash data
                hashPayload.append(fieldName);
                hashPayload.append(Symbol.EQUAL);
                hashPayload.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                if (itr.hasNext()) {
                    hashPayload.append(Symbol.AND);
                }
            }
        }

        var secureHash = cryptoService.sign(hashPayload.toString());
        return secureHash.equals(reqSecureHash);
    }

    private String buildPaymentDetail(InitPaymentRequest request) {
        return String.format("Thanh toan don dat phong %s", request.getTxnRef());
    }

    private String buildReturnUrl(String txnRef) {
        return String.format(returnUrlFormat, txnRef);
    }

    @SneakyThrows
    private String buildInitPaymentUrl(Map<String, String> params) {
        var hashPayload = new StringBuilder();
        var query = new StringBuilder();
        var fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);   // 1. Sort field names

        var itr = fieldNames.iterator();
        while (itr.hasNext()) {
            var fieldName = itr.next();
            var fieldValue = params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                // 2.1. Build hash data
                hashPayload.append(fieldName);
                hashPayload.append(Symbol.EQUAL);
                hashPayload.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                // 2.2. Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append(Symbol.EQUAL);
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                if (itr.hasNext()) {
                    query.append(Symbol.AND);
                    hashPayload.append(Symbol.AND);
                }
            }
        }

        // 3. Build secureHash
        var secureHash = cryptoService.sign(hashPayload.toString());

        // 4. Finalize query
        query.append("&vnp_SecureHash=");
        query.append(secureHash);

        return initPaymentPrefixUrl + "?" + query;
    }

//    @PostConstruct
//    void init() {
//        var resp = init(InitPaymentRequest.builder()
//                .requestId("r45")
//                .ipAddress("127.0.0.1")
//                .userId(34L)
//                .txnRef("b02")
//                .amount(100000)
//                .build());
//        log.info("Init payment url: {}", resp.getVnpUrl());
//    }
}
