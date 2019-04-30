// Special getEmployees functions to autofill employee drop-down
function getEmployeesTrans() {
	$.ajax({
		type : 'GET',
		url : './api/employees',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getEmployeesTransSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving items","Item Retrieve Error");
        }
	});
}

function getEmployeesTransSucceeded(response, io) {
	$("#transEmployee").append("<option selected value='none' onclick='selectedInterface.transactionHeader.employee.id=0'>Select</option>");
	$.each(response,
			function(index, employee){
				$("#transEmployee").append("<option onclick='selectedInterface.setEmployee("+ employee.id +")'>" + employee.employee_name + "</option>");
				selectedInterface.employees.push(employee);
	});
}

// Special getCustomers functions to fill the customer dropdown
function getCustomersTrans() {
    $.ajax({
            type : 'GET',
            url : './api/customers',
            contentType : 'application/json; charset=utf-8', dataType : 'json',
            success : getCustomersTransSucceeded, error : function(jqXHR, textStatus, errorThrown) {
                alert("An error occurred while retrieving items","Item Retrieve Error");
            }
   });
}

function getCustomersTransSucceeded(response, io) {
	$("#transCustomer").append("<option selected value='none' onclick='selectedInterface.transactionHeader.employee.id=0'>Select</option>");
	$.each(response, 
    		function(index, customer) {
				//console.log("Selected: ", selected);
    			$("#transCustomer").append("<option onclick='selectedInterface.setCustomer("+ customer.id +")'>" + customer.customer_name + "</option>");
    			selectedInterface.customers.push(customer);
    });
}

// Special getInventory functions to get inventory for transactions
function getInventoryTrans() {
	$.ajax({
		type : 'GET',
		url : './api/inventory',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getInventoryTransSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving inventory","Inventory Retrieval Error");
        }
	});
}

function getInventoryTransSucceeded(response, io) {
	$("#transItemSku").append("<option selected value='none' onclick='selectedInterface.setItemID(0)'>Select</option>")
	$.each(response,
			function(index, inventory){
				selectedInterface.inventory.push(inventory);
				$("#transItemSku").append("<option onclick='selectedInterface.setItemID("+inventory.id+")'>" + inventory.sku + " - " + inventory.item_name + "</option>");
	});
}

function updateInventoryTrans(id, name, amount_in_stock, sku, description, price) {
	let item = {};
	item.id=id;
	item.item_name=name;
	item.amount_in_stock=amount_in_stock;
	item.sku=sku;
	item.description=description;
	item.price=price;
	$.ajax({
            type : 'PUT',
            data: JSON.stringify(item),
            url : './api/inventory/'+id,
            contentType : 'application/json; charset=utf-8',
            success : updateInventoryTransSucceeded, error : function(jqXHR, textStatus, errorThrown) {
                alert("An error occurred while updating item","Item Update Error");
            }
   });
}
function updateInventoryTransSucceeded(response, io) {}

// API calls to handle maintenance of transaction header
function addTransactionHeader(header) {
	$.ajax({
            type : 'POST',
            data: JSON.stringify(header),
            url : './api/transactionHeaders',
            contentType : 'application/json; charset=utf-8',
            success : addTransactionHeaderSucceeded, error : function(jqXHR, textStatus, errorThrown) {
                alert("An error occurred while adding transaction","Transaction Add Error");
            }
   });
}
function addTransactionHeaderSucceeded(response, io) {
	console.log("Transaction header added");
	getLatestTransactionHeader();
}

// Get transactionHeaders functions for display
function getTransactionHeaders() {
	$.ajax({
		type : 'GET',
		url : './api/transactionHeaders',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getTransactionHeadersSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving items","Item Retrieve Error");
        }
	});
}

