package controller.include;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Animal;
import model.Client;
import model.service.*;

public class IncludeListAnimalController implements Controller{

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {	
    	
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.findAnimalList();
		
		request.setAttribute("includeAnimal", animalList);			
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
		
		return "/animal/includeAnimal.jsp";        
    }
}
