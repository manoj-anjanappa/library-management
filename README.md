# library-management

=====

build application using ./mvnw spring-boot:build-image 

API URLS
=====

TAGS
GET    <serveraddress>/tags
POST   <serveraddress>/tags/upload --body form {file: <multipart>}
POST   <serveraddress>/tags/add -body  [    {        "tageName" : "bedTime"    }]
DELETE <serveraddress>/tag/<tagName>
DELETE <serveraddress>/tag -body ["Horror", "Historical"]

USER
POST   <serveraddress>/user/add -body  {    "firstName":"Man",    "lastName" : "Anjan",    "email" : "man*****pa@gmail.com",    "phoneNumber" : "5468131565"}

BOOKS
POST   <serveraddress>/books/upload --body form {file: <multipart>}
GET    <serveraddress>/books
GET    <serveraddress>/books/name?bookName=<name of book>
GET    <serveraddress>/books/isbn?isbn=<ISBN>
GET    <serveraddress>/books/search?key=<wildcard search on book title>
GET    <serveraddress>/books/id?bookId=<book id in long>

Library
POST   <serveraddress>/library/issue -- body [    {       "bookSerialId": "1",        "userId":"1",        "fromDate":"2021-04-16 13:12:00",        "toDate":"2021-05-16 13:12:00",        "bookId":"1"    }]
PUT    <serveraddress>/library/update --body {    "leadgerId": 1,    "bookSerialId": 1,    "bookId": 1,    "userId": 1,    "fromDate": "2021-04-16 13:12:00",    "toDate": "2021-05-16 13:12:00",    "returnDate": "2021-05-16 13:12:00",    "createTimestamp": "2021-05-16T09:58:29.746+00:00"}
GET    <serveraddress>/library/user?userId=<userid>

