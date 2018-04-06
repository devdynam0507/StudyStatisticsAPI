package com.api.statistics;

import com.api.statistics.connection.ControllerFactory;
import com.api.statistics.connection.WebController;
import com.api.statistics.connection.WebURL;
import com.api.statistics.json.JSONDataWrapper;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ConnectException;

public class Main
{

    /*
    * Statistics Web Connector API Example
    * */
    public static void main(String... args) throws IOException, ParseException
    {
        try {
            /* Get user data example */
            WebController controller1 = ControllerFactory.create(WebURL.GET_USER_ALL_DATA).connect();
            JSONDataWrapper<String> wrapper = controller1.getUserData("dynam0507");
            String view = wrapper.getSecondItem("0", "math");
            System.out.println(view);

            /* Get user date data example */
            WebController controller2 = ControllerFactory.create(WebURL.GET_USER_DATE_DATA).connect();
            JSONDataWrapper<String> wrapper2 = controller2.getUserCondData("dynam0507", "2018", "3", "31");
            System.out.println(wrapper2.getSecondItem("2018-3-31", "math"));

            /* User id&password validation example */
            WebController controller3 = ControllerFactory.create(WebURL.GET_USER_VALIDATE).connect();
            JSONDataWrapper<Boolean> wrapper3 = controller3.existsUser("id", "password");
            boolean valid = wrapper3.get("valid");
            System.out.println(valid);

            /* Get user week data example */
            WebController controller4 = ControllerFactory.create(WebURL.GET_USER_WEEK_DATA).connect();
            JSONDataWrapper<String> wrapper4 = controller4.getStatisticsWeekData("dynam0507");
            String math = wrapper4.getSecondItem("2", "math");
            System.out.println(math);

            /* Get user average month data example */
            WebController controller5 = ControllerFactory.create(WebURL.GET_USER_AVERAGE_MONTH).connect();
            JSONDataWrapper<Double> wrapper5 = controller5.getStatisticsMonthAverageData("dynam0507");
            double korean = wrapper5.get("korean");
            System.out.println(korean);

        }catch (ConnectException e)
        {
            /* 인터넷 또는 서버 연결 실패시 실행되는 스코프 */
            System.out.println("[ERROR] 서버에 연결할수 없습니다.");
        }
    }
}
