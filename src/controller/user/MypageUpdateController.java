package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Client;
import model.service.ClientManager;

public class MypageUpdateController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ClientManager manager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		Client client = new Client(
				clientId,
				request.getParameter("pw"),
				request.getParameter("name"),
				request.getParameter("tel"),
				request.getParameter("addr"),
				Integer.parseInt(request.getParameter("don")),
				request.getParameter("account")
				);
		manager.update(client);
		
		return "redirect:/user/mypage";
	}

}
