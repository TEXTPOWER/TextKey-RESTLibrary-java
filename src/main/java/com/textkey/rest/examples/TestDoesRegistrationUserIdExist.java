package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.TextKeyRest;

public class TestDoesRegistrationUserIdExist {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		  
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		String UserID = "Stan";
		String isHashed = "TRUE";

		/* Make the REST API Call */
		String JSONpayload =  textkey.perform_DoesRegistrationUserIDExist(UserID, 
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

