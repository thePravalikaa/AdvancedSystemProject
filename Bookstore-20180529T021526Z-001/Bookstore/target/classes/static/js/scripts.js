/**
 * 
 */

//$(document).ready(function(){
//   $('.button').click(function(){
//        if ($('#AccountText').val() == ""){
//        	bootbox.confirm({
//    			message: "Do you wish to proceed?",
//    			buttons: {
//    				cancel: {
//    					label:'<i class="fa fa-times"></i> Cancel'
//    				},
//    				confirm: {
//    					label:'<i class="fa fa-check"></i> Confirm'
//    				}
//    			}
//    			
//        }
//    });
//});



function checkValidEmail() {
	var email = $("#email").val();
	var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (regex.test(email)) {
		$("#createAcct").prop("disabled", false);
		$("#newUserEmail").html("");
    }
    else if(email!=""){
    	$("#createAcct").prop("disabled", true);
    	$("#newUserEmail").html("Please enter a valid Email address!");
    }
}

function checkFirstNameVal() {
	var firstName = $("#firstName").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(firstName)) {
		$("#updateUserInfoButton").prop("disabled", false);
		$("#fstName").html("");
    }
	 else if(firstName!=""){
    	$("#updateUserInfoButton").prop("disabled", true);
    	$("#fstName").html("Please enter a valid First Name!");
    }
}

function checkLastNameVal() {
	var lastName = $("#lastName").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(lastName)) {
		$("#updateUserInfoButton").prop("disabled", false);
		$("#lstName").html("");
    }
	 else if(lastName!=""){
    	$("#updateUserInfoButton").prop("disabled", true);
    	$("#lstName").html("Please enter a valid Last Name!");
    }
}

function checkCreditCardName() {
	var cardName = $("#cardName").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(cardName)) {
		$("#creditCardInfo").prop("disabled", false);
		$("#creditCardName").html("");
    }
	 else if(cardName!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#creditCardName").html("Please enter a valid Card Name!");
    }
}

function checkBillingName() {
	var billingName = $("#billingName").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(billingName)) {
		$("#creditCardInfo").prop("disabled", false);
		$("#receiverName").html("");
    }
	 else if(billingName!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#receiverName").html("Please enter a valid Name!");
    }
}

function checkBillingCity() {
	var billingCity = $("#billingCity").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(billingCity)) {
		$("#creditCardInfo").prop("disabled", false);
		$("#billingCityName").html("");
    }
	 else if(billingCity!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#billingCityName").html("Please enter a valid city name!");
    }
}


function checkCardHolderName() {
	var cardHolder = $("#cardHolder").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(cardHolder)) {
		$("#creditCardInfo").prop("disabled", false);
		$("#cardHolderName").html("");
    }
	 else if(cardHolder!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#cardHolderName").html("Please enter a valid name!");
    }
}

function checkShippingName() {
	var shippingName = $("#shippingName").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(shippingName)) {
		$("#shippingInfo").prop("disabled", false);
		$("#shpngName").html("");
    }
	 else if(shippingName!=""){
    	$("#shippingInfo").prop("disabled", true);
    	$("#shpngName").html("Please enter a valid name!");
    }
}

function checkShippingCity() {
	var shippingCity = $("#shippingCity").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(shippingCity)) {
		$("#shippingInfo").prop("disabled", false);
		$("#shpngCity").html("");
    }
	 else if(shippingCity!=""){
    	$("#shippingInfo").prop("disabled", true);
    	$("#shpngCity").html("Please enter a valid City!");
    }
}

function checkShippingZipCode() {
	var shippingZipcode = $("#shippingZipcode").val();
	var regex = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
	if (regex.test(shippingZipcode)) {
		$("#shippingInfo").prop("disabled", false);
		$("#shpngZipCode").html("");
    }
	 else if(shippingZipcode!=""){
    	$("#shippingInfo").prop("disabled", true);
    	$("#shpngZipCode").html("Please enter a valid zip code!");
    }
}


function checkBillingZipCode() {
	var billingZip = $("#billingZipcode").val();
	var regex = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
	if (regex.test(billingZip)) {
		$("#creditCardInfo").prop("disabled", false);
		$("#billingZip").html("");
    }
	 else if(billingZip!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#billingZip").html("Please enfter a valid zip code!");
    }
}

