package gooiseGolfclub.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import gooiseGolfclub.persistensie.Leden;
import gooiseGolfclub.persistensie.LedenDao;
import gooiseGolfclub.persistensie.LedenMySqlDaoImpl;

@Path("/leden")
public class LedenResource {
	private LedenService service = ServiceProvider.getLedenService();

	// Zet de POJO Leden om naar JSON
	public JsonObjectBuilder ledenNaarJson(Leden lid) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("NGF", lid.getNGF());
		job.add("Voornaam", lid.getVoornaam());
		job.add("Achternaam", lid.getAchternaam());
		job.add("Telefoonnummer", lid.getTelefoonnummer());
		job.add("Emailadres", lid.getEmailadres());
		job.add("Handicap", lid.getHandicap());

		return job;
	}

	LedenDao lDao = new LedenMySqlDaoImpl();

	@GET
	@Produces("application/json")
	// Haalt alle leden op in een JSON array
	public String getAllLeden() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Leden lid : service.getAllLeden()) {
			jab.add(ledenNaarJson(lid));
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@POST
	@RolesAllowed("commissielid")
	@Produces("application/json")
	// Voert een POST request uit en voegt een nieuw lid aan de database toe
	public Response insertLid(@Context SecurityContext sc, @FormParam("NGF") int NGF, @FormParam("Voornaam") String vm,
			@FormParam("Achternaam") String am, @FormParam("Telefoonnummer") int tel,
			@FormParam("Emailadres") String email, @FormParam("Handicap") double h) {
		
		boolean role = sc.isUserInRole("commissielid");

		if (role) {
			Leden nieuwLid = service.save(NGF, vm, am, tel, email, h);

			if (nieuwLid == null) {
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Lid is niet toegevoegd");
				return Response.status(409).entity(messages).build();
			}

			return Response.ok(nieuwLid).build();
		}

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Account mag dit niet uitvoeren!");
		return Response.status(410).entity(messages).build();
	}

	@PUT
	@Path("/{NGF}")
	@RolesAllowed("commissielid")
	@Produces("application/json")
	// Voert een put request uit en wijzigt het lid in de database
	public Response updateLid(@Context SecurityContext sc, @PathParam("NGF") int NGF, @FormParam("Voornaam") String vm,
			@FormParam("Achternaam") String am, @FormParam("Telefoonnummer") int tel,
			@FormParam("Emailadres") String email, @FormParam("Handicap") double h) {

		boolean role = sc.isUserInRole("commissielid");

		if (role) {
			Leden wijzigLid = service.updateLid(NGF, vm, am, tel, email, h);

			if (wijzigLid == null) {
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Lid bestaat niet!");
				return Response.status(409).entity(messages).build();
			}

			return Response.ok(wijzigLid).build();
		}

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Account mag dit niet uitvoeren!");
		return Response.status(410).entity(messages).build();
	}

	@DELETE
	@Path("/{NGF}")
	@RolesAllowed("commissielid")
	@Produces("application/json")
	public Response deleteLid(@Context SecurityContext sc, @PathParam("NGF") int NGF) {
		boolean role = sc.isUserInRole("commissielid");

		if (role) {
			if (!service.deleteLid(NGF)) {
				return Response.status(404).build();
			}
			return Response.ok().build();
		}

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Account mag dit niet uitvoeren!");
		return Response.status(410).entity(messages).build();
	}
}
