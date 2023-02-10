package station.models;

import java.util.List;

public class TotleTrips {

	private int customerId;
	private Long totalCostInCents;
	private List<Trip> trips;
	
	

	public TotleTrips(int customerId, Long totaleCostInCents, List<Trip> trips) {
		super();
		this.customerId = customerId;
		this.totalCostInCents = totaleCostInCents;
		this.trips = trips;
	}

	public int getCustomerId() {
		return customerId;
	} 

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Long getTotalCostInCents() {
		return totalCostInCents;
	}

	public void setTotalCostInCents(Long totalCostInCents) {
		this.totalCostInCents = totalCostInCents;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	@Override
	public String toString() {
		return "TotleTrips [customerId=" + customerId + ", totaleCostInCents=" + totalCostInCents + ", trips=" + trips
				+ "]";
	}

}
