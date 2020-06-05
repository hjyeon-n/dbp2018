package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeasonDAO {
	private JDBCUtil jdbcUtil = null;
	
	public SeasonDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	
	public List<String> findList(String dates) throws SQLException {
		String sql = "SELECT clientID "
				+ "FROM  (SELECT clientID " 
						+ "FROM season " 
						+ "WHERE seasonID = ?"
						+ "ORDER BY seasonDon) "
				+ "where rownum <= 3";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {dates});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<String> topList = new ArrayList<String>();	// User���� ����Ʈ ����
			while (rs.next()) {
				String clientId = rs.getString("clientID");
					topList.add(clientId);		
			}
			return topList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public int update(String dates, String clientId) throws SQLException {
		
		String sql = "UPDATE season "
				+ "SET seasonDon = (SELECT seasonDon FROM season WHERE clientId = ?) + (SELECT clientDon FROM client WHERE clientId = ?) "
				+ "WHERE seasonID = ? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId, clientId, dates});		// JDBCUtil�� query�� ����
					
		try {
			int result = jdbcUtil.executeUpdate();
			return result;				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return 0;
	}
	
public int reset(String dates) throws SQLException {
		
		String sql = "UPDATE season "
				+ "SET seasonDon = 0, seasonID = ? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {dates});		// JDBCUtil�� query�� ����
					
		try {
			int result = jdbcUtil.executeUpdate();
			return result;				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return 0;
	}
	
}
