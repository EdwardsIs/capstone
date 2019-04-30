			function getEmployees() {
				$.ajax({
					type : 'GET',
					url : './api/employees',
					contentType : 'application/json; charset=utf-8', dataType : 'json',
					success : getEmployeesSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			            alert("An error occurred while retrieving items","Item Retrieve Error");
			        }
				});
			}

			function getEmployeesSucceeded(response, io) {
				$("#displayArea").append("<table><tr class='tableHeader'><td style='width: 66px;'></td><td style='width: 30px;'>ID</td><td style='width: 221px;'>Name</td><td style='width: 361px;'>Address</td><td style='width: 181px;'>City</td><td style='width: 56px;'>State</td><td style='width: 61px;'>Zip</td><td style='width: 311px;'>Email</td><td style='width: 130px;'>Phone</td></tr>");
				$.each(response,
						function(index, employee){
							$('#displayArea').append("<tr id="+employee.id+" onclick='selectedInterface.select("+employee.id+",\""+employee.employee_name+"\",\""+employee.address+"\",\""+employee.city+"\",\""+employee.state+"\",\""+employee.zip+"\",\""+employee.email+"\",\""+employee.phone+"\")'>" +
									"<td style='width: 65px;'><button onclick='deleteEmployee("+employee.id+")'>X</button></td><td style='width: 24px;'>"+employee.id+"</td><td style='width: 220px;'>"+employee.employee_name+"</td><td style='width: 360px;'>"+employee.address+"</td><td style='width: 180px;'>"+employee.city+"</td><td style='width: 50px;'>"+employee.state+"</td><td style='width: 60px;'>"+employee.zip+"</td><td style='width: 310px;'>"+employee.email+"</td><td style='width: 140px;'>"+employee.phone+"</td>"
							+"</tr></table>");
				});
				selectedInterface.clear();
			}
			
			function deleteEmployee(id){
			    $.ajax({
			        type : 'DELETE',
			        url : './api/employees/'+id,
			        contentType : 'application/json; charset=utf-8',
			        success : deleteEmployeeSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			        	if(jqXHR.responseText.indexOf("ConstraintViolation") > -1) {
			        		alert("Employee can't be deleted while transactions for this employee exist", "Error");
						    selectedInterface.clear();
			        	} else {
			        		alert("An error occurred while deleting employee","Employee Delete Error");
			        		selectedInterface.clear();
			        	}
			        }
				});		
			}
			
			function deleteEmployeeSucceeded(response, io) {
			    //clear text area
				$('#displayArea').text('');
				//call new list of employees
			    getEmployees();
			}
			
			function addEmployee(name, address, city, state, zip, email, phone) {
				$.ajax({
			            type : 'POST',
			            url : './api/employees?employee_name='+name+'&address='+address+'&city='+city+'&state='+state+'&zip='+zip+'&email='+email+'&phone='+phone,
			            contentType : 'application/json; charset=utf-8',
			            success : addEmployeeSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			                alert("An error occurred while adding employee","Employee Add Error");
			            }
			   });
			}
			function addEmployeeSucceeded(response, io) {
			    //clear text area
				$('#displayArea').text('');
			    //call new list of employees
			    getEmployees();
			}
			
			function updateEmployee(id, name, address, city, state, zip, email, phone) {
				let employee = {};
				employee.id=id;
				employee.employee_name=name;
				employee.address=address;
				employee.city=city;
				employee.state=state;
				employee.zip=zip;
				employee.email=email;
				employee.phone=phone;
				$.ajax({
			            type : 'PUT',
			            data: JSON.stringify(employee),
			            url : './api/employees/'+id,
			            contentType : 'application/json; charset=utf-8',
			            success : updateEmployeeSucceeded, error : function(jqXHR, textStatus, errorThrown) {
			                alert("An error occurred while updating employee","Employee Update Error");
			            }
			   });
			}
			function updateEmployeeSucceeded(response, io) {
			    //clear text area
				$('#displayArea').text('');
			    //call new list of employees
			    getEmployees();
			}