function checkCardNum() {
	var cardType = $("#cardType").val();
	var cardNumber = $("#cardNumber").val();
	var amex = /(^\d{15}$)/;
	var others = /(^\d{16}$)/;
	if(cardType == 'amex'){
	if (amex.test(cardNumber)) {
		$("#creditCardInfo").prop("disabled", false);
		$("#cardNum").html("");
    }
	 else if(cardType!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#cardNum").html("Please enter a valid credit card no!");
    }
	}
	else if(cardType == 'visa' || cardType == 'mastercard' || cardType == 'discover'){
		if (others.test(cardNumber)) {
			$("#creditCardInfo").prop("disabled", false);
			$("#cardNum").html("");
	    }
		 else if(cardNum!=""){
	    	$("#creditCardInfo").prop("disabled", true);
	    	$("#cardNum").html("Please enter a valid credit card no!");
	    }
	}
	else if(cardNum!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#cardNum").html("Please enter a valid credit card no!");
    }
    
}

function checkCardCVV() {
	var cardType = $("#cardType").val();
	var cardCVC = $("#cardCVC").val();
	var amex = /(^\d{4}$)/;
	var others = /(^\d{3}$)/;
	if(cardType == 'amex'){
	if (amex.test(cardCVC)) {
		$("#creditCardInfo").prop("disabled", false);
		$("#cardNum").html("");
    }
	else if(cardNum!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#cardNum").html("Please enter a valid code!");
    }
	}
	else if(cardType == 'visa' || cardType == 'mastercard' || cardType == 'discover'){
		if (others.test(cardCVC)) {
			$("#creditCardInfo").prop("disabled", false);
			$("#cardNum").html("");
	    }
		else if(cardNum!=""){
	    	$("#creditCardInfo").prop("disabled", true);
	    	$("#cardNum").html("Please enter a valid code!");
	    }
	}
	else if(cardNum!=""){
    	$("#creditCardInfo").prop("disabled", true);
    	$("#cardNum").html("Please enter a valid code!");
    }
    
}


function checkCurrentPswd() {
	var currPassword = $("#currentPassword").val();
	if (currPassword.length==18) {
		$("#updateUserInfoButton").prop("disabled", false);
		$("#currPassword").html("");
    }
    else if(currPassword!=""){
    	$("#updateUserInfoButton").prop("disabled", true);
    	$("#currPassword").html("Incorrect password!");
    }
}


function checkBillingAddress() {
	if($("#theSameAsShippingAddress").is(":checked")) {
		$(".billingAddress").prop("disabled", true);
	} else {
		$(".billingAddress").prop("disabled", false);
	}
}

function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();
	
	if(password == "" && confirmPassword =="") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else if(confirmPassword!=""){
		if(password != confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
}
setTimeout(function() {
	  $("#updateId").fadeOut().empty();
	}, 5000);
function myFunction()
{
    $("#popupBox").show();


}

function confirmStocks(){
	
	var errorMsg=document.getElementById('cartid').value;

	
	    if (errorMsg!=null&&errorMsg!=''){
	    	 if(confirm(errorMsg)){
	             return true;
	        } else {
	             return false;
	        }
	    }else{
	    	  return true;
	    }
}
	

$(document).ready(function(){
	$("#popupBox").show();

    $("#deleteBtn").click(function()
    {
        // handle button
    });

    $("#deactivateBtn").click(function()
    {
        // handle button
    });
	
	$(".cartItemQty").on('change', function(){
		var id=this.id;
		
		$('#update-item-'+id).css('display', 'inline-block');
	});
	$("#theSameAsShippingAddress").on('click', checkBillingAddress);
	// $("#createAcct").on('click', checkValidEmail);
	$("#txtConfirmPassword").keyup(checkPasswordMatch);
	$("#txtNewPassword").keyup(checkPasswordMatch);
	$("#email").on('change',checkValidEmail);
	$("#currentPassword").on('change',checkCurrentPswd);
	$("#firstName").on('change',checkFirstNameVal);
	$("#lastName").on('change',checkLastNameVal);
	$("#cardName").on('change',checkCreditCardName);
	$("#billingName").on('change',checkBillingName);
	$("#billingCity").on('change',checkBillingCity);
	$("#billingZipcode").on('change',checkBillingZipCode);
	$("#cardHolder").on('change',checkCardHolderName);
	$("#cardNumber").on('change',checkCardNum);
	$("#cardCVC").on('change',checkCardCVV);
	$("#shippingName").on('change',checkShippingName);
	$("#shippingCity").on('change',checkShippingCity);
	$("#shippingZipcode").on('change',checkShippingZipCode);
});