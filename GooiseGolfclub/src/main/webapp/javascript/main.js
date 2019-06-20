// Laat een lijst met leden zien
function showLedenLijst() {
	// Roep een GET request aan
	fetch("restservices/leden")
	.then(response => response.json())
	.then(function(myJson) {
		var table = document.querySelector("#ledenLijst"), rIndex;
		
		var nieuwLid = document.querySelector("#nieuwLid");
		
		nieuwLid.addEventListener("click", function() {
			window.location.href = "nieuwlid.html";
		});
		
		for (const lid of myJson) {
			
			var ngf = document.createTextNode(lid.NGF);
			var voornaam = document.createTextNode(lid.Voornaam);
			var achternaam = document.createTextNode(lid.Achternaam);
			var telefoonnummer = document.createTextNode(lid.Telefoonnummer);
			var emailadres = document.createTextNode(lid.Emailadres);
			var handicap = document.createTextNode(lid.Handicap);
			var wijzigBtn = document.createElement("button");
			wijzigBtn.textContent = "wijzig";
			var verwijderBtn = document.createElement("button");
			verwijderBtn.textContent = "verwijder";
			
			var row = table.insertRow(-1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);
			var cell7 = row.insertCell(6);
			
			cell1.append(ngf);
			cell2.append(voornaam);
			cell3.append(achternaam);
			cell4.append(telefoonnummer);
			cell5.append(emailadres);
			cell6.append(handicap);
			cell7.append(wijzigBtn);
			cell7.append(verwijderBtn);
			
			for (var i = 1; i < table.rows.length; i ++) {
				table.rows[i].cells[6].onmouseover = function() {
					rIndex = this.parentElement.rowIndex;
					console.log(rIndex);
				}
			}
			
			var fetchOptionsDelete = {
					method: 'DELETE'
					/*
					 * headers : { 'Authorization': 'Bearer ' +
					 * window.sessionStorage.getItem("sessionToken") }
					 */
			}
			
			verwijderBtn.addEventListener("click", function() {
				
				var ngf = lid.NGF;
				
				var confirmatie = confirm("Weet u zeker dat u dit lid wilt verwijderen?");
				
				if (confirmatie) {
					//Roep een DELETE request aan
					fetch("restservices/leden/" + ngf, {method : "DELETE"})
					.then(function(response) {
						if(response.ok) {
							table.deleteRow(rIndex);
							console.log("Land verwijderd!")
						}
						else if (response.status == 404) {
							console.log("Land niet gevonden!");
						}
						else {
							console.log("Kan land niet verwijderen!");
						}
					});
				}
			});
		}
		
	});
}

//Voegt een nieuw lid toe
function toevoegen() {
	var lidToevoegen = document.querySelector("#lidToevoegen");
	var toevoegenConfirmatie = document.querySelector("#confirmInsert");
	var annuleerToevoegen = document.querySelector("#annuleerToevoegen");
	
	toevoegenConfirmatie.addEventListener("click", function() {
		
		if(NGF.value == "" || vm.value == "" || am.value == "" 
			|| tel.value == "" || email.value == "" || h.value == "") {
			alert("Voer alle velden correct in alstublieft!");
		}
		else if (NGF.value < 0) {
			alert("NGF kan geen negatief getal zijn!");
		}
		else if (h.value > 45) {
			alert("Handicap mag niet hoger zijn dan 45!");
		} 
		else if (tel.length < 10) {
			alert("Een telefoonnummer bevat 10 cijfers!");
		}
		else {
			var formData = new FormData(lidToevoegen);
			var encData = new URLSearchParams(formData.entries());
			
			//definieer de methode van het request en de headers
			var fetchOptionsPost = {
					method: 'POST',
					body: encData,
					headers : {
						'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
					}
			}
			
			// Roep een POST request aan
			fetch("restservices/leden", {method : "POST", body : encData})
				.then(response => response.json())
			
			window.location.href = "leden.html";
		}
	});
	
	annuleerToevoegen.addEventListener("click", function() {
		window.location.href = "leden.html";
	})
}