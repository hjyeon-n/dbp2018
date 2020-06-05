package controller.missing;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Client;
import model.Missing;
import model.service.ClientManager;
import model.service.ClientNotFoundException;
import model.service.MissingManager;

public class ListMissingController implements Controller {
	private static final int countPerPage = 6;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	
    	String currentPageStr = request.getParameter("page");	
		int page = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			page = Integer.parseInt(currentPageStr);
		}
    	
		MissingManager manager = MissingManager.getInstance();
		List<Missing> missingList = manager.findMissingList(page, countPerPage);
		
		int totalPage = manager.findMissingList().size() / countPerPage + 1;
		
		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("missingList", missingList);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPage", totalPage);

		Client client = null;
		ClientManager cmanager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		try {
			client = cmanager.findClient(clientId);	// client 정보 검색
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}	
		
		request.setAttribute("client", client);
		
		// 실종동물 리스트 화면으로 이동(forwarding)
		return "/missing/list.jsp";        
    }
}
