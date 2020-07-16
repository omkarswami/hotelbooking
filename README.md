# Hotel Booking
Assignment submission for M3BI interview process.

I have used Spring Boot framework for solutioning given problem and as a database I have used H2.

The database contains 3 tables 
  User - Saves User Details
  HotelRoom - Saves Hotel Room Details
  Booking - Saves Booking transaction details
  
Initial data in table looks like this
##### User Table

<img alt="User" src="https://raw.githubusercontent.com/omkarswami/hotelbooking/master/screenshots/User.png">

##### HotelRoom Table
<img alt="HotelRoom" src="https://raw.githubusercontent.com/omkarswami/hotelbooking/master/screenshots/Hotelroom.png">



## Requst details for booking API is as follows.
### EndPoint:
```
 http://localhost:8090/bookHotel
```
### Request Structure : 
```
  {
  "userId":5, //User ID for whoom hotelRoom is to be booked
  "roomId":400 //Hotel Room Id to be booked
  }
```
### Response Structure :
```
{
    "errorCode": 1, //Error code
    "message": "Invalid User" // Message
}
```
### Error Codes :
```
              0-> Success
              1 -> Invalid User
              2 -> Invalid Room
              3 -> Booking failure
```          
#### Message will contain booking id as well as Booking Status in case of success. 
```
   {
    "errorCode": 0,
    "message": "1 : BOOKED"
} 
```
### Booking Table after booking

<img alt="Booking After" src="https://raw.githubusercontent.com/omkarswami/hotelbooking/master/screenshots/Booking_after.png">

