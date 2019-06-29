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
			cell1.setAttribute("class", "numberAlignment");
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);
			cell6.setAttribute("class", "numberAlignment");
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
					fetch("restservices/leden/" + ngf, fetchOptionsDelete)
					.then(function(response) {
						if(response.ok) {
							table.deleteRow(rIndex);
							console.log("Lid verwijderd!")
						}
						else if (response.status == 404) {
							console.log("Lid niet gevonden!");
						}
						else {
							alert("U bent niet gemachtigd om deze handeling uit te voeren!");
							console.log("Kan lid niet verwijderen!");
						}
					});
				}
			});
		}
		
	});
}

// Voegt een nieuw lid toe
function toevoegenLid() {
	var lidToevoegen = document.querySelector("#lidToevoegen");
	
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
			fetch("restservices/leden", fetchOptionsPost)
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						window.location.href = "leden.html";
					}
					else if (status == 409) {
						alert("Dit NGF bestaat al!");
					}
					else if (status == 410) {
						alert("U bent niet gemachtigd om deze handeling uit te voeren! " +
						"U wordt teruggestuurd naar de hoofdpagina!");
						window.location.href = "index.html"
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
function wijzigenLid() {
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
			fetch("restservices/leden/" + myJson.NGF, fetchOptionsPut)
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						window.location.href = "leden.html";
						sessionStorage.removeItem("lid");
					}
					else if (status == 409) {
						alert("Dit NGF bestaat al!");
					}
					else if (status == 410) {
						alert("U bent niet gemachtigd om deze handeling uit te voeren! " +
						"U wordt teruggestuurd naar de hoofdpagina!");
						sessionStorage.removeItem("lid");
						window.location.href = "index.html"
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
function wijzigenBaanstatus() {
	var baanStatusWijzigen = document.querySelector("#baanStatusWijzigen");
	var baan_id = document.querySelector("#bId").value;
	var annuleerWijziging = document.querySelector("#annuleerWijziging");
	
	wijzigenConfirmatie.addEventListener("click", function() {
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
		
			var formData = new FormData(baanStatusWijzigen);
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
			fetch("restservices/baanstatus/" + baan_id, fetchOptionsPut)
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						window.location.href = "index.html";
					}
					else {
						alert("U bent niet gemachtigd om deze handeling uit te voeren! " +
								"U wordt teruggestuurd naar de hoofdpagina!");
						window.location.href = "index.html"
					}
				});
	});
	
	annuleerWijziging.addEventListener("click", function() {
		window.location.href= "index.html";
	});
}

