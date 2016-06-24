package ge.pandora.controller;

import ge.pandora.front.SpeedAngle;
import ge.pandora.parser.ThreadManager;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by zviad on 5/28/16.
 */
@RestController
@RequestMapping("/parser")
public class ParserController {

    @RequestMapping(value = "/hello/{param1}" ,method = RequestMethod.GET)
    public String sayHello(@PathVariable String param1 ){
        return  "hello"+param1;

    }
    @RequestMapping(value = "/hello1" ,method = RequestMethod.GET)
    public String sayHello( ){
        return  "hello";

    }

    @RequestMapping(value = "/hello2" ,method = RequestMethod.GET)
    public String sayHello( @RequestParam(value="name", required = false) String name,
                            @RequestParam(value="name", required = false) String lastname
                            ){


        return  "hello"+name+lastname;

    }





    @RequestMapping(value = "/x" ,method = RequestMethod.GET)
    public String getX(){
        return String.valueOf(ThreadManager.x);
    }

    @RequestMapping(value = "/speed" ,method = RequestMethod.GET)
    public String getSpeed(){
        if(ThreadManager.checkPointCurrentId!=null && ThreadManager.checkPointCurrentId>0) {
            return String.valueOf(ThreadManager.speed);
        }else{
            return "0";
        }
    }


    @RequestMapping(value = "/angle" ,method = RequestMethod.GET)
    public String getAndgle(){
        if(ThreadManager.checkPointCurrentId!=null && ThreadManager.checkPointCurrentId>0) {
            return String.valueOf(ThreadManager.angle);
        }else{
            return "0";
        }
    }


    @RequestMapping(value = "/checkpoints" ,method = RequestMethod.GET)
    public List<Double> getChekPoints(){
        return ThreadManager.checkPointValues;
    }


    @RequestMapping(value = "/checkpointids" ,method = RequestMethod.GET)
    public List<Long> getChekPointIds(){
        return ThreadManager.checkPoindIds;
    }

    @RequestMapping(value = "/speed-angles" ,method = RequestMethod.GET)
    public List<SpeedAngle> getSpeedAngle(){

        return ThreadManager.speedAngles;
    }


    @RequestMapping(value = "/speed-angles-avg" ,method = RequestMethod.GET)
    public  SpeedAngle  getSpeedAngleAvg(){
        SpeedAngle speedAngle=new SpeedAngle();
         speedAngle.setAngle("");
         speedAngle.setSpeed("");
        Double sp=0d,ng=0d;
        DecimalFormat decimalFormat=new DecimalFormat("#.##");
        if(ThreadManager.speedAngles!=null) {
            int i=0;
            for (SpeedAngle spAngle : ThreadManager.speedAngles) {
                i++;
                sp+=Double.parseDouble(spAngle.speed);
                ng+=Double.parseDouble(spAngle.angle);
            }
        speedAngle.setSpeed(decimalFormat.format(sp/i));
        speedAngle.setAngle(decimalFormat.format(ng/i));
        }
        return  speedAngle;
    }

    @RequestMapping(value = "/start" ,method = RequestMethod.GET)
    public String start(){
        try {
            ThreadManager.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return " started";
    }

    @RequestMapping(value = "/stop" ,method = RequestMethod.GET)
    public String stop(){

            ThreadManager.stop();

        return "stopped";
    }

}
