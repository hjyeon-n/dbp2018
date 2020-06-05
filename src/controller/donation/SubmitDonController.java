package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.ClientSessionUtils;
import model.Animal;
import model.Client;
import model.dao.DonationDAO;
import model.service.*;

public class SubmitDonController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DonationController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	
    	// 로그인 여부 확인
    	if (!ClientSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }
    	Animal animal = null;
    	Client client = null;
    	
    	AnimalManager amanager = AnimalManager.getInstance();
    	ClientManager cmanager = ClientManager.getInstance();
    	DonationManager dmanager = DonationManager.getInstance();
    	
    	String animalID = request.getParameter("animalId");
    	
		HttpSession session = request.getSession();
		String clientID = (String)session.getAttribute("clientId");
			
		try {
			animal = amanager.findAnimal(animalID);
			client = cmanager.findClient(clientID);
			
		} catch (AnimalNotFoundException e) {				
	        return "redirect:/animal/detail";
		}

    	dmanager.update(animalID);	
    	dmanager.create(animalID, client);
    	
        return "redirect:/animal/animalList";		
    }
}
