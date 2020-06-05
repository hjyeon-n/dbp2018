package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdoptionCareDAO {
private JDBCUtil jdbcUtil = null;
	
	public AdoptionCareDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	public int create(String clientId, int animalId, String careTerm, String state) throws SQLException {
		String sql = "INSERT INTO ADOPTION_CARE (clientID, animalID, startCare, careTerm, state) "
					+ "values(?, ?, sysdate, ?, ?)";
		Object[] param = new Object[] {clientId, animalId, careTerm, state}; 
			
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
		jdbcUtil.setSqlAndParameters(sql, null);	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
	//�Ŵ� careTerm �پ���
	public int updateCareTerm() throws SQLException {
		String sql = "UPDATE ADOPTION_CARE SET careTerm=careTerm-1 "
				+ "WHERE to_char(sysdate,'dd')=to_char(ADD_MONTHS(startCare,1),'dd') " //������ startCare�κ��� �� �� ���̰�
				+ "and to_char(sysdate,'mm')=to_char(startCare,'mm') " //������ startCare�� �ƴϰ�(careTerm�� 3�����̱� ������ 1�� �Ĵ� ��� �� ��)
				+ "and state=2"; //������ ���°� �ӽ� ��ȣ�� ���

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
	
	//�ӽ� ��ȣ�� ���� ����ID ã��
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
