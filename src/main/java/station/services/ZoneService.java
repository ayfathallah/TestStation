package station.services;

import java.util.ArrayList;
import java.util.List;

import station.utils.Constant;

public class ZoneService  implements IZoneService{

    
	public int[] getZoneFromEnd(String startSatation, String endStation) {
		int[] formEnd = new int[2];
		boolean sameZone = false;
		List<Integer> from = findZone(startSatation);
		List<Integer> end = findZone(endStation);

		// meme zone
		for (int i = 0; i < from.size(); i++) {
			for (int j = 0; j < end.size(); j++) {
				if (from.get(i) == end.get(j)) {
					formEnd[0] = from.get(i);
					formEnd[1] = from.get(i);
					sameZone = true;
				}
			}
		}
		if (!sameZone) {
			int val = 0;

			int indexI = 0;
			int indexJ = 0;
			for (int i = 0; i < from.size(); i++) {
				for (int j = 0; j < end.size(); j++) {
					int distance = Math.abs(from.get(i) - end.get(j));
					if (i == 0 && j == 0) {
						val = distance;
						indexI = i;
						indexJ = j;
					}
					if (distance < val) {
						indexI = i;
						indexJ = j;
					}
				}
			}
			formEnd[0] = from.get(indexI);
			formEnd[1] = end.get(indexJ);

		}

		return formEnd;
	}

	public List<Integer> findZone(String station) {
		List<Integer> zone = new ArrayList<Integer>();
		if (Constant.zone1.contains(station)) {
			zone.add(1);
		}
		if (Constant.zone2.contains(station)) {
			zone.add(2);
		}
		if (Constant.zone3.contains(station)) {
			zone.add(3);
		}

		if (Constant.zone4.contains(station)) {
			zone.add(4);
		}
		return zone;

	}

	public int costZone(int zone[]) {
		if(zone[0]==zone[1]) {
			return Constant.tarif200;
		}
		if((zone[0]==1 && zone[1]==2) || (zone[1]==1 && zone[0]==2)  ) {
			return Constant.tarif240;	
		}
		if((zone[0]==3 && zone[1]==4) || (zone[1]==3 && zone[0]==4)  ) {
			return Constant.tarif200;	
		}
		if((zone[0]==3 && (zone[1]==1 || zone[1]==2)) || (zone[1]==3 && (zone[0]==1 || zone[0]==2)) ) {
			return Constant.tarif280;	
		}
		if((zone[0]==4 && (zone[1]==1 || zone[1]==2)) || (zone[1]==4 && (zone[0]==1 || zone[0]==2)) ) {
			return Constant.tarif300;	
		}
		
		return 0;
	}

	

}
