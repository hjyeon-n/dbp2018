package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Missing;

/**
 * �������� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * MISSINGANIMAL ���̺� �������� ������ �߰�, ����, ����, �˻� ���� 
 */
public class MissingDAO {
	private JDBCUtil jdbcUtil = null;
	
	public MissingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * MissingAnimal ���̺� ���ο� ����� ����.
	 */
	public int create(Missing missing) throws SQLException {
		String sql = "INSERT INTO MISSINGANIMAL VALUES (POSTID_SQ.nextval, ?, ?, ?, ?, ?, ?, ?) ";		
		Object[] param = new Object[] {missing.getClientId(), 
				missing.getMissingName(), missing.getMissingType(), missing.getMissingAddr(),
				missing.getMissingDate(), missing.getMissingDetail(), missing.getMissingImgPath()};				
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

	/**
	 * ������ �������� ������ ����.
	 */
	public int update(Missing missing) throws SQLException {
		String sql = "UPDATE MISSINGANIMAL "
					+ "SET missingName=?, missingType=?, missingAddr=?, missingDate=?, missingDetail=? "
					+ "WHERE postId=?";
		Object[] param = new Object[] {missing.getMissingName(), missing.getMissingType(), 
				missing.getMissingAddr(), missing.getMissingDate(), missing.getMissingDetail(),
				missing.getPostId()};				
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

	/**
	 * �������� postID�� �ش��ϴ� ���������� ����.
	 */
	public int remove(String postId) throws SQLException {
		String sql = "DELETE FROM MISSINGANIMAL WHERE postid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�� delete���� �Ű� ���� ����

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

	/**
	 * �־��� postID�� �ش��ϴ� �������� ������ �����ͺ��̽����� ã�� Missing ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public Missing findMissing(String postId) throws SQLException {
        String sql = "SELECT clientId, missingName, missingType, missingAddr, missingDate, missingDetail, missingImgPath "
        			+ "FROM MISSINGANIMAL "
        			+ "WHERE postId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �������� ���� �߰�
				Missing missing = new Missing(		// missing ��ü�� �����Ͽ� �������� ������ ����
					Integer.parseInt(postId),
					rs.getString("clientId"),
					rs.getString("missingName"),
					rs.getString("missingType"),
					rs.getString("missingAddr"),
					rs.getString("missingDate"),					
					rs.getString("missingDetail"),
					rs.getString("missingImgPath"));
				return missing;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	/**
	 * ��ü �������� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Missing> findMissingList() throws SQLException {
		
        String sql = "SELECT postId, clientId, missingName, missingType, missingAddr, missingDate, missingDetail, missingImgPath " 
        		   + "FROM MISSINGANIMAL "
        		   + "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Missing> missingList = new ArrayList<Missing>();	// Missing���� ����Ʈ ����
			while (rs.next()) {
				Missing missing = new Missing(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("postId"),
						rs.getString("clientId"),
						rs.getString("missingName"),
						rs.getString("missingType"),
						rs.getString("missingAddr"),
						rs.getString("missingDate"),					
						rs.getString("missingDetail"),
						rs.getString("missingImgPath"));
				missingList.add(missing);				// List�� Missing ��ü ����
			}		
			return missingList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü �������� ������ �˻��� �� ���� �������� �������� ����� �������� ���� �̿��Ͽ�
	 * �ش��ϴ� �������� �������� List�� �����Ͽ� ��ȯ.
	 */
	public List<Missing> findMissingList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT postId, clientId, missingName, missingType, missingAddr, missingDate, missingDetail, missingImgPath " 
     		   + "FROM MISSINGANIMAL "
     		   + "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<Missing> missingList = new ArrayList<Missing>();	// Missing���� ����Ʈ ����
				do {
					Missing missing = new Missing(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getInt("postId"),
							rs.getString("clientId"),
							rs.getString("missingName"),
							rs.getString("missingType"),
							rs.getString("missingAddr"),
							rs.getString("missingDate"),					
							rs.getString("missingDetail"),
							rs.getString("missingImgPath"));
					missingList.add(missing);				// List�� Missing ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return missingList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

}