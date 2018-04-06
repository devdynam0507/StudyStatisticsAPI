package com.api.statistics.connection;

import com.api.statistics.json.JSONDataWrapper;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebController {

    private HttpURLConnection connection;

    /**
    * HTTP Server Adress:Django Port/response adress
    * */
    public static String DEFAULT_URL = "http://dynam0507.pythonanywhere.com/";
    private final String USER_AGENT = "Mozilla/5.0";

    private WebURL url;

    /** @param url response 주소*/
    WebController(WebURL url)
    {
        this.url = url;
    }

    public WebController connect() throws IOException
    {
        if(this.connection == null)
        {
            URL url = new URL(this.url.getURL());
            this.connection = (HttpURLConnection) url.openConnection();
        }
        return this;
    }

    public WebController disconnect()
    {
        if(this.connection != null)
        {
            connection.disconnect();
            this.connection = null;
        }
        return this;
    }

    private WebController output(String parameters) throws IOException
    {
        this.connection.setDoOutput(true);
        DataOutputStream dos = new DataOutputStream(this.connection.getOutputStream());
        dos.writeBytes(parameters);
        dos.flush();
        dos.close();

        return this;
    }

    private WebController setup(String method) throws IOException
    {
        this.connection.setRequestMethod(method);
        this.connection.setRequestProperty("User-Agent", USER_AGENT);
        this.connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        return this;
    }

    private String getResponse() throws IOException, ParseException
    {
        if (this.connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()), 8192);
            String responseLine = null;
            StringBuilder builder = new StringBuilder();

            while ((responseLine = reader.readLine()) != null) builder.append(responseLine);
            System.out.println(builder.toString());
            reader.close();
            this.disconnect();

            return builder.toString();
        }
        return null;
    }

// ====================================================================================================================//
    /**
    * 유저의 모든 데이터를 검색합니다.
    * */
    public JSONDataWrapper getUserData(String username) throws IOException, ParseException, ConnectException
    {
        String parameters = "sn=C02G8416DRJM&cn=&locale=&caller=&username=" + username;
        String response = this.setup("POST").output(parameters).getResponse();

        if(response != null) return new JSONDataWrapper(response);
        return null;
    }

    /**
    *  해당 날짜의 유저 데이터를 검색합니다.
    */
    public JSONDataWrapper getUserCondData(String username, String year, String month, String day) throws IOException, ParseException, ConnectException
    {
        String parameters = "sn=C02G8416DRJM&cn=&locale=&caller=&username=" + username + "&year=" + year +
                "&month=" + month + "&day=" + day;
        String response = this.setup("POST").output(parameters).getResponse();

        if(response != null) return new JSONDataWrapper(response);
        return null;
    }

    /**
    * 유저의 계정이 존재하는지 확인합니다.
    * */
    public JSONDataWrapper existsUser(String id, String password) throws IOException, ParseException, ConnectException
    {
        String parameters = "sn=C02G8416DRJM&cn=&locale=&caller=&username="+ id + "&password=" + password;
        String response = this.setup("POST").output(parameters).getResponse();

        if(response != null) return new JSONDataWrapper(response);
        return null;
    }

    /**
    * 해당 유저의 주간 통계를 가져옵니다.
    * */
    public JSONDataWrapper getStatisticsWeekData(String username) throws IOException, ParseException, ConnectException
    {
        String parameters = "sn=C02G8416DRJM&cn=&locale=&caller=&username="+ username;
        String response = this.setup("POST").output(parameters).getResponse();

        if(response != null) return new JSONDataWrapper(response);
        return null;
    }

    /**
    * 해당 유저의 월간 평균 통계를 가져옵니다.
    * */
    public JSONDataWrapper getStatisticsMonthAverageData(String username) throws IOException, ParseException, ConnectException
    {
        String parameters = "sn=C02G8416DRJM&cn=&locale=&caller=&username="+ username;
        String response = this.setup("POST").output(parameters).getResponse();

        if(response != null) return new JSONDataWrapper(response);
        return null;
    }

// ====================================================================================================================//
}
