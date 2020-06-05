package model.service;

import java.sql.SQLException;
import model.dao.AdoptionCareDAO;

public class AdoptionCareManager {
	private static AdoptionCareManager adoptioncareMan = new AdoptionCareManager();
	private AdoptionCareDAO adoptioncareDAO;

	private AdoptionCareManager() {
		try {
			adoptioncareDAO = new AdoptionCareDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static AdoptionCareManager getInstance() {
		return adoptioncareMan;
	}
	
	public int create(String clientId, int animalId, String careTerm, String state) throws SQLException, ExistingClientException {
		return adoptioncareDAO.create(clientId, animalId, careTerm, state);
	}

	//����
	public int remove() throws SQLException {
		return adoptioncareDAO.remove();
	}
	public boolean findState(int animalId) throws SQLException {
		return adoptioncareDAO.findState(animalId);
	}

	public int update(String state, int animalId) throws SQLException {
		return adoptioncareDAO.update(state, animalId);
	}
	
	public int dupdate(String state, int animalId) throws SQLException {
		return adoptioncareDAO.dupdate(state, animalId);
	}
	
	public AdoptionCareDAO getAdoptionCareDAO() {
		return this.adoptioncareDAO;
	}
	
	//�Ŵ� careTerm �پ���
	public int updateCareTerm() throws SQLException {
		return adoptioncareDAO.updateCareTerm();
	}
	
	//�ӽ� ��ȣ�� ���� ����ID ã��
	public int findAnimalID() throws SQLException {
		return adoptioncareDAO.findAnimalID();
	}
}
