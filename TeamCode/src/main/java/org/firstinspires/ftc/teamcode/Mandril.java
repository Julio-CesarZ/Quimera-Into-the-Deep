package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "Mandril")
public class Mandril extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-23.910087572891527, -55.655436300036726, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        Trajectory t1 = drive.trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(-5.5, -30), Math.toRadians(0))
                .build();
        Trajectory t2 = drive.trajectoryBuilder(t1.end())
                .back(3)
                .splineToConstantHeading(new Vector2d(43, -57), Math.toRadians(0))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(t1);
        drive.followTrajectory(t2);


    }
}