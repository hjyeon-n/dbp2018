package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.Donation;
import model.Client;

public class DonationDAO {

   private JDBCUtil jdbcUtil = null;
   Connection conn;
   
   public DonationDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
      ConnectionManager cm = new ConnectionManager();
      conn = cm.getConnection();
   }
   
   public int create(String animalID, Client client) throws SQLException {
      SimpleDateFormat format = new SimpleDateFormat ("dd");
      Date date = new Date();            
      String time = format.format(date);

      String sql = "INSERT INTO Donation (animalID, clientID, totalDon, DonDate) VALUES (?, ?, ?, date) ";      
      Object[] param = new Object[] {animalID, client.getClientID(), client.getClientDon()};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
                  
      try {            
         int result = jdbcUtil.executeUpdate();   // insert 문 실행
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;         
   }
   
   public int update(String animalID) throws SQLException {
      String sql = "UPDATE ANIMAL "
               + "SET state=1 "
               + "WHERE animalID=?";
      Object[] param = new Object[] {animalID};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
      try {            
         int result = jdbcUtil.executeUpdate();   // update 문 실행
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;
   }
   
   /**
    * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
    */
   public boolean existingAnimal(String animalID) throws SQLException {
      String sql = "SELECT count(*) FROM donation WHERE animalID=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {animalID});   // JDBCUtil에 query문과 매개 변수 설정

      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query 실행
         if (rs.next()) {
            int count = rs.getInt(1);
            return (count == 1 ? true : false);
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource 반환
      }
      return false;
   }
   
   //해당 동물을 후원하고 있는 사용자 찾기
   public Donation findClient(String animalID) throws SQLException {
      String sql = "SELECT clientID FROM donation WHERE animalID=?";
      jdbcUtil.setSqlAndParameters(sql, new Object[] {animalID});
      
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         if(rs.next()) {
            Donation donation = new Donation(
                  rs.getString("clientID")
                  );
            return donation;
         }
      } catch(Exception e) {
         e.printStackTrace();
      } finally {
         jdbcUtil.close();
      }
      return null;
   }
   
   public int updateTerm() throws SQLException {
      String sql = "UPDATE client "
               + "SET DonTerm=DonTerm+1, flag=1 "
               + "WHERE clientID in ("
               + "select clientID "
               + "from donation) and flag=0";
                  
      jdbcUtil.setSqlAndParameters(sql, null);
      try {            
         int result = jdbcUtil.executeUpdate();   // update 문 실행   
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;
   }
   
   public int totalDonUpdate(String date) {
      String sql;
      int result = 0;
      
      try {
         List<Integer> DonList = new ArrayList<Integer>();
         List<Integer> NumList = new ArrayList<Integer>();
         
         DonList = selectDon(date);
         NumList = selectAnimalNum(date);
         
         for (int i = 0; i < DonList.size(); i++) {
        	 int selectDon = DonList.get(i);
        	 int selectNum = NumList.get(i);
        	 
        	 System.out.println(selectDon);
        	 System.out.println(selectNum);
        	
        	 sql = "UPDATE donation " + 
        	 		"set totalDon = totalDon + (? * ?) " + 
        	 		"where clientID IN (SELECT clientID " + 
        	 		"from donation " + 
        	 		"WHERE DonDate=?)";
        	 
        	 Object[] param = new Object[] {selectDon, selectNum, date}; 
        	 jdbcUtil.setSqlAndParameters(sql, param);
        	
        	 result = jdbcUtil.executeUpdate();
         }
         return result;
      } catch(Exception e) {
    	  jdbcUtil.rollback();
    	  e.printStackTrace();
      } finally {
    	  jdbcUtil.commit();
          jdbcUtil.close(); 
      }
      return result;
   }
   
   public List<Integer> selectDon(String date) throws SQLException {
		String sql = "SELECT clientDon "
					+ "FROM client "
					+ "WHERE clientID IN (SELECT clientID "
					+ "from donation " 
					+ "WHERE DonDate=?)";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {date});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Integer> donList = new ArrayList<Integer>();	// User들의 리스트 생성
			while (rs.next()) {
					System.out.println("don " + rs.getInt("clientDon"));
					donList.add(rs.getInt("clientDon"));
			}
			return donList;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
   
   public List<Integer> selectAnimalNum(String date) throws SQLException { 		
 		String sql = "SELECT count(animalID) AS a " 
 					+ "FROM donation " 
 					+ "WHERE DonDate=? and clientID IN (SELECT clientID "
 					+ "from donation "
 					+ "WHERE DonDate=?) "
 					+ "group by clientID";

 		Object[] param = new Object[] {date, date}; 
 		jdbcUtil.setSqlAndParameters(sql, param);		// JDBCUtil에 query문 설정
 					
 		try {
 			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
 			List<Integer> numList = new ArrayList<Integer>();	// User들의 리스트 생성
 			while (rs.next()) {
 					int certain = rs.getInt("a");
 					numList.add(rs.getInt("a"));
 					System.out.println("num = " + certain);
 			}
 			return numList;					
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			jdbcUtil.close();		// resource 반환
 		}
 		return null;
 	}
}