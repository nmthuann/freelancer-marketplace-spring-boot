# Description API `/user` path



- **domain**: `localhost:8888`.
- **url**:  `http://localhost:8888/api/v1/user`


1. Create Profile (Seller) -> OK
2. Create User Payment(Seller) -> OK
3. Update User Information (Seller - Buyer) -> OK
4. Get User By Email -> OK
5. Get Profile By Email -> NO
6. Get User Payment By Email
7. Get User Is Seller (Page)
8. Get User Is Buyer (Page)
9. Get Users (Page)
10. =>  Call API in User Controller affect to handle User Service too much code line.

## 1. Create Profile (Seller)

### 1.1 General Information and Endpoints

| Name               | Version | Đường dẫn         | Method | Mô tả                     |
|--------------------|---------|-------------------|--------|---------------------------|
| API Create Profile | 1.0.0   | `/create-profile` | `POST` | Create Profile For Seller |

### 1.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `Bearer <token>`   |

- #### Required body
| Field               | Type                             | Required     |
|---------------------|----------------------------------|--------------|
| `occupation`        | `String`                         | `@NotEmpty`  |
| `profileAttributes` | `Set<CreateProfileAttributeDto>` | `@NotEmpty`  |


| Field                 | Type                         | Required     |
|-----------------------|------------------------------|--------------|
| `profileAttributeId`  | `Integer`                    | `@NotEmpty`  |
| `profileValues`       | `Set<CreateProfileValueDto>` | `@NotEmpty`  |


| Field            | Type                      | Required     |
|------------------|---------------------------|--------------|
| `profileValue`   | `String`                  | `@NotEmpty`  |

**Body:**
```json
{
  "occupation": "data scientist",
  "profileAttributes": [
    {
      "profileAttributeId": 1,
      "profileValues": [
        {"profileValue": "Master of Data Science"}
      ]
    },
    {
      "profileAttributeId": 2,
      "profileValues": [
        {"profileValue": "Python programming"},
        {"profileValue": "R programming"}
      ]
    },
    {
      "profileAttributeId": 3,
      "profileValues": [
        {"profileValue": "Data Scientist at XYZ Data Solutions"},
        {"profileValue": "Senior Data Analyst at ABC Analytics"},
        {"profileValue": "Machine Learning Engineer at DEF AI Labs"}
      ]
    }
  ]
}
```

### 1.3 Response

#### Response Body (200 OK)
```json
{
  "userId": "c1a1b6c1-12d4-4a1a-94e6-123456789abc",
  "firstName": "John",
  "lastName": "Doe",
  "avatarURL": "http://example.com/avatar.jpg",
  "gender": "Male",
  "birthday": "1990-01-01T00:00:00.000+00:00",
  "location": "New York",
  "phone": "1234567890",
  "account": {
    "email": "johndoe@example.com",
    "password": "hashed_password",
    "role": "USER"
  },
  "userPayment": {
    "paymentId": "paymentId"
  },
  "profile": {
    "profileId": "profileId",
    "bio": "Bio text",
    "experience": "Experience details"
  }
}
``` 

#### Error Code Reference

| Status Code | Error Name            | Description                                             | Example Response Body                                                                                                   |
|-------------|-----------------------|---------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| 400         | Bad Request           | (e.g., malformed request syntax, invalid parameters).   | ```json { "error": "Bad Request", "message": "Invalid request payload" }```                                             |
| 401         | Unauthorized          | The client blocked black list.                          | ```json { "error": "Unauthorized", "message": "Invalid authentication token" }```                                       |
| 403         | Forbidden             | The client does not have access rights to the content.  | ```json { "error": "Forbidden", "message": "You do not have permission to access this resource" }```                    |
| 404         | Not Found             | The client cannot register account                      | ```json { "error": "Not Found", "message": "Resource not found" }```                                                    |
| 500         | Internal Server Error | given when an unexpected condition was encountered.     | ```json { "error": "Internal Server Error", "message": "An unexpected error occurred on the server" }```                |

## 2. Create User Payment (Seller)

### 2.1 General Information and Endpoints

| Name                    | Version | Đường dẫn              | Method | Mô tả                    |
|-------------------------|---------|------------------------|--------|--------------------------|
| API Create User Payment | 1.0.0   | `/create-user-payment` | `POST` | Create User Paymen       |

### 2.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `Bearer <token>`   |

