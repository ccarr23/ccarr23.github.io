use guessNumber;

Insert Into games(answer, isFinished) values ('',0);
Select games.gameId, answer, isFinished From games;
Select rounds.roundId, games.gameId, games.answer, games.isFinished 
from rounds
inner join games on rounds.gameId = rounds.roundId;
Select roundId, gameId, guess, result from rounds where roundId = 1;
Select gameId, answer, isFinished From games Where gameId = 0;