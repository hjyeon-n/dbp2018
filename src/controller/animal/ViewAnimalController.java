package controller.animal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.AnimalManager;
import model.Animal;
import model.Client;
import model.service.AnimalNotFoundException;
import model.service.ClientManager;
import model.service.ClientNotFoundException;

public class ViewAnimalController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			

    	Animal animal = null;
		AnimalManager manager = AnimalManager.getInstance();
		String animalID = request.getParameter("animalID");

		try {
			animal = manager.findAnimal(animalID);
		} catch (AnimalNotFoundException e) {				
	        return "redirect:animalList";
		}	
		
		request.setAttribute("animal", animal);
			
		Client client = null;
		ClientManager cmanager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		try {
			client = cmanager.findClient(clientId);
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}	
		
		request.setAttribute("client", client);
		
		return "/animal/detail.jsp";
    }
}
