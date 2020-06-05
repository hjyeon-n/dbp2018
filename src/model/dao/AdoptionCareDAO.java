package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdoptionCareDAO {
private JDBCUtil jdbcUtil = null;
	
	public AdoptionCareDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	public int create(String clientId, int animalId, String careTerm, String state) throws SQLException {
		String sql = "INSERT INTO ADOPTION_CARE (clientID, animalID, startCare, careTerm, state) "
					+ "values(?, ?, sysdate, ?, ?)";
		Object[] param = new Object[] {clientId, animalId, careTerm, state}; 
			
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

	public int update(String state, int animalId) throws SQLException {
		String sql = "UPDATE ANIMAL set state=? where animalID=? ";
		
		Object[] param = new Object[] {Integer.valueOf(state), Integer.valueOf(animalId)};
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	public int dupdate(String state, int animalId) throws SQLException {
		String sql = "UPDATE ADOPTION_CARE set state=? where animalID=?";
		
		Object[] param = new Object[] {Integer.valueOf(state), Integer.valueOf(animalId)};
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	
	
	public boolean findState(int animalId) throws SQLException {
		String sql = "SELECT state from ADOPTION_CARE where animalID=?";
		
		Object[] param = new Object[] {animalId};
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				String state = rs.getString("state");
				return false;
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return true;
	}
	
	public int remove() throws SQLException {
		String sql = "DELETE FROM ADOPTION_CARE where careTerm < 0";		
		jdbcUtil.setSqlAndParameters(sql, null);	// JDBCUtil에 delete문과 매개 변수 설정

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
	
	//매달 careTerm 줄어들기
	public int updateCareTerm() throws SQLException {
		String sql = "UPDATE ADOPTION_CARE SET careTerm=careTerm-1 "
				+ "WHERE to_char(sysdate,'dd')=to_char(ADD_MONTHS(startCare,1),'dd') " //오늘이 startCare로부터 딱 한 달이고
				+ "and to_char(sysdate,'mm')=to_char(startCare,'mm') " //오늘이 startCare가 아니고(careTerm이 3개월이기 때문에 1년 후는 고려 안 함)
				+ "and state=2"; //동물의 상태가 임시 보호일 경우

		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	//임시 보호가 끝난 동물ID 찾기
	public int findAnimalID() throws SQLException {
		String sql = "SELECT animalID FROM ADOPTION_CARE where careTerm < 0";
				
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				int animalID = rs.getInt("animalID");
				return animalID;
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return 0;
	}
}
