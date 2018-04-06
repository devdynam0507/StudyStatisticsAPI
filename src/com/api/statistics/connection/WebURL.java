package com.api.statistics.connection;

public enum WebURL {

    /** Web Response URL */
    GET_USER_ALL_DATA(WebController.DEFAULT_URL + "response/userdata"),
    GET_USER_DATE_DATA(WebController.DEFAULT_URL + "response/userdatedata"),
    GET_USER_VALIDATE(WebController.DEFAULT_URL + "response/uservalid"),
    GET_USER_WEEK_DATA(WebController.DEFAULT_URL + "response/userweekdata"),
    GET_USER_AVERAGE_MONTH(WebController.DEFAULT_URL + "response/useraveragemonth");

    private String string;
    WebURL(String string)
    {
        this.string = string;
    }

    public String getURL()
    {
        return this.string;
    }
}
