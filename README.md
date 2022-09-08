### Task board

---

A RESTful based test project that performs operations 
for managing project with users and their roles.

- Data storage - Docker Engine 20.10.14 + PostgreSQL 14.1
- Test : Postman *!need to add headers in request* [ Content-Type ] [ text/xml ]; [ SOAPAction ] [ "#POST" ]
- API location example via Swagger [ http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ ]
- Test 1 step: Postman *!need to add paramerer [ Type = No Auth ] in tab "Authorization"
- Test 2 step: Postman *!prepare [ "POST" ] request to http://localhost:8080/api/v1/users <br/> with body:
```
{
"userRole": "ADMIN",
"userLogin": "TestUser123",
"userPasswd": "TestUser123",
"userPasswd2": "TestUser123",
"userStatus": "ACTIVE"
}
``` 

- Test 3 step: now Admin Login and Password [ TestUser123 : TestUser123 ]. Use it to get access to API. <br/> 
Default location [ http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ ]

---
