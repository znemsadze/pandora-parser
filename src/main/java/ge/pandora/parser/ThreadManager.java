package ge.pandora.parser;

import ge.pandora.front.SpeedAngle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by zviad on 5/28/16.
 */
public class ThreadManager {


    public static Long x;

    public static String speed;
    public static String angle;
    public static Integer checkPointCurrentId;

    public static List<Long> checkPoindIds;
    public static List<Double> checkPointValues;

    public static List<SpeedAngle> speedAngles;

    public static boolean started = false;


    private static ExecutorService pool =null;


    public static void start() throws InterruptedException {
        checkPoindIds=new ArrayList<Long>();
        checkPoindIds.add(1l);
        checkPoindIds.add(2l);
        checkPoindIds.add(3l);
        checkPoindIds.add(2l);
        checkPoindIds.add(3l);
        checkPointValues=new ArrayList<Double>();
        checkPointValues.add(-1d);
        checkPointValues.add(-1d);
        checkPointValues.add(-1d);
        checkPointValues.add(-1d);
        checkPointValues.add(-1d);

        x = 0l;
        speedAngles=new ArrayList<SpeedAngle>();
//        SpeedAngle speedAngle=new SpeedAngle();
//        speedAngle.setAngle("343.24");
//        speedAngle.setSpeed("23.33");
//        ThreadManager.speedAngles.add(speedAngle);
//        speedAngle=new SpeedAngle();
//        speedAngle.setAngle("344.24");
//        speedAngle.setSpeed("333.33");
//        ThreadManager.speedAngles.add(speedAngle);
        pool=Executors.newFixedThreadPool(5,
                new ThreadFactory() {
                    public Thread newThread(Runnable runnable) {
                        Thread thread = new Thread(runnable);
                        thread.setName(String.format("Parse Shell Threaads%s",
                                thread.getId()));
                        return thread;
                    }
                });
        if (!started) {
            pool.submit(new RunShellScript());
            pool.submit(new CheckPointDistance());
            started = true;
        }
    }


    public static void stop(){
        started=false;
        pool.shutdownNow();

        System.out.println("shutdown all threads");

    }



    public static void main(String argv[]) {

        try {
            start();
            while (true) {
                Thread.sleep(5 * 1000l);
                System.out.println("1 hthread is started");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
