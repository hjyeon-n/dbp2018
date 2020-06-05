package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Animal;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class AnimalDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Animal findAnimal(String animalId) throws SQLException {
		String sql = "SELECT * "
				+ "FROM animal "
				+ "WHERE animalID = ? "
				+ "ORDER BY enterdate";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animalId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				Animal animal = new Animal(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getInt("animalID"),
					rs.getString("animalName"),
					rs.getString("type"),
					rs.getString("age"),
					rs.getString("sex"),
					rs.getString("detail"),
					rs.getString("enterDate"),
					rs.getInt("state"),
					rs.getInt("shelterID"));
	
					return animal;			// List에 User 객체 저장
			}		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
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
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Animal> findAnimalList() throws SQLException {
		String sql = "SELECT animalID, animalName, sex, age, type "
				+ "FROM  (SELECT animalID, animalName, sex, age, type " 
						+ "FROM animal " 
						+ "ORDER BY enterdate) "
				+ "where rownum <= 3";

		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Animal> animalList = new ArrayList<Animal>();	// User들의 리스트 생성
			while (rs.next()) {
					Animal animal = new Animal(			// User 객체를 생성하여 현재 행의 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<Animal> findAllAnimalList() throws SQLException {
		String sql = "SELECT animalID, animalName, sex, age, type "
				+ "FROM  animal "
				+ "ORDER BY enterdate";

		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Animal> animalList = new ArrayList<Animal>();	// User들의 리스트 생성
			while (rs.next()) {
					Animal animal = new Animal(			// User 객체를 생성하여 현재 행의 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	
	public List<Animal> findAnimalList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT animalID, animalName, sex, age, type "
				+ "FROM animal";
		
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<Animal> animalList = new ArrayList<Animal>();	// User들의 리스트 생성
				do {
					Animal animal = new Animal(			// User 객체를 생성하여 현재 행의 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingAnimal(String animalID) throws SQLException {
		String sql = "SELECT count(*) FROM animal WHERE animalID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animalID});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
}
