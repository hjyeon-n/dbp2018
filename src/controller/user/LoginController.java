package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Client;
import model.service.ClientManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String clientId = request.getParameter("clientId");
		String password = request.getParameter("password");
		
		try {
			// 모델에 로그인 처리를 위임
			ClientManager manager = ClientManager.getInstance();
			Client client = manager.login(clientId, password);
			
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(ClientSessionUtils.USER_SESSION_KEY, clientId);
            session.setAttribute(ClientSessionUtils.USER_SESSION_NAME, client.getClientName());
            
            String id = (String)session.getAttribute("clientId");
            String name = (String)session.getAttribute("clientName");
            
            request.setAttribute("clientId", id);
            request.setAttribute("clientName", name);
            
            return "redirect:/mainPage.jsp";			
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/login.jsp";			
		}	
    }
}
