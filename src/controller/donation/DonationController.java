package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.ClientSessionUtils;
import model.Client;
import model.Animal;

import model.service.*;

public class DonationController implements Controller {
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
    	
    	String animalID = request.getParameter("animalId");
    	
		HttpSession session = request.getSession();
		String clientID = (String)session.getAttribute("clientId");
		
		try {
			animal = amanager.findAnimal(animalID);	// 수정하려는 animal 정보 검색
			client = cmanager.findClient(clientID);
		} catch (AnimalNotFoundException e) {				
	        return "redirect:detail";
		}
		
		request.setAttribute("DonAnimal", animal);	
		request.setAttribute("DonClient", client);

		return "/animal/donationForm.jsp";
    }
}