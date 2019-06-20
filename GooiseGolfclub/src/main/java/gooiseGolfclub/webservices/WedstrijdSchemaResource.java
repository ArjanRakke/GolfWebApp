package gooiseGolfclub.webservices;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import gooiseGolfclub.persistensie.WedstrijdSchema;
import gooiseGolfclub.persistensie.WedstrijdSchemaDao;
import gooiseGolfclub.persistensie.WedstrijdSchemaMySqlDaoImpl;

@Path("/wedstrijdschema")
public class WedstrijdSchemaResource {
	private WedstrijdSchemaService service = ServiceProvider.getWedstrijdSchemaService();
	
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
	public String getWedstrijden() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (WedstrijdSchema wedstrijd : service.getAllWedstrijden()) {
			jab.add(WedstrijdSchemaNaarJson(wedstrijd));
		}
		JsonArray array = jab.build();
		return array.toString();
	}
}
