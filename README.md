## Description

Admin-Service is an Application Programming interface for add,update,delete and get admin of ZOHS company.

---
## Running the app
```bash
# build app
$ mvn clean install
# run the app
$ java -jar target/admin-service.jar
```
---
## Dockerize the app
```bash
# dockerize the app with mysql database
$ docker compose up
# stop the application
$ docker compose down
```
---

## DTOs (Data Transfer Objects) for Admin

### 1. AdminCreateDTO

Represents the data required to create a new admin.

- **Fields:**
    - `username: String` - Username of the admin.
    - `email: String` - Email of the admin.
    - `password: String `- Password of the admin.

### 2. AdminLoginDTO

Represents the data of a admin in login.

- **Fields:**
  - `email: String` - Email of the admin.
  - `password: String `- Password of the admin.
    

### 3. AdminUpdateDTO

Represents the data used to update an existing admin.

- **Fields:**
  - `username: String` - Username of the admin.
  - `email: String` - Email of the admin.
  - `password: String `- Password of the admin.


### 4. ApiResponse

Represents the data used to send message response.

- **Fields:**
  - `message: String` - message of the response.


### 4. JwtResponse

Represents the data used to send token.

- **Fields:**
  - `token: String` - token of jwt.


---

## Available Endpoints

### 1. Save Admin

- **Endpoint:** `POST /admin`
- **Description:** Save a new admin.
- **Request Body:**
    - `AdminCreateDTO`: Data for creating a new admin.
- **Response:**
    - `201`: Admin created successfully.
    - `400`: Bad Request (invalid input).
    - `500`: Internal Server Error.

### 2. Get All Admins

- **Endpoint:** `GET /admin`
- **Description:** Retrieve a list of all admins.
    - `200`: Successful retrieval with a list of admins.
    - `500`: Internal Server Error.

### 5. Get Admin by ID

- **Endpoint:** `GET /admin/client/{id}`
- **Description:** Retrieve admin associated with a specific ID.
- **Response:**
    - `200`: Successful retrieval with a list of valid driver licenses.
    - `400`: Bad Request.
    - `404`: Admin not found.
    - `500`: Internal Server Error.

### 6. Login Admin

- **Endpoint:** `GET /admin/invalid/client/{clientId}`
- **Description:** .
- **Request Body:**
  - `AdminLoginDTO`: Data for sign in admin.
- **Response:**
    - `200`: Successful retrieval with a token of login.
    - `400`: Bad Request.
    - `500`: Internal Server Error.

### 7. Delete Admin By Id

- **Endpoint:** `DELETE /admin/{id}`
- **Description:** Delete an existing admin by ID.
- **Response:**
    - `200`: Admin deleted successfully.
    - `404`: Admin not found.
    - `400`: Bad Request.
    - `500`: Internal Server Error.




---





## Stay in touch
- Author - [Ouail Laamiri](https://www.linkedin.com/in/ouaillaamiri/) 


## License

Admin Service is [GPL licensed](LICENSE).