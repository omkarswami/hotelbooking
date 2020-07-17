# Hotel Booking
Assignment submission for M3BI interview process.

I have used Spring Boot framework for solutioning given problem and as a database I have used H2.

The database contains 3 tables 
 1. User - Saves User Details
 2. HotelRoom - Saves Hotel Room Details
 3. Booking - Saves Booking transaction details
  
Initial data in table looks like this
##### User Table

<img alt="User" src="https://raw.githubusercontent.com/omkarswami/hotelbooking/master/screenshots/User.png">

##### HotelRoom Table
<img alt="HotelRoom" src="https://raw.githubusercontent.com/omkarswami/hotelbooking/master/screenshots/Hotelroom.png">



## Requst details for booking API is as follows.
### EndPoint: Type : POST
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
## Update Bonus points API
#### Update bonus points and books pendig booking for user if points are enough for booking
### End point : Type : POST
```
http://localhost:8090/updatePoints
```
### Request Structure
```
{
	"userId":1, //User Id for which bonus points are alloted
	"points":100 // additional bonus points alloted
}
```
### Response Structure
```
{
    "errorCode": 0,//Error code integer
    "message": "USER BONUS POINTS UPDATED SUCCESSFULLY. UPDATED POINTS : 200" // Message string 
}
```
### Error codes
```
   0-> Success // Incase of booking update message will contain booked ids and still pending ids
   1-> Invalid user
   2-> User points alloted but exception in booking peinding bookings
```

### Booking Table after booking

<img alt="Booking After" src="https://raw.githubusercontent.com/omkarswami/hotelbooking/master/screenshots/Booking_after.png">

