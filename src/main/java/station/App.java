package station;

import java.io.IOException;

import station.services.FileService;
import station.services.ITripService;
import station.services.TripService;
import station.services.ZoneService;

public class App {

	static ITripService tripService;
	
	//Injection
	public static void init() {
		System.out.println("Injection services..");
		try {
			tripService = new TripService(new FileService(), new ZoneService());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		System.out.println("fin Injection services");

	}
	public static void main(String[] args) throws IOException {
		
		//Test with parameter jar 
		String UrlInput= args[0];
		String UrlOutput=args[1];
		
		//Test in local 
		//String UrlInput= "C:\\Users\\pc\\Documents\\CandidateInputExample.txt";
        //String UrlOutput="C:\\Users\\pc\\Documents\\CandidateOutPutExample.txt";
		if (UrlInput == null || UrlOutput == null) {
			System.out.println("un des chemins est vide (input, output)");
		} else {
			init();
			tripService.TripTraitement(UrlInput, UrlOutput);
			
		}

	}

}
