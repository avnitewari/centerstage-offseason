package treamcode;
import RobotUtilities.MovementVars;

public abstract class OpMode {

    public abstract void init();
    public abstract void loop(){
        RobotMove.robotMovement(0,0,0.5,Math.toRadians(45));
    }
}
