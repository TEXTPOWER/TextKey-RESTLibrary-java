TextKey REST Library
====================

This Java library allows you to use TextKey's REST API calls server-side from a Java backend.

Common use-Case
---------------

To ensure a secure environment, you don't want to use APIs directly from the front-end, but rather through web-services inside your Java backend.

Installing the Library
----------------------

This project is built using Maven. To install Maven go to to the public [maven](http://maven.apache.org/download.html) repository and follow the installation instructions.

Use the following dependency in your project:

       <dependency>
          <groupId>com.textkey.rest</groupId>
          <artifactId>textkey-java-rest</artifactId>
          <version>1.0.0</version>
          <scope>compile</scope>
       </dependency>

If you want to compile it yourself, here's how:

    $ git clone git@github.com:TEXTPOWER/RESTLibrary-java
    $ cd RESTLibrary-java
    $ mvn install

This will also build the javadoc in `RESTLibrary-java/target/apidocs`. You can open the
index.html located there to view it locally.

Pre-built jars are available [here](http://search.maven.org/XXXXX). Select the directory for the latest version and download one of these jar files:

* textkey-java-rest-1.x.x-with-dependencies.jar
* textkey-java-rest-1.x.x.jar -- use this if you have issues with conflicting jars in your project. You'll need to include versions of the dependencies yourself. See the [pom.xml](https://github.com/TEXTPOWER/RESTLibrary-java/pom.xml) file in [RESTLibrary-java](https://github.com/TEXTPOWER/RESTLibrary-java) for the list of libraries.

You can view the javadocs for this project at:
[http://textpower.github.io/RESTLibrary-java](http://textpower.github.io/RESTLibrary-java)

How to use it?
--------------

The simple use case is to create a TextKeyRest object, call the appropriate API method and handle the returned JSON payload. The class will handle the details between the request and response and will return a JSON string to work with.

For example, here is a use case to check if a user has already been registered using the `doesRegistrationUserIDExist` API Call.

```java
package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.*;

public class TestDoesRegistrationUserIdExist {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT YOUR API KEY HERE";
		
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Setup the API call parameters */
		String UserID = "BobSmithUID";
		String isHashed = "TRUE";
		  
		/* Make the REST API Call */
		String JSONpayload =  textkey.perform_DoesRegistrationUserIDExist(UserID, isHashed);
		
		/* Display the API Results */
		try {
			JSONObject results = new JSONObject(JSONpayload).getJSONObject("d");
			System.out.println("Test Results: \n" + TextKeyRest.toPrettyFormat(results.toString()));
		} catch(Exception pe){
			pe.printStackTrace();
		} 				
	}
	
}
```

Here is what the result should look like:

	Test Results: 
	{
	  "__type": "TextKeyCommon.TKStructures+RegistrationExistence",
	  "cellNumberCount": -2,
	  "errorDescr": "",
	  "userIDCount": 1
	}

Initialization
---------------

The basic initialize step consists of including the REST Library as well as the JSON Library and then creating a TextKeyRest object.

```java
import org.json.JSONObject;
import com.textkey.rest.*;
```

```java
/* Setup */
String TK_API = "PUT YOUR API KEY HERE";
  
/* Create the TextKey object */
TextKeyRest textkey = new TextKeyRest(TK_API, false);
```

Making the API Call
-------------------

Once initialized, you can now make a call out to the specific TextKey API using the TextKeyRest object you just created.

```java
/* Setup the API call parameters */
String UserID = "BobSmithUID";
String isHashed = "TRUE";

/* Make the REST API Call */
String JSONpayload =  textkey.perform_DoesRegistrationUserIDExist(UserID, isHashed);
```

Handling the resulting payload
------------------------------

The API call will return back a JSON string with all of the API fields included. It is wrapped in the `d` parent element so that wrapper should be removed.

```java
/* Display the API Results */
try {
	JSONObject results = new JSONObject(JSONpayload).getJSONObject("d");
	System.out.println("Test Results: \n" + TextKeyRest.toPrettyFormat(results.toString()));
} catch(Exception pe){
	pe.printStackTrace();
} 				
```

NOTE: If there is an error, the `errorDescr` field of the returned JSON payload will contain a value.

Sample Code
-----------

There is sample code in the [examples Folder](https://github.com/TEXTPOWER/RESTLibrary-java/blob/master/src/main/java/com/textkey/rest/examples) for each TextKey API call using the shared library.

Contributing to this SDK
------------------------

**Issues**

Please discuss issues and features on Github Issues. We'll be happy to answer to your questions and improve the SDK based on your feedback.

**Pull requests**

You are welcome to fork this SDK and to make pull requests on Github. We'll review each of them, and integrate in a future release if they are relevant.
