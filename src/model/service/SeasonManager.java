package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.SeasonDAO;

public class SeasonManager {
	private static SeasonManager seasonMan = new SeasonManager();
	private SeasonDAO SeasonDAO;

	private SeasonManager() {
		try {
			SeasonDAO = new SeasonDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static SeasonManager getInstance() {
		return seasonMan;
	}
	
	public List<String> findList(String dates) throws SQLException {
		return SeasonDAO.findList(dates);
	}
	
	public int reset(String dates) throws SQLException {
		return SeasonDAO.reset(dates);
	}
	
	public int update(String id, String dates) throws SQLException {
		return SeasonDAO.update(dates, id);
	}
}