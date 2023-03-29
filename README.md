# Account Maker

This project was generated with SpringBoot Initializer. To create accounts for already available customers.

## Prerequisites

- Java 17
- Node latest
- Maven
- Docker

## Technology Used

| Stack         | Value                                                                                                                    |
| ------------- | ------------------------------------------------------------------------------------------------------------------------ |
| Tools         | Intellij, Postman, Visualstudio Code, Github                                                                             |
| Languages     | Java, javaScript, Shell                                                                                                  |
| Technologies  | Spring, Spring Boot, Spring Batch, Spring Data JPA, Junit, H2, Docker, ReactJs, Swagger, RestAPI, Github Actions         |

## Getting Started

  So to run the application you just need to run this batch script on your machine it will run both of the services on docker and you'll be able to hit the services on
  following endpoints.
frontend:  http://localhost:3000/
backend: http://localhost:8080/
swagger: http://localhost:8080/swagger-ui/index.html

----
	$ .\startScript.bat
----
this will run the frontend container and the backend container on Docker. As shown in the image
![image](https://user-images.githubusercontent.com/54959355/225381636-f79fff9c-84ea-4893-92c2-e8bea90c4eb6.png)

After running this you'll be able to hit the API's on the Endpoints mentioned. Here's some sample snippets for reference.

## Frontend
To hit the API's from frontend no extra steps needed for now. As a default user is been setup to interact with the Application.

![image](https://user-images.githubusercontent.com/54959355/225382035-bf45a1b6-3f72-4ae6-b2ea-f9e8e8eb5a5d.png)

## Backend
For Backend you need to generate the Token by calling this endPoint from Postman. http://localhost:8080/authentication
with default user name and password. Then use the Generated token as Autherization Header for other Requests.

![image](https://user-images.githubusercontent.com/54959355/228573551-11beb3c2-6d1d-4f50-993c-ff4b072c266e.png)

![image](https://user-images.githubusercontent.com/54959355/225382468-521d350c-21ad-4e28-986a-49482fe44c9b.png)
![image](https://user-images.githubusercontent.com/54959355/228573965-2ce86acb-cfc8-4ce5-b33a-e8213bde8b91.png)

## Swagger-UI
For Swagger-UI you need to generate the token using the post call for authentication.

![image](https://user-images.githubusercontent.com/54959355/228574709-ecdf0cd1-cebd-43e1-b708-6cb3b965ea6c.png)

After token generation put the token in the Authorization in Swagger-UI page without Bearer 

![image](https://user-images.githubusercontent.com/54959355/228575084-cc361209-a6b2-4632-988e-159edce3078d.png)

![image](https://user-images.githubusercontent.com/54959355/225382779-b403210e-3abb-4b79-a28a-887d86f0b155.png)

## Github Actions

	Added github Actions to ensure the successfull integration of the system.
![image](https://user-images.githubusercontent.com/54959355/225385022-433e2b25-969a-4528-b9a4-33bc86c39832.png)

