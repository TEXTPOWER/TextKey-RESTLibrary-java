package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.TextKeyRest;

public class TestValidateTextKeyFromUserId {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		  
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		String UserID = "Stan";
		String isHashed = "TRUE";
		String TextKey = "5233019";
		String TextKeyVC = "4DE30";

		/* Make the REST API Call */
		String JSONpayload =  textkey.perform_ValidateTextKeyFromUserId(UserID, 
																		TextKey,
																		TextKeyVC,
																        isHashed);

		/* Display the API Results */
		try {
			JSONObject results = new JSONObject(JSONpayload).getJSONObject("d");
		    System.out.println("Test Results: \n" + TextKeyRest.toPrettyFormat(results.toString()));
		} catch(Exception pe){
			pe.printStackTrace();
		} 				
	}

}

