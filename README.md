# Airbnb Clone

## Introduction
- Description:
  - Developing a Homestay Booking System like Airbnb

## Techniques
- Data Modeling
- API Design
- Codebase: Principles, Design Patterns
- Database
- JPA/Hibernate
- Caching
- Unit Testing
- Integration
- Security
- Deployment
- ...

## Tech Stack
- Programming Language: Java 21
- Framework: Spring Boot 3.3.0
- Database: Postgres 16
- Cache: Redis 7
- Unit Testing: JUnit 5
- Integration: VNPay Payment Gateway
- Security: JWT, Spring Security, ...
- Deployment
  - Container: Docker
  - CICD: Github Actions

  
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

