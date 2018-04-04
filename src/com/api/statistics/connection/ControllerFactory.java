package com.api.statistics.connection;

public class ControllerFactory {

    public static WebController create(WebURL url)
    {
        return new WebController(url);
    }

}
