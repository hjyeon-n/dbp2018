package controller.missing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.Missing;
import model.service.MissingManager;

public class DeleteMissingController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteMissingController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	String postId = request.getParameter("postId");
    	String writerId = request.getParameter("writerId");
    	String page = request.getParameter("page");

		MissingManager manager = MissingManager.getInstance();
		String curClientId = (String)request.getSession().getAttribute("clientId");	
			
		if (writerId.equals(curClientId))  { // 자신이 쓴 글인 경우
			manager.remove(postId);			// 실종동물 정보 삭제
			return "redirect:/missing/list?page=1";		// 실종동물리스트로 이동
		}
		else {
			Missing missing = manager.findMissing(postId);	// 실종동물 정보 검색
			request.setAttribute("missing", missing);
			String msg = "not writer";													
			request.setAttribute("exception", new IllegalStateException(msg));            
			return "redirect:/missing/detail?postId="+postId+"&page="+page;		// 상세 보기 화면으로 이동
		}
	}
}
