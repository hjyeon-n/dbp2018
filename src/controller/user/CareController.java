package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Animal;
import model.Client;
import model.Donation;
import model.service.AnimalManager;
import model.service.ClientManager;
import model.service.ClientNotFoundException;
import model.service.DonationManager;

public class CareController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!ClientSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }

		ClientManager cmanager = ClientManager.getInstance();
		AnimalManager amanager = AnimalManager.getInstance();
		DonationManager dmanager = DonationManager.getInstance();

		Animal animal = null;
		Client client = null;
		Donation donation = null;
		
		HttpSession session = request.getSession();
		String clientID = (String)session.getAttribute("clientId");
		String animalId = request.getParameter("animalId");
		
		try {
			client = cmanager.findClient(clientID);
			animal = amanager.findAnimal(animalId);
			donation = dmanager.findClient(animalId);
		} catch (ClientNotFoundException e) {				
	        return "redirect:/animal/detail";
		}		
		
		request.setAttribute("client", client);
		request.setAttribute("animal", animal);		
		request.setAttribute("donation", donation);

		return "/animal/careForm.jsp";		
    }
}
