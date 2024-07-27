package Main;

import com.company.ComputerDebugging;
import com.company.FloatPoint;
import com.company.Robot;

import treamcode.ConcreteOpMode; // Import the concrete subclass

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * The program runs here
     */
    public void run() {
        ComputerDebugging computerDebugging = new ComputerDebugging();
        Robot robot = new Robot();
        ConcreteOpMode opMode = new ConcreteOpMode(); // Use the concrete subclass
        opMode.init();

        ComputerDebugging.clearLogPoints();

        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            opMode.loop();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.update();
            ComputerDebugging.sendRobotLocation(robot);
            ComputerDebugging.sendLogPoint(new FloatPoint(Robot.worldXPosition, Robot.worldYPosition));
            ComputerDebugging.markEndOfUpdate();
        }
    }
}