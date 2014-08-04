package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.TextKeyRest;

public class TestRemoveTempAPIKey {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		Integer minutesDuration = 2;

		/* Make the REST API Call to get a Temp API Key */
		String JSONpayloadGet = textkey.perform_GetTempAPI_Key(minutesDuration);
		
		/* Get the tempAPI Key and remove it */
		try {
			JSONObject getAPI = new JSONObject(JSONpayloadGet);
			String tempAPI = getAPI.getJSONObject("d").getString("apiKey");

			/* Make the REST API Call to remove the Temp API Key */
			String JSONpayload = textkey.perform_RemoveTempAPIKey(tempAPI, minutesDuration);

			/* Display the API Results */
			try {
				JSONObject results = new JSONObject(JSONpayload).getJSONObject("d");
			    System.out.println("Test Results: \n" + TextKeyRest.toPrettyFormat(results.toString()));
			} catch(Exception pe){
				pe.printStackTrace();
			} 				
		} catch(Exception pe){
			pe.printStackTrace();
		} 
	}

}

