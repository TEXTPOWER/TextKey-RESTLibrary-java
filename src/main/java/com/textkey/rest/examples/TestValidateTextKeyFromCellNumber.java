package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.TextKeyRest;

public class TestValidateTextKeyFromCellNumber {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		  
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		String CellNumber = "8181231234";
		String TextKey = "4819399";
		String TextKeyVC = "57G14";

		/* Make the REST API Call */
		String JSONpayload =  textkey.perform_ValidateTextKeyFromCellNumber(CellNumber, 
																			TextKey,
																			TextKeyVC);

		/* Display the API Results */
		try {
			JSONObject results = new JSONObject(JSONpayload).getJSONObject("d");
		    System.out.println("Test Results: \n" + TextKeyRest.toPrettyFormat(results.toString()));
		} catch(Exception pe){
			pe.printStackTrace();
		} 						
	}

}

