package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "Red L 11 (Cesta)")
public class RedLeftCesta extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.servo2(0.23);
        drive.servo3(0.22);

        Pose2d startPose = new Pose2d(-23.910087572891527, -55.655436300036726, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        Trajectory t1 = drive.trajectoryBuilder(startPose)
                .splineToSplineHeading(new Pose2d(-47, -47, Math.toRadians(-135)), Math.toRadians(180))
                .build();

        Trajectory t2 = drive.trajectoryBuilder(t1.end())
                .lineTo(new Vector2d(-55.00, -53.00))
                .build();

        Trajectory t3 = drive.trajectoryBuilder(t2.end())
                .back(5)
                .build();

        Trajectory t4 = drive.trajectoryBuilder(new Pose2d(-56.50, -50.00, Math.toRadians(0)))
                .splineTo(new Vector2d(-37.19, -14), Math.toRadians(90))
                .build();

        Trajectory t5 = drive.trajectoryBuilder(t4.end())
                .splineToLinearHeading(new Pose2d(-43, -8.5, Math.toRadians(90)), Math.toRadians(-100))
                .build();

        Trajectory t6 = drive.trajectoryBuilder(t5.end())
                .lineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)))
                .build();

        Trajectory t7 = drive.trajectoryBuilder(t6.end())
                .splineToLinearHeading(new Pose2d(-43.94, -8, Math.toRadians(90)), Math.toRadians(90))
                .build();

        Trajectory t8 = drive.trajectoryBuilder(t7.end())
                .lineTo(new Vector2d(-54, -10))
                .build();

        Trajectory t9 = drive.trajectoryBuilder(t8.end())
                .lineToLinearHeading(new Pose2d(-55, -50, Math.toRadians(45)))
                .build();

        Trajectory t10 = drive.trajectoryBuilder(t9.end())
                .splineToLinearHeading(new Pose2d(-25, -5, Math.toRadians(180)), Math.toRadians(0))
                .build();




        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(t1);
        drive.servo3(0.56);
        drive.garra("cima",330,0.5);
        drive.followTrajectory(t2);
        drive.servo1(0);
        drive.followTrajectory(t3);
        drive.turnAsync(Math.toRadians(135));
        drive.garra("baixo",280,0.5);
        drive.servo3(0.22);
        drive.followTrajectory(t4);
        drive.followTrajectory(t5);
        drive.followTrajectory(t6);
        drive.followTrajectory(t7);
        drive.followTrajectory(t8);
        drive.followTrajectory(t9);
        drive.followTrajectory(t10);


    }
}