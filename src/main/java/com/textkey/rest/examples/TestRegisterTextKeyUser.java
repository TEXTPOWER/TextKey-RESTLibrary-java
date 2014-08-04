package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.TextKeyRest;

public class TestRegisterTextKeyUser {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		  
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		Integer Command = 0;
		String CellNumber = "8181231234";
		String OwnerFName = "";
		String OwnerLName = "";
		String Suppl1 = "";
		String Suppl2 = "";
		String UserID = "";
		String isHashed = "TRUE";
		String PinCode = ""; 
		String DistressPinCode = "";
		Integer TextKeyMode = 0;
		Integer ReceiveMode = 0;

		/* Make the REST API Call */
		String JSONpayload =  textkey.perform_registerTextKeyUser(Command,
																  CellNumber, 
																  OwnerFName, 
																  OwnerLName, 
																  Suppl1, 
																  Suppl2, 
																  UserID, 
																  isHashed, 
																  PinCode, 
																  DistressPinCode, 
																  TextKeyMode, 
																  ReceiveMode);

		/* Display the API Results */
		try {
			JSONObject results = new JSONObject(JSONpayload).getJSONObject("d");
		    System.out.println("Test Results: \n" + TextKeyRest.toPrettyFormat(results.toString()));
		} catch(Exception pe){
			pe.printStackTrace();
		} 				
	}

}

