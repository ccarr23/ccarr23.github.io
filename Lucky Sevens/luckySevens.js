function rollDice() {
  var rollOne = Math.floor(Math.random() * 6) + 1;
  var rollTwo = Math.floor(Math.random() * 6) + 1;
  return rollOne + rollTwo;

}

function playGame() {
  console.log("inside function gameResults()");


  let diceRoll = 0;

  let currentAmount = document.forms["luckySevens"]["startingBet"].value;

  currentAmount = Number(currentAmount);
  let highestAmount = currentAmount;

  // if (currentAmount == "" || isNaN(currentAmount)) {
  //
  //   alert("Must enter an amount.");
  //   document.getElementById("startingBet").value = null;
  //   document.getElementById("startingBet").focus();
  //
  //   return;
  //
  // } else {
  //
  //  document.getElementById("resetButton").style.display = "in-line block";
  //  document.getElementById("submitButton").style.display = "none";
  //  document.getElementById("results").style.display = "block";


  diceRoll = (Number(diceRoll));
  let totalRolls = diceRoll;

 while (currentAmount > 0) {

   diceRoll++;

   //rollDice();
  if (rollDice() == 7) {

     currentAmount += 4;

   if (currentAmount > highestAmount) {

       highestAmount += 4;
       totalRolls = diceRoll;

     }

   } else {

<<<<<<< HEAD
  startingBet = Number(gameMoney);
=======
     currentAmount -= 1;


   }
>>>>>>> origin

   console.log("current amount is:", currentAmount);
   console.log("highest amount is:", highestAmount);
   console.log("highest roll count at highest win:", totalRolls);
   console.log("current roll #:", diceRoll);


 }
 document.getElementById("currentAmount").innerText = currentAmount;
 document.getElementById("diceRoll").innerText = diceRoll;
 document.getElementById("highestAmount").innerText = highestAmount;
 document.getElementById("totalRolls").innerText = totalRolls;



return false;

}
