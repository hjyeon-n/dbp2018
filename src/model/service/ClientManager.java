package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Animal;
import model.Client;
import model.dao.ClientDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
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
			throw new ExistingClientException(client.getClientID() + "는 존재하는 아이디입니다.");
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
			throw new ClientNotFoundException(clientId + "는 존재하지 않는 아이디입니다.");
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
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
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
