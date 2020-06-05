package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Animal;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * USERINFO ���̺� ����� ������ �߰�, ����, ����, �˻� ���� 
 */
public class AnimalDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public Animal findAnimal(String animalId) throws SQLException {
		String sql = "SELECT * "
				+ "FROM animal "
				+ "WHERE animalID = ? "
				+ "ORDER BY enterdate";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animalId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				Animal animal = new Animal(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("animalID"),
					rs.getString("animalName"),
					rs.getString("type"),
					rs.getString("age"),
					rs.getString("sex"),
					rs.getString("detail"),
					rs.getString("enterDate"),
					rs.getInt("state"),
					rs.getInt("shelterID"));
	
					return animal;			// List�� User ��ü ����
			}		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public int update(int animalID) throws SQLException {
		String sql = "UPDATE ANIMAL set state=1 where animalID=?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animalID});
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Animal> findAnimalList() throws SQLException {
		String sql = "SELECT animalID, animalName, sex, age, type "
				+ "FROM  (SELECT animalID, animalName, sex, age, type " 
						+ "FROM animal " 
						+ "ORDER BY enterdate) "
				+ "where rownum <= 3";

		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Animal> animalList = new ArrayList<Animal>();	// User���� ����Ʈ ����
			while (rs.next()) {
					Animal animal = new Animal(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getInt("animalID"),
							rs.getString("animalName"),
							rs.getString("type"),
							rs.getString("age"),
							rs.getString("sex"));
						animalList.add(animal);		
			}
			return animalList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public List<Animal> findAllAnimalList() throws SQLException {
		String sql = "SELECT animalID, animalName, sex, age, type "
				+ "FROM  animal "
				+ "ORDER BY enterdate";

		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Animal> animalList = new ArrayList<Animal>();	// User���� ����Ʈ ����
			while (rs.next()) {
					Animal animal = new Animal(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getInt("animalID"),
							rs.getString("animalName"),
							rs.getString("type"),
							rs.getString("age"),
							rs.getString("sex"));
						animalList.add(animal);		
			}
			return animalList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
	
	public List<Animal> findAnimalList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT animalID, animalName, sex, age, type "
				+ "FROM animal";
		
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<Animal> animalList = new ArrayList<Animal>();	// User���� ����Ʈ ����
				do {
					Animal animal = new Animal(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getInt("animalID"),
							rs.getString("animalName"),
							rs.getString("type"),
							rs.getString("age"),
							rs.getString("sex"));
						animalList.add(animal);									
				} while ((rs.next()) && (--countPerPage > 0));		
				return animalList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingAnimal(String animalID) throws SQLException {
		String sql = "SELECT count(*) FROM animal WHERE animalID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animalID});	// JDBCUtil�� query���� �Ű� ���� ����

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
