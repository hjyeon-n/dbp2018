package model.service;

import java.sql.SQLException;
import java.util.List;
import model.Report;
import model.dao.ReportDAO;

public class ReportManager {
	private static ReportManager reportMan = new ReportManager();
	private ReportDAO reportDAO;

	private ReportManager() {
		try {
			reportDAO = new ReportDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ReportManager getInstance() {
		return reportMan;
	}

	public int create(Report report)throws SQLException, ExistingUserException {
		return reportDAO.create(report);
	}
	public int remove(String reportId) throws SQLException, ClientNotFoundException {
		return reportDAO.remove(reportId);
	}

	public List<Report> findReport(String clientId, String animalId)
		throws SQLException, ReportNotFoundException {
		List<Report> report = reportDAO.findReport(clientId, animalId);
		
		return report;
	}

	public List<Report> findReportList() throws SQLException {
			return reportDAO.findReportList();
	}

	public List<Report> findReportList(int currentPage, int countPerPage)
		throws SQLException {
		return reportDAO.findReportList(currentPage, countPerPage);
	}

	public ReportDAO getReportDAO() {
		return this.reportDAO;
	}

	
	
}
