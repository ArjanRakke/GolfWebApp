package gooiseGolfclub.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import gooiseGolfclub.persistensie.BaanStatus;
import gooiseGolfclub.persistensie.BaanStatusDao;
import gooiseGolfclub.persistensie.BaanStatusMySqlDaoImpl;

@Path("/baanstatus")
public class BaanStatusResource {
	private BaanStatusService service = ServiceProvider.getbaanStatusService();

	// Zet de BaanStatus POJO om in JSON
	public JsonObjectBuilder baanStatusNaarJson(BaanStatus status) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("baan_id", status.getBaan_id());
		job.add("GolfbaanBeschikbaar", status.getGolfbaanBeschikbaar());
		job.add("Qualifying", status.getQualifying());
		job.add("ZomerOfWinterGreens", status.getZomerOfWintergreens());
		job.add("TrolleysEnGolfkarren", status.getTrolleysEnGolfkarren());
		job.add("Bemest", status.getBemest());
		job.add("Onderhoud", status.getOnderhoud());
		if (status.getAankondiging() != null) {
			job.add("Aankondiging", status.getAankondiging());
		}

		return job;
	}

	BaanStatusDao bDao = new BaanStatusMySqlDaoImpl();

	@GET
	@Produces("application/json")
	//Haalt de baanstatus op in een JSON array
	public String getBaanStatus() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (BaanStatus status : service.getBaanStatus()) {
			jab.add(baanStatusNaarJson(status));
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@PUT
	@Path("{baan_id}")
	@RolesAllowed("commissielid")
	@Produces("application/json")
	// Voert een put request uit en wijzigt de baanstatus in de database
	public Response updateBaanStatus(@Context SecurityContext sc, @PathParam("baan_id") int bId,
			@FormParam("open") String gbBes, @FormParam("greens") String zwg,
			@FormParam("trolleysEnGolfkarren") String trlsGfk, @FormParam("qualifying") String qual,
			@FormParam("bemest") String bem, @FormParam("onderhoud") String ond,
			@FormParam("aankondiging") String aan) {

		boolean role = sc.isUserInRole("commissielid");

		if (role) {
			BaanStatus wijzigBaanStatus = service.updateBaanStatus(bId, gbBes, qual, zwg, trlsGfk, bem, ond, aan);

			if (wijzigBaanStatus == null) {
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Lid bestaat niet!");
				return Response.status(409).entity(messages).build();
			}

			return Response.ok(wijzigBaanStatus).build();
		}

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Account mag dit niet uitvoeren!");
		return Response.status(410).entity(messages).build();

	}
}
