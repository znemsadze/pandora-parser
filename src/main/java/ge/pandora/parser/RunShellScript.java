package ge.pandora.parser;

import ge.pandora.front.SpeedAngle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by zviad on 5/28/16.
 */
public class RunShellScript implements Runnable{



    @Override
    public void run() {
        System.out.println("start============");
        StringBuilder sb=new StringBuilder("");
        try {
            Process process = Runtime.getRuntime().exec("sh /home/pi/install/shellscripts/speed-angle.sh ");
//            process.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            Integer magInd=-1;
            Integer driftStart=-1;
            Double speedDouble=0d;

            DecimalFormat decimalFormat=new DecimalFormat("#.##");

            while ((line = reader.readLine())!= null && ThreadManager.started) {
                sb.append(line + "\n");
                ThreadManager.x++;
                if(line!=null && line.length()>20 && "Speed".equals(line.substring(0, 5))) {
                     magInd=line.indexOf("MAG");
                     driftStart=line.indexOf("drift=");
                     if(magInd>-1) {
                         speedDouble=Double.parseDouble(line.substring(9, magInd));
                         speedDouble=speedDouble*3.6;
                         ThreadManager.speed = decimalFormat.format(speedDouble);
                         ThreadManager.angle=(line.substring(driftStart+6,line.indexOf("("))).trim();
                         SpeedAngle speedAngle=new SpeedAngle();
                         speedAngle.setAngle(ThreadManager.angle);
                         speedAngle.setSpeed(ThreadManager.speed);
                         ThreadManager.speedAngles.add(speedAngle);
                     }
                    magInd=-1;
                    driftStart=-1;
                    System.out.println(line);
                }
            }
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("end=============");
    }
}
