package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Missing;
import model.dao.MissingDAO;

/**
 * 실종동물 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * MissingDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class MissingManager {
	private static MissingManager missingMan = new MissingManager();
	private MissingDAO missingDAO;

	private MissingManager() {
		try {
			missingDAO = new MissingDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MissingManager getInstance() {
		return missingMan;
	}
	
	public int create(Missing missing) throws SQLException, ExistingUserException {
		return missingDAO.create(missing);
	}

	public int remove(String postId) throws SQLException, ClientNotFoundException {
		return missingDAO.remove(postId);
	}

	public Missing findMissing(String postId)
		throws SQLException, MissingNotFoundException {
		Missing missing = missingDAO.findMissing(postId);
		
		if (missing == null) {
			throw new MissingNotFoundException(postId + "는 존재하지 않는 포스트 번호입니다.");
		}		
		return missing;
	}

	public List<Missing> findMissingList() throws SQLException {
			return missingDAO.findMissingList();
	}
	
	public List<Missing> findMissingList(int currentPage, int countPerPage)
		throws SQLException {
		return missingDAO.findMissingList(currentPage, countPerPage);
	}

	public MissingDAO getMissingDAO() {
		return this.missingDAO;
	}
}
