package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Animal;
import model.dao.AnimalDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
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
				throw new AnimalNotFoundException(animalID + "는 존재하지 않는 동물 아이디입니다.");
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