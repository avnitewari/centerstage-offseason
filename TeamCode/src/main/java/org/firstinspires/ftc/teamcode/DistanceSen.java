package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

import androidx.appcompat.app.WindowDecorActionBar;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Sensor: Rev2mDistance", group = "Sensor")
@Disabled
public class DistanceSen extends LinearOpMode{

    private  DistanceSensor sensorDistance;

    @Override
    public void runOpMode() {

        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_Distance");

        Rev2mDistanceSensor sensorTimeofFlight = (Rev2mDistanceSensor) sensorDistance;
        Sensor motorTest = null;
        Object tgtPower = null;
        Servo servoTest = null;

        telemetry.addData(">>", "Press start to continue");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Servo Position", servoTest.getPosition());
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", motorTest.getPower());
            telemetry.addData("Distance (m)", sensorDistance.getDistance(DistanceUnit.METER));
            telemetry.addData("Distance (in)", sensorDistance.getDistance(DistanceUnit.INCH));
            telemetry.addData("Distance (cm)", sensorDistance.getDistance(DistanceUnit.CM));
            telemetry.addData("Distance (mm)", sensorDistance.getDistance(DistanceUnit.MM));
            telemetry.addData("Status", "Running");
            telemetry.update();

            telemetry.addData("ID", String.format("%x", sensorTimeofFlight.getModelID()));
            telemetry.addData("did time out", Boolean.toString(sensorTimeofFlight.didTimeoutOccur()));

            telemetry.update();
        }

    }

}
