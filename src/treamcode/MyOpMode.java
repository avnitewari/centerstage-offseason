package treamcode;
import java.util.ArrayList;

public abstract class MyOpMode {
    public abstract void init();

    public void loop() {
        ArrayList<curvePoint> allPoints = new ArrayList<>();
        allPoints.add(new curvePoint(0, 0, 1.0, 1.0, 50, 0, 0, Math.toRadians(50)));
        RobotMove.followCurve(allPoints, Math.toRadians(90));
    }
}