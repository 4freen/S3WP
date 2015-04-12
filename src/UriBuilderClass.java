import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class UriBuilderClass {

	public static URL buildLocUrl(String location) throws URISyntaxException, MalformedURLException {
		// TODO Auto-generated method stub
		  final String LOC_BASE_URL="nominatim.openstreetmap.org";
          //final String QUERY_PARAM="q";
          final String FORMAT_PARAM="format=json";
          
          location = "/search/" + location;
          URI builturi = new URI("http", LOC_BASE_URL, location, FORMAT_PARAM, null);
          URL builturl = builturi.toURL();
          
          System.out.println("URL for location : " + builturl.toString());
          //String locUrl = builturl.toString();
          
		return builturl;
	}
	
	public static URL buildDataUrl() {
		
	}


}
