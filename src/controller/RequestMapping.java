package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.animal.ListAnimalController;
import controller.animal.ViewAnimalController;
import controller.missing.DeleteMissingController;
import controller.missing.DetailMissingController;
import controller.missing.ListMissingController;
import controller.missing.WriteMissingController;
import controller.report.DeleteReportController;
import controller.report.ListReportController;
import controller.report.ReportSubmitController;
import controller.report.ReportWriteController;
import controller.season.TopClientController;
import controller.donation.*;
import controller.include.IncludeListAnimalController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/mainPage", new ForwardController("mainPage.jsp"));
        mappings.put("/introduction", new ForwardController("introduction.jsp"));
        
        mappings.put("/user/join", new JoinClientController());
        mappings.put("/user/join/form", new ForwardController("/user/joinForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/login/form", new ForwardController("/user/login.jsp")); 
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/mypage", new MypageController());
        mappings.put("/user/mypage/update", new MypageUpdateController());
        mappings.put("/user/mypage/update/form", new MypageUpdateFormController());
        
        mappings.put("/missing/list", new ListMissingController());
        mappings.put("/missing/detail", new DetailMissingController());
        mappings.put("/missing/write/form", new ForwardController("/missing/writeForm.jsp"));
        mappings.put("/missing/write", new WriteMissingController());
        mappings.put("/missing/delete", new DeleteMissingController());
        
   	 	mappings.put("/animal/animalList", new ListAnimalController());
   	 	mappings.put("/animal/detail", new ViewAnimalController());
   	 	mappings.put("/animal/adoption/form", new AdoptionController());
   	 	mappings.put("/animal/adoption", new SubmitFormController());
   	 	mappings.put("/animal/care/form", new CareController());
   	 	mappings.put("/animal/care", new SubmitFormController());
   	 	mappings.put("/animal/donation/form", new DonationController());
   	 	mappings.put("/animal/donation/submit", new SubmitDonController());
   	 	mappings.put("/animal/includeAnimal", new IncludeListAnimalController());
   	 	
   	 	mappings.put("/season/top", new TopClientController());
   	 	
   	 	mappings.put("/report/reportlist", new ListReportController());
   	 	mappings.put("/report/write", new ReportSubmitController());
   	 	mappings.put("/report/form", new ReportWriteController());
   	 	mappings.put("/report/delete", new DeleteReportController());


        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
