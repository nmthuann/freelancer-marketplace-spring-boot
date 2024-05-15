# Description API `/auth` path



- **domain**: `localhost:8888`.
- **url**:  `http://localhost:8888/api/v1/auth`

## 1. Login

### 1.1 General Information and Endpoints

| Name      | Version | Đường dẫn      | Method | Mô tả            |
|-----------|---------|----------------|--------|------------------|
| API Login | 1.0.0   | `/user/login`  | `POST` | Login the system |

### 1.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `null`             |

- #### Required body
| Field      | Required                                                                  |
|------------|---------------------------------------------------------------------------|
| `email`    | `@NotEmpty`                                                               |
| `password` | `@Size(min = 8, message = "Password must be at least 8 characters long")` |


**Body:**
```json
{ 
  "email": "johndoe@example.com",
  "password": "password123"
}
```

### 1.3 Response

#### Response Body (200 OK)
```json
{
  "accessToken": "<token>",
  "refreshToken": "<token>"
}
```

#### Error Code Reference

| Status Code | Error Name            | Description                                                                                               | Example Response Body                                                                                                   |
|-------------|-----------------------|-----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| 400         | Bad Request           | (e.g., malformed request syntax, invalid parameters).                                                     | ```json { "error": "Bad Request", "message": "Invalid request payload" }```                                             |
| 401         | Unauthorized          | The client blocked black list.                                                                            | ```json { "error": "Unauthorized", "message": "Invalid authentication token" }```                                       |
| 403         | Forbidden             | The client does not have access rights to the content, i.e., the server refuses to authorize the request. | ```json { "error": "Forbidden", "message": "You do not have permission to access this resource" }```                    |
| 404         | Not Found             | The client cannot register account                                                                        | ```json { "error": "Not Found", "message": "Resource not found" }```                                                    |
| 500         | Internal Server Error | given when an unexpected condition was encountered.                                                       | ```json { "error": "Internal Server Error", "message": "An unexpected error occurred on the server" }```                |




## 2. Register

### 2.1 General Information and Endpoints

| Name      | Version | Đường dẫn        | Method | Mô tả                        |
|-----------|---------|------------------|--------|------------------------------|
| API Login | 1.0.0   | `/user/register` | `POST` | Create Account in the system |

### 2.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `null`             |

- #### Required body
| Field       | Required                      |
|-------------|-------------------------------|
| `email`     | `@NotEmpty`                   |
| `password`  | `@Size(min = 8)`, `@NotEmpty` |
| `firstName` | `@NotEmpty`                   |
| `lastName`  | `@NotEmpty`                   |
| `gender`    | `@NotEmpty`                   |
| `birthday`  | ``                            |
| `phone`     | `@NotEmpty`                   |
| `location`  | `@NotEmpty`                   |


**Body:**
```json
 {
 "email": "test.register@email.com",
   "password": "123456",
   "firstName": "John",
   "lastName": "Doe",
   "gender": "Male",
   "birthday": "1990-01-01",
   "phone": "1234567890",
   "location": "Viet Nam"
 }
```

### 2.3 Response

#### Response Body (200 OK)
```json
{
  "accessToken": "<token>",
  "firstName": "Thuan"
}
``` 

#### Error Code Reference

| Status Code | Error Name            | Description                                                                                               | Example Response Body                                                                                                   |
|-------------|-----------------------|-----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| 400         | Bad Request           | (e.g., malformed request syntax, invalid parameters).                                                     | ```json { "error": "Bad Request", "message": "Invalid request payload" }```                                             |
| 401         | Unauthorized          | The client blocked black list.                                                                            | ```json { "error": "Unauthorized", "message": "Invalid authentication token" }```                                       |
| 403         | Forbidden             | The client does not have access rights to the content, i.e., the server refuses to authorize the request. | ```json { "error": "Forbidden", "message": "You do not have permission to access this resource" }```                    |
| 404         | Not Found             | The client cannot register account                                                                        | ```json { "error": "Not Found", "message": "Resource not found" }```                                                    |
| 500         | Internal Server Error | given when an unexpected condition was encountered.                                                       | ```json { "error": "Internal Server Error", "message": "An unexpected error occurred on the server" }```                |


## 3. Logout

### 3.1 General Information and Endpoints

| Name      | Version | Đường dẫn      | Method | Mô tả             |
|-----------|---------|----------------|--------|-------------------|
| API Login | 1.0.0   | `/user/logout` | `POST` | Logout the system |

### 3.2. Endpoint

- #### Required header

| Headers             | Values              |
|---------------------|---------------------|
| `Content-Type`      | `application/json`  |
| `Authorization`     | `Bearer <token>`    |

- #### Required body
`null`


**Body:**
```json
{ 
  "email": "johndoe@example.com",
  "password": "password123"
}
```

### 3.3 Response
#### Response Body (200 OK)
```json
{
  "email": "...@mail.com",
  "status": "200",
  "message": "Success",
  "timestamp": "now()"
  
}
``` 
#### Error Code Reference

| Status Code | Error Name            | Description                                                                                               | Example Response Body                                                                                                   |
|-------------|-----------------------|-----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| 400         | Bad Request           | (e.g., malformed request syntax, invalid parameters).                                                     | ```json { "error": "Bad Request", "message": "Invalid request payload" }```                                             |
| 401         | Unauthorized          | The client blocked black list.                                                                            | ```json { "error": "Unauthorized", "message": "Invalid authentication token" }```                                       |
| 403         | Forbidden             | The client does not have access rights to the content, i.e., the server refuses to authorize the request. | ```json { "error": "Forbidden", "message": "You do not have permission to access this resource" }```                    |
| 404         | Not Found             | The client cannot register account                                                                        | ```json { "error": "Not Found", "message": "Resource not found" }```                                                    |
| 500         | Internal Server Error | given when an unexpected condition was encountered.                                                       | ```json { "error": "Internal Server Error", "message": "An unexpected error occurred on the server" }```                |

