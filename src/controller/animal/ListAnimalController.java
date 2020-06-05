package controller.animal;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Animal;
import model.Client;
import model.service.*;

public class ListAnimalController implements Controller{
	private static final int countPerPage = 6;

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	String currentPageStr = request.getParameter("currentPage");	
    	int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.findAnimalList(currentPage, countPerPage);
    	
		int totalPage = manager.findAllAnimalList().size() / countPerPage + 1;
		System.out.println("totalPage is " + totalPage);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("animalList", animalList);			
		
		Client client = null;
		ClientManager cmanager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		try {
			client = cmanager.findClient(clientId);	// 사용자 정보 검색
		} catch (ClientNotFoundException e) {				
	        return "/animal/animalList.jsp";
		}	
		
		request.setAttribute("client", client);
		
		return "/animal/animalList.jsp";        
    }
}