// Laat een lijst met wedstrijden zien
function showWedstrijdSchema() {
	// Roep een GET request aan
	fetch("restservices/wedstrijdschema")
	.then(response => response.json())
	.then(function(myJson) {
		var table = document.querySelector("#wedstrijdSchema"), rIndex;
		
		nieuweWedstrijd.addEventListener("click", function() {
			window.location.href = "nieuwewedstrijd.html";
		});
		
		for (const wedstrijd of myJson) {
			
			var naam = document.createTextNode(wedstrijd.Naam);
			var type = document.createTextNode(wedstrijd.Type);
			var holes = document.createTextNode(wedstrijd.Holes);
			var begindatum = document.createTextNode(wedstrijd.Begindatum);
			var wijzigBtn = document.createElement("button");
			wijzigBtn.textContent = "wijzig";
			var verwijderBtn = document.createElement("button");
			verwijderBtn.textContent = "verwijder";
			
			var row = table.insertRow(-1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			cell3.setAttribute("class", "numberAlignment");
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			
			cell1.append(naam);
			cell2.append(type);
			cell3.append(holes);
			cell4.append(begindatum);
			cell5.append(wijzigBtn);
			cell5.append(verwijderBtn);
			
			for (var i = 1; i < table.rows.length; i ++) {
				table.rows[i].cells[4].onmouseover = function() {
					rIndex = this.parentElement.rowIndex;
				}
			}

			wijzigBtn.addEventListener("click", function() {
				sessionStorage.setItem("wedstrijd", JSON.stringify(wedstrijd));
				window.location.href = "wedstrijdwijzigen.html";
			});
			
			// defineer de methode en de headers van het request
			var fetchOptionsDelete = {
					method: 'DELETE',
					headers : { 'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken") }
			}
			
			verwijderBtn.addEventListener("click", function() {
				
				var wedstrijd_id = wedstrijd.Wedstrijd_id;
				
				var confirmatie = confirm("Weet u zeker dat u deze wedstrijd wilt verwijderen?");
				
				if (confirmatie) {
					// Roep een DELETE request aan
					fetch("restservices/wedstrijdschema/" + wedstrijd_id, fetchOptionsDelete)
					.then(function(response) {
						if(response.ok) {
							table.deleteRow(rIndex);
							console.log("Wedstrijd verwijderd!")
						}
						else if (response.status == 404) {
							console.log("Wedstrijd niet gevonden!");
						}
						else {
							alert("U bent niet gemachtigd om deze handeling uit te voeren!");
							console.log("Kan wedstrijd niet verwijderen!");
						}
					});
				}
			});
		}
	});
}

function toevoegenWedstrijd() {
	var wedstrijdToevoegen = document.querySelector("#wedstrijdToevoegen");
	
	toevoegenConfirmatie.addEventListener("click" , function() {
		if(wId.value == "" || nm.value == "" || tp.value == "" 
			|| holes.value == "" || bgDatum.value == "") {
			alert("Voer alle velden correct in alstublieft!");
		}
		else {
			var formData = new FormData(wedstrijdToevoegen);
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
			fetch("restservices/wedstrijdschema", fetchOptionsPost)
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						window.location.href = "wedstrijden.html";
					}
					else if (status == 409) {
						alert("Dit wedstrijd_id bestaat al!");
					}
					else if (status == 410) {
						alert("U bent niet gemachtigd om deze handeling uit te voeren!" +
						"U wordt teruggestuurd naar de hoofdpagina!");
						window.location.href = "index.html"
					}
					else {
						alert("Er is een onbekende fout opgetreden! Voer alle gegevens opnieuw in!");
						wedstrijdToevoegen.reset();
					}
				})
				.catch(error => console.log(error.message));
		}	
	});
	
	annuleerToevoegen.addEventListener("click", function() {
		window.location.href = "wedstrijden.html";
	});
}

//wijzigt een lid in de lijst
function wijzigenWedstrijd() {
	var wedstrijdWijzigen = document.querySelector("#wedstrijdWijzigen");
	var wedstrijd = sessionStorage.getItem("wedstrijd");
	var myJson = JSON.parse(wedstrijd);
	
	document.querySelector("#wId").value = myJson.Wedstrijd_id;
	document.querySelector("#nm").value = myJson.Naam;
	document.querySelector("#tp").value = myJson.Type;
	document.querySelector("#holes").value = myJson.Holes;
	document.querySelector("#bgDatum").value = myJson.Begindatum;
	
	wijzigenConfirmatie.addEventListener("click", function() {
		
		if(wId.value == "" || nm.value == "" || tp.value == "" 
			|| holes.value == "" || bgDatum.value == "") {
			alert("Voer alle velden correct in alstublieft!");
		}
		else {
			var formData = new FormData(wedstrijdWijzigen);
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
			fetch("restservices/wedstrijdschema/" + myJson.Wedstrijd_id, fetchOptionsPut)
				.then(response => Promise.all([response.status, response.json()]))
				.then(function([status, myJson]) {
					if (status == 200) {
						window.location.href = "wedstrijden.html";
						sessionStorage.removeItem("wedstrijd");
					}
					else if (status == 410) {
						alert("U bent niet gemachtigd om deze handeling uit te voeren! " +
						"U wordt teruggestuurd naar de hoofdpagina!");
						sessionStorage.removeItem("wedstrijd");
						window.location.href = "index.html"
					}
					else {
						alert("Er is een onbekende fout opgetreden! Voer alle gegevens opnieuw in!");
						wedstrijdToevoegen.reset();
					}
				});
		}
	});
	
	annuleerWijziging.addEventListener("click", function() {
		sessionStorage.removeItem("wedstrijd");
		window.location.href = "wedstrijden.html";
	});
}