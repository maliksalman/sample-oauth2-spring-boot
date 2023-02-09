# Oauth2 with Spring Security 5.2.x 

There are sample resource-server and resource-client spring-boot applications which demonstrate how a service to service secure communication can occur. Got most of the reference information from https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide.

This example was tested against UAA as described in https://www.baeldung.com/cloud-foundry-uaa and against KeyCloak running in docker.


## Run KeyCloak in Docker

```
docker run -p 4040:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev
```

Login to the KeyCloak admin interface at http://localhost:4040/ with username and password both set to `admin`. Create a new *Realm* by importing the `keycloak.json` file

