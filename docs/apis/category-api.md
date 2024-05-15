# Description API `/categories` path



- **domain**: `localhost:8888`.
- **url**:  `http://localhost:8888/api/v1/categories`


## 1. Create Category (Admin)

### 1.1 General Information and Endpoints

| Name                | Version | Đường dẫn | Method | Mô tả                      |
|---------------------|---------|-----------|--------|----------------------------|
| API Create Category | 1.0.0   | `/create` | `POST` | Create Category For System |

### 1.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `Bearer <token>`   |

- #### Required body
| Field            | Type      | Required    |
|------------------|-----------|-------------|
| `parentId`       | `int`     | ` @NotNull` |
| `categoryName`   | `String`  | `@NotEmpty` |
| `description`    | `String`  | ``          |


**Body:**
```json
{
  "parentId": 1,
  "categoryName": "Graphic and Design",
  "description": "A single place, millions of creative talents"
}
```

### 1.3 Response

#### Response Body (201 CREATED)
```json
{
  "categoryId": 2,
  "categoryName": "Graphic and Design",
  "description": "A single place, millions of creative talents",
  "leftValue": 5,
  "rightValue": 6,
  "parentId": 1
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
