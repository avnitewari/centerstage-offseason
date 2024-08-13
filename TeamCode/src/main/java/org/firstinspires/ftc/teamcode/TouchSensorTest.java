package org.firstinspires.ftc.teamcode;

// FTC SDK classes
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

// Define this OpMode as TelOp and give it a name - type of op mode
@TeleOp(name = "Touch Sensor Test")
@SuppressWarnings("unused")
public class TouchSensorTest extends OpMode {

    // Declare the touch sensor
    private TouchSensor touchSensor;

    // This runs once when the OpMode is initialized
    @Override
    public void init() {
        // Get the touch sensor from the hardware map
        touchSensor = hardwareMap.get(TouchSensor.class, "touch_sensor");
    }

    // This method runs repeatedly after the play button is pressed
    @Override
    public void loop() {
        // Check if the touch sensor is pressed
        if (touchSensor.isPressed()) {
            // If pressed, add "Pressed" to telemetry
            telemetry.addData("Touch Sensor", "Pressed");
        } else {
            // If not pressed, add "Not Pressed" to telemetry
            telemetry.addData("Touch Sensor", "Not Pressed");
        }

        // Update the telemetry display
        telemetry.update();
    }
}

