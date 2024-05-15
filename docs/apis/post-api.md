# Description API `/posts` path



- **domain**: `localhost:8888`.
- **url**:  `http://localhost:8888/api/v1/posts`



1. createPost -> ok
2. updatePost -> ok
3. getOneById -> Ok
4. getAll {size, page, latest, by major, best seller, top feedback}
5. getPostByUser -> OK
6. getPostByMajor -> OK
7. getPostByCategory -> OK
8. updatePackage -> OK
9. updateImages -> OK 
10. stopPost -> OK
11. deletePost -> OK
12. getPostTopRating



## 1. Create Post (User)

### 1.1 General Information and Endpoints

| Name            | Version | Đường dẫn | Method | Mô tả                        |
|-----------------|---------|-----------|--------|------------------------------|
| API Create Post | 1.0.0   | `/create` | `POST` | Create a new Post For System |

### 1.2. Endpoint

- #### Required header

| Headers             | Values             |
|---------------------|--------------------|
| `Content-Type`      | `application/json` |
| `Authorization`     | `Bearer <token>`   |

- #### Required body
| Field          | Type      | Required     |
|----------------|-----------|--------------|
| `title`        | `int`     | ` @NotNull`  |
| `description`  | `String`  | `@NotEmpty`  |
| `description`  | `String`  | ``           |
| `majorId`      | `String`  | `@NotNull`   |
| `images`       | `String`  | `@NotEmpty`  |
| `packages`     | `String`  | `@NotEmpty`  |



**Body:**
```json
{
  "title": "Analyse financial data, build chart using python, excel.",
  "description": "Iam a Computer Information and Systems Engineer.",
  "faq": "Frequently asked questions can be added here.",
  "majorId": 9,
  "images": [
    { "url": "https://res.cloudinary.com/dhvwulfgc/image/upload/v1699292758/xwmjmexrkqsbqrpgqx8s.jpg" },
    { "url": "https://res.cloudinary.com/dhvwulfgc/image/upload/v1699292572/rzkwmalll6lost7pk6ue.jpg" }
  ],
  "packages": [
    {
      "packageName": "Data Analytics",
      "caption": "I will analysis on dataset to obtain results.",
      "revision": "No revision",
      "deliveryDay": 1,
      "unitPrice": 95.00,
      "beginAt": "2024-04-06"
    },
    {
      "packageName": "Deep Data Analytics",
      "caption": "Create Report, statistical, build Chart in Dataset.",
      "revision": "No revision",
      "deliveryDay": 2,
      "unitPrice": 115.00,
      "beginAt": "2024-04-06"
    },
    {
      "packageName": "Predict based on Deep Data Analytics",
      "caption": "Predict based on Deep Data Analytics",
      "revision": "2 revisions included",
      "deliveryDay": 5,
      "unitPrice": 150.00,
      "beginAt": "2024-04-06"
    }
  ]
}
```

### 1.3 Response

#### Response Body (201 CREATED)
```json
{
  "majorId": 1,
  "postId": "123e4567-e89b-12d3-a456-426614174000",
  "title": "Learn Java Programming",
  "description": "This course covers the basics of Java programming.",
  "faq": "What is the duration of the course?",
  "rating": 4.5,
  "vote": 120,
  "sellerEmail": "john.doe@example.com",
  "sellerFullName": "John Doe",
  "images": [
    {
      "imageId": 1,
      "url": "http://example.com/image1.jpg"
    },
    {
      "imageId": 2,
      "url": "http://example.com/image2.jpg"
    }
  ],
  "packages": [
    {
      "packageId": 1,
      "name": "Basic Package",
      "description": "Access to all video lessons",
      "price": 49.99,
      "deliveryTime": 5,
      "revisions": 1
    },
    {
      "packageId": 2,
      "name": "Standard Package",
      "description": "Access to all video lessons",
      "price": 59.99,
      "deliveryTime": 4,
      "revisions": 2
    },
    {
      "packageId": 3,
      "name": "Premium Package",
      "description": "Access to all video lessons and one-on-one mentorship",
      "price": 99.99,
      "deliveryTime": 2,
      "revisions": 3
    }
  ]
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
