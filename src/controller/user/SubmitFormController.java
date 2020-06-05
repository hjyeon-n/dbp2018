package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Animal;
import model.service.AdoptionCareManager;
import model.service.AnimalManager;

public class SubmitFormController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!ClientSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login";		// login form 요청으로 redirect
        }
    	
    	AdoptionCareManager manager = AdoptionCareManager.getInstance();
    	
    	String clientId = request.getParameter("clientId");
    	int animalId = Integer.parseInt(request.getParameter("animalId"));
    	String careTerm = request.getParameter("careTerm");		
    	String state = request.getParameter("state");    	

    	if(manager.findState(animalId))
    		manager.create(clientId, animalId, careTerm, state);
    	else
    		manager.update(state, animalId);
    	manager.dupdate(state, animalId);
    	
		Animal animal = null;
		AnimalManager amanager = AnimalManager.getInstance();
		animal = amanager.findAnimal(String.valueOf(animalId));
		
		request.setAttribute("animal", animal);
		
		return "/animal/detail.jsp";		
    }
}
