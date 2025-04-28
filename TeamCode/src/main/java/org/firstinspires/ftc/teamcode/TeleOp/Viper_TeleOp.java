package org.firstinspires.ftc.teamcode.TeleOp;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "Viper_TeleOp")
public class Viper_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotor viper1 = hardwareMap.get(DcMotor.class, "viper1");
        DcMotor baseViperRight = hardwareMap.get(DcMotor.class, "baseViperRight");
        Servo servo1 = hardwareMap.get(Servo.class, "servo1");
        Servo servo2 = hardwareMap.get(Servo.class, "servo2");

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d(10, 10, Math.toRadians(90)));

        drive.leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        servo1.setPosition(0.5);
        servo2.setPosition(1);

        double servoP = 0.5;

        float Y = 0;

        boolean garraAtivada = false;

        boolean viperativado = false;

        double powerMotor = 0;

        double Power = 0;

        boolean lastBumperState = false;

        waitForStart();

        while(!isStopRequested()) {

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * 0.7,
                            -gamepad1.left_stick_x * 0.7,
                            gamepad1.right_stick_x * 0.7
                    )
            );

            Y = gamepad1.right_stick_y;

            // Alterna viperativado somente quando right_bumper for pressionado uma vez
            if (gamepad1.right_bumper && !lastBumperState) {
                viperativado = !viperativado; // Alterna entre true e false
                garraAtivada = !garraAtivada;
                sleep(200);
            }
            lastBumperState = gamepad1.right_bumper; // Atualiza estado anterior

            if (viperativado) {
                if (gamepad1.x) {
                    viper1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    viper1.setPower(Power);
                } else if (gamepad1.a) {
                    viper1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                    viper1.setPower(-Power);
                } else {
                    viper1.setPower(0);
                    viper1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
            }

            if (gamepad1.right_trigger > 0.3 && viperativado) {
                Power = Power + 0.1;
                sleep(200);
            } else if (gamepad1.left_trigger > 0.3 && viperativado) {
                Power = Power - 0.1;
                sleep(200);
            }

            if (gamepad1.b) {
                servo1.setPosition(0.5);
                servo2.setPosition(1);
                servoP = 0.5;
            }

            if (gamepad1.y) {
                servo1.setPosition(0.8);
                servo2.setPosition(0.9);
                servoP = 0.8;
            }

            if (gamepad1.right_trigger > 0.3) {
                powerMotor = powerMotor + 0.1;
                sleep(200);
            } else if (gamepad1.left_trigger > 0.3) {
                powerMotor = powerMotor - 0.1;
                sleep(200);
            }

            if(garraAtivada && Y != 0) {
                viper1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                baseViperRight.setPower(Y * powerMotor);
            } else {
                baseViperRight.setPower(0);
                viper1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addData("viperValue: ", viperativado);
            telemetry.addData("garraValue: ", garraAtivada);
            telemetry.addData("Power: ", Power);
            telemetry.addData("Poder do motor: ", powerMotor);
            telemetry.addData("Posição do Servo: ", servoP);
            telemetry.update();
        }
    }
}
