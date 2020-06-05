package controller.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Client;
import model.Report;
import model.service.ClientManager;
import model.service.ClientNotFoundException;
import model.service.ReportManager;


public class ReportSubmitController implements Controller {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	response.setCharacterEncoding("EUC-KR");
		request.setCharacterEncoding("EUC-KR");
    	
    	Client client = null;
		ClientManager cmanager = ClientManager.getInstance();
		
		HttpSession session = request.getSession();
		String clientId = (String)session.getAttribute("clientId");
		
		try {
			client = cmanager.findClient(clientId);	// 사용자 정보 검색
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}	
		
		String animalId = request.getParameter("animalId"); 
		
		request.setAttribute("animalId", animalId);
		
		int iAnimalId = Integer.parseInt(animalId);
    	
		Report report = new Report(
				0,
				request.getParameter("vaccined"),
				request.getParameter("condition"),
				iAnimalId,
				request.getParameter("reportDate"),
				clientId);
      

        if(!report.getReportDate().equals("") && 
        		 !report.getCondition().equals("")&& !report.getVaccined().equals("")) {
			ReportManager manager = ReportManager.getInstance();
			manager.create(report);
			request.setAttribute("animalId", animalId);
			return "/report/reportlist";	// 성공 시 리스트 화면으로 redirect
        } else {
            request.setAttribute("postingFailed", true);
			request.setAttribute("report", report);
			return "/report/reportForm.jsp";
        }
    }
}
