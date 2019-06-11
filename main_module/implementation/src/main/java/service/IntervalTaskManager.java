package service;

import jms.service.JMSService;

import javax.ejb.EJB;
import java.util.Timer;
import java.util.TimerTask;


class MyTask extends TimerTask
{
    @EJB(lookup = "java:global/implementation-1.0-SNAPSHOT/JMSServiceImpl!jms.service.JMSService")
    JMSService topic;

    public static int i = 0;
    public void run()
    {


    }
}

public class IntervalTaskManager {

    private static boolean isCalled = false;
    private static int time = 30_000;;


    public static void startIntervalTask(){
        if(isCalled) return;
        isCalled = true;
        Timer timer = new Timer();
        TimerTask task = new MyTask();

        timer.schedule(task, 0, time);
    }
}
