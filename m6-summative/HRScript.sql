USE hotelreservation;

INSERT INTO `hotelreservation`.`roomtype` (`RoomtypeID`, `RoomName`) VALUES ('001', 'Twin');
INSERT INTO `hotelreservation`.`roomtype` (`RoomtypeID`, `RoomName`) VALUES ('002', 'Double Twin');
INSERT INTO `hotelreservation`.`roomtype` (`RoomtypeID`, `RoomName`) VALUES ('003', 'Full');
INSERT INTO `hotelreservation`.`roomtype` (`RoomtypeID`, `RoomName`) VALUES ('004', 'Queen');
INSERT INTO `hotelreservation`.`roomtype` (`RoomtypeID`, `RoomName`) VALUES ('005', 'King');

INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('100', '1', '001');
INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('101', '2', '002');
INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('102', '2', '003');
INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('103', '3', '004');
INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('104', '3', '005');
INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('105', '1', '001');
INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('106', '2', '002');
INSERT INTO `hotelreservation`.`room` (`RoomID`, `OccupationLimit`, `RoomTypeID`) VALUES ('107', '2', '003');

INSERT INTO `hotelreservation`.`addons` (`AddOnID`, `AddOnName`) VALUES ('01', 'Dinner'),
('02', 'Breakfast'),
('03', 'Movies')
;

INSERT INTO `hotelreservation`.`addonprice` (`PriceID`, `AddOnID`, `Price`, `ForDate`, `ToDate`) VALUES ('001', '1', '50.00', '2019/08/19', '2019/08/25'), 
('002', '2', '85.00', '2019/08/19', '2019/08/25'), 
('003', '3', '45.00', '2019/08/19', '2019/08/25');

INSERT INTO `hotelreservation`.`amenities` (`AmenitiesID`, `AmenitiesName`) VALUES ('01', 'Mini Bar'), 
('02', 'Wi-Fi'), 
('03', 'Smart Companion'), 
('04', 'Streamed Towels');

INSERT INTO RoomAmenities (RoomID, AmenitiesID) VALUES ('100', '01'),
('101', '01'),
('101', '02'),
('102', '01'),
('102', '03'),
('103', '01'),
('103', '02'),
('103', '04'),
('104', '01'),
('104', '02'),
('104', '03'),
('105', '01'),
('105', '02'),
('105', '03'),
('105', '04');

INSERT INTO `hotelreservation`.`roomrate` (`RateID`, `ForDate`, `ToDate`, `Price`) VALUES ('0001', '2019/08/19', '2019/08/25', '200.00');
INSERT INTO `hotelreservation`.`roomrate` (`RateID`, `ForDate`, `ToDate`, `Price`) VALUES ('0002', '2019/08/19', '2019/08/25', '289.00');
INSERT INTO `hotelreservation`.`roomrate` (`RateID`, `ForDate`, `ToDate`, `Price`) VALUES ('0003', '2019/08/19', '2019/08/25', '350.00');
INSERT INTO `hotelreservation`.`roomrate` (`RateID`, `ForDate`, `ToDate`, `Price`) VALUES ('0004', '2019/08/19', '2019/08/25', '500.00');

INSERT INTO `hotelreservation`.`customer` (`CustomerID`, `FirstName`, `LastName`, `Email`, `PhoneNumber`) VALUES ('01', 'Corey', 'Carr', 'magitheagi@gmail.com', '2342342333');
INSERT INTO `hotelreservation`.`customer` (`CustomerID`, `FirstName`, `LastName`, `Email`, `PhoneNumber`) VALUES ('02', 'Randall', 'Vandal', 'RandalTheVandal@hotmail.com', '1231231234');
INSERT INTO `hotelreservation`.`customer` (`CustomerID`, `FirstName`, `LastName`, `Email`, `PhoneNumber`) VALUES ('03', 'Kamina', 'Great', 'gurrenlagannfan@gmail.com', '3213214321');
INSERT INTO `hotelreservation`.`customer` (`CustomerID`, `FirstName`, `LastName`, `Email`, `PhoneNumber`) VALUES ('04', 'Kid', 'Buu', 'Ahhhhhhhhhhhhh@hotmail.com', '5555555555');

INSERT INTO `hotelreservation`.`promotype` (`PTID`, `TypeName`) VALUES ('01', '% Off');
INSERT INTO `hotelreservation`.`promotype` (`PTID`, `TypeName`) VALUES ('02', '$ Off');

INSERT INTO `hotelreservation`.`promo` (`PromoID`, `PTID`, `PromoName`, `VALUE`) VALUES ('01', '01', '25% Off!', '0.25');
INSERT INTO `hotelreservation`.`promo` (`PromoID`, `PTID`, `PromoName`, `VALUE`) VALUES ('02', '01', '$100 Off!', '100.00');

INSERT INTO `hotelreservation`.`reservation` (`ResID`, `CheckInDate`, `CheckOutDate`, `CustomerID`) VALUES ('012', '2019/08/19', '2019/08/22', '1');
INSERT INTO `hotelreservation`.`reservation` (`ResID`, `CheckInDate`, `CheckOutDate`, `CustomerID`) VALUES ('013', '2019/08/19', '2019/08/23', '2');
INSERT INTO `hotelreservation`.`reservation` (`ResID`, `CheckInDate`, `CheckOutDate`, `CustomerID`) VALUES ('014', '2019/08/21', '2019/08/24', '3');
INSERT INTO `hotelreservation`.`reservation` (`ResID`, `CheckInDate`, `CheckOutDate`, `CustomerID`) VALUES ('015', '2019/08/20', '2019/08/25', '4');

