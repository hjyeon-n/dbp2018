package controller.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.ReportManager;

public class DeleteReportController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	String reportId = request.getParameter("reportId");
    	
    	String animalId = request.getParameter("animalId");

		ReportManager manager = ReportManager.getInstance();
			
		manager.remove(reportId);

		request.setAttribute("animalId", animalId);
		return "/report/reportlist";		
	}
}
