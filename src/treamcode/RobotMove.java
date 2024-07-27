package treamcode;
import static com.company.Robot.*;
import static RobotUtilities.MovementVars.movement_turn;
import static treamcode.mathFunctions.angleWrap;
import static treamcode.mathFunctions.lineCircleIntersections;
import static RobotUtilities.MovementVars.movement_x;
import static RobotUtilities.MovementVars.movement_y;
import java.util.ArrayList;
import org.opencv.core.Point;

import com.company.ComputerDebugging;
import com.company.FloatPoint;
import com.company.Range;

import org.opencv.core.Point;


public class RobotMove {

    public static void followCurve(ArrayList<curvePoint> allpoints, double followAngle){
        for(int i = 0; i < allpoints.size() - 1; i++ ){
            ComputerDebugging.sendLine(new FloatPoint())
        }
    }

    public static curvePoint getFollowPointPath(ArrayList<curvePoint> pathPoints, Point robotLocation, double followRadius){
        curvePoint followMe = new curvePoint(pathPoints.get(0));

        for (int i = 0; i < pathPoints.size() - 1; i++){
            curvePoint startLine = pathPoints.get(i);
            curvePoint endLine = pathPoints.get(i+1);

            ArrayList<Point> intersection = lineCircleIntersection(robotLocation, followRadius, startLine.toPoint(), endLine.toPoint());

            double closestAngle = 1000;

            for(thisIntersection : intersections){
                double angle = Math.atan2(thisIntersection.y - worldYPosition, thisIntersection.x - worldXPosition)
                double deltaAngle = Math.abs(mathFunctions.angleWrap(angle - worldAngle_rad));
                        if (deltaAngle < closestAngle) {
                            closestAngle = deltaAngle;
                            followMe.setPoint(thisIntersection);
                        }
            }
        }
        return followMe;

    }



    public static void robotMovement(double x, double y, double speed, double preferredAngle){
        double distanceToTarget = Math.hypot(x-worldXPosition, y-worldYPosition)
        double absAngleToTarget = Math.atan2(worldXPosition, worldYPosition);
        double relativeToTarget = angleWrap(absAngleToTarget - (worldAngle_rad - Math.toRadians(90)));

        double relativeX = Math.cos(relativeToTarget) * distanceToTarget;
        double relativeY = Math.sin(relativeToTarget) * distanceToTarget;

        double moveXPower = relativeX / Math.abs(relativeX) + Math.abs(relativeY);
        double moveYPower = relativeY / Math.abs(relativeY) + Math.abs(relativeX);

        movement_x = moveXPower * speed;
        movement_y = moveXPower * speed;

        double relativeTurnAngle = relativeToTarget - Math.toRadians(180) + preferredAngle;
        movement_turn = Range.clip(relativeTurnAngle / Math.toRadians(30), -1, 1) * turnSpeed;
         if (distanceToTarget < 10){
             movement_turn = 0;
         }

    }
}
