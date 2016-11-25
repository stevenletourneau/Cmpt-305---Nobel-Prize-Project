/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprize;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

/**
 *
 * @author Alex Neilson
 */
public class Query {
    //This function creates a JSON object in order to read the API URL. It returns
    //the JSON object to be put into the results Scene.
    public JSONObject jsonCategoryQuery(String category){
        StringBuilder baseURL = new StringBuilder();
        baseURL.append("http://api.nobelprize.org/v1/prize.json");
        baseURL.append("?category="+category);
        JSONObject json = readURL(baseURL.toString());
        return json;
    }
    
    //This function creates a JSON object in order to read the API URL. It returns
    //the JSON object to be put into the results Scene.
    public JSONObject jsonCountryQuery(String country){
        StringBuilder baseURL = new StringBuilder();
        baseURL.append("http://api.nobelprize.org/v1/country.json");
        baseURL.append("?name="+country);
        JSONObject json = readURL(baseURL.toString());
        return json;
    }
        
    //This function reads data from the Nobel prize API. It takes the 
    //API URL as an argument and returns a JSON object, which holds
    //the name/value pairs of the Nobel prize JSON data.
    public static JSONObject readURL(String url) {
        //create a URL object to identify the webaddress
        URL nobelAPI = null;
        try {
            nobelAPI = new URL (url);
        } catch (MalformedURLException ex){
            System.out.println("Exception" + ex);
        }

        //Create URLConnection to access the actual content information of the URL
        URLConnection nobelContent= null;
        try {
            nobelContent= nobelAPI.openConnection();
        } catch (IOException ex){
            System.out.println("Exception" + ex);
        }
        
        //Return the InputStream linked to the API to obtain content
        InputStream nobelDataStream= null;
        try {
            nobelDataStream= nobelContent.getInputStream();
        } catch (IOException ex){
            System.out.println("Exception" + ex);
        }
        
        //Create a Reader obj to hold text from a character stream
        //and use InputStreamReader to decode bytes into characters
        Reader reader;
        reader = new InputStreamReader(nobelDataStream);
    
        //call readData to put all the text into a string and put in JSONOBject
        String jsonText = readData(reader);
        JSONObject jsonObj;
        jsonObj = new JSONObject(jsonText);
        
        try {
            nobelDataStream.close();
        }catch (IOException ex){
            System.out.println("Exception" + ex);
        }
        return jsonObj;
    }

    //this function reads data from the Reader object which reads the character
    //stream of API data. The data is read and returned as a string.
    private static String readData (Reader readObj) {
        //create a StringBuilder to hold data
        StringBuilder stringData = new StringBuilder();
        int charVal;
        try {
            //read chars and append to the stringbuilder until end of stream reached
            while ((charVal=readObj.read())!= -1){
                stringData.append((char)charVal);
            }
        } catch (IOException ex){
            System.out.println("IOException" + ex);
        }
        
        return stringData.toString();
    }
    
}
