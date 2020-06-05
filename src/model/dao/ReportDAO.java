package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Report;


public class ReportDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReportDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	public int create(Report report) throws SQLException {
		String sql = "INSERT INTO REPORT VALUES (REPORTID_SQ.nextval, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {report.getVaccined(), report.getCondition(), report.getAnimalID(), report.getReportDate(), report.getClientID()};
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}

	public int update(Report report) throws SQLException {
		String sql = "UPDATE REPORT "
					+ "SET  "
					+ "WHERE reportid=?";
		Object[] param = new Object[] {};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	public int remove(String reportID) throws SQLException {
		String sql = "DELETE FROM REPORT WHERE reportid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {reportID});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	
	public List<Report> findReport(String clientId, String animalId) throws SQLException {
        String sql = "SELECT reportid, vaccined, condition, reportdate " 
        			+ "FROM REPORT "
        			+ "WHERE clientid=? AND animalid=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId, animalId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	
	public List<Report> findReportList() throws SQLException {
		String sql = "SELECT reportId, vaccined, condition, animalId, reportdate, clientId "  
	     		   + "FROM REPORT "
	     		   + "ORDER BY reportId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
	public List<Report> findReportList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT reportId, vaccined, condition, animalId, reportdate, clientId " 
     		   + "FROM REPORT "
     		   + "ORDER BY reportId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

}