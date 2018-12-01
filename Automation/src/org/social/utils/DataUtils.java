package org.social.utils;

import org.json.JSONObject;
import com.google.gson.Gson;

/**
 * 
 * This class is responsible for manipulating the data into required formats
 *
 */
public class DataUtils {

	/**
	 * Converts a string to json
	 * @param json is the json string
	 * @return it returns a json object
	 */
	public static JSONObject convertToJson(String json) {
		return new JSONObject(json);
	}
	
	/**
	 * Returns an object of the required class from the json string
	 * @param json is the input json string, from which we have to construct an object
	 * @param obj is the dummy object which we need for getting the class name for which we have construct an object
	 * @return it returns the corresponding object
	 */
	public static Object convertJsonToObject(String json, Object obj) {
		Gson gson = new Gson();
		return gson.fromJson(json, obj.getClass());
	}
	
	/**
	 * 
	 * @param obj is the object for which we need json string
	 * @return returns a json object
	 */
	public static JSONObject convertObjectToJson(Object obj) {
		Gson gson = new Gson();
        String json = gson.toJson(obj, obj.getClass());
        return convertToJson(json);
 
	}
	
}
