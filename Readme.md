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
 - Get list of all restaurants
```    curl -s http://localhost:8080/rest/restaurants/ --user user1@yandex.ru:password ```
Response
[{"id":100003,"name":"Ginza","address":"Sadovaya 12"},{"id":100004,"name":"Teremok","address":"Nevskij 10"}]

 - Get restaurant by id
``` curl -s http://localhost:8080/rest/restaurants/100003/ --user user1@yandex.ru:password```
Response
{"id":100003,"name":"Ginza","address":"Sadovaya 12"}

 - Get all restaurants with dishes for this day
``` curl -s http://localhost:8080/rest/restaurants/dish/by?date=2018-07-26 --user user1@yandex.ru:password```
Response
{"Restaurant{restaurantId=100004, name=Teremok, address=Nevskij 10}":[{"id":100008,"name":"Saltwort","price":260,"restaurant":{"id":100004,"name":"Teremok","address":"Nevskij 10"},"date":"2018-07-26"},{"id":100009,"name":"Cutlet","price":175,"restaurant":{"id":100004,"name":"Teremok","address":"Nevskij 10"},"date":"2018-07-26"},{"id":100010,"name":"Orange juice","price":40,"restaurant":{"id":100004,"name":"Teremok","address":"Nevskij 10"},"date":"2018-07-26"}],"Restaurant{restaurantId=100003, name=Ginza, address=Sadovaya 12}":[{"id":100005,"name":"Borsch","price":250,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-07-26"},{"id":100006,"name":"Cutlet","price":175,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-07-26"},{"id":100007,"name":"Stewed fruit","price":55,"restaurant":{"id":100003,"name":"Ginza","address":"Sadovaya 12"},"date":"2018-07-26"}]

## 1.2 Admin requests
 - Input a new restaurant
```curl -s -X POST -d '{"name":"NewRestaurantName","address":"AddressNewRestaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/ --user admin@gmail.com:admin```
Response:
{"id":100015,"name":"NewRestaurantName","address":"AddressNewRestaurant"}

 - Update a restaurant
```curl -s -X PUT -d '{"id":100015,"name":"UpdatedRestaurantName","address":"UpdatedAddressNewRestaurant"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/ --user admin@gmail.com:admin```

 - Delete a restaurant
```curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100015 --user admin@gmail.com:admin```