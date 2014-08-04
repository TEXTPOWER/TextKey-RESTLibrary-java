package com.textkey.rest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * The Class TextKeyRest
 */
public class TextKeyRest {
	/** The VERSION */
	private static final String VERSION = "1.0";

	/** The base REST url */
	private static final String TK_REST = "https://secure.textkey.com/REST/TKRest.asmx/";

	/** The private variables */
	private final String authKey;
	private final Boolean displayInfo;

	/**
	 * Main
	 *
	 */
	public static void main(String[] args) {
	}

	/**
	 * Construct a TextKeyRest object
	 *
	 * @param authKey The TextKey API Key
	 * @param displayInfo A flag to display API information
	 */
	public TextKeyRest(final String authKey, final Boolean displayInfo) {
		this.authKey = authKey;
		this.displayInfo = displayInfo;
	}

	/**
     * toPrettyFormat - Returns a JSON string in pretty print format
     * 
     * @param jsonString The JSON payload to include with the API request
     * @return The formatted JSON string 
     */
    public static String toPrettyFormat(String jsonString) {
    	/* Setup GSON */
    	JsonParser parser = new JsonParser();
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
       	
    	/* Format the JSON */
    	JsonElement el = parser.parse(jsonString);
    	String json = gson.toJson(el);
    	
        /* Return the JSON */
    	return json;
    }
    
	/**
	 * sendAPIRequest - Send the API Request and return the results
	 *
	 * @param url The url for the request
	 * @param postdata The JSON payload
     * @return The returned JSON payload as a string
	 */
	public String sendAPIRequest(final String url, final JSONObject postdata) {
		/* Setup */
		String result = null;

	    /* Create a new HttpClient */
		CloseableHttpClient httpclient = HttpClients.createDefault();  

	    try {

		    /* Handle the request */
	        HttpPost request = new HttpPost(url);
	        StringEntity params = new StringEntity(postdata.toString());
	        request.addHeader("content-type", "application/json");
	        request.setEntity(params);
	        
	        /* Handle the response */
			CloseableHttpResponse response = httpclient.execute(request);  
	        try {  				
				/* Set the result JSON string */
				result = EntityUtils.toString(response.getEntity());
			} finally {  
				// Closing the response  
		    	try{
					response.close();  
		    	} catch(Exception ex) {
		    		ex.printStackTrace();
		    	}
			}  
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	try{
                httpclient.close();
	    	} catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    }
	    
		/* Display the URL, params and resulting payload */
		if (this.displayInfo) {
		    System.out.println("URL: " + url);
		    System.out.println("JSON Input Payload: \n" + TextKeyRest.toPrettyFormat(postdata.toString()));
		    System.out.println("JSON Output Payload: \n" + TextKeyRest.toPrettyFormat(result.toString()));
		}

	    /* Return the JSON */
		return result;
	}

	/**
	 * perform_GetTempAPI_Key - Send the getTempAPIKey API Request and return the results
	 *
	 * @param minutesDuration The number of minutes that this API key will be valid
     * @return The returned JSON payload as a string
	 */
	public String perform_GetTempAPI_Key(final Integer minutesDuration) {		
		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "getTempAPIKey";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("minutesDuration", minutesDuration);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}

