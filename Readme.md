# The voting system for deciding where to have lunch

Design and implement a REST API using Hibernate/Spring/SpringMVC/SpringSecurity without frontend.
The task is:
Build a voting system for deciding where to have lunch.
  - 2 types of users: admin and regular users 
  - Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
  - Menu changes each day (admins do the updates)
  - Users can vote on which restaurant they want to have lunch at
  - Only one vote counted per user
  - If user votes again the same day: 
  - If it is before 11:00 we asume that he changed his mind.
  - If it is after 11:00 then it is too late, vote can't be changed
  
Each restaurant provides new menu each day.
Project is used HSQLDB database, providing some test data. Settings: 
  - database.url=jdbc:hsqldb:file:D:/temp/restaurantsvoting 
  - database.username=sa 
  - database.password

# 1.Restaurant requests
## 1.1 User requests
 - Get list of all restaurants <br>
```    curl -s http://localhost:8080/rest/restaurants/ --user user1@yandex.ru:password ``` <br>
Response <br>
[{"id":100003,"name":"Ginza","address":"Sadovaya 12"},{"id":100004,"name":"Teremok","address":"Nevskij 10"}]

 - Get restaurant by id <br>
``` curl -s http://localhost:8080/rest/restaurants/100003/ --user user1@yandex.ru:password``` 
<br> Response <br>
{"id":100003,"name":"Ginza","address":"Sadovaya 12"}

 - Get all restaurants with dishes for this day <br>
``` curl -s http://localhost:8080/rest/restaurants/dish/by?date=2018-07-26 --user user1@yandex.ru:password```
<br> Response <br>
{"Restaurant{restaurantId=100004, name=Teremok, address=Nevskij 10}":[{"id":100008,"name":"Saltwort","price":260,"restaurant":{"id":100004,"name":"Teremok","address":"Nevskij 10"},"date":"2018-07-26"},{"id":100009,"name":"Cutlet","price":175,"restaurant":{"id":100004,"name":"Teremok","address":"Nevskij 10"},"date":"2018-07-26"},{"id":100010,"name":"Orange juice","price":40,"restaurant":{"id":100004,"name":"Teremok","address":"Nevskij 10"},"date":"2018-07-26"}],"Restaurant{restaurantId=100003, name=Ginza, address=Sadovaya 12}":[{"id":100005,"name":"Borsch","price":250,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-07-26"},{"id":100006,"name":"Cutlet","price":175,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-07-26"},{"id":100007,"name":"Stewed fruit","price":55,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-07-26"}]

## 1.2 Admin requests
 - Input a new restaurant <br>
```curl -s -X POST -d '{"name":"NewRestaurantName","address":"AddressNewRestaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/ --user admin@gmail.com:admin```
<br> Response <br>
{"id":100015,"name":"NewRestaurantName","address":"AddressNewRestaurant"}

 - Update a restaurant <br>
```curl -s -X PUT -d '{"id":100015,"name":"UpdatedRestaurantName","address":"UpdatedAddressNewRestaurant"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/ --user admin@gmail.com:admin```

 - Delete a restaurant <br>
```curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100015 --user admin@gmail.com:admin```

# 2.Dish requests
## 2.1 User requests
 - Get dish by id <br>
```curl -s http://localhost:8080/rest/dish/100006 --user user1@yandex.ru:password```
<br> Response <br>
{"id":100006,"name":"Cutlet","price":175,"restaurant":null,"date":"2018-07-26"}

## 2.2 Admin requests
 - Create a dish <br>
```curl -s -X POST -d '{"name": "New dish","price": 52150,"date": [2018, 9, 22]}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/rest/admin/dish/restaurant/100003/ --user admin@gmail.com:admin```
<br> Response <br>
{"id":100016,"name":"New dish","price":52150,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-09-22"}

 - Update a dish <br>
```curl -s -X PUT -d '{"id": 100016,"name": "Update dish","price": 62150,"date": [2018, 9, 22]}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/dish/ --user admin@gmail.com:admin```
 - Delete a dish <br>
```curl -s -X DELETE http://localhost:8080/rest/admin/dish/100016 --user admin@gmail.com:admin```

# 3.Vote request
## 3.1 User requests
 - Vote for restaurant at chosen day <br>
```curl -s -X POST http://localhost:8080/rest/votes/restaurant/100003/date/2018-09-21 --user user1@yandex.ru:password```
 - Get votes of user <br>
```curl -s http://localhost:8080/rest/votes/user/ --user user1@yandex.ru:password```
<br> Response <br>
[{"id":100011,"user":null,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-07-26"},{"id":100014,"user":null,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-08-30"},{"id":100018,"user":null,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-09-21"}]
 - Get vote at chosen day <br>
```curl -s http://localhost:8080/rest/votes/2018-09-21 --user user1@yandex.ru:password```
<br> Response <br>
{"id":100017,"user":null,"restaurant":null,"date":"2018-09-21"}

 - Delete vote at chosen day <br>
```curl -s -X DELETE http://localhost:8080/rest/votes/2018-09-21 --user user1@yandex.ru:password```


## 3.2 Admin request
 - Get votes amount for restaurants at chosen day  <br>
```curl -s http://localhost:8080/rest/admin/votes/2018-07-26 --user admin@gmail.com:admin```
{"Restaurant{restaurantId=100004, name=Teremok, address=Nevskij 10}":2,"Restaurant{restaurantId=100003, name=Ginza, address=Sadovaya 12}":1}

# 4. User request
## 4.1 User requests
 - Get profile <br>
```curl -s http://localhost:8080/rest/user/ --user user1@yandex.ru:password```
<br> Response <br>
{"id":100000,"name":"User1","email":"user1@yandex.ru","password":"password","registered":"2018-08-29","isAdmin":false}
 - Update profile <br>
```curl -s -X PUT -d '{"id":100000,"name":"newUser","email":"new@yandex.ru","password":"newpass"}' -H 'Content-Type: application/json' http://localhost:8080/rest/user/ --user user1@yandex.ru:password```

 - Delete user <br>
```curl -s -X DELETE http://localhost:8080/rest/user/ --user user1@yandex.ru:password```

## 4.2 Admin requests
 - Create user <br>
```curl -s -X POST -d '{"name": "Created User","email": "createdUser@yandex.ru", "password":"createdUserPassword"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/ --user admin@gmail.com:admin```
<br> Response <br>
{"id":100018,"name":"Created User","email":"createdUser@yandex.ru","password":"createdUserPassword","registered":"2018-09-23","isAdmin":false}

 - Get user by id   <br>
```curl -s http://localhost:8080/rest/admin/users/100018 --user admin@gmail.com:admin```
<br> Response <br>
{"id":100018,"name":"UpdatedUser","email":"update@yandex.ru","password":"updatedpass","registered":"2018-09-23","isAdmin":false}

 - Request to delete user by id <br>
```curl -s -X DELETE http://localhost:8080/rest/admin/users/100018 --user admin@gmail.com:admin```

 - Request to get all users <br>
```curl -s http://localhost:8080/rest/admin/users/ --user admin@gmail.com:admin```
<br> Response <br>
[{"id":100001,"name":"User2","email":"user2@yandex.ru","password":"password","registered":"2018-09-23","isAdmin":false},{"id":100002,"name":"Admin","email":"admin@gmail.com","password":"admin","registered":"2018-09-23","isAdmin":true}]