package Model;

/**
 * This Interfaces is implemented by any class that is concerned with 
 * the state of the current airport.
 * @author Edward
 */
public interface AirportObserver {
	public void updateAirport(Airport airport);
}
