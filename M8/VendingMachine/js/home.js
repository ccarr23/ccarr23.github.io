var ds = new DataService();

var cashInput = $("#cashInput");
var cashBalance = 0.00;
const DOLLAR = 1.00;
const QUARTER = 0.25;
const DIME = 0.10;
const NICKLE = 0.05;
const PENNIES = 0.01;

function addToBalance(cash){
    cashBalance += cash;
    cashInput.val(cashBalance.toFixed(2));
}

function myErrorFunc (msg){
    return $("#message").val(msg.responseJSON.message);
}


function formatList(item) {
 return `<div class="col-9" id=ItemInfo>
 <ul style="list-style-type: none;">
    <a href="#" onclick="return false;" id='${item.id}' class='itemChoice'>#${item.id}</a>
    <li>${item.name}</li>
    <li>$${item.price}</li>
    <li>Qty: ${item.quantity}</li>
  </ul>
  </div>`
}

function returnChange(item) {
        $("#message").val("Thank You!");
        $("#changeInput").val((' Quarters: ' + item.quarters + '') +
        (' Dimes: ' + item.dimes + '') +
        (' Nickles: ' + item.nickels + '') +
        (' Pennies: ' + item.pennies + ''));
        cashBalance = 0.00;
        $("#cashInput").val("");
        ds.getItems(refreshInventory);       
           
}

function refreshInventory(items) {
  let itemList = $("#itemGrid");
  itemList.empty();
  for (let i = 0; i < items.length; i++){
  const item = items[i];
  $(itemList).append(formatList(item));
  $(`#${item.id}`).click(function () {
    $("#itemSelected").val(`${item.id}`);
});
  }
}

$(document).ready(function () {
    // alert('test'); 
    $("#cashInput").val("");
    $("#itemSelected").val("");
    $("#changeInput").val("");
    $("#message").val("");
    ds.getItems(refreshInventory);
    $("#confirmPurchase").click(function () {
        ds.vendItem($("#itemSelected").val(), cashBalance, returnChange, myErrorFunc);
    });
    $("#addDollar").click(function () {
        addToBalance(DOLLAR);
    });
    $("#addNickle").click(function () {
        addToBalance(NICKLE);
    });
    $("#addDime").click(function () {
        addToBalance(DIME);
    });
    $("#addQuarter").click(function () {
        addToBalance(QUARTER);
    });
    $("#returnChange").click(function () {
        var quarters = Math.floor(cashBalance / QUARTER);
        cashBalance %= QUARTER;
        var dimes = Math.floor(cashBalance / DIME);
        cashBalance %= DIME;
        var nickels = Math.floor(cashBalance / NICKLE);
        cashBalance %= NICKLE;
        var pennies = Math.floor(cashBalance);
        cashBalance %= PENNIES;
        $("#changeInput").val((' Quarters: ' + quarters + '') +
        (' Dimes: ' + dimes + '') +
        (' Nickles: ' + nickels + '') +
        (' Pennies: ' + pennies + ''));
        cashBalance = 0.00;
        $("#cashInput").val("");
        $("#itemSelected").val("");
        $("#message").val("");
    }); 
})