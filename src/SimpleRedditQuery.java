
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
// add json external jar to classpath
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleRedditQuery {

	private static final String APP_ID = "AV79bHKhGrsMuZgmoL2chw";
	private static final String APP_SECRET = "3eYG48zgNaVKoTWJcf3KTfVBin1GNQ";  // PRIVATE: should not be shared
	private static final String USER_AGENT = "SocialScope/0.1 by u/SocialScopeBot";
	
	public static void main(String[] args) {
		String accessToken = getAccessToken();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO test query hardcoded
		String query = "pizza";
		System.out.println("Query: " + query);
		JSONObject queryData = queryString(query, accessToken);
		printQueryData(queryData);
	}
	
	public static String getAccessToken() {
		String token = null;
		try {
			// open connection
			URL url = new URL("https://ssl.reddit.com/api/v1/access_token");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// setup connection properties
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			
		 	// set authentication to id:secret encoded in base 64
		 	String auth = APP_ID + ":" + APP_SECRET;
		 	connection.setRequestProperty("Authorization", "Basic " + 
		 			Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8)));
			
		 	// set post parameters
			Map<String, String> parameters = new HashMap<>();
		 	parameters.put("grant_type", "client_credentials");	
		 	connection.setDoOutput(true);
		 	DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());
		 	outStream.writeBytes(buildParameterString(parameters));
		 	outStream.flush();
		 	outStream.close();
		 	
		 	// set timeout values in ms
		 	connection.setConnectTimeout(10000);
		 	connection.setReadTimeout(10000);
		 	
		 	// read response
		 	int status = connection.getResponseCode();	
		 	InputStreamReader streamReader = (status > 299) ? 
		 			new InputStreamReader(connection.getErrorStream()) :
		 			new InputStreamReader(connection.getInputStream());	
		 	BufferedReader connectionReader = new BufferedReader(streamReader);
		 	String currentLine;
		 	StringBuffer content = new StringBuffer();
		 	while ((currentLine = connectionReader.readLine()) != null) {
		 		content.append(currentLine);
		 	}
		 	connectionReader.close();
		 	
		 	// close connection
		 	connection.disconnect();
		 	
		 	// TODO debug print
		 	System.out.println(content.toString());
		 	
		 	JSONObject contentJSON = new JSONObject(content.toString());
		 	token = (status > 299) ? null : (String)contentJSON.get("access_token");
		 	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	
	public static JSONObject queryString(String query, String accessToken) {
		try {
			// set get parameters
			Map<String, String> parameters = new HashMap<>();
		 	parameters.put("q", query);	
		 	
			// open connection
			URL url = new URL("https://oauth.reddit.com/search?" + buildParameterString(parameters));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// setup connection properties
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			
			// set authentication
		 	connection.setRequestProperty("Authorization", "bearer " + accessToken);
		 	
		 	// set timeout values in ms
		 	connection.setConnectTimeout(10000);
		 	connection.setReadTimeout(10000);
		
		 	// read response
		 	int status = connection.getResponseCode();	
		 	InputStreamReader streamReader = (status > 299) ? 
		 			new InputStreamReader(connection.getErrorStream()) :
		 			new InputStreamReader(connection.getInputStream());	
		 	BufferedReader connectionReader = new BufferedReader(streamReader);
		 	String currentLine;
		 	StringBuffer content = new StringBuffer();
		 	while ((currentLine = connectionReader.readLine()) != null) {
		 		content.append(currentLine);
		 	}
		 	connectionReader.close();
		 	
		 	// close connection
		 	connection.disconnect();
		 	
		 	JSONObject contentJSON = new JSONObject(content.toString());
			return contentJSON;
		 	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public static void printQueryData(JSONObject queryData) {
		try {
			assert(queryData.getString("kind").equals("Listing"));
			JSONArray posts = queryData.getJSONObject("data").getJSONArray("children");
			for (int i = 0; i < posts.length(); i++) {
				assert(posts.getJSONObject(i).getString("kind").equals("t3"));
				String posterID = posts.getJSONObject(i).getJSONObject("data").getString("author_fullname");
				String postTitle = posts.getJSONObject(i).getJSONObject("data").getString("title");
				String postText = posts.getJSONObject(i).getJSONObject("data").getString("selftext");
				String postLink = posts.getJSONObject(i).getJSONObject("data").getString("permalink");
				System.out.println(posterID + " | " + postTitle + " | " + postLink);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String buildParameterString(Map<String, String> parameters) throws UnsupportedEncodingException {
		StringBuilder outString = new StringBuilder();
		for (Map.Entry<String, String> param : parameters.entrySet()) {
			outString.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			outString.append("=");
			outString.append(URLEncoder.encode(param.getValue(), "UTF-8"));
			outString.append("&");
		}
		// remove trailing '&'
		if (outString.length() > 0) outString.setLength(outString.length() - 1);
		return outString.toString();
	}
	
}
