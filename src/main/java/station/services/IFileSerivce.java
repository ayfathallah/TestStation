package station.services;

import java.io.IOException;

public interface IFileSerivce {
	
	public String readFromInputStream(String urlInput) throws IOException ;
	public void writeFileOutPut(String json, String urlOutPut) throws IOException ;

}
