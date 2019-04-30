			function getCustomers() {
			    $.ajax({
			            type : 'GET',
			            url : './api/customers',
			            contentType : 'application/json; charset=utf-8', dataType : 'json',
			            success : getCustomersSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			                alert("An error occurred while retrieving items","Item Retrieve Error");
			            }
			   });
			}
			
			function getCustomersSucceeded(response, io) {
				$('#displayArea').append("<table><tr class='tableHeader'><td style='width: 66px;'></td><td style='width: 30px;'>ID</td><td style='width: 221px;'>Name</td><td style='width: 358px;'>Address</td><td style='width: 176px;'>City</td><td style='width: 51px;'>State</td><td style='width: 61px;'>Zip</td><td style='width: 311px;'>Email</td><td style='width: 139px;'>Phone</td></tr>");
				$.each(response, 
			    		function(index, customer) {
			    			$('#displayArea').append("<tr id="+customer.id+" onclick='selectedInterface.select("+customer.id+",\""+customer.customer_name+"\",\""+customer.address+"\",\""+customer.city+"\",\""+customer.state+"\",\""+customer.zip+"\",\""+customer.email+"\",\""+customer.phone+"\")'>"+
			    					"<td style='width: 65px;'><button onclick='deleteCustomer("+customer.id+")'>X</button></td><td style='width: 20px;'>"+customer.id+"</td><td style='width: 220px;'>"+customer.customer_name+"</td><td style='width: 360px;'>"+customer.address+"</td><td style='width: 180px;'>"+customer.city+"</td><td style='width: 50px;'>"+customer.state+"</td><td style='width: 60px;'>"+customer.zip+"</td><td style='width: 310px;'>"+customer.email+"</td><td style='width: 140px;'>"+customer.phone+"</td>"
			    					+"</tr></table>");
			    });
				selectedInterface.clear();
			}
			
			function deleteCustomer(id){
			    $.ajax({
			        type : 'DELETE',
			        url : './api/customers/'+id,
			        contentType : 'application/json; charset=utf-8',
			        success : deleteCustomerSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			        	if(jqXHR.responseText.indexOf("ConstraintViolation") > -1) {
			        		alert("Customer can't be deleted while transactions for this customer exist", "Error");
			        		selectedInterface.clear();
			        	} else {
			        		alert("An error occurred while deleting customer","Customer Delete Error");
			        		selectedInterface.clear();
			        	}
			        }
				});
			}
			
			function deleteCustomerSucceeded(response, io) {
			    //clear text area
				$('#displayArea').text('');
			    getCustomers();
			}
			
			function addCustomer(name, address, city, state, zip, email, phone) {
				$.ajax({
			            type : 'POST',
			            url : './api/customers?customer_name='+name+'&address='+address+'&city='+city+'&state='+state+'&zip='+zip+'&email='+email+'&phone='+phone,
			            contentType : 'application/json; charset=utf-8',
			            success : addCustomerSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			                alert("An error occurred while adding item","Item Add Error");
			            }
			   });
			}
			function addCustomerSucceeded(response, io) {
			    //clear text area
				$('#displayArea').text('');
			    //call new list of customers
			    getCustomers();
			}
			
			function updateCustomer(id, name, address, city, state, zip, email, phone) {
				let customer = {};
				customer.id=id;
				customer.customer_name=name;
				customer.address=address;
				customer.city=city;
				customer.state=state;
				customer.zip=zip;
				customer.email=email;
				customer.phone=phone;
				$.ajax({
			            type : 'PUT',
			            data: JSON.stringify(customer),
			            url : './api/customers/'+id,
			            contentType : 'application/json; charset=utf-8',
			            success : addCustomerSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			                alert("An error occurred while updating customer","Customer Update Error");
			            }
			   });
			}
			function updateCustomerSucceeded(response, io) {
			    //clear text area
				$('#displayArea').text('');
			    //call new list of customers
			    getCustomers();
			}