package controller.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Animal;
import model.Client;
import model.Report;
import model.service.AnimalManager;
import model.service.ClientManager;
import model.service.ClientNotFoundException;
import model.service.ReportManager;

public class ListReportController implements Controller {
	private static final int countPerPage = 6;

	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 	response.setCharacterEncoding("EUC-KR");
			request.setCharacterEncoding("EUC-KR");
	    	
	    	String currentPageStr = request.getParameter("page");	
			int currentPage = 1;
			if (currentPageStr != null && !currentPageStr.equals("")) {
				currentPage = Integer.parseInt(currentPageStr);
			}
			
			ReportManager manager = ReportManager.getInstance();
			List<Report> reportList = manager.findReportList(currentPage, countPerPage);
			
			int totalPage = manager.findReportList().size() / countPerPage + 1;
						
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("reportList", reportList);
			
			Client client = null;
			ClientManager cmanager = ClientManager.getInstance();
			Animal animal = null;
			AnimalManager amanager = AnimalManager.getInstance();
			
			String animalId = request.getParameter("animalId");
			HttpSession session = request.getSession();
			String clientId = (String)session.getAttribute("clientId");
			
			try {
				client = cmanager.findClient(clientId);	// client 정보 검색
			} catch (ClientNotFoundException e) {				
		        return "redirect:/user/login/form";
			}
			animal = amanager.findAnimal(animalId);
			
			List<Report> report = manager.findReport(clientId, animalId);
			
			request.setAttribute("report", report);
			request.setAttribute("client", client);
			request.setAttribute("animal", animal);
			
			return "/report/reportList.jsp";        
	    }
}
