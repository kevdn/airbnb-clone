# Airbnb Clone

## Introduction
- Description:
  - Developing a Homestay Booking System like Airbnb

## Techniques
- Data Modeling
- API Design
- Codebase
- Database
- JPA/Hibernate
- Deployment
- ...

## Tech Stack
- Programming Language: Java 21
- Framework: Spring Boot 3.3.0
- Database: Postgres 16
- Integration: VNPay Payment Gateway
- Deployment
  - Container: Docker


  
## API cURL Sample

1. Search Homestay by Area
```bash
curl --location 'http://localhost:8080/api/v1/homestays?longitude=105.85093677113572&latitude=21.00331838574515&radius=1000&checkin_date=2024-07-04&checkout_date=2024-07-07&guests=2'
```
2. Book Homestay
```bash
curl --location 'http://localhost:8080/api/v1/bookings' \
--header 'Content-Type: application/json' \
--data '{
    "request_id": "qweq",
    "user_id": 1,
    "homestay_id": 1,
    "checkin_date": "2024-07-10",
    "checkout_date": "2024-07-13",
    "guests": 2,
    "note": "note test"
}'
```
## Payment Testing
This guide for Local Testing.

1. Create an ngrok account and register a domain.
2. Install ngrok in local machine and add the auth token.
```bash
snap install ngrok
ngrok config add-authtoken <token>
```
3. Create tunnel
```bash
ngrok http 8080 --domain=<your-domain>.ngrok-free.app
```

4. Call the booking homestay API.

5. Copy the `vnp_url` then paste onto the browser.

6. Fake a payment:
   - Choose "Thẻ nội địa"
   - Bank: NCB
   - Enter the card info:
    ```
    9704198526191432198
    NGUYEN VAN A
    07/15
    ```
   - Enter OTP: `123456`


