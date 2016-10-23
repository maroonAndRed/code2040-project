package APIChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * Contains commons methods used among each of the steps.
 * @author Jonathan
 *
 */
public class CommonMethods {
	
	final static String token = "bd65002e3921154babf10a8219856548";
	final static String github = "https://github.com/cheekj/code2040-project.git";
	
	static HttpURLConnection urlConnect;
	
	public static Map<String , String> makeJSON(boolean registration) {
		Map<String , String> map  = null;
		if (registration == true) {
			map = new HashMap<String , String>();
			map.put("token", token);
			map.put("github", github);
		} else {
			map = new HashMap<String , String>();
			map.put("token", token);
		}
		return map;
	}
	
	/**
	 * Sends the the desired JSON to the desired URL. If the user is expceting a response
	 * from the URL, also gets that from the URL.
	 * @param urltoConnect URL to connect to.
	 * @param JSON String representing JSON to send to URL using POST method.
	 * @return A map with string "Connect URL Code" as a key and the response code as the value.
	 */
	public static Map<String , Integer> sendJSONToURL(String urltoConnect , String JSON) {
		Map<String , Integer> map = null;
		try {
			URL url = new URL(urltoConnect);
			urlConnect = (HttpURLConnection) url.openConnection();
			urlConnect.setDoOutput(true);
			urlConnect.setRequestMethod("POST");
			urlConnect.setRequestProperty("Content-Type", "application/json");
			
			OutputStreamWriter writer = new OutputStreamWriter(urlConnect.getOutputStream());
			writer.write(JSON);
			writer.flush();
			writer.close();
			map = new HashMap<String , Integer>();
			map.put("Connect URL Code", urlConnect.getResponseCode());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * Uses url and httpURLConnection from sendJSONtoURL method.
	 * @return StringBuilder representing response from URL.
	 */
	public static StringBuilder getResponsefromURL() {
		StringBuilder response = null;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
			response = new StringBuilder();
			String responseLine;
			while((responseLine = input.readLine()) != null) {
				response.append(responseLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static ArrayList<String> parseResponse(StringBuilder response , String regex) {
		ArrayList<String> answer = new ArrayList<String>();
		String word = "";
		
		for (int index = 0; index < response.length(); index++) {
			String character = response.substring(index, index + 1);
			if (Pattern.matches(regex, character)) {
				word += character;
			} else {
				if (word != "") {
					answer.add(word);
					word = "";
				}
			}
			
		}
		return answer;
	}

}
