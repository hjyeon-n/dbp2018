package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Client;
import model.service.ClientManager;
import model.service.ClientNotFoundException;

public class MypageUpdateFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인
    	if (!ClientSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
		ClientManager manager = ClientManager.getInstance();
		Client client = null;
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		try {
			client = manager.findClient(clientId);	// 사용자 정보 검색
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}
		
		session.setAttribute(ClientSessionUtils.USER_SESSION_NAME, client.getClientName());
		request.setAttribute("client", client);
		
		return "/user/updateUser.jsp";
	}

}
