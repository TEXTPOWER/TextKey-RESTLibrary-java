# Installing

This project is build using Maven. To install Maven got to the public [maven](http://maven.apache.org/download.html) repository and follow the installation instrucions.

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

# Examples

There is sample code in the [Examples Folder](https://github.com/TEXTPOWER/RESTLibrary-java/blob/master/src/main/java/com/textkey/rest/examples) for each TextKey API call.

For example, here is the code for the getTempAPI_Key API call using the shared library.

```java
package com.textkey.rest.examples;

import org.json.JSONObject;
import com.textkey.rest.*;

public class TestGetTempAPIKey {

	public static void main(String[] args) {
		/* Setup */
		String TK_API = "PUT API KEY HERE";
		Integer minutesDuration = 2;
		
		/* Create the TextKey object */
		TextKeyRest textkey = new TextKeyRest(TK_API, false);
		
		/* Make the REST API Call */
		String JSONpayload = textkey.perform_GetTempAPI_Key(minutesDuration);

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
