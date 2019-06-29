package gooiseGolfclub.webservices;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import gooiseGolfclub.persistensie.WedstrijdSchema;
import gooiseGolfclub.persistensie.WedstrijdSchemaDao;
import gooiseGolfclub.persistensie.WedstrijdSchemaMySqlDaoImpl;

@Path("/wedstrijdschema")
public class WedstrijdSchemaResource {
	private WedstrijdSchemaService service = ServiceProvider.getWedstrijdSchemaService();

	// Zet de POJO WedstrijdSchema om naar JSON
	public JsonObjectBuilder WedstrijdSchemaNaarJson(WedstrijdSchema wedstrijd) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("Wedstrijd_id", wedstrijd.getWedstrijd_id());
		job.add("Naam", wedstrijd.getNaam());
		job.add("Type", wedstrijd.getType());
		job.add("Holes", wedstrijd.getHoles());
		Date date = wedstrijd.getBegindatum();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = dateFormat.format(date);
		job.add("Begindatum", strDate);

		return job;
	}

	WedstrijdSchemaDao wDao = new WedstrijdSchemaMySqlDaoImpl();

	@GET
	@Produces("application/json")
	// Haalt alle wedstrijden op en zet ze in een JSON array
	public String getWedstrijden() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (WedstrijdSchema wedstrijd : service.getAllWedstrijden()) {
			jab.add(WedstrijdSchemaNaarJson(wedstrijd));
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@POST
	@RolesAllowed("commissielid")
	@Produces("application/json")
	// Voert een POST request uit en voegt een nieuwe wedstrijd aan de database toe
	public Response insertWedstrijd(@Context SecurityContext sc, @FormParam("wedstrijd_id") int wId,
			@FormParam("naam") String nm, @FormParam("type") String tp, @FormParam("holes") int holes,
			@FormParam("begindatum") Date bgDatum) {
		boolean role = sc.isUserInRole("commissielid");

		if (role) {
			WedstrijdSchema nieuweWedstrijd = service.save(wId, nm, tp, holes, bgDatum);

			if (nieuweWedstrijd == null) {
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Lid is niet toegevoegd");
				return Response.status(409).entity(messages).build();
			}

			return Response.ok(nieuweWedstrijd).build();
		}

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Account mag dit niet uitvoeren!");
		return Response.status(410).entity(messages).build();
	}

	@PUT
	@Path("/{wedstrijd_id}")
	@RolesAllowed("commissielid")
	@Produces("application/json")
	// Voert een PUT request uit en wijzigt de wedstrijd in de database
	public Response updateWedstrijd(@Context SecurityContext sc, @PathParam("wedstrijd_id") int wId,
			@FormParam("naam") String nm, @FormParam("type") String tp, @FormParam("holes") int holes,
			@FormParam("begindatum") Date bgDatum) {

		boolean role = sc.isUserInRole("commissielid");

		if (role) {
			WedstrijdSchema wijzigWedstrijd = service.update(wId, nm, tp, holes, bgDatum);

			if (wijzigWedstrijd == null) {
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Lid bestaat niet!");
				return Response.status(409).entity(messages).build();
			}

			return Response.ok(wijzigWedstrijd).build();
		}

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Account mag dit niet uitvoeren!");
		return Response.status(410).entity(messages).build();
	}

	@DELETE
	@Path("/{wedstrijd_id}")
	@RolesAllowed("commissielid")
	@Produces("application/json")
	// Voert een DELETE request uit en verwijderd de wedstrijd uit de database
	public Response deleteWedstrijd(@Context SecurityContext sc, @PathParam("wedstrijd_id") int wId) {
		boolean role = sc.isUserInRole("commissielid");

		if (role) {
			if (!service.delete(wId)) {
				return Response.status(404).build();
			}
			return Response.ok().build();
		}

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Account mag dit niet uitvoeren!");
		return Response.status(410).entity(messages).build();
	}
}
