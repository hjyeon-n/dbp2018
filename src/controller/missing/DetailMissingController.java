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

		missing = manager.findMissing(Integer.toString(postId));	// 실종동물 정보 검색	
		
		request.setAttribute("missing", missing);		// 실종동물 정보 저장
		request.setAttribute("currentPage", currentPage);		// 실종동물 정보 저장	
		
		Client client = null;
		ClientManager cmanager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		try {
			client = cmanager.findClient(clientId);	// 사용자 정보 검색
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}	
		
		request.setAttribute("client", client);
		
		return "/missing/detail.jsp";		// 실종동물 리스트 화면으로 이동
    }
}
