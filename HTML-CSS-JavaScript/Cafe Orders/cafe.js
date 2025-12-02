"use strict";

let numItems = [0,0,0,0,0,0];
let itemNames = ["Espresso","Latte","Cappuccino","Coffee","Biscotti","Scone"];
let itemPrices = [1.95,2.95,3.45,1.75,1.95,2.95];

function preloadImage(){
	new Image().src = this.id;
	$("<img/>").src = this.id;
	$("<img/>").attr("src",this.id).appendTo("body").css("display", "none");
	//I'm not actually sure if any of these work?
}


$(function(){
	$("li a img").each(preloadImage);
	
	$("#place_order").on("click", placeOrder);
	$("#clear_order").on("click", clearOrder);

	$("li a img").on("click", addItem);
	$("li a img").on("mouseover", hoverOn);
	$("li a img").on("mouseout", hoverOff);
});

function placeOrder(){
	document.location.href = "checkout.html";
}

function clearOrder(){
	$("#order").html("");
	$("#total").text("");
	numItems = [0,0,0,0,0,0];
}

function hoverOn(){
	this.src = this.id;
}

function hoverOff(){
	this.src = `images/${this.alt}.jpg`;
}

function addItem(){
	switch(this.alt){
		case "espresso":
			numItems[0]++;
			break;
		case "latte":
			numItems[1]++;
			break;
		case "cappuccino":
			numItems[2]++;
			break;
		case "coffee":
			numItems[3]++;
			break;
		case "biscotti":
			numItems[4]++;
			break;
		case "scone":
			numItems[5]++;
			break;
	}

	let html = "";
	let total = 0;
	
	for(let i = 0; i < numItems.length; i++){
		total += numItems[i] * itemPrices[i];
		if(numItems[i] > 0){
			html += `<option>$${(numItems[i] * itemPrices[i]).toFixed(2)} - ${numItems[i]} X ${itemNames[i]} @ $${itemPrices[i].toFixed(2)} ea.</option>`;
		}
	}
	
	$("#total").text(`Total: $${total.toFixed(2)}`);
	$("#order").html(html);
}
