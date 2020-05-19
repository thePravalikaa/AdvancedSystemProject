/**
 * 
 */



$(document).ready(function() {
	$('.delete-book').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this book? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}
	
	$('.delete-order').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var oldId=$(this).attr('id');
		
		var splitV=oldId.split('@');
		
		var id=splitV[1];
		
		var newDat=new Date();

		var latDate=formatDate(newDat);
		
		var x = splitV[2];
		
		var ordDate=x.substring(0,10);
		
		if(latDate==ordDate){
		
		bootbox.confirm({
			message: "Are you sure to cancel this order? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
		}else{
			alert("Order cannot be deleted!!! after 24 hours")
		}
	});
	

	
	$('#deleteSelected').click(function() {
		var idList= $('.checkboxBook');
		var bookIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				bookIdList.push(idList[i]['id'])
			}
		}
		if(bookIdList.length>0){
		console.log(bookIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all the Selected books? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(bookIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	 }
	});
	$('#deleteOrderSelected').click(function() {
		var idList= $('.checkboxOrder');
		var orderdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				orderdList.push(idList[i]['id'])
			}
		}
		if(orderdList.length>0){
		console.log(orderdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected Orders? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(orderdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	  }
	});
	
	$("#selectAllBooks").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxBook").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxBook").prop("checked",false);
		}
	})
	$("#selectAllOrders").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxOrder").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxOrder").prop("checked",false);
		}
	})
});