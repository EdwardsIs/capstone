// Special getEmployees functions to autofill employee drop-down
function getEmployeesReport() {
	$.ajax({
		type : 'GET',
		url : './api/employees',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getEmployeesReportSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving items","Item Retrieve Error");
        }
	});
}

function getEmployeesReportSucceeded(response, io) {
	$("#reportEmployee").append("<option selected value='none' onclick='selectedInterface.employeeID=0'>Select</option>");
	$.each(response,
			function(index, employee){
				$("#reportEmployee").append("<option onclick='selectedInterface.setEmployee("+employee.id+",\""+employee.employee_name+"\")'>" + employee.employee_name + "</option>");
	});
}

function getCustomersReport() {
    $.ajax({
            type : 'GET',
            url : './api/customers',
            contentType : 'application/json; charset=utf-8', dataType : 'json',
            success : getCustomersReportSucceeded, error : function(jqXHR, textStatus, errorThrown) {
                alert("An error occurred while retrieving items","Item Retrieve Error");
            }
   });
}

function getCustomersReportSucceeded(response, io) {
	$("#displayArea").text('');
	$('#displayArea').append("<table><tr class='tableHeader'><td style='width: 30px;'>ID</td><td style='width: 221px;'>Name</td><td style='width: 358px;'>Address</td><td style='width: 176px;'>City</td><td style='width: 51px;'>State</td><td style='width: 61px;'>Zip</td><td style='width: 311px;'>Email</td><td style='width: 139px;'>Phone</td></tr>");
	$.each(response, 
    		function(index, customer) {
    			$('#displayArea').append("<tr>"+
    					"<td style='width: 20px;'>"+customer.id+"</td><td style='width: 220px;'>"+customer.customer_name+"</td><td style='width: 360px;'>"+customer.address+"</td><td style='width: 180px;'>"+customer.city+"</td><td style='width: 50px;'>"+customer.state+"</td><td style='width: 60px;'>"+customer.zip+"</td><td style='width: 310px;'>"+customer.email+"</td><td style='width: 140px;'>"+customer.phone+"</td>"
    					+"</tr></table>");
    });
}

function getInventoryReport() {
	$.ajax({
		type : 'GET',
		url : './api/inventory',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getInventoryReportSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving inventory","Inventory Retrieval Error");
        }
	});
}

function getInventoryReportSucceeded(response, io) {
	$("#displayArea").text('');
	$('#displayArea').append("<table><tr class='tableHeader'><td style='width: 25px;'>ID</td><td style='width: 326px;'>Time Created</td><td style='width: 326px;'>Time Updated</td><td style='width: 211px;'>Name</td><td style='width: 151px;'>Amount in Stock</td><td style='width: 51px;'>SKU</td><td style='width: 321px;'>Description</td><td style='width: 61px;'>Price</td><td style='width: 55px;'>Reorder?</td></tr>");
	$.each(response,
			function(index, inventory){
				// Date formatting
				let dateCreateValue = Date.parse(inventory.time_created);
				let dateCreated = new Date(dateCreateValue);
				let dateCreatedString = stringDate(dateCreated);
				
				let dateUpdateValue = Date.parse(inventory.time_updated);
				let dateUpdated = new Date(dateUpdateValue);
				let dateUpdatedString = stringDate(dateUpdated);
				
				// Check whether or not to reorder
				let reorder = "No";
				if(inventory.amount_in_stock < 5) {
					reorder = "Yes";
				}
				$('#displayArea').append("<tr>" +
						"<td style='width: 24px;'>"+inventory.id+"</td><td style='width: 325px;'>"+dateCreatedString+"</td><td style='width: 325px;'>"+dateUpdatedString+"</td><td style='width: 210px;'>"+inventory.item_name+"</td><td style='width: 151px;'>"+inventory.amount_in_stock+"</td><td style='width: 50px;'>"+inventory.sku+"</td><td style='width: 320px;'>"+inventory.description+"</td><td style='width: 60px;'>"+inventory.price+"</td>"
				+"<td style='width: 74px;'>"+reorder+"</td>"+"</tr>");
	});
	$("#displayArea").append("</table>");
}

function loadInventoryReport() {
	$.ajax({
		type : 'GET',
		url : './api/inventory',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : loadInventoryReportSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving inventory","Inventory Retrieval Error");
        }
	});
}

