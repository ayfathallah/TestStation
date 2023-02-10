package station.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import station.models.Tap;
import station.models.Taps;
import station.models.TotleTrips;

public class MapperJson {

	public static  List<Tap> getTaps(String tapsJson) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Taps taps = mapper.readValue(tapsJson, new TypeReference<Taps>() {
		});
		List<Tap> listTaps = taps.getTaps();
		return listTaps;
	}

	public  static String getJsonTrips(List<TotleTrips> totaleTrips) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writer().withRootName(Constant.root).withDefaultPrettyPrinter()
				.writeValueAsString(totaleTrips);
		return jsonInString;
	}

}
