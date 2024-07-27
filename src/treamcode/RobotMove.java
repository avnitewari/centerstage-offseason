package treamcode;
import static com.company.Robot.*;
import static RobotUtilities.MovementVars.*;
import java.util.ArrayList;
import org.opencv.core.Point;
import com.company.ComputerDebugging;
import com.company.FloatPoint;
import com.company.Range;

public class RobotMove {

    public static void followCurve(ArrayList<curvePoint> allPoints, double followAngle) {
        for (int i = 0; i < allPoints.size() - 1; i++) {
            ComputerDebugging.sendLine(new FloatPoint(allPoints.get(i).x, allPoints.get(i).y),
                    new FloatPoint(allPoints.get(i + 1).x, allPoints.get(i + 1).y));
        }
        curvePoint followMe = getFollowPointPath(allPoints, new Point(worldXPosition, worldYPosition), allPoints.get(0).followDistance);
        ComputerDebugging.sendKeyPoint(new FloatPoint(followMe.x, followMe.y));
        goToPosition(followMe.x, followMe.y, followMe.moveSpeed, followAngle, followMe.turnSpeed);
    }

    public static curvePoint getFollowPointPath(ArrayList<curvePoint> pathPoints, Point robotLocation, double followRadius) {
        curvePoint followMe = new curvePoint(pathPoints.get(0));

        for (int i = 0; i < pathPoints.size() - 1; i++) {
            curvePoint startLine = pathPoints.get(i);
            curvePoint endLine = pathPoints.get(i + 1);

            ArrayList<Point> intersections = mathFunctions.lineCircleIntersection(robotLocation, followRadius, startLine.toPoint(), endLine.toPoint());

            double closestAngle = 1000;

            for (Point thisIntersection : intersections) {
                double angle = Math.atan2(thisIntersection.y - worldYPosition, thisIntersection.x - worldXPosition);
                double deltaAngle = Math.abs(mathFunctions.angleWrap(angle - worldAngle_rad));
                if (deltaAngle < closestAngle) {
                    closestAngle = deltaAngle;
                    followMe.setPoint(thisIntersection);
                }
            }
        }
        return followMe;
    }

    public static void goToPosition(double x, double y, double moveSpeed, double preferredAngle, double turnSpeed) {
        double distanceToTarget = Math.hypot(x - worldXPosition, y - worldYPosition);
        double absAngleToTarget = Math.atan2(y - worldYPosition, x - worldXPosition);
        double relativeAngleToTarget = mathFunctions.angleWrap(absAngleToTarget - (worldAngle_rad - Math.toRadians(90)));

        double relativeX = Math.cos(relativeAngleToTarget) * distanceToTarget;
        double relativeY = Math.sin(relativeAngleToTarget) * distanceToTarget;

        double moveXPower = relativeX / (Math.abs(relativeX) + Math.abs(relativeY));
        double moveYPower = relativeY / (Math.abs(relativeX) + Math.abs(relativeY));

        movement_x = moveXPower * moveSpeed;
        movement_y = moveYPower * moveSpeed;

        double relativeTurnAngle = mathFunctions.angleWrap(relativeAngleToTarget - Math.toRadians(180) + preferredAngle);
        movement_turn = Range.clip(relativeTurnAngle / Math.toRadians(30), -1, 1) * turnSpeed;

        if (distanceToTarget < 10) {
            movement_turn = 0;
        }
    }
}