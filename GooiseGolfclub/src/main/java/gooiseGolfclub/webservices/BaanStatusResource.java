package gooiseGolfclub.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import gooiseGolfclub.persistensie.BaanStatus;
import gooiseGolfclub.persistensie.BaanStatusDao;
import gooiseGolfclub.persistensie.BaanStatusMySqlDaoImpl;

@Path("/baanstatus")
public class BaanStatusResource {
	private BaanStatusService service = ServiceProvider.getbaanStatusService();

	public JsonObjectBuilder baanStatusNaarJson(BaanStatus status) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("GolfbaanBeschikbaar", status.getGolfbaanBeschikbaar());
		job.add("Qualifying", status.getQualifying());
		job.add("ZomerOfWinterGreens", status.getZomerOfWintergreens());
		job.add("Bemest", status.getBemest());
		job.add("Onderhoud", status.getOnderhoud());
		if(status.getAankondiging() != null) {
			job.add("Aankondiging", status.getAankondiging());
		}

		return job;
	}

	BaanStatusDao bDao = new BaanStatusMySqlDaoImpl();

	@GET
	@Produces("application/json")
	public String getBaanStatus() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (BaanStatus status : service.getBaanStatus()) {
			jab.add(baanStatusNaarJson(status));
		}
		JsonArray array = jab.build();
		return array.toString();
	}
}
