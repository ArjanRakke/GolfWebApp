package gooiseGolfclub.webservices;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gooiseGolfclub.persistensie.Leden;
import gooiseGolfclub.persistensie.LedenDao;
import gooiseGolfclub.persistensie.LedenMySqlDaoImpl;

@Path("/leden")
public class LedenResource {
	private LedenService service = ServiceProvider.getLedenService();

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
	public String getAllLeden() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Leden lid : service.getAllLeden()) {
			jab.add(ledenNaarJson(lid));
		}
		JsonArray array = jab.build();
		return array.toString();
	}
}