- #### Required body
| Field            | Type                      | Required                                |
|------------------|---------------------------|-----------------------------------------|
| `cardNumber`     | `String`                  | `@NotEmpty`, `@Size(min=16, max = 16)`  |
| `cardholderName` | `String`                  | `@NotEmpty`, `min=5, max = 50)`         |
| `expired`        | `Date `                   | `@NotNull`                              |
| `cvv`            | `String`                  | `@NotEmpty`, `@Size(min=3, max = 3)`    |
| `country`        | `String`                  | `@NotEmpty`,  `@Size(min=16, max = 16)` |

**Body:**
```json
{
  "cardNumber": "1234567812345678",
  "cardholderName": "John Doe",
  "expired": "2025-12-31T00:00:00.000+00:00",
  "cvv": "123",
  "country": "VN"
}
```

### 3.3 Response

#### Response Body (200 OK)
```json
{
  "userPaymentId": "c1a1b6c1-12d4-4a1a-94e6-123456789abc",
  "cardNumber": "1234567812345678",
  "cardholderName": "John Doe",
  "expired": "2025-12-31T00:00:00.000+00:00",
  "cvv": "123",
  "country": "VN"
}
``` 

#### Error Code Reference

| Status Code | Error Name            | Description                                             | Example Response Body                                                                                                   |
|-------------|-----------------------|---------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| 400         | Bad Request           | (e.g., malformed request syntax, invalid parameters).   | ```json { "error": "Bad Request", "message": "Invalid request payload" }```                                             |
| 401         | Unauthorized          | The client blocked black list.                          | ```json { "error": "Unauthorized", "message": "Invalid authentication token" }```                                       |
| 403         | Forbidden             | The client does not have access rights to the content.  | ```json { "error": "Forbidden", "message": "You do not have permission to access this resource" }```                    |
| 404         | Not Found             | The client cannot register account                      | ```json { "error": "Not Found", "message": "Resource not found" }```                                                    |
| 500         | Internal Server Error | given when an unexpected condition was encountered.     | ```json { "error": "Internal Server Error", "message": "An unexpected error occurred on the server" }```                |





## 3. Update User

### 3.1 General Information and Endpoints

| Name       | Version | Đường dẫn | Method | Mô tả                   |
|------------|---------|-----------|--------|-------------------------|
| API Update | 1.0.0   | `/update` | `PUT`  | Update User Information |

### 3.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `Bearer <token>`   |

- #### Required body
| Field       | Required | Required    |
|-------------|----------|-------------|
| `firstName` | `String` | `@NotEmpty` |
| `lastName`  | `String` | `@NotEmpty` |
| `gender`    | `String` | `@NotEmpty` |
| `birthday`  | `Date`   | ``          |
| `phone`     | `String` | `@NotEmpty` |
| `location`  | `String` | `@NotEmpty` |
| `avatarUrl` | `String` | ``          |


**Body:**
```json
 {
  "firstName": "John",
  "lastName": "Doe",
  "gender": "Male",
  "birthday": "1990-01-01",
  "phone": "1234567870",
  "location": "New York",
  "avatarURL": null
}
```

### 3.3 Response

#### Response Body (200 OK)
```json
{
  "userId": "c1a1b6c1-12d4-4a1a-94e6-123456789abc",
  "firstName": "John",
  "lastName": "Doe",
  "avatarURL": "http://example.com/avatar.jpg",
  "gender": "Male",
  "birthday": "1990-01-01T00:00:00.000+00:00",
  "location": "New York",
  "phone": "1234567890",
  "account": {
    "email": "johndoe@example.com",
    "password": "hashed_password",
    "role": "USER"
  },
  "userPayment": {
    "paymentId": "paymentId"
  },
  "profile": {
    "profileId": "profileId",
    "bio": "Bio text",
    "experience": "Experience details"
  }
}
``` 

#### Error Code Reference

| Status Code | Error Name            | Description                                             | Example Response Body                                                                                                   |
|-------------|-----------------------|---------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| 400         | Bad Request           | (e.g., malformed request syntax, invalid parameters).   | ```json { "error": "Bad Request", "message": "Invalid request payload" }```                                             |
| 401         | Unauthorized          | The client blocked black list.                          | ```json { "error": "Unauthorized", "message": "Invalid authentication token" }```                                       |
| 403         | Forbidden             | The client does not have access rights to the content.  | ```json { "error": "Forbidden", "message": "You do not have permission to access this resource" }```                    |
| 404         | Not Found             | The client cannot register account                      | ```json { "error": "Not Found", "message": "Resource not found" }```                                                    |
| 500         | Internal Server Error | given when an unexpected condition was encountered.     | ```json { "error": "Internal Server Error", "message": "An unexpected error occurred on the server" }```                |
