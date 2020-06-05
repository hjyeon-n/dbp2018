package model.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class Task extends TimerTask{
   @Override
   public void run() {
      DonationManager dmanager = DonationManager.getInstance();
      
      SimpleDateFormat format = new SimpleDateFormat ("dd");
      SimpleDateFormat fmon = new SimpleDateFormat ("MM");
      SimpleDateFormat fyymm = new SimpleDateFormat ("yyMM");
      
      Date date = new Date();            
      
      String time = format.format(date);
      String month = fmon.format(date);
      String syymm = fyymm.format(date);
      
      AdoptionCareManager manager = AdoptionCareManager.getInstance();
      AnimalManager amanager = AnimalManager.getInstance();
						//careTerm update
      try {
			manager.updateCareTerm();
      } catch (SQLException e) {
			e.printStackTrace();
      }
		
	 //careTerm remove(�ӽ� ��ȣ ����)
      try {
    	  int animalID = manager.findAnimalID(); //�ӽ� ��ȣ�� ���� ����ID ã��
    	  manager.remove(); //�ӽ� ��ȣ�� ���� ���� adoption_care ���̺��� ����
    	  amanager.update(animalID); //�ӽ� ��ȣ�� ���� ������ ���¸� Animal ���̺��� �Ŀ� ����(1)�� ����
      } catch (SQLException e) {
			e.printStackTrace();
      }
      
//      totalDonUpdate
      try {
         dmanager.totalDonUpdate(time);
         dmanager.updateTerm();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      SeasonManager sMan = SeasonManager.getInstance();
      ClientManager cMan = ClientManager.getInstance();
      
//      grade update
      try {
    	  cMan.grade1to2();
      } catch (SQLException e) {
    	  e.printStackTrace();
      }
      
      try {
    	  cMan.grade2to3();
      } catch (SQLException e) {
    	  e.printStackTrace();
      }
      
      if(time.equals("01")&&(month.equals("04") || month.equals("07") || month.equals("10") ||month.equals("01"))){
    	  try {
      		sMan.reset(syymm);
      	  } catch (SQLException e) {
      		e.printStackTrace();
      	  }
      }

      if(time.equals("01")) {
    	  try {
    		  cMan.initFlag();
    		  List<String> clientList = cMan.findClientList();
    		  for(String id : clientList) {
    			  sMan.update(id, syymm);
    		  }
    	  } catch (SQLException e) {
    		  e.printStackTrace();
    	  }
      }
   }
}