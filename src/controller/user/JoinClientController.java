package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Client;
import model.service.ClientManager;
import model.service.ExistingClientException;


public class JoinClientController implements Controller {  
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("EUC-KR");
		request.setCharacterEncoding("EUC-KR");
		
		String cD = request.getParameter("don");
		int cDi = Integer.parseInt(cD);
		String cA = request.getParameter("account");

		String cAi = cA;
		Client client = new Client(
			request.getParameter("id"),
			request.getParameter("pw"),
			request.getParameter("name"),
			cDi,
			cAi,
			request.getParameter("tel"),
			request.getParameter("addr"));
        try {
        	ClientManager manager = ClientManager.getInstance();
			manager.create(client);
	        return "redirect:/mainPage";
	        
		} catch (ExistingClientException e) {	// 예외 발생 시 입력 form으로 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("client", client);
			return	"/user/joinForm.jsp";
		}
    }
}
