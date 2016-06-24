package ge.pandora.front;

import java.io.Serializable;

/**
 * Created by zviad on 5/28/16.
 */
public class SpeedAngle implements Serializable {

    public String speed;
    public String angle;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }
}
