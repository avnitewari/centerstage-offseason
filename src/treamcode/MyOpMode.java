package treamcode;
import java.lang.reflect.Array;
import java.util.ArrayList;

import RobotUtilities.MovementVars;

public abstract class OpMode {

    public abstract void init();
    public abstract void loop(){
        ArrayList<curvePoint> allPoints = new ArrayList<>();
        allPoints.add(new curvePoint(0,0,1.0,50,Math.toRadians(50)));
        followCurve(allPoints, Math.toRadians(90));
    }
}