INSERT INTO `hotelreservation`.`roomreservation` (`RoomResID`, `RoomID`, `ResID`, `RateID`) VALUES ('025', '105', '12', '3');
INSERT INTO `hotelreservation`.`roomreservation` (`RoomResID`, `RoomID`, `ResID`, `RateID`) VALUES ('026', '101', '13', '1');
INSERT INTO `hotelreservation`.`roomreservation` (`RoomResID`, `RoomID`, `ResID`, `RateID`) VALUES ('027', '104', '14', '4');
INSERT INTO `hotelreservation`.`roomreservation` (`RoomResID`, `RoomID`, `ResID`, `RateID`) VALUES ('028', '102', '14', '2');
INSERT INTO `hotelreservation`.`roomreservation` (`RoomResID`, `RoomID`, `ResID`, `RateID`) VALUES ('029', '103', '15', '3');
INSERT INTO `hotelreservation`.`roomreservation` (`RoomResID`, `RoomID`, `ResID`, `RateID`) VALUES ('030', '100', '15', '1');

INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('030', '026', '2');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('031', '025', '1');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('032', '025', '2');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('033', '029', '1');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('034', '029', '2');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('035', '029', '3');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('036', '029', '1');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('037', '029', '2');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('038', '027', '1');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('039', '027', '2');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('040', '027', '3');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('041', '030', '3');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('042', '025', '1');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('043', '025', '2');
INSERT INTO `hotelreservation`.`resaddon` (`ResAddOnID`, `RoomResID`, `PriceID`) VALUES ('044', '025', '3');

INSERT INTO `hotelreservation`.`guests` (`GuestID`, `ResID`, `FirstName`, `LastName`, `Age`) VALUES ('050', '12', 'Corey', 'Carr', '27');
INSERT INTO `hotelreservation`.`guests` (`GuestID`, `ResID`, `FirstName`, `LastName`, `Age`) VALUES ('051', '13', 'Randall', 'Vandall', '33');
INSERT INTO `hotelreservation`.`guests` (`GuestID`, `ResID`, `FirstName`, `LastName`, `Age`) VALUES ('052', '14', 'Kamina', 'Great', '30');
INSERT INTO `hotelreservation`.`guests` (`GuestID`, `ResID`, `FirstName`, `LastName`, `Age`) VALUES ('053', '14', 'Simone', 'Diggs', '25');
INSERT INTO `hotelreservation`.`guests` (`GuestID`, `ResID`, `FirstName`, `LastName`, `Age`) VALUES ('054', '15', 'Fredrick', 'Michs', '23');
INSERT INTO `hotelreservation`.`guests` (`GuestID`, `ResID`, `FirstName`, `LastName`, `Age`) VALUES ('055', '15', 'Kid', 'Buu', '99');

INSERT INTO `hotelreservation`.`bill` (`BillID`, `ResID`, `RateID`, `PromoID`, `Tax`, `Total`) VALUES ('0121', '12', '3', '1', '33.00', '1061.50');
INSERT INTO `hotelreservation`.`bill` (`BillID`, `ResID`, `RateID`, `Tax`, `Total`) VALUES ('0122', '13', '1', '22.00', '666.00');
INSERT INTO `hotelreservation`.`bill` (`BillID`, `ResID`, `RateID`, `Tax`, `Total`) VALUES ('0123', '14', '4', '50.00', '1500.00');
INSERT INTO `hotelreservation`.`bill` (`BillID`, `ResID`, `RateID`, `PromoID`, `Tax`, `Total`) VALUES ('0124', '14', '2', '2', '35.00', '224.00');
INSERT INTO `hotelreservation`.`bill` (`BillID`, `ResID`, `RateID`, `PromoID`, `Tax`, `Total`) VALUES ('0125', '15', '3', '1', '45.00', '1007.50');
INSERT INTO `hotelreservation`.`bill` (`BillID`, `ResID`, `RateID`, `Tax`, `Total`) VALUES ('0126', '15', '1', '20.00', '620.00');

SELECT Room.RoomID, roomtype.`roomname`
FROM Room
JOIN roomtype ON room.RoomtypeID = roomtype.RoomtypeID;

SELECT roomamenities.RoomID, roomamenities.AmenitiesID, Amenities.`amenitiesName`
From Amenities
INNER JOIN roomamenities ON amenities.AmenitiesID = roomAmenities.AmenitiesID;

SELECT roomreservation.RoomResID, roomreservation.RoomID, FirstName, LastName, Email, PhoneNumber, reservation.CheckInDate, reservation.CheckOutDate
FROM customer
JOIN reservation ON customer.CustomerID = reservation.CustomerID
LEFT OUTER JOIN roomreservation ON roomreservation.ResID = reservation.ResID;



UPDATE customer
SET Email = 'BlowUpDaPlanet45@Gmail.com'
WHERE CustomerID = 4;


DELETE FROM roomreservation
WHERE RoomResID = 30;




