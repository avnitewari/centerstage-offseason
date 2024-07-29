package org.firstinspires.ftc.teamcode.teleop;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.teamcode.drive.opmode.subsystems.IntakeClaw;
//import org.firstinspires.ftc.teamcode.drive.opmode.subsystems.IntakeSingleClaw;
//import org.firstinspires.ftc.teamcode.drive.opmode.subsystems.OuttakeArm;
//import org.firstinspires.ftc.teamcode.drive.opmode.subsystems.OuttakeSlides;

@TeleOp
@Config
public class MotorTest extends LinearOpMode {

    // Drivetrain Motors & Config
    private DcMotorEx frontLeft = null, frontRight = null, rearLeft = null, rearRight = null;

    public static double axialCoefficient = 1, yawCoefficient = 1, lateralCoefficient = 1.1;
    public static double slowModePower = 0.5, regularPower = 0.85;


    // Timer
    public ElapsedTime timer;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize DT
        frontLeft = hardwareMap.get(DcMotorEx.class, "leftFront");
        frontRight = hardwareMap.get(DcMotorEx.class, "rightFront");
        rearLeft = hardwareMap.get(DcMotorEx.class, "leftRear");
        rearRight = hardwareMap.get(DcMotorEx.class, "rightRear");

        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Voltage System
        VoltageSensor voltageSensor = hardwareMap.voltageSensor.iterator().next();

        waitForStart();

        timer = new ElapsedTime();

        while(!isStopRequested() && opModeIsActive()) {
            /*  Drivetrain Commands -------- */
            // input
            double axial = -gamepad1.left_stick_y * axialCoefficient;  // forward, back
            double lateral = gamepad1.left_stick_x * lateralCoefficient; // side to side
            double yaw = gamepad1.right_stick_x * yawCoefficient; // turning

            double dtDenominator = Math.max(Math.abs(axial) + Math.abs(lateral) + Math.abs(yaw), 1);

            // slowmode
            boolean slowModeOn = false;
            if (gamepad1.left_bumper) slowModeOn = true;

            double powerModifier = slowModeOn ? slowModePower : regularPower;

            // making the equations in order to have the directions synchronous
            double leftFrontPower = ((axial + lateral + yaw) / dtDenominator) * powerModifier;
            double rightFrontPower = ((axial - lateral - yaw) / dtDenominator) * powerModifier;
            double leftBackPower = ((axial - lateral + yaw) / dtDenominator) * powerModifier;
            double rightBackPower = ((axial + lateral - yaw) / dtDenominator) * powerModifier;

            // set powers
            frontLeft.setPower(leftFrontPower);
            frontRight.setPower(rightFrontPower);
            rearLeft.setPower(leftBackPower);
            rearRight.setPower(rightBackPower);


            }
        }
    }
