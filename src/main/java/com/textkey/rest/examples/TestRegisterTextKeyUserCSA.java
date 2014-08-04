package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.TextKeyRest;

public class TestRegisterTextKeyUserCSA {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		  
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		Integer Command = 1;
		String CellNumber = "8181231234";
		String OwnerFName = "Stan";
		String OwnerLName = "Smith";
		String Suppl1 = "Suppl1";
		String Suppl2 = "Suppl2";
		String ownerBirthDate = "12/25/1970";
		String ownerGender = "M";
		String UserID = "Stan";
		String isHashed = "TRUE";
		String PinCode = "1234"; 
		String DistressPinCode = "4321";
		String q1 = "Question 1";
		String a1 = "Answer 1";
		String q2 = "Question 2";
		String a2 = "Answer 2";
		String q3 = "Question 3";
		String a3 = "Answer 3";
		Integer TextKeyMode = 0;
		Integer ReceiveMode = 0;

		/* Make the REST API Call */
		String JSONpayload =  textkey.perform_registerTextKeyUserCSA(Command,
														             CellNumber, 
														             OwnerFName, 
														             OwnerLName, 
														             Suppl1, 
														             Suppl2,
														             ownerBirthDate,
														             ownerGender,
														             UserID, 
														             isHashed, 
														             PinCode, 
														             DistressPinCode, 
														             q1,
														             a1,
														             q2,
														             a2,
														             q3,
														             a3,
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

