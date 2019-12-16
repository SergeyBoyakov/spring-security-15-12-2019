# Protect REST APIs with Spring Security and JWT

> When you design REST APIs, you have to consider how to protect REST APIs. In a Spring based application, Spring Security is a great 
authentication and authorization solution, and it provides several options for securing your REST APIs.
The simplest approach is utilizing HTTP Basic which is activated by default when you are bootstrap a Spring Boot based application. 
It is good for development purpose, and it is used frequently in development phase, but it is not recommended in a production environment.
Spring Session(with Spring Security) provides a simple strategy to create and validate header based token(session id), it can be used
 for protecting RESTful APIs, I have demonstrated it in my microservice sample and my RESTful sample.
Beside these, Spring Security OAuth (a subproject under Spring Security) provides a complete solution of OAuth authorization, including
 the implementations of all roles defined in OAuth2 protocol, such as Authorization Server, Resource Server, OAuth2 Client etc. Spring
  Cloud adds Single Sign On capability to OAuth2 Client via its subproject Spring Cloud Security. In the Spring Security OAuth based
   solution, the content of access token can be a signed JWT token or an opaque value, and we have to follow the standard OAuth2 
   authorization flow to obtain access token.
But for those applications owned by the resource owner and there is no plan to expose these APIs to third party applications, a simple 
JWT token based authorization is more simple and reasonable(we do not need manage the credentials of third party client applications).
 Spring Security itself does not provide such an option, fortunately it is not difficult to implement it by weaving our custom filter
  into the Spring Security Filter Chain. In this post, we will create such a custom JWT authentication solution.
In this sample application, the custom JWT token based authentication flow can be designated as the following steps.
1. Get the JWT based token from the authentication endpoint, eg /auth/signin.
2. Extract token from the authentication result.
3. Set the HTTP header Authorization value as Bearer jwt_token.
4. Then send a request to access the protected resources.
5. If the requested resource is protected, Spring Security will use our custom Filter to validate the JWT token, and build an Authentication object and set it in Spring Security specific SecurityContextHolder to complete the authentication progress.
6. If the JWT token is valid it will return the requested resource to client.
