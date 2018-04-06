package com.api.statistics.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONDataWrapper<T> {

    private JSONObject object;

    public JSONDataWrapper(String json) throws ParseException
    {

        JSONParser parser = new JSONParser();
        this.object = (JSONObject) parser.parse(json);
//        JSONObject object1 = (JSONObject) parser.parse( object.get("0").toString());
//        System.out.println(object1.toString());
    }

    public T get(String key)
    {
        Object object = this.object.get(key);
        return (T) object;
    }

    /**
    * @param1 = "3-31" or Number
    * @param2 = "math", "english"... etc
    * */
    public T getSecondItem(String firstKey, String secondKey)
    {
        try {
            JSONObject object = this.getSecondJSONObject(firstKey, secondKey);
            return (T) object.get(secondKey).toString();
        }catch (NullPointerException e)
        {
            System.out.println("JSON Key is NULL!");
            return (T) "0";
        }
    }

    public JSONObject getSecondJSONObject(String firstKey, String secondKey)
    {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(this.object.get(firstKey).toString());
            return object;
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJsonObject()
    {
        return this.object;
    }

}
