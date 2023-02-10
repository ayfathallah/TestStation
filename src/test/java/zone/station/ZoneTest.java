package zone.station;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import station.services.IZoneService;
import station.services.ZoneService;


public class ZoneTest   extends TestCase {
    /**
     *
     * @param testZone 
     */
	IZoneService zoneService= new ZoneService();
	
    public ZoneTest( String testName )
    {
        super( testName );
    }

    /**
     * @return 
     */
    public static Test suite()
    {
        return new TestSuite( ZoneTest.class );
    }

   
    public void testFindZone()
    {
    	assertEquals(1, zoneService.findZone("A").size());
    	assertEquals(Integer.valueOf(1), zoneService.findZone("A").get(0));
    	assertEquals(Integer.valueOf(4), zoneService.findZone("I").get(0));
    	
    	assertEquals(Integer.valueOf(2), zoneService.findZone("E").get(0));
    	assertEquals(Integer.valueOf(3), zoneService.findZone("E").get(1));
        
    }
    public void testcostZone()
    {
    	int tab[]= new int[2];
    	
    	//tarif zone 1 to 2
    	tab[0]=1;tab[1]=2;
    	assertEquals(240, zoneService.costZone(tab));
    	
    	//tarif zone 4 to 3
    	tab[0]=4;tab[1]=3;
    	assertEquals(200, zoneService.costZone(tab));
    	
    	//tarif zone 4 to 2
    	tab[0]=4;tab[1]=2;
    	assertEquals(300, zoneService.costZone(tab));
    	
        
    }
    
    public void testFromTo() {
    	
    	//zone expected 1 to 1
    	int AB[]=zoneService.getZoneFromEnd("A", "B");
    	assertEquals(AB[0], 1);
    	assertEquals(AB[1], 1);
    	
    	//zone expected 4 to 3
    	int IE[]=zoneService.getZoneFromEnd("I", "E");
    	assertEquals(IE[0], 4);
    	assertEquals(IE[1], 3);
    	
    	//zone expected 3 to 3
    	int EC[]=zoneService.getZoneFromEnd("E", "C");
    	assertEquals(EC[0], 3);
    	assertEquals(EC[1], 3);
    	
    	//zone expected 3 to 2
    	int FD[]=zoneService.getZoneFromEnd("F", "D");
    	assertEquals(FD[0], 3);
    	assertEquals(FD[1], 2);
    	
    	//zone expected 2 to 1
    	int EB[]=zoneService.getZoneFromEnd("E", "B");
    	assertEquals(EB[0], 2);
    	assertEquals(EB[1], 1);
    	
    	
    	
    }
}
