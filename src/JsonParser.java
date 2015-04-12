import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonParser {

	public static String getJsonData(URL locUrl) throws IOException {
		// TODO Auto-generated method stub
		HttpURLConnection urlConnection = (HttpURLConnection) locUrl.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        // Read the input stream into a String
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            // Nothing to do.
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
            // But it does make debugging a *lot* easier if you print out the completed
            // buffer for debugging.
            buffer.append(line + "\n");
        }

        if (buffer.length() == 0) {
            // Stream was empty.  No point in parsing.
            return null;
        }

        String locJsonStr = buffer.toString();
        System.out.println("Unparsed JSON output for location URL : "+ locJsonStr);
		return locJsonStr;
	}

	public static String getBBox(String locJsonStr) throws JSONException {
		// TODO Auto-generated method stub
		
		final String BBOX = "boundingbox";
		JSONArray loc = new JSONArray(locJsonStr);
		JSONObject locobj = loc.getJSONObject(0);
		JSONArray locArray = locobj.getJSONArray(BBOX);
		double[] bboxCoord = new double[4];
		int k=0;
		
		for(int i = 0; i < 4; i++) {
			bboxCoord[k] = locArray.optDouble(i);
			k++;
		}
		
		//Format in Nominatim API : S,N,W,E
		//							0,1,2,3
		//Format in GCMD API	  : W,S,E,N
		//							2,0,3,1
		
		String bb = String.valueOf(bboxCoord[2]) + "," + String.valueOf(bboxCoord[0]) + "," + String.valueOf(bboxCoord[3]) + "," + String.valueOf(bboxCoord[1]);
		System.out.println("Bounding box coordinates in correct format for API : "+ bb);
		return bb;
	}


}
