
function checkTitleValidation() {
	var booktitle = $("#title").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(booktitle)) {
		$("#addBookSuccess").prop("disabled", false);
		$("#bookTitle").html("");
    }
    else if(bookTitle!=""){
    	$("#addBookSuccess").prop("disabled", true);
    	$("#bookTitle").html("Please enter a valid title!");
    }
}

function checkNameValidation() {
	var author = $("#author").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(author)) {
		$("#addBookSuccess").prop("disabled", false);
		$("#authorName").html("");
    }
    else if(authorName!=""){
    	$("#addBookSuccess").prop("disabled", true);
    	$("#authorName").html("Please enter a valid name!");
    }
}

function checkPublisherValidation() {
	var publisher = $("#publisher").val();
	var regex = /^[a-zA-Z ]{2,100}$/;
	if (regex.test(publisher)) {
		$("#addBookSuccess").prop("disabled", false);
		$("#publisherName").html("");
    }
    else if(publisherName!=""){
    	$("#addBookSuccess").prop("disabled", true);
    	$("#publisherName").html("Please enter a valid name!");
    }
}

function checkPageValidation() {
	var pageno = $("#pageNumber").val();
	if (pageno == 0 || pageno < 0) {
		$("#addBookSuccess").prop("disabled", true);
    	$("#pageNum").html("Please enter no of pages!");
		
    }
    else if(pageNum!=""){
    	$("#addBookSuccess").prop("disabled", false);
		$("#pageNum").html("");
    }
}

function checkIsbnValidation() {
	var isbnno = $("#isbn").val();
	if (isbnno == 0 || isbnno < 0 || isbnno.length<10) {
		$("#addBookSuccess").prop("disabled", true);
    	$("#isbnNo").html("Please enter a valid 10 digit number!");
		
    }
    else if(isbnNo!=""){
    	$("#addBookSuccess").prop("disabled", false);
		$("#isbnNo").html("");
    }
}

function checkShpngWgtValidation() {
	var shpngWeight = $("#shippingWeight").val();
	if (shpngWeight == 0.0 || shpngWeight < 0) {
		$("#addBookSuccess").prop("disabled", true);
    	$("#shpngWght").html("Please enter a valid shipping weight!");
		
    }
    else if(shpngWght!=""){
    	$("#addBookSuccess").prop("disabled", false);
		$("#shpngWght").html("");
    }
}

function checkLstPriceValidation() {
	var listPrice = $("#listPrice").val();
	if (listPrice == 0.0 || listPrice < 0) {
		$("#addBookSuccess").prop("disabled", true);
    	$("#lstPrice").html("Please enter a valid List Price!");
		
    }
    else if(listPrice!=""){
    	$("#addBookSuccess").prop("disabled", false);
		$("#lstPrice").html("");
    }
}

function checkOurPriceValidation() {
	var ourPrice = $("#ourPrice").val();
	if (ourPrice == 0.0 || ourPrice < 0) {
		$("#addBookSuccess").prop("disabled", true);
    	$("#givenPrice").html("Please enter a valid Price!");
		
    }
    else if(givenPrice!=null){
    	$("#addBookSuccess").prop("disabled", false);
		$("#givenPrice").html("");
    }
}

function checkInStockValidation() {
	var inStock = $("#inStockNumber").val();
	if (inStock == 0.0 || inStock < 0) {
		$("#addBookSuccess").prop("disabled", true);
    	$("#inStockNo").html("Please enter a valid number!");
		
    }
    else if(inStockNo!=""){
    	$("#addBookSuccess").prop("disabled", false);
		$("#inStockNo").html("");
    }
}

function checkDescValidation() {
	alert("hi");
	var desc = $("#description").val();
	var regex = /^[a-zA-Z ]$/;
	if (regex.test(desc)) {
		$("#addBookSuccess").prop("disabled", false);
		$("#desc").html("");
    }
    else if(desc!=""){
    	$("#addBookSuccess").prop("disabled", true);
    	$("#desc").html("Please enter a valid description!");
    }
}

$(document).ready(function(){
	$("#title").on('change',checkTitleValidation);
	$("#author").on('change',checkNameValidation);
	$("#publisher").on('change',checkPublisherValidation);
	$("#pageNumber").on('change',checkPageValidation);
	$("#isbn").on('change',checkIsbnValidation);
	$("#shippingWeight").on('change',checkShpngWgtValidation);
	$("#listPrice").on('change',checkLstPriceValidation);
	$("#ourPrice").on('change',checkOurPriceValidation);
	$("#inStockNumber").on('change',checkInStockValidation);
	$("#description").on('change',checkDescValidation);
	
	
});