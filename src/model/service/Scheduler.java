package model.service;

import java.util.Calendar;
import java.util.Timer;

public class Scheduler {

   public static void main() {
      // TODO Auto-generated method stub
      Timer m_timer = new Timer(); // Ÿ�̸��� ��� ����
      Calendar cal = Calendar.getInstance();
                  
      Task m_task = new Task();
   
      m_timer.schedule(m_task, cal.getTime(), 1000 * 60 * 60 * 24);
   }
}