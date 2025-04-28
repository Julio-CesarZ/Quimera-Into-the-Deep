package org.firstinspires.ftc.teamcode.TeleOp;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "ViperTest")
public class ViperTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        double Power = 0;

        double powerMotor = 0;

        float Y = 0;

        boolean viperativado = false;

        boolean garraAtivada = false;

        boolean lastBumperState = false;

        boolean lastBumperState2 = false;

        DcMotor viper1 = hardwareMap.get(DcMotor.class, "viper1");
        DcMotor baseViperRight = hardwareMap.get(DcMotor.class, "baseViperRight");
        DcMotor baseViperLeft = hardwareMap.get(DcMotor.class, "baseViperLeft");

        waitForStart();

        while(!isStopRequested()) {

            Y = gamepad1.right_stick_y;

            // Alterna viperativado somente quando right_bumper for pressionado uma vez
            if (gamepad1.right_bumper && !lastBumperState) {
                viperativado = !viperativado; // Alterna entre true e false
                sleep(200);
            }
            lastBumperState = gamepad1.right_bumper; // Atualiza estado anterior

            // Alterna garraAtivada somente quando left_bumper for pressionado uma vez
            if (gamepad1.left_bumper && !lastBumperState2) {
                garraAtivada = !garraAtivada; // Alterna entre true e false
                sleep(200);
            }
            lastBumperState2 = gamepad1.right_bumper; // Atualiza estado anterior

            if (gamepad1.right_trigger > 0.3) {
                Power = Power + 0.1;
                sleep(200);
            } else if (gamepad1.left_trigger > 0.3) {
                Power = Power - 0.1;
                sleep(200);
            }

            if (gamepad1.right_trigger > 0.3) {
                powerMotor = powerMotor + 0.1;
                sleep(200);
            } else if (gamepad1.left_trigger > 0.3) {
                powerMotor = powerMotor - 0.1;
                sleep(200);
            }

            // Se viperativado estiver ativo, permite controle
            if (viperativado) {
                if (gamepad1.x) {
                    viper1.setPower(Power);
                } else if (gamepad1.a) {
                    viper1.setPower(-Power);
                } else {
                    viper1.setPower(0); // Para o motor caso nenhum botão seja pressionado
                }
            }

            if(garraAtivada) {
                baseViperRight.setPower(Y * powerMotor);
            }

            // Define o modo de travamento baseado nos botões
            if (gamepad1.x || gamepad1.a) {
                viper1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            } else {
                viper1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            telemetry.addData("viperValue: ", viperativado);
            telemetry.addData("GarraAtivada: ", garraAtivada);
            telemetry.addData("Power: ", Power);
            telemetry.addData("Power do motor: ", powerMotor);
            telemetry.update();
        }
    }
}
