Drop Database if exists guessNumber;
Create Database guessNumber;

Use guessNumber;

Create Table games (
gameId Int Primary Key Auto_Increment,
answer varChar(4),
isFinished Bool Not Null default 0
);

Create Table rounds (
roundId Int Primary Key auto_increment,
guess varChar(4) not Null,
result varChar(7),
gameId int null
);

Alter Table rounds
Add FOREIGN KEY fk_rounds_games(gameId) references rounds(roundId);

Insert into games (answer, isFinished)
	     values ('1234', 0);
         
Insert into rounds (guess, result, gameId) values 
('5678', 'e:0:p:0', 1);         


        