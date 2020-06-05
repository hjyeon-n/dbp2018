package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Missing;
import model.dao.MissingDAO;

/**
 * �������� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * MissingDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
			throw new MissingNotFoundException(postId + "�� �������� �ʴ� ����Ʈ ��ȣ�Դϴ�.");
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
