package ge.pandora.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * Created by zviad on 5/28/16.
 */
public class CheckPointDistance implements Runnable{



    @Override
    public void run() {
        System.out.println("start============");
        StringBuilder sb=new StringBuilder("");
        try {
            Process process = Runtime.getRuntime().exec("sh /home/pi/install/shellscripts/check-points.sh ");
//            process.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            Long checkPointId=-1l;
            Double distance=-1d;
            String[] resultArr;

            DecimalFormat decimalFormat=new DecimalFormat("#.##");

            ThreadManager.checkPointCurrentId=0;
            while ((line = reader.readLine())!= null && ThreadManager.started) {
                sb.append(line + "\n");
                System.out.println(line);
                if(line!=null&&line.length()>0){
                resultArr=line.split(" ");
                 if(resultArr.length>1){
                     checkPointId=Long.parseLong(resultArr[0].trim());
                     distance=Double.parseDouble(resultArr[1].trim());
                 }

                    if(checkPointId==ThreadManager.checkPoindIds.get(ThreadManager.checkPointCurrentId)){
                        if(distance<500) {
                            ThreadManager.checkPointValues.set(ThreadManager.checkPointCurrentId, distance);
                            ThreadManager.checkPointCurrentId++;
                        }

                    };
                    if(ThreadManager.checkPointCurrentId>=ThreadManager.checkPoindIds.size() ){
                        break;

                    }


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
