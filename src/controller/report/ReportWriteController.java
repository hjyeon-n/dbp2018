package controller.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ReportWriteController implements Controller {

		 @Override
		    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			 	response.setCharacterEncoding("EUC-KR");
				request.setCharacterEncoding("EUC-KR");
				
				String animalId = request.getParameter("animalId");
				
				request.setAttribute("animalId", animalId);
				
				return "/report/reportForm.jsp";        
		    }
}
