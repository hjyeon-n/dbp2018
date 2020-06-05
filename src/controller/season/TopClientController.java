package controller.season;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.*;

public class TopClientController implements Controller{

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		SeasonManager seasonMan = SeasonManager.getInstance();
		List<String> topList = seasonMan.findList("1803");
		
		request.setAttribute("top1", topList.get(0));
		request.setAttribute("top2", topList.get(1));
		request.setAttribute("top3", topList.get(2));
		
		
		return "/season/DonOfSeason.jsp";        
    }
}
