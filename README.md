# REST API phone book application

Phone book Rest API

## Requirements
    Maven
    Docker

## Install

    mvn clean install

## Run the app

    docker-compose build --no-cache
    docker-compose up
Then navigate to [localhost:8080](http://localhost:8080) in your browser

## Swagger documentation

    http://localhost:8080/swagger-ui.html

## Run tests
```mvn site```

When it's done report will be available in ```/phonebook/target/site/surefire-report.html```

# REST API

The REST API to phone book app is described below.

## Get list of contacts

Get list of contacts based on criteria search param

### Request

`POST /v1/contacts/search/`

    curl -X POST \
      http://localhost:8080/v1/contacts/search \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 106' \
      -H 'Content-Type: application/json' \
      -H 'Cookie: BBXSRF=6095fb9e-9af8-4c45-b9bc-45621deed00e' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: 1fe01ce7-bc0d-4ce4-a328-d4e5b88f45d6,e9dca113-850b-46f7-9a50-3c4289fbddfe' \
      -H 'User-Agent: PostmanRuntime/7.16.3' \
      -H 'cache-control: no-cache' \
      -d '{
        "filterParam": "jack",
        "sort":{
            "order": "ASC",
            "field": "firstName"
        },
        "page": 0,
        "size": 10
    }'


## Create a contact

### Request

`POST /v1/contacts/`

    curl -X POST \
      http://localhost:8080/v1/contacts \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Cache-Control: no-cache' \
      -H 'Connection: keep-alive' \
      -H 'Content-Length: 151' \
      -H 'Content-Type: application/json' \
      -H 'Cookie: BBXSRF=6095fb9e-9af8-4c45-b9bc-45621deed00e' \
      -H 'Host: localhost:8080' \
      -H 'Postman-Token: ffa9738e-799d-4fb0-99b7-4a14ed77f325,9dc62efd-d591-490f-b7f3-f3c96e55c1b8' \
      -H 'User-Agent: PostmanRuntime/7.16.3' \
      -H 'cache-control: no-cache' \
      -d '{
        "prefix": "Miss",
        "firstName": "Karen",
        "middleName": "Alejandra",
        "lastName": "Fa",
        "phone": "77777777777",
        "email": "ka@gmail.com"
    }'