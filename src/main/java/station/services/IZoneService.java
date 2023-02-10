package station.services;

import java.util.List;

public interface IZoneService {
	
	public int[] getZoneFromEnd(String startSatation, String endStation);
	
	public List<Integer> findZone(String station);
	public int costZone(int zone[]);

}
