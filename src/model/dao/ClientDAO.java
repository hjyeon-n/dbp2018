package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Animal;
import model.Client;

public class ClientDAO {
private JDBCUtil jdbcUtil = null;
	
	public ClientDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.(회원가입)
	 */
	public int create(Client client) throws SQLException {
		String sql = "INSERT INTO CLIENT (clientID, clientGrade, clientDon, clientTel, "
				+ "clientAddr, isBlackList, donTerm, clientPW, clientAccount, clientName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {client.getClientID(), "캬악", client.getClientDon(),  client.getClientTel(), 
				client.getClientAddr(), "0", 0, client.getClientPW(), client.getClientAccount(), client.getClientName()}; 
			
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
	 * 기존의 사용자 정보를 수정.(회원정보변경)
	 */
	public int update(Client client) throws SQLException {
		String sql = "UPDATE CLIENT "
					+ "SET clientName=?, clientPW=?, clientTel=?, clientAddr=?, clientDon=?, clientAccount=? "
					+ "WHERE clientID=?";
		Object[] param = new Object[] {client.getClientName(), client.getClientPW(), client.getClientTel(), 
				client.getClientAddr(), client.getClientDon(),client.getClientAccount(), client.getClientID()};				
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
	
	public int grade1to2() throws SQLException {
		String sql = "UPDATE CLIENT "
				+ "SET clientGrade=\'웨옹\' "
				+ "where donTerm >= 6 and donTerm < 9";
		jdbcUtil.setSqlAndParameters(sql, null);
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("result = " + result);
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
	
	public int grade2to3() throws SQLException {
		String sql = "UPDATE CLIENT "
				+ "SET clientGrade='애옹' "
				+ "where donTerm >= 9";
		jdbcUtil.setSqlAndParameters(sql, null);
		try {
			int result = jdbcUtil.executeUpdate();
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
	
	public int initFlag() throws SQLException {
		String sql = "UPDATE CLIENT "
				+ "SET Flag = 0";
		jdbcUtil.setSqlAndParameters(sql, null);
		try {
			int result = jdbcUtil.executeUpdate();
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
	 
	public int remove(String clientId) throws SQLException {
		String sql = "DELETE FROM CLIENT WHERE clientID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});	// JDBCUtil에 delete문과 매개 변수 설정

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
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 Client 도메인 클래스에 
	 * 저장하여 반환. (마이페이지)
	 */
	public Client findClient(String clientId) throws SQLException {
        String sql = "SELECT clientID, clientPW, clientName, clientGrade, clientTel, clientAddr, "
        			+ "clientDon, clientAccount "
        			+ "FROM CLIENT WHERE clientID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						
				Client client = new Client(		
					rs.getString("clientID"),
					rs.getString("clientPW"),
					rs.getString("clientName"),
					rs.getString("clientGrade"),
					rs.getInt("clientDon"),
					rs.getString("clientAccount"),
					rs.getString("clientTel"),
					rs.getString("clientAddr"));
				return client;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<String> findClientList() throws SQLException {
        String sql = "SELECT clientID"
        			+ "FROM CLIENT";              

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {					
				List<String> clientList = new ArrayList<String>();
				while(rs.next()) {
					clientList.add(rs.getString("clientID"));
				}
				return clientList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//마이페이지 - 후원 중인 동물
	public List<Animal> findDonationAnimals(String clientId) throws SQLException {
		String sql = "SELECT animalName, animalID, state "
				+ "FROM ANIMAL join DONATION using(animalID) where clientID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Animal> donationAnimals = new ArrayList<Animal>();
			while(rs.next()) {
				Animal animal = new Animal();
				animal.setAnimalID(rs.getInt("animalID"));
				animal.setAnimalName(rs.getString("animalName"));
				animal.setState(rs.getInt("state"));
				donationAnimals.add(animal);
			}
			return donationAnimals;
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	//마이페이지 - 입양, 임보 중인 동물
	public List<Animal> findAdoptCareAnimals(String clientId) throws SQLException {
		String sql = "SELECT animalName, animalID, state "
				+ "FROM ANIMAL join ADOPTION_CARE using(animalID) where clientID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Animal> adoptcareAnimals = new ArrayList<Animal>();
			while(rs.next()) {
				Animal animal = new Animal();
				animal.setAnimalID(rs.getInt("animalID"));
				animal.setAnimalName(rs.getString("animalName"));
				animal.setState(rs.getInt("state"));
				adoptcareAnimals.add(animal);
			}
			return adoptcareAnimals;
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingClient(String clientId) throws SQLException {
		String sql = "SELECT count(*) FROM CLIENT WHERE clientID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
}
