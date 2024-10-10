package org.firstinspires.ftc.teamcode.TeleOp;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "Solo")
public class Solo extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotor braco1 = hardwareMap.get(DcMotor.class, "braco1");
        DcMotor braco2 = hardwareMap.get(DcMotor.class, "braco2");
        Servo servo1 = hardwareMap.get(Servo.class, "servo1"); // captura
        Servo servo2 = hardwareMap.get(Servo.class, "servo2"); // movimentação de captura
        Servo servo3 = hardwareMap.get(Servo.class, "servo3"); // garra

        float Y2;

        servo2.setPosition(0); // 0 e 0.35

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d(10, 10, Math.toRadians(90)));

        drive.leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        drive.rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        drive.rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(!isStopRequested()) {

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * 0.7,
                            -gamepad1.left_stick_x * 0.7,
                            gamepad1.right_stick_x * 0.7
                    )
            );

            Y2 = gamepad1.right_stick_y;

            braco1.setPower(Y2 * 0.7);
            braco2.setPower(Y2 * 0.7);

            if(gamepad1.right_bumper) {
                servo2.setPosition(0.28);
            }

            if(gamepad1.left_bumper) {
                servo2.setPosition(0);
            }


            if(gamepad1.b) {
                servo1.setPosition(1);
                servo2.setPosition(0.28);
            } else if(gamepad1.a) {
                servo1.setPosition(0);
            } else {
                servo1.setPosition(0.5);
            }

            if(gamepad1.left_trigger > 0.3) {
                servo3.setPosition(0.22);
            }

            if(gamepad1.right_trigger > 0.3) {
                servo3.setPosition(0.56);
            }

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}
