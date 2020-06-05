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

	//삭제
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
	
	//매달 careTerm 줄어들기
	public int updateCareTerm() throws SQLException {
		return adoptioncareDAO.updateCareTerm();
	}
	
	//임시 보호가 끝난 동물ID 찾기
	public int findAnimalID() throws SQLException {
		return adoptioncareDAO.findAnimalID();
	}
}
