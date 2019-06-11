//package service;
//
//import models.CarPlace;
//import models.Zone;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
//class MyTask extends TimerTask
//{
//    public static int i = 0;
//    public void run()
//    {
//        ZoneServiceImpl zs = new ZoneServiceImpl();
//        for(Zone z : zs.getAll()) {
//            for (CarPlace cp :z.getPlaces()) {
//                Date date = new Date();
//                long time = date.getTime();//time to milisecundy
//                Timestamp ts = new Timestamp(time);
//                if (cp.getCurrentTicket().getEndTime().isAfter(LocalDateTime.now())) {
//
//                    //tutaj insertujemy event do bazy bo zaszło zdarzenie nieporzadane
//                    //tutaj wysyłamy topic powiadmiajacy o nowym evencie
//                }
//            }
//        }
//
//    }
//}
//
//public class IntervalTaskManager {
//
//    private static boolean isCalled = false;
//    private static int time = 30_000;;
//
//
//    public static void startIntervalTask(){
//        if(isCalled) return;
//        isCalled = true;
//        Timer timer = new Timer();
//        TimerTask task = new MyTask();
//
//        timer.schedule(task, 0, time);
//    }
//}
