package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Missing;

/**
 * 실종동물 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * MISSINGANIMAL 테이블에 실종동물 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class MissingDAO {
	private JDBCUtil jdbcUtil = null;
	
	public MissingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * MissingAnimal 테이블에 새로운 사용자 생성.
	 */
	public int create(Missing missing) throws SQLException {
		String sql = "INSERT INTO MISSINGANIMAL VALUES (POSTID_SQ.nextval, ?, ?, ?, ?, ?, ?, ?) ";		
		Object[] param = new Object[] {missing.getClientId(), 
				missing.getMissingName(), missing.getMissingType(), missing.getMissingAddr(),
				missing.getMissingDate(), missing.getMissingDetail(), missing.getMissingImgPath()};				
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

	/**
	 * 기존의 실종동물 정보를 수정.
	 */
	public int update(Missing missing) throws SQLException {
		String sql = "UPDATE MISSINGANIMAL "
					+ "SET missingName=?, missingType=?, missingAddr=?, missingDate=?, missingDetail=? "
					+ "WHERE postId=?";
		Object[] param = new Object[] {missing.getMissingName(), missing.getMissingType(), 
				missing.getMissingAddr(), missing.getMissingDate(), missing.getMissingDetail(),
				missing.getPostId()};				
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

	/**
	 * 실종동물 postID에 해당하는 실종동물을 삭제.
	 */
	public int remove(String postId) throws SQLException {
		String sql = "DELETE FROM MISSINGANIMAL WHERE postid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil에 delete문과 매개 변수 설정

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

	/**
	 * 주어진 postID에 해당하는 실종동물 정보를 데이터베이스에서 찾아 Missing 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Missing findMissing(String postId) throws SQLException {
        String sql = "SELECT clientId, missingName, missingType, missingAddr, missingDate, missingDetail, missingImgPath "
        			+ "FROM MISSINGANIMAL "
        			+ "WHERE postId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 실종동물 정보 발견
				Missing missing = new Missing(		// missing 객체를 생성하여 실종동물 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 전체 실종동물 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Missing> findMissingList() throws SQLException {
		
        String sql = "SELECT postId, clientId, missingName, missingType, missingAddr, missingDate, missingDetail, missingImgPath " 
        		   + "FROM MISSINGANIMAL "
        		   + "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Missing> missingList = new ArrayList<Missing>();	// Missing들의 리스트 생성
			while (rs.next()) {
				Missing missing = new Missing(			// User 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("postId"),
						rs.getString("clientId"),
						rs.getString("missingName"),
						rs.getString("missingType"),
						rs.getString("missingAddr"),
						rs.getString("missingDate"),					
						rs.getString("missingDetail"),
						rs.getString("missingImgPath"));
				missingList.add(missing);				// List에 Missing 객체 저장
			}		
			return missingList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 실종동물 정보를 검색한 후 현재 페이지와 페이지당 출력할 실종동물 수를 이용하여
	 * 해당하는 실종동물 정보만을 List에 저장하여 반환.
	 */
	public List<Missing> findMissingList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT postId, clientId, missingName, missingType, missingAddr, missingDate, missingDetail, missingImgPath " 
     		   + "FROM MISSINGANIMAL "
     		   + "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<Missing> missingList = new ArrayList<Missing>();	// Missing들의 리스트 생성
				do {
					Missing missing = new Missing(			// User 객체를 생성하여 현재 행의 정보를 저장
							rs.getInt("postId"),
							rs.getString("clientId"),
							rs.getString("missingName"),
							rs.getString("missingType"),
							rs.getString("missingAddr"),
							rs.getString("missingDate"),					
							rs.getString("missingDetail"),
							rs.getString("missingImgPath"));
					missingList.add(missing);				// List에 Missing 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return missingList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

}