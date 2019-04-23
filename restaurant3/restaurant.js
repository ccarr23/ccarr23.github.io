function validateForm() {

let name = document.getElementById("name").value;
let email = document.getElementById("email").value;
let phone = document.getElementById("phone").value;

if (name == "" || email == "") {

 alert("Please enter your name.")
 document.getElementById("name").focus();

 alert("Please enter a valid Email address.")
 document.getElementById("email").focus();

 return false;

}

// if (email == "") {
//
//   alert("Please enter a valid Email address.")
//   document.getElementById("email").focus();
//   return false;
//
//
//  }
alert("Thank you for your business!");

return true;
}
