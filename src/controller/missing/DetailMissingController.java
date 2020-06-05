package controller.missing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.ClientManager;
import model.service.ClientNotFoundException;
import model.service.MissingManager;
import model.Client;
import model.Missing;

public class DetailMissingController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	Missing missing = null;
		MissingManager manager = MissingManager.getInstance();
		int postId = Integer.parseInt(request.getParameter("postId"));
		int currentPage = Integer.parseInt(request.getParameter("page"));

		missing = manager.findMissing(Integer.toString(postId));	// �������� ���� �˻�	
		
		request.setAttribute("missing", missing);		// �������� ���� ����
		request.setAttribute("currentPage", currentPage);		// �������� ���� ����	
		
		Client client = null;
		ClientManager cmanager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		try {
			client = cmanager.findClient(clientId);	// ����� ���� �˻�
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}	
		
		request.setAttribute("client", client);
		
		return "/missing/detail.jsp";		// �������� ����Ʈ ȭ������ �̵�
    }
}
