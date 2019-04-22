function diceOne(numSides) {

  return math.floor(Math.random() * numSides) + 1

}

function diceTwo(numSides) {

  return math.floor(Math.random() * numSides) + 1

}

function gameResults() {

console.log(diceOne(6), diceTwo(6));

  diceOne(6);
  diceTwo(6);


  startingBet = Number(gameMoney);

  for (let i = startingBet; i <= startingBet; i++) {

  if (diceOne(6) + diceTwo(6) === 7) {

    Number(gameMoney) + 1;

    //console.log(Number(gameMoney));

  } else if (diceOne(6) + diceTwo(6) > 7 || diceOne(6) + diceTwo(6) < 7) {

    number(gameMoney) - 1;

    //console.log(Number(gameMoney));
  }
  }
  return false;
}
