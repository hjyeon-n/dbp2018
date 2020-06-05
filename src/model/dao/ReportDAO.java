package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Report;


public class ReportDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReportDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	public int create(Report report) throws SQLException {
		String sql = "INSERT INTO REPORT VALUES (REPORTID_SQ.nextval, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {report.getVaccined(), report.getCondition(), report.getAnimalID(), report.getReportDate(), report.getClientID()};
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	public int update(Report report) throws SQLException {
		String sql = "UPDATE REPORT "
					+ "SET  "
					+ "WHERE reportid=?";
		Object[] param = new Object[] {};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	public int remove(String reportID) throws SQLException {
		String sql = "DELETE FROM REPORT WHERE reportid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {reportID});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	
	public List<Report> findReport(String clientId, String animalId) throws SQLException {
        String sql = "SELECT reportid, vaccined, condition, reportdate " 
        			+ "FROM REPORT "
        			+ "WHERE clientid=? AND animalid=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId, animalId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Report> reportList = new ArrayList<Report>();
			while (rs.next()) {
				Report report = new Report(
						Integer.parseInt(rs.getString("reportid")),
						rs.getString("vaccined"),
						rs.getString("condition"),
						Integer.parseInt(animalId),
						rs.getString("reportDate"),
						clientId);
				reportList.add(report);	
			}
			return reportList;				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	
	public List<Report> findReportList() throws SQLException {
		String sql = "SELECT reportId, vaccined, condition, animalId, reportdate, clientId "  
	     		   + "FROM REPORT "
	     		   + "ORDER BY reportId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Report> reportList = new ArrayList<Report>();
			while (rs.next()) {
				Report report = new Report(
						rs.getInt("reportID"),
						rs.getString("vaccined"),
						rs.getString("condition"),
						rs.getInt("animalID"),
						rs.getString("reportDate"),
						rs.getString("clientID")
						);
				reportList.add(report);	
			}
			return reportList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
	public List<Report> findReportList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT reportId, vaccined, condition, animalId, reportdate, clientId " 
     		   + "FROM REPORT "
     		   + "ORDER BY reportId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<Report> reportList = new ArrayList<Report>();
				do {
					Report report = new Report(
							rs.getInt("reportID"),
							rs.getString("vaccined"),
							rs.getString("condition"),
							rs.getInt("animalID"),
							rs.getString("reportDate"),
							rs.getString("clientID")
							);
					reportList.add(report);				
				} while ((rs.next()) && (--countPerPage > 0));		
				return reportList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

}