	/**
	 * perform_registerTextKeyUser - Send the perform_registerTextKeyUser API Request 
	 * and return the results
	 *
	 * @param Command The register command	
	 * @param CellNumber The Cell Number that you are registering	
	 * @param OwnerFName User's First Name
	 * @param OwnerLName User's Last Name
	 * @param Suppl1 An additional identifier chosen by the customer
	 * @param Suppl2 Another additional identifier chosen by the customer
	 * @param UserID The user id that the end user has entered on the web site login page
	 * @param isHashed Is the user id hashed
	 * @param PinCode The numeric Pin Code
	 * @param DistressPinCode The numeric Distress Pin Code 
	 * @param TextKeyMode The TextKey validation mode
	 * @param ReceiveMode The TextKey receive mode.
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_registerTextKeyUser(final Integer Command,
											  final String CellNumber, 
											  final String OwnerFName, 
											  final String OwnerLName, 
											  final String Suppl1, 
											  final String Suppl2, 
											  final String UserID, 
											  final String isHashed, 
											  final String PinCode, 
											  final String DistressPinCode, 
											  final Integer TextKeyMode, 
											  final Integer ReceiveMode) {		
		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "registerTextKeyUser";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("command", Command);
			params.put("cellNumber", CellNumber);
			params.put("ownerFName", OwnerFName);
			params.put("ownerLName", OwnerLName);
			params.put("suppl1", Suppl1);
			params.put("suppl2", Suppl2);
			params.put("userID", UserID);
			params.put("isHashed", isHashed);
			params.put("pinCode", PinCode);
			params.put("distressPinCode", DistressPinCode);
			params.put("TextKeyMode", TextKeyMode);
			params.put("ReceiveMode", ReceiveMode);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}

	/**
	 * perform_registerTextKeyUserCSA - Send the perform_registerTextKeyUserCSA API Request 
	 * and return the results
	 *
	 * @param Command The register command	
	 * @param CellNumber The Cell Number that you are registering	
	 * @param OwnerFName User's First Name
	 * @param OwnerLName User's Last Name
	 * @param Suppl1 An additional identifier chosen by the customer
	 * @param Suppl2 Another additional identifier chosen by the customer
	 * @param OwnerBirthDate Users birth date. The format should be MM/DD/YYYY (i.e. 12/25/1984)
	 * @param Gender The users gender. The values should be either M or F.
	 * @param UserID The user id that the end user has entered on the web site login page
	 * @param isHashed Is the user id hashed
	 * @param PinCode The numeric Pin Code
	 * @param q1 The first user profile question. 
	 * @param a1 The answer to the first user question.
	 * @param q2 The second user profile question. 
	 * @param a2 The answer to the second user question.
	 * @param q3 The third user profile question. 
	 * @param a3 The answer to the third user question.
	 * @param TextKeyMode The TextKey validation mode
	 * @param ReceiveMode The TextKey receive mode.
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_registerTextKeyUserCSA(final Integer Command,
											     final String CellNumber, 
											     final String OwnerFName, 
											     final String OwnerLName, 
											     final String Suppl1, 
											     final String Suppl2, 
											     final String OwnerBirthDate, 
											     final String Gender, 											     
											     final String UserID, 
											     final String isHashed, 
											     final String PinCode, 
											     final String DistressPinCode, 
											     final String q1, 
											     final String a1, 
											     final String q2, 
											     final String a2, 
											     final String q3, 
											     final String a3, 
											     final Integer TextKeyMode, 
											     final Integer ReceiveMode) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "registerTextKeyUser";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("command", Command);
			params.put("cellNumber", CellNumber);
			params.put("ownerFName", OwnerFName);
			params.put("ownerLName", OwnerLName);
			params.put("suppl1", Suppl1);
			params.put("suppl2", Suppl2);
			params.put("ownerBirthDate", OwnerBirthDate);
			params.put("ownerGender", Gender);
			params.put("userID", UserID);
			params.put("isHashed", isHashed);
			params.put("pinCode", PinCode);
			params.put("distressPinCode", DistressPinCode);
			params.put("q1", q1);
			params.put("a1", a1);
			params.put("q2", q2);
			params.put("a2", a2);
			params.put("q3", q3);
			params.put("a3", a3);
			params.put("TextKeyMode", TextKeyMode);
			params.put("ReceiveMode", ReceiveMode);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}

	/**
	 * perform_IssueTextKeyFromUserId - Send the perform_IssueTextKeyFromUserId API Request 
	 * and return the results
	 *
	 * @param UserID The unique identifier that was used when the user was registered with
	 * @param isHashed Indicates if the UserID was hashed before the user was registered.
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_IssueTextKeyFromUserId(final String UserID, 
		     									 final String isHashed) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "issueTextKeyFromUserId";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("userID", UserID);
			params.put("isHashed", isHashed);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}

	/**
	 * perform_IssueTextKeyFromUserId - Send the perform_IssueTextKeyFromUserId API Request 
	 * and return the results
	 *
	 * @param RetrieveBy Defines the type of retrieve option to use
	 * @param CellNumber The Cell Number that you are registering	
	 * @param Suppl1 The suppl1 field value that you are searching for
	 * @param Suppl2 The suppl2 field value that you are searching fo
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_getTextKeyRegistration(final Integer RetrieveBy,
											     final String CellNumber, 
											     final String Suppl1, 
											     final String Suppl2) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "getTextKeyRegistration";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("retrieveBy", RetrieveBy);
			params.put("cellNumber", CellNumber);
			params.put("suppl1", Suppl1);
			params.put("suppl2", Suppl2);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}

	/**
	 * perform_DoesRegistrationUserIDExist - Send the perform_DoesRegistrationUserIDExist API Request 
	 * and return the results
	 *
	 * @param UserID The unique identifier that was used when the user was registered with
	 * @param isHashed Indicates if the UserID was hashed before the user was registered.
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_DoesRegistrationUserIDExist(final String UserID, 
		     									      final String isHashed) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "doesRegistrationUserIdExist";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("userID", UserID);
			params.put("isHashed", isHashed);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}

	/**
	 * perform_DoesRegistrationCellNumberExist - Send the doesRegistrationCellNumberExist API Request 
	 * and return the results
	 *
	 * @param CellNumber The Cell Number that was used when the user was registered with
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_DoesRegistrationCellNumberExist(final String CellNumber) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "doesRegistrationCellNumberExist";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("cellNumber", CellNumber);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}
	
	/**
	 * perform_CreateNewCellNumberProxy - Send the createNewCellNumberProxy API Request 
	 * and return the results
	 *
	 * @param CellNumber The Cell Number that was used when the user was registered with
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_CreateNewCellNumberProxy(final String CellNumber) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "createNewCellNumberProxy";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("trueCellNumber", CellNumber);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}	

	/**
	 * perform_RemoveTempAPIKey - Send the removeTempAPIKey API Request 
	 * and return the results
	 *
	 * @param TempAPIKey The temporary API Key to remove
	 * @param MinutesDuration This parameter is ignored but still must still be passed in with an integer value
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_RemoveTempAPIKey(final String TempAPIKey,
										   final Integer MinutesDuration) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "removeTempAPIKey";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", TempAPIKey);
			params.put("minutesDuration", MinutesDuration);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}	
	
	/**
	 * perform_IssueTextKeyFromCellNumber - Send the issueTextKeyFromCellNumber API Request 
	 * and return the results
	 *
	 * @param CellNumber The Cell Number that was used when the user was registered with
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_IssueTextKeyFromCellNumber(final String CellNumber) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "issueTextKeyFromCellNumber";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("cellNumber", CellNumber);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}	

	/**
	 * perform_PollForIncomingTextKey - Send the pollForIncomingTextKey API Request 
	 * and return the results
	 *
	 * @param TextKey The TextKey to check for
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_PollForIncomingTextKey(final String TextKey) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "pollForIncomingTextKey";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("textKey", TextKey);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}	

	/**
	 * perform_ValidateTextKeyFromUserId - Send the validateTextKeyFromUserId API Request 
	 * and return the results
	 *
	 * @param UserID The unique identifier that was used when the user was registered with
	 * @param TextKey The TextKey to check for
	 * @param TextKeyVC The TextKey Validation Code to check for
	 * @param isHashed Indicates if the UserID was hashed before the user was registered.
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_ValidateTextKeyFromUserId(final String UserID,
													final String TextKey,
													final String TextKeyVC,
													final String isHashed) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "validateTextKeyFromUserId";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("userID", UserID);
			params.put("textKey", TextKey);
			params.put("validationCode", TextKeyVC);
			params.put("isHashed", isHashed);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}	

	/**
	 * perform_ValidateTextKeyFromCellNumber - Send the validateTextKeyFromCellNumber API Request 
	 * and return the results
	 *
	 * @param CellNumber The Cell Number that was used when the user was registered with
	 * @param TextKey The TextKey to check for
	 * @param TextKeyVC The TextKey Validation Code to check for
     * @return The returned JSON payload as a string
	 * 
	 */
	public String perform_ValidateTextKeyFromCellNumber(final String CellNumber,
													    final String TextKey,
													    final String TextKeyVC) {		

		/* Setup */
		String result = null;

		/* Build the REST API URL */
		String url = TextKeyRest.TK_REST + "validateTextKeyFromCellNumber";
		
		/* Build the payload */
		try {
			JSONObject params = new JSONObject();
			params.put("apiKey", this.authKey);
			params.put("cellNumber", CellNumber);
			params.put("textKey", TextKey);
			params.put("validationCode", TextKeyVC);
			JSONObject payload = new JSONObject();
			payload.put("DataIn", params);

		    /* Send out the request */
			result = sendAPIRequest(url, payload);

		} catch(Exception pe){
			pe.printStackTrace();
		} 
		
	    /* Return the JSON */
 		return result;		
	}	
}