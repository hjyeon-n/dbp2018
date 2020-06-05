package controller.user;

import javax.servlet.http.HttpSession;

public class ClientSessionUtils {
    public static final String USER_SESSION_KEY = "clientId";
    public static final String USER_SESSION_NAME = "clientName";
    
    //현재 로그인한 사용자 ID 구하기
    public static String getUserFromSession(HttpSession session) {
        String clientId = (String)session.getAttribute(USER_SESSION_KEY);
        return clientId;
    }

    //현재 로그인한 사용자 이름 구하기
    public static String getUserNameFromSession(HttpSession session) {
    	String clientName = (String)session.getAttribute(USER_SESSION_NAME);
    	return clientName;
    }
    
    //로그인한 상태인지 검사
    public static boolean isLogined(HttpSession session) {
        if (getUserFromSession(session) != null) {
            return true;
        }
        return false;
    }
    
    //현재 로그인한 사용자의 ID가 clientId인지 검사
    public static boolean isLoginUser(String clientId, HttpSession session) {
        if (!isLogined(session)) {
            return false;
        }
        if (clientId == null) {
            return false;
        }
        return clientId.equals(getUserFromSession(session));
    }    

}
