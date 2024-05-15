# Description API `/majors` path



- **domain**: `localhost:8888`.
- **url**:  `http://localhost:8888/api/v1/majors`


1. Create Major (Admin) -> OK


## 1. Create Major (Admin)

### 1.1 General Information and Endpoints

| Name             | Version | Đường dẫn | Method | Mô tả                   |
|------------------|---------|-----------|--------|-------------------------|
| API Create Major | 1.0.0   | `/create` | `POST` | Create Major For System |

### 1.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `Bearer <token>`   |

- #### Required body


| Field          | Type        | Required    |
|----------------|-------------|-------------|
| `categoryId`   | `int`       | `@NonNull`  |
| `categoryName` | `String `   | `@NotEmpty` |



**Body:**
```json
{
  "categoryId": 15,
  "categoryName": "Remixing"
}
```

### 1.3 Response

#### Response Body (201 CREATED)
```json
{
  "majorId": 1,
  "majorName": "Computer Science",
  "category": {
    "categoryId": 2,
    "categoryName": "Technology",
    "description": "All kinds of technology-related subjects",
    "leftValue": 1,
    "rightValue": 10,
    "parentId": 0
  },
  "majorValues": [],
  "posts": []
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
