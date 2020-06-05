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
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.(ȸ������)
	 */
	public int create(Client client) throws SQLException {
		String sql = "INSERT INTO CLIENT (clientID, clientGrade, clientDon, clientTel, "
				+ "clientAddr, isBlackList, donTerm, clientPW, clientAccount, clientName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {client.getClientID(), "ļ��", client.getClientDon(),  client.getClientTel(), 
				client.getClientAddr(), "0", 0, client.getClientPW(), client.getClientAccount(), client.getClientName()}; 
			
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
	 * ������ ����� ������ ����.(ȸ����������)
	 */
	public int update(Client client) throws SQLException {
		String sql = "UPDATE CLIENT "
					+ "SET clientName=?, clientPW=?, clientTel=?, clientAddr=?, clientDon=?, clientAccount=? "
					+ "WHERE clientID=?";
		Object[] param = new Object[] {client.getClientName(), client.getClientPW(), client.getClientTel(), 
				client.getClientAddr(), client.getClientDon(),client.getClientAccount(), client.getClientID()};				
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
	
	public int grade1to2() throws SQLException {
		String sql = "UPDATE CLIENT "
				+ "SET clientGrade=\'����\' "
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
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	public int grade2to3() throws SQLException {
		String sql = "UPDATE CLIENT "
				+ "SET clientGrade='�ֿ�' "
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
			jdbcUtil.close();	// resource ��ȯ
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
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	 
	public int remove(String clientId) throws SQLException {
		String sql = "DELETE FROM CLIENT WHERE clientID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� Client ������ Ŭ������ 
	 * �����Ͽ� ��ȯ. (����������)
	 */
	public Client findClient(String clientId) throws SQLException {
        String sql = "SELECT clientID, clientPW, clientName, clientGrade, clientTel, clientAddr, "
        			+ "clientDon, clientAccount "
        			+ "FROM CLIENT WHERE clientID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
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
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
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
	
	//���������� - �Ŀ� ���� ����
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	//���������� - �Ծ�, �Ӻ� ���� ����
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingClient(String clientId) throws SQLException {
		String sql = "SELECT count(*) FROM CLIENT WHERE clientID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clientId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
}
