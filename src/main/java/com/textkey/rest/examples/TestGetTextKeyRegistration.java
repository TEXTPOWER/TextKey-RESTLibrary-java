package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.TextKeyRest;

public class TestGetTextKeyRegistration {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		  
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		Integer RetrieveBy = 0;
		String CellNumber = "8181231234";
		String Suppl1 = "";
		String Suppl2 = "";

		/* Make the REST API Call */
		String JSONpayload =  textkey.perform_getTextKeyRegistration(RetrieveBy,
														             CellNumber, 
														             Suppl1, 
														             Suppl2);
		
		/* Display the API Results */
		try {
			JSONObject results = new JSONObject(JSONpayload).getJSONObject("d");
		    System.out.println("Test Results: \n" + TextKeyRest.toPrettyFormat(results.toString()));
		} catch(Exception pe){
			pe.printStackTrace();
		} 				
	}

}