function getTransactionHeadersSucceeded(response, io) {
	$("#displayArea").text('');
	$("#displayArea").append("<table><tr class='tableHeader'><td style='width: 25px;'>ID</td><td style='width: 311px;'>Time Created</td><td style='width: 106px;'>Customer ID</td><td style='width: 106px;'>Employee ID</td><td style='width: 70px'>Subtotal</td><td style='width: 71px;'>Tax</td></tr>");
	$.each(response,
			function(index, transHeader){
				$('#displayArea').append("<tr id="+transHeader.id+" onclick='selectedInterface.select("+transHeader.id+")'><td style='width: 24px;'>"+transHeader.id+"</td><td style='width: 310px;'>"+transHeader.time_created+"</td><td style='width: 105px;'>"+transHeader.customer.id+"</td><td style='width: 105px;'>"+transHeader.employee.id+"</td><td style='width: 70px;'>"+transHeader.subtotal_amount+"</td><td style='width: 70px'>"+transHeader.tax_amount+"</td>"
				+"</tr>");
	});
	$("#displayArea").append("</table>");
}

function getLatestTransactionHeader() {
	$.ajax({
		type : 'GET',
		url : './api/transactionHeaders',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getLatestTransactionHeaderSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving items","Item Retrieve Error");
        }
	});
}

function getLatestTransactionHeaderSucceeded(response, io) {
	$.each(response,
			function(index, header){
				selectedInterface.setLatestHeaderID(header.id);
	});
	console.log("Latest transaction header acquired, " + selectedInterface.latestHeaderID);
	selectedInterface.saveDetails();
}

function deleteTransactionHeader(id){
    $.ajax({
        type : 'DELETE',
        url : './api/transactionHeaders/'+id,
        contentType : 'application/json; charset=utf-8',
        success : deleteTransactionHeaderSucceeded, error : function(jqXHR, textStatus, errorThrown) {
        	if(jqXHR.responseText.indexOf("ConstraintViolation") > -1) {
        		alert("Transaction can't be deleted", "Error");
        	} else {
        		alert("An error occurred while deleting transaction","Transaction Delete Error");
        	}
        }
	});		
}

function deleteTransactionHeaderSucceeded(response, io) {
	//clear text area
	$('#displayArea').text('');
	//call new list of transactions
    getTransactionHeaders();
}

// Transaction details functions
function addTransactionDetail(detail) {
	console.log("Adding transaction detail");
	$.ajax({
            type : 'POST',
            data: JSON.stringify(detail),
            url : './api/transactionDetails',
            contentType : 'application/json; charset=utf-8',
            success : addTransactionDetailSucceeded, error : function(jqXHR, textStatus, errorThrown) {
                alert("An error occurred while adding detail","Detail Add Error");
            }
   });
}
function addTransactionDetailSucceeded(response, io) {
	console.log("We added the details");
}

// Functions to return all details for a given header ID
function getDetailsFor(id) {
	$.ajax({
		type: 'GET',
		url: './api/transactionDetailsFor/'+id,
		contentType: 'application/json; charset=utf-8',
		success: getDetailsForSucceeded, error: function(jqXHR, textStatus, errorThrown) {
			alert("An error occurred retrieving the details for your header", "Error");
		}
	});
}
function getDetailsForSucceeded(response, io) {
	$.each(response,
			function(response, detail){
				let item = selectedInterface.searchItemID(detail.inventory.id);
				$("#detailDisplay").append(detail.quantity + " " + item.item_name + " @ $" + detail.price + "<br>");
				selectedInterface.selectedDetailIDs.push(detail.id);
	});
}

// Functions to delete transaction details
function deleteTransactions(detail_ids, header_id){
	for(i = 0; i < detail_ids.length; i++) {
		$.ajax({
			//Strictly testing no async...
			async: false,
	        type : 'DELETE',
	        url : './api/transactionDetails/'+detail_ids[i],
	        contentType : 'application/json; charset=utf-8',
	        success : deleteTransactionsSucceeded, error : function(jqXHR, textStatus, errorThrown) {
	        	if(jqXHR.responseText.indexOf("ConstraintViolation") > -1) {
	        		alert("Transaction can't be deleted", "Error");
	        	} else {
	        		alert("An error occurred while deleting transaction","Transaction Delete Error");
	        	}
	        }
		});		
	}
    deleteTransactionHeader(header_id);
}

function deleteTransactionsSucceeded(response, io) {
	//clear text area
	$("#detailDisplay").text('');
	$("#detailDisplay").hide();
	$("#deleteTransaction").hide();
}
