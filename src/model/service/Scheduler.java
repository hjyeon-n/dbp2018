package model.service;

import java.util.Calendar;
import java.util.Timer;

public class Scheduler {

   public static void main() {
      // TODO Auto-generated method stub
      Timer m_timer = new Timer(); // 타이머의 기능 수행
      Calendar cal = Calendar.getInstance();
                  
      Task m_task = new Task();
   
      m_timer.schedule(m_task, cal.getTime(), 1000 * 60 * 60 * 24);
   }
}