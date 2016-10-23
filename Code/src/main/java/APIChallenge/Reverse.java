package APIChallenge;

import java.util.Map;

import com.google.gson.Gson;

//import com.google.gson.Gson;

public class Reverse {
	
	final static String token = "bd65002e3921154babf10a8219856548";

	public static void main(String[] args) {
		Map<String , String> map = CommonMethods.makeJSON(false);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		Map<String , Integer> responseMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/reverse", json);
		System.out.println(responseMap);
		
		StringBuilder response = CommonMethods.getResponsefromURL();
		StringBuilder answer = response.reverse();
		map.put("string", answer.toString());
		String reverse = gson.toJson(map);
		System.out.println(reverse);
		
		Map<String , Integer> validateMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/reverse/validate", reverse);
		System.out.println(validateMap);
	} 
}
