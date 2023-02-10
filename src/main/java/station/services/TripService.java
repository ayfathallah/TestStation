package station.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import station.models.Tap;
import station.models.TotleTrips;
import station.models.Trip;
import station.utils.MapperJson;

public class TripService implements ITripService{

	IZoneService zoneService;
	IFileSerivce fileservice ;
	
	//Dependency injection by constructor
	public TripService(IFileSerivce fileSerivce, IZoneService zoneService) {
		 this.fileservice=fileSerivce;
		 this.zoneService=zoneService;
	}

	public void TripTraitement(String urlInput, String urlOutput) throws IOException {
		
		//Get json txt from file serivce 
		String jsonFile = fileservice.readFromInputStream(urlInput);
        
		
		//Convert json to objects listTaps
		List<Tap> listTaps = MapperJson.getTaps(jsonFile);

		Map<Integer, List<Tap>> tapGrouped = listTaps.stream().collect(Collectors.groupingBy(w -> w.getCustomerId()));

		List<TotleTrips> totleTrips = new ArrayList<TotleTrips>();
		for (Map.Entry<Integer, List<Tap>> entry : tapGrouped.entrySet()) {

			List<Trip> trips = new ArrayList<Trip>();
			List<Tap> tapsClient = entry.getValue();
			boolean startStation = true;
			Trip trip = new Trip();
			for (Tap tap : tapsClient) {
				if (startStation) {
					trip.setStationStart(tap.getStation());
					trip.setStartedJourneyAt(tap.getUnixTimestamp());
					startStation = false;
				} else {
					trip.setStationEnd(tap.getStation());
					startStation = true;
					int zones[] = zoneService.getZoneFromEnd(trip.getStationStart(), trip.getStationEnd());
					trip.setZoneFrom(zones[0]);
					trip.setZoneTo(zones[1]);
					int price = zoneService.costZone(zones);
					trip.setCostInCents(price);
					trips.add(trip);
					trip = new Trip();

				}

			}
			Long totaleCost = (long) trips.stream().mapToInt(e -> e.getCostInCents()).sum();

			totleTrips.add(new TotleTrips(entry.getKey(), totaleCost, trips));

		}

		String jsonReturn = MapperJson.getJsonTrips(totleTrips);
		fileservice.writeFileOutPut(jsonReturn, urlOutput);
		System.out.println("File generated in "+urlOutput);

	}
	
	

}
