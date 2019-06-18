package gooiseGolfclub.webservices;

public class ServiceProvider {
	private static LedenService ledenService = new LedenService();

	public static LedenService getLedenService() {
		return ledenService;
	}
}
