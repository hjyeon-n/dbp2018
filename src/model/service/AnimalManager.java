package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Animal;
import model.dao.AnimalDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class AnimalManager {
	private static AnimalManager animalMan = new AnimalManager();
	private AnimalDAO AnimalDAO;

	private AnimalManager() {
		try {
			AnimalDAO = new AnimalDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static AnimalManager getInstance() {
		return animalMan;
	}
	
	public int update(int animalID) throws SQLException {
		return AnimalDAO.update(animalID);
	}
	
	public Animal findAnimal(String animalID) throws SQLException, AnimalNotFoundException {
			Animal animal = AnimalDAO.findAnimal(animalID);
			
			if (animal == null) {
				throw new AnimalNotFoundException(animalID + "�� �������� �ʴ� ���� ���̵��Դϴ�.");
			}		
			return animal;
	}
	
	public List<Animal> findAnimalList(int currentPage, int countPerPage) throws SQLException {
		return AnimalDAO.findAnimalList(currentPage, countPerPage);
	}
	public List<Animal> findAnimalList() throws SQLException {
		return AnimalDAO.findAnimalList();
	}
	public List<Animal> findAllAnimalList() throws SQLException {
		return AnimalDAO.findAllAnimalList();
	}
}