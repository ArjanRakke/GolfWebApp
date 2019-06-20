package gooiseGolfclub.webservices;

public class ServiceProvider {
	private static LedenService ledenService = new LedenService();
	private static BaanStatusService baanStatusService = new BaanStatusService();
	private static WedstrijdSchemaService wedstrijdSchemaService = new WedstrijdSchemaService();

	public static LedenService getLedenService() {
		return ledenService;
	}
	
	public static BaanStatusService getbaanStatusService() {
		return baanStatusService;
	}
	
	public static WedstrijdSchemaService getWedstrijdSchemaService() {
		return wedstrijdSchemaService;
	}
}