function loadInventoryReportSucceeded(response, io) {
	$.each(response,
			function(index, inventory){
				selectedInterface.inventory.push(inventory);
	});
}

function getTransactionHeadersReport() {
	$.ajax({
		type : 'GET',
		url : './api/transactionHeaders',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getTransactionHeadersReportSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving headers","Header Retrieval Error");
        }
	});
}

function getTransactionHeadersReportSucceeded(response, io) {
	$("#displayArea").text('');
	$("#displayArea").append();
	$.each(response,
			function(index, transHeader){
				let dateCreateValue = Date.parse(transHeader.time_created);
				let dateCreated = new Date(dateCreateValue);
				let dateCreatedString = stringDate(dateCreated);
				$('#displayArea').append("Transaction ID: "+transHeader.id+"<br>"+dateCreatedString+"<br>Employee ID: "+transHeader.employee.id+"<br>Customer ID: " +
						transHeader.customer.id+"<br>");
				getDetailsForReport(transHeader.id);
				$("#displayArea").append("<br>");
	});
}

function getEmployeeReport() {
	$.ajax({
		type : 'GET',
		url : './api/employees',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getEmployeeReportSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving items","Item Retrieve Error");
        }
	});
	selectedInterface.commissionAmount = 0;
}

function getEmployeeReportSucceeded(response, io) {
	$("#displayArea").text('');
	$.each(response,
			function(index, employee){
				selectedInterface.employeeID = employee.id;
				selectedInterface.employeeName = employee.employee_name;
				getHeaderForReport(employee.id);
	});
}

function getHeaderForReport(id) {
	$.ajax({
		async: false,
		type: 'GET',
		url: './api/transactionHeaderFor/'+id,
		contentType: 'application/json; charset=utf-8',
		success: getHeaderForReportSucceeded, error: function(jqXHR, textStatus, errorThrown) {
			alert("An error occurred retrieving the headers for your employee", "Error");
		}
	});
}

function getHeaderForReportSucceeded(response, io) {
	$("#displayArea").append("Commission Report for: "+selectedInterface.employeeName+"<br>Employee" +
			" ID: "+selectedInterface.employeeID+"<br>");
	let transactionCount = 0;
	let totalAmount = 0;
		$.each(response,
			function(response, header){
				totalAmount += header.subtotal_amount;
				transactionCount++;
	});
	$("#displayArea").append("Transactions: " + transactionCount + "<br>");
	$("#displayArea").append("Total Revenue: $" + totalAmount.toFixed(2) + "<br>");
	let commission = totalAmount * 0.1; 
	$("#displayArea").append("Commission @ 10%: $" + commission.toFixed(2) + "<br><br>");
}

function getDetailsForReport(id) {
	$.ajax({
		async: false,
		type: 'GET',
		url: './api/transactionDetailsFor/'+id,
		contentType: 'application/json; charset=utf-8',
		success: getDetailsForReportSucceeded, error: function(jqXHR, textStatus, errorThrown) {
			alert("An error occurred retrieving the details for your header", "Error");
		}
	});
}
function getDetailsForReportSucceeded(response, io) {
		$.each(response,
			function(response, detail){
				let item = selectedInterface.searchItemID(detail.inventory.id);
				$("#displayArea").append(detail.quantity+" of "+item.item_name+" @ $"+detail.price+"<br>");
	});
}

// Get profit report
function getProfitReport() {
	$.ajax({
		type : 'GET',
		url : './api/transactionHeaders',
		contentType : 'application/json; charset=utf-8', dataType : 'json',
		success : getProfitReportSucceeded, error : function(jqXHR, textStatus, errorThrown) {
            alert("An error occurred while retrieving headers","Header Retrieval Error");
        }
	});
}

function getProfitReportSucceeded(response, io) { selectedInterface.commissionAmount += 
	$("#displayArea").text('');
	let totalSales = 0;
	$.each(response,
			function(index, transHeader){
				totalSales += transHeader.subtotal_amount;
		});
	let expenses = totalSales * 0.7;
	let commission = totalSales * 0.1;
	let profits = totalSales * 0.2;
	$("#displayArea").append("Total Sales: $" + totalSales.toFixed(2) + "<br>");
	$("#displayArea").append("Expenses: $" + expenses.toFixed(2) + "<br>");
	$("#displayArea").append("Commission: $" + commission.toFixed(2) + "<br>");
	$("#displayArea").append("Profits: $" + profits.toFixed(2) + "<br>");
}
