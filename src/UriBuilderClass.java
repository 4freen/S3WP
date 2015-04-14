import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;


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
	
	
	public static URIBuilder buildDataUrl(String location, int day, int month, int year, String[] conditionArr) throws URISyntaxException {
		
		/*
		 * The URL is of the format
		 * http://gcmd.gsfc.nasa.gov/KeywordSearch/OpenSearch.do?
		 * searchTerms=water&output=html&geoBox=-124.4820029,32.5295236,-114.1307815,42.009499&
		 * timeStart=2009-02-14T00:00:00Z
		 * 
		 * We build it in Java using URIBuilder because the default URI cannot build complex URLs

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
	    qparams.add(new BasicNameValuePair("searchTerms", "water"));
	    qparams.add(new BasicNameValuePair("output", "html"));
	    qparams.add(new BasicNameValuePair("geoBox", "-124.4820029,32.5295236,-114.1307815,42.009499"));
	    qparams.add(new BasicNameValuePair("timeStart", "2009-02-14T00:00:00Z"));
	    URIBuilder uri = new URIBuilder().setScheme("http").setHost("gcmd.gsfc.nasa.gov/KeywordSearch/OpenSearch.do").setParameters(qparams);
	    */

		String searchQuery = "searchTerms";
		String outputQuery = "output";
		String locQuery = "geobox";
		String timeStart = "timeStart";
		String condition = "";
		String time = null;
		int i=0;
		
		while(conditionArr[i]!=null) {
			condition += conditionArr[i] + " ";
			i++;
		}
		
		System.out.println(condition);
		
		time = year + "-" + month + "-" + day;
		

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
	    qparams.add(new BasicNameValuePair(searchQuery, condition));
	    qparams.add(new BasicNameValuePair(outputQuery, "html"));
	    qparams.add(new BasicNameValuePair(locQuery, location));
	    qparams.add(new BasicNameValuePair(timeStart, time));
	    URIBuilder uri = new URIBuilder().setScheme("http").setHost("gcmd.gsfc.nasa.gov/KeywordSearch/OpenSearch.do").setParameters(qparams);
	    
	    System.out.println(uri.toString());
	    
	    return uri;
	}

}
