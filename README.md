# StudyStatisticsAPI
[dynam0507.pythonanywhere.com](dynam0507.pythonanywhere.com)

# StudyStatistics Protocol - HTTP POST 
_dynam0507.pythonanywhere.com/userdata - ["username"]_ <br>
_dynam0507.pythonanywhere.com/userdatedata - ["year", "month", "day"]_ <br>
_adynam0507.pythonanywhere.com/uservalid - ["username", "password"]_ <br>
_dynam0507.pythonanywhere.com/userweekdata - ["username"]_ <br>
_dynam0507.pythonanywhere.com/useraveragemonth - ["username"]_

# Relationship between WebURL Enum and WebController function
_WebURL.GET_USER_ALL_DATA - WebController.getUserData(id);_
_WebURL.GET_USER_DATE_DATA - WebController.getUserCondData(id, year, month, day);_
_WebURL.GET_USER_VALIDATE - WebController.existsUser(username, password);_
_WebURL.GET_USER_WEEK_DATA - WebController.getStatisticsWeekData(username);_
_WebURL.GET_USER_AVERAGE_MONTH - WebController.getStatisticsMonthAverageData(username);_

# StudyStatistics Java Example

```java
/* 해당 URL에 연결합니다. */
WebController controller = ControllerFactory.create(WebURL.GET_USER_ALL_DATA).connect();

/* JSONDataWrapper를 반환하는 함수를 호출합니다 */
JSONDataWrapper<String> wrapper = controller.getUserData("ID");

/* {"0": {"math": 80, "science": 200, "english": 200, "korean": 80}, "1": {"math": 90, "science": 195, "english": 250, "korean": 140}, "2": {"math": 30, "science": 210, "english": 140, "korean": 70}, "3": {"math": 80, "science": 100, "english": 200, "korean": 200}, "4": {"math": 200, "science": 70, "english": 320, "korean": 290}}
 */ 
 
 /* Json 데이터를 파싱합니다. */
 String data = wrapper.getSecondItem("0", "math");

```
