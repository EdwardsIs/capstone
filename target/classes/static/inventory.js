	function getInventory() {
		$.ajax({
			type : 'GET',
			url : './api/inventory',
			contentType : 'application/json; charset=utf-8', dataType : 'json',
			success : getInventorySucceeded, error : function(jqXHR, textStatus, errorThrown) {
	            alert("An error occurred while retrieving inventory","Inventory Retrieval Error");
	        }
		});
	}

	function getInventorySucceeded(response, io) {
		$('#displayArea').append("<table><tr class='tableHeader'><td style='width: 66px;'></td><td style='width: 25px;'>ID</td><td style='width: 326px;'>Time Created</td><td style='width: 326px;'>Time Updated</td><td style='width: 211px;'>Name</td><td style='width: 151px;'>Amount in Stock</td><td style='width: 51px;'>SKU</td><td style='width: 321px;'>Description</td><td style='width: 61px;'>Price</td></tr>");
		$.each(response,
				function(index, inventory){
					let dateCreateValue = Date.parse(inventory.time_created);
					let dateCreated = new Date(dateCreateValue);
					let dateCreatedString = stringDate(dateCreated);
					
					let dateUpdateValue = Date.parse(inventory.time_updated);
					let dateUpdated = new Date(dateUpdateValue);
					let dateUpdatedString = stringDate(dateUpdated);
					$('#displayArea').append("<tr id="+inventory.id+" onclick='selectedInterface.select("+inventory.id+",\""+inventory.item_name+"\",\""+inventory.amount_in_stock+"\",\""+inventory.sku+"\",\""+inventory.description+"\",\""+inventory.price+"\")'>" +
							"<td style='width: 65px;'><button onclick='deleteInventory("+inventory.id+")'>X</button></td><td style='width: 24px;'>"+inventory.id+"</td><td style='width: 325px;'>"+dateCreatedString+"</td><td style='width: 325px;'>"+dateUpdatedString+"</td><td style='width: 210px;'>"+inventory.item_name+"</td><td style='width: 151px;'>"+inventory.amount_in_stock+"</td><td style='width: 50px;'>"+inventory.sku+"</td><td style='width: 320px;'>"+inventory.description+"</td><td style='width: 60px;'>"+inventory.price+"</td>"
					+"</tr></table>");
		});
		selectedInterface.clear();
	}
	
	function deleteInventory(id){
	    $.ajax({
	        type : 'DELETE',
	        url : './api/inventory/'+id,
	        contentType : 'application/json; charset=utf-8',
	        success : deleteInventorySucceeded, error : function(jqXHR, textStatus, errorThrown) {
	        	if(jqXHR.responseText.indexOf("ConstraintViolation") > -1) {
	        		alert("Item can't be deleted while transactions for this item exist", "Error");
				    selectedInterface.clear();
	        	} else {
	        		alert("An error occurred while deleting item","Inventory Delete Error");
				    selectedInterface.clear();
	        	}
	        }
		});		
	}
	
	function deleteInventorySucceeded(response, io) {
	    //clear text area
		$('#displayArea').text('');
		//call new list of inventory items
	    getInventory();
	}
	
	function addInventory(name, amount_in_stock, sku, description, price) {
		$.ajax({
	            type : 'POST',
	            url : './api/inventory?item_name='+name+'&amount_in_stock='+amount_in_stock+'&sku='+sku+'&description='+description+'&price='+price,
	            contentType : 'application/json; charset=utf-8',
	            success : addInventorySucceeded, error : function(jqXHR, textStatus, errorThrown) {
	                alert("An error occurred while adding item","Inventory Add Error");
	            }
	   });
	}
	function addInventorySucceeded(response, io) {
	    //clear text area
		$('#displayArea').text('');
	    //call new list of inventory items
	    getInventory();
	}
	
	function updateInventory(id, name, amount_in_stock, sku, description, price) {
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
	            success : updateInventorySucceeded, error : function(jqXHR, textStatus, errorThrown) {
	                alert("An error occurred while updating item","Item Update Error");
	            }
	   });
	}
	function updateInventorySucceeded(response, io) {
	    //clear text area
		$('#displayArea').text('');
	    //call new list of items
	    getInventory();
	}