package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.ClientManager;
import model.service.ClientNotFoundException;
import model.Animal;
import model.Client;

public class MypageController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!ClientSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	Client client = null;
		ClientManager manager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");

		try {
			client = manager.findClient(clientId);	// 사용자 정보 검색
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}	

		List<Animal> donationAnimals = manager.findDonationAnimals(clientId);
		List<Animal> adoptcareAnimals = manager.findAdoptcareAnimals(clientId);
		
		request.setAttribute("client", client);		// 사용자 정보 저장		
		request.setAttribute("donationAnimals", donationAnimals);
		request.setAttribute("adoptcareAnimals", donationAnimals);
		return "/user/mypage.jsp";				// 사용자 보기 화면으로 이동
    }
}
