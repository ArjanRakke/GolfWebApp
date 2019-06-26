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
			var telefoonnummer = document.createTextNode("0" + lid.Telefoonnummer);
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
				}
			}
			
			wijzigBtn.addEventListener("click", function() {
				sessionStorage.setItem("lid", JSON.stringify(lid));
				window.location.href = "lidwijzigen.html";
			});
			
			var fetchOptionsDelete = {
					method: 'DELETE',
					headers : { 'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken") }
			}
			
			verwijderBtn.addEventListener("click", function() {
				
				var ngf = lid.NGF;
				
				var confirmatie = confirm("Weet u zeker dat u dit lid wilt verwijderen?");
				
				if (confirmatie) {
					// Roep een DELETE request aan
					fetch("restservices/leden/" + ngf, {method : "DELETE"})
					.then(function(response) {
						if(response.ok) {
							table.deleteRow(rIndex);
							console.log("Lid verwijderd!")
						}
						else if (response.status == 404) {
							console.log("Lid niet gevonden!");
						}
						else {
							console.log("Kan lid niet verwijderen!");
						}
					});
				}
			});
		}
		
	});
}

// Voegt een nieuw lid toe
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
		else if (h.value > 54 || h.value < 0) {
			alert("Handicap mag niet hoger zijn dan 54 en lager dan 0!");
		}
		else {
			var formData = new FormData(lidToevoegen);
			var encData = new URLSearchParams(formData.entries());
			
			// definieer de methode van het request en de headers
			var fetchOptionsPost = {
					method: 'POST',
					body: encData,
					headers : {
						'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
					}
			}
			
			// Roep een POST request aan
			fetch("restservices/leden", {method : "POST", body : encData})
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						window.location.href = "leden.html";
					}
					else if (status == 409) {
						alert("Dit NGF bestaat al!");
					}
					else {
						alert("Er is een onbekende fout opgetreden! Voer alle gegevens opnieuw in!");
						lidToevoegen.reset();
					}
				})
				.catch(error => console.log(error.message));
		}
	});
	
	annuleerToevoegen.addEventListener("click", function() {
		window.location.href = "leden.html";
	})
}

// wijzigt een lid in de lijst
function wijzigen() {
	var lidWijzigen = document.querySelector("#lidWijzigen");
	var wijzigenConfirmatie = document.querySelector("#confirmUpdate");
	var annuleerWijziging = document.querySelector("#annuleerWijziging");
	var lid = sessionStorage.getItem("lid");
	var myJson = JSON.parse(lid);
	
	document.querySelector("#NGF").value = myJson.NGF;
	document.querySelector("#vm").value = myJson.Voornaam;
	document.querySelector("#am").value = myJson.Achternaam;
	document.querySelector("#tel").value = myJson.Telefoonnummer;
	document.querySelector("#email").value = myJson.Emailadres;
	document.querySelector("#h").value = myJson.Handicap;
	
	wijzigenConfirmatie.addEventListener("click", function() {
		
		if(NGF.value == "" || vm.value == "" || am.value == "" 
			|| tel.value == "" || email.value == "" || h.value == "") {
			alert("Voer alle velden correct in alstublieft!");
		}
		else if (NGF.value < 0) {
			alert("NGF kan geen negatief getal zijn!");
		}
		else if (h.value > 54) {
			alert("Handicap mag niet hoger zijn dan 54!");
		} 
		else if (tel.length < 10) {
			alert("Een telefoonnummer bevat 10 cijfers!");
		}
		else {
			var formData = new FormData(lidWijzigen);
			var encData = new URLSearchParams(formData.entries());
			
			// definieer de methode van het request en de headers
			var fetchOptionsPut = {
					method: 'PUT',
					body: encData,
					headers : {
						'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
					}
			}
			
			// Roep een PUT request aan
			fetch("restservices/leden/" + myJson.NGF, {method : "PUT", body : encData})
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						window.location.href = "leden.html";
						sessionStorage.removeItem("lid");
					}
					else {
						alert("Er is een onbekende fout opgetreden!");
					}
				});
		}
	});
	
	annuleerWijziging.addEventListener("click", function() {
		sessionStorage.removeItem("lid");
		window.location.href = "leden.html";
	});
}

// Laat de baanstatus zien
function showBaanStatus() {
	// Roep een GET request aan
	fetch("restservices/baanstatus")
	.then(response => response.json())
	.then(function(myJson) {
		
		for (const status of myJson) {
			var baan_id = status.baan_id
			var golfbaanBeschikbaar = document.createTextNode(status.GolfbaanBeschikbaar);
			var qualifying = document.createTextNode(status.Qualifying);
			var zomerOfWinterGreens = document.createTextNode(status.ZomerOfWinterGreens);
			var trolleysEnGolfkarren = document.createTextNode(status.TrolleysEnGolfkarren)
			var bemest = document.createTextNode(status.Bemest);
			var onderhoud = document.createTextNode(status.Onderhoud);
			var aankondiging = document.createTextNode("Aankondiging: " + status.Aankondiging);
			
			document.querySelector("#open").append(golfbaanBeschikbaar);
			document.querySelector("#zomerOfWinterGreens").append(zomerOfWinterGreens);
			document.querySelector("#trolleysEnGolfkarren").append(trolleysEnGolfkarren);
			document.querySelector("#qualifying").append(qualifying);
			document.querySelector("#bemest").append(bemest);
			document.querySelector("#onderhoud").append(onderhoud);
			document.querySelector("#aankondiging").append(aankondiging);
			
			var wijzigBaanStatus = document.querySelector("#wijzigBaanStatus");
			
			wijzigBaanStatus.addEventListener("click", function() {
				window.location.href = "baanstatuswijzigen.html";
			});
		}
		
		
	});
}

// wijzigt de baanstatus
function baanStatusWijzigen() {
	var baanstatusWijzigen = document.querySelector("#baanstatusWijzigen");
	var baan_id = document.querySelector("#bId").value;
	var wijzigenConfirmatie = document.querySelector("#confirmUpdate");
	var annuleerWijziging = document.querySelector("#annuleerWijziging");
	
	if(document.querySelector("#op").checked) {
		document.querySelector("#opUnchecked").disabled = true;
	}
	
	if(document.querySelector("#trlsGfk").checked) {
		document.querySelector("#trlsGfkUnchecked").disabled = true;
	}
	
	if(document.querySelector("#qual").checked) {
		document.querySelector("#qualUnchecked").disabled = true;
	}
	
	if(document.querySelector("#bem").checked) {
		document.querySelector("#bemUnchecked").disabled = true;
	}
	
	if(document.querySelector("#ond").checked) {
		document.querySelector("#ondUnchecked").disabled = true;
	}
	
	wijzigenConfirmatie.addEventListener("click", function() {
			var formData = new FormData(baanstatusWijzigen);
			var encData = new URLSearchParams(formData.entries());
			
			// definieer de methode van het request en de headers
			var fetchOptionsPut = {
					method: 'PUT',
					body: encData,
					headers : {
						'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
					}
			}
			
			// Roep een PUT request aan
			fetch("restservices/baanstatus/" + baan_id, {method : "PUT", body : encData})
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						//window.location.href = "index.html";
					}
					else {
						alert("Er is een onbekende fout opgetreden!");
					}
				});
	});
	
	
}