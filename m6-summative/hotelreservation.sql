DROP DATABASE IF EXISTS HotelReservation;
CREATE DATABASE HotelReservation;

USE HotelReservation;

CREATE TABLE Roomtype (
RoomtypeID INT PRIMARY KEY,
`RoomName` VarChar(50) NOT NULL
);

CREATE TABLE Room (
RoomID INT PRIMARY KEY,
OccupationLimit INT, 
RoomTypeID INT NOT NULL,
FOREIGN KEY fk_Room_Roomtype(RoomtypeID)
references Roomtype(RoomtypeID)
);

CREATE TABLE Customer (
CustomerID INT PRIMARY KEY,
FirstName VarChar(50),
LastName VarChar(50),
Email VarChar(50),
PhoneNumber VarChar(10)
);



CREATE TABLE Reservation (
ResID INT PRIMARY KEY,
CheckInDate DATE,
CheckOutDate DATE,
CustomerID INT NOT NULL,
FOREIGN KEY fk_Reservation_Customer(CustomerID)
references Customer(CustomerID) ON DELETE CASCADE
);

CREATE TABLE Roomrate (
RateID INT PRIMARY KEY,
ForDate DATE NOT NULL,
ToDate DATE NULL, 
Price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE RoomReservation (
RoomResID INT PRIMARY KEY,
RoomID INT NOT NULL,
FOREIGN KEY fk_RoomReservation_Room(RoomID)
references Room(RoomID) ON DELETE CASCADE,
ResID INT NOT NULL,
FOREIGN KEY fk_RoomReservation_Reservation(ResID)
references Reservation(ResID) ON DELETE CASCADE,
RateID INT NOT NULL,
FOREIGN KEY fk_RoomReservation_RoomRate(RateID)
references RoomRate(RateID) ON DELETE CASCADE
);

CREATE TABLE Guests (
GuestID INT PRIMARY KEY,
ResID INT NOT NULL,
FOREIGN KEY fk_Guests_Reservation(ResID)
references Reservation(ResID) ON DELETE CASCADE,
FirstName VarChar(50),
LastName VarChar(50),
Age TinyInt
);


CREATE TABLE AddOns (
AddOnID INT PRIMARY KEY,
AddOnName VarChar(50) NOT NULL
);

CREATE TABLE AddOnPrice (
PriceID INT PRIMARY KEY,
AddOnID INT NOT NULL,
FOREIGN KEY fk_AddOnPrice_AddOns(AddOnID)
references AddOns(AddOnID),
Price DECIMAL NOT NULL,
ForDate DATE NOT NULL,
ToDate DATE NULL
);

CREATE TABLE ResAddOn (
ResAddOnID INT PRIMARY KEY,
RoomResID INT NULL,
FOREIGN KEY fk_ResAddOn_RoomReservation(RoomResID)
references RoomReservation(RoomResID) ON DELETE SET NULL,
PriceID INT NOT NULL,
FOREIGN KEY fk_ResAddOn_AddOnPrice(PriceID)
references AddOnPrice(PriceID)
);

CREATE TABLE Amenities (
AmenitiesID INT PRIMARY KEY,
`AmenitiesName` VarChar(50) NOT NULL
);

CREATE TABLE RoomAmenities (
RoomID INT NOT NULL,
FOREIGN KEY fk_RoomAmenities_Room(RoomID)
references Room(RoomID),
AmenitiesID INT NOT NULL,
FOREIGN KEY fk_RoomAmenities_Amenities(AmenitiesID)
references Amenities(AmenitiesID)
);

CREATE TABLE Promotype (
PTID INT PRIMARY KEY,
`TypeName` VarChar(50)
);

CREATE TABLE Promo (
PromoID INT PRIMARY KEY,
PTID INT NOT NULL,
FOREIGN KEY fk_Promo_Promotype(PTID)
references Promotype(PTID),
`PromoName` VarChar(50),
VALUE DECIMAL
);

CREATE TABLE Bill (
BillID INT PRIMARY KEY,
ResID INT NOT NULL,
FOREIGN KEY fk_Bill_Reservation(ResID)
references Reservation(ResID) ON DELETE CASCADE,
RateID INT NOT NULL,
FOREIGN KEY fk_Bill_Roomrate(RateID)
references Roomrate(RateID),
PromoID INT NULL,
FOREIGN KEY fk_Bill_Promo(PromoID)
references Promo(PromoID),
Tax DECIMAL NULL, 
Total DECIMAL NULL
);



