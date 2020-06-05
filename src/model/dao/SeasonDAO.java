package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeasonDAO {
	private JDBCUtil jdbcUtil = null;
	
	public SeasonDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	
	public List<String> findList(String dates) throws SQLException {
		String sql = "SELECT clientID "
				+ "FROM  (SELECT clientID " 
						+ "FROM season " 
						+ "WHERE seasonID = ?"
						+ "ORDER BY seasonDon) "
				+ "where rownum <= 3";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {dates});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<String> topList = new ArrayList<String>();	// User들의 리스트 생성
			while (rs.next()) {
				String clientId = rs.getString("clientID");
					topList.add(clientId);		
			}
			return topList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public int update(String dates, String clientId) throws SQLException {
		
		String sql = "UPDATE season "
				+ "SET seasonDon = (SELECT seasonDon FROM season WHERE clientId = ?) + (SELECT clientDon FROM client WHERE clientId = ?) "
				+ "WHERE seasonID = ? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId, clientId, dates});		// JDBCUtil에 query문 설정
					
		try {
			int result = jdbcUtil.executeUpdate();
			return result;				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return 0;
	}
	
public int reset(String dates) throws SQLException {
		
		String sql = "UPDATE season "
				+ "SET seasonDon = 0, seasonID = ? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {dates});		// JDBCUtil에 query문 설정
					
		try {
			int result = jdbcUtil.executeUpdate();
			return result;				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return 0;
	}
	
}
