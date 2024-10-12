package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "RedLeftClipe")
public class RedLeftClipe extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.servo2(0.28);
        drive.servo3(0.22);
        drive.servo1(1);

        Pose2d startPose = new Pose2d(-24, -55.65, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        Trajectory t1 = drive.trajectoryBuilder(startPose)
                .lineToSplineHeading(new Pose2d(-5, -34, Math.toRadians(90)))
                .build();

        Trajectory t2 = drive.trajectoryBuilder(t1.end())
                .forward(8)
                .build();

        Trajectory t3 = drive.trajectoryBuilder(t1.end())
                .back(0.2)
                .build();

        Trajectory t4 = drive.trajectoryBuilder(t1.end())
                .back(10)
                .build();

        Trajectory t5 = drive.trajectoryBuilder(new Pose2d(-5,-33.5),Math.toRadians(90))
                //.splineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(0)), Math.toRadians(0))
                .lineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(0)))
                .build();

        Trajectory t6 = drive.trajectoryBuilder(t5.end())
                .splineToConstantHeading(new Vector2d(-37.19, -14), Math.toRadians(90))
                .build();

        Trajectory t7 = drive.trajectoryBuilder(t6.end())
                .splineToLinearHeading(new Pose2d(-45, -8.5, Math.toRadians(90)), Math.toRadians(-100))
                .build();

        Trajectory t8 = drive.trajectoryBuilder(t7.end())
                .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                .build();

        Trajectory t9 = drive.trajectoryBuilder(t8.end())
                .splineToLinearHeading(new Pose2d(-43.94, -8, Math.toRadians(90)), Math.toRadians(90))
                .build();

        Trajectory t10 = drive.trajectoryBuilder(t9.end())
                .lineTo(new Vector2d(-57.00, -10))
                .build();

        Trajectory t11 = drive.trajectoryBuilder(t10.end())
                .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                .build();

        Trajectory t12 = drive.trajectoryBuilder(t11.end())
                .splineToLinearHeading(new Pose2d(-28, -5, Math.toRadians(180)), Math.toRadians(0))
                //.splineTo(new Vector2d(-30, -1), Math.toRadians(64))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(t1);
        drive.garra("cima",270,0.5);
        drive.followTrajectory(t2);
        drive.garra("baixo",80, 1);
        drive.followTrajectory(t3);
        drive.servo3(0.3);
        drive.followTrajectoryAsync(t4);
        drive.servo3(0.22);
        drive.followTrajectory(t5);
        drive.followTrajectory(t6);
        drive.followTrajectory(t7);
        drive.followTrajectory(t8);
        drive.followTrajectory(t9);
        drive.followTrajectory(t10);
        drive.followTrajectory(t11);
        drive.followTrajectory(t12);

    }
}