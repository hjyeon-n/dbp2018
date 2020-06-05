package controller.user;

import javax.servlet.http.HttpSession;

public class ClientSessionUtils {
    public static final String USER_SESSION_KEY = "clientId";
    public static final String USER_SESSION_NAME = "clientName";
    
    //���� �α����� ����� ID ���ϱ�
    public static String getUserFromSession(HttpSession session) {
        String clientId = (String)session.getAttribute(USER_SESSION_KEY);
        return clientId;
    }

    //���� �α����� ����� �̸� ���ϱ�
    public static String getUserNameFromSession(HttpSession session) {
    	String clientName = (String)session.getAttribute(USER_SESSION_NAME);
    	return clientName;
    }
    
    //�α����� �������� �˻�
    public static boolean isLogined(HttpSession session) {
        if (getUserFromSession(session) != null) {
            return true;
        }
        return false;
    }
    
    //���� �α����� ������� ID�� clientId���� �˻�
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
