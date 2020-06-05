package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Animal;
import model.Client;
import model.dao.ClientDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class ClientManager {
	private static ClientManager clientMan = new ClientManager();
	private ClientDAO clientDAO;

	private ClientManager() {
		try {
			clientDAO = new ClientDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ClientManager getInstance() {
		return clientMan;
	}
	
	public int create(Client client) throws SQLException, ExistingClientException {
		if (clientDAO.existingClient(client.getClientID()) == true) {
			throw new ExistingClientException(client.getClientID() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		
		return clientDAO.create(client);
	}

	public int update(Client client) throws SQLException {
		return clientDAO.update(client);
	}	
	
	public int remove(String clientId) throws SQLException {
		return clientDAO.remove(clientId);
	}

	public Client findClient(String clientId)
		throws SQLException, ClientNotFoundException {
		Client client = clientDAO.findClient(clientId);
		
		if (client == null) {
			throw new ClientNotFoundException(clientId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return client;
	}
	
	public List<String> findClientList()
			throws SQLException {
			return clientDAO.findClientList();
		}
	
	public List<Animal> findDonationAnimals(String clientId)
		throws SQLException {
		return clientDAO.findDonationAnimals(clientId);
	}
	
	public List<Animal> findAdoptcareAnimals(String clientId)
		throws SQLException {
		return clientDAO.findAdoptCareAnimals(clientId);
	}

	public Client login(String clientId, String password)
		throws SQLException, ClientNotFoundException, PasswordMismatchException {
		Client client = findClient(clientId);

		if (!client.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return client;
	}

	public ClientDAO getClientDAO() {
		return this.clientDAO;
	}
	
	public int grade1to2() throws SQLException{
		return clientDAO.grade1to2();
	}
	
	public int grade2to3() throws SQLException{
		return clientDAO.grade2to3();
	}
	
	public int initFlag() throws SQLException {
		return clientDAO.initFlag();
	}
}
