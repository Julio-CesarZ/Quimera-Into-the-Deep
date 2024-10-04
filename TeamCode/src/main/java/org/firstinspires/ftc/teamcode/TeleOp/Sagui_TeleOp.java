package org.firstinspires.ftc.teamcode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Sagui_TeleOp")
public class Sagui_TeleOp extends LinearOpMode {

    private boolean leftTriggerPressed = false;
    private boolean rightTriggerPressed = false;

    @Override
    public void runOpMode() {

        DcMotor frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        DcMotor frontright = hardwareMap.get(DcMotor.class, "frontright");
        DcMotor backleft = hardwareMap.get(DcMotor.class, "backleft");
        DcMotor backright = hardwareMap.get(DcMotor.class, "backright");
        DcMotor braco1 = hardwareMap.get(DcMotor.class, "braco1");
        DcMotor braco2 = hardwareMap.get(DcMotor.class, "braco2");
        Servo servo1 = hardwareMap.get(Servo.class, "servo1"); // captura
        Servo servo2 = hardwareMap.get(Servo.class, "servo2"); // movimentação de captura
        Servo servo3 = hardwareMap.get(Servo.class, "servo3"); // garra

        frontright.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);

        float Y;
        float X;
        float Z;
        float Y2;

        double maxPower = 1;

        servo2.setPosition(0); // 0 e 0.35
        servo3.setPosition(0); // 0 e 0.48

        waitForStart();
        while(opModeIsActive()) {
            Y = gamepad1.left_stick_y;
            X = gamepad1.left_stick_x;
            Z = gamepad1.right_stick_x;
            Y2 = gamepad2.left_stick_y;

            // Controle de potência com os gatilhos
            if (gamepad1.left_trigger > 0.1 && !leftTriggerPressed && maxPower > 0.0) {
                maxPower -= 0.1;
            }
            leftTriggerPressed = gamepad1.left_trigger > 0.1;

            if (gamepad1.right_trigger > 0.1 && !rightTriggerPressed && maxPower < 1.0) {
                maxPower += 0.1;
            }
            rightTriggerPressed = gamepad1.right_trigger > 0.1;

            // Garante que maxPower esteja no intervalo [0.1, 1.0]
            maxPower = Math.max(0.1, Math.min(1.0, maxPower));

            // Aplica o maxPower nos componentes X, Y, Z
            X *= (float) maxPower;
            Y *= (float) maxPower;
            Z *= (float) maxPower;

            frontleft.setPower(-X);
            frontright.setPower(X);
            backleft.setPower(-X);
            backright.setPower(X);

            frontleft.setPower(Y);
            frontright.setPower(Y);
            backleft.setPower(-Y);
            backright.setPower(-Y);

            frontleft.setPower(-Z);
            frontright.setPower(Z);
            backleft.setPower(Z);
            backright.setPower(-Z);

            braco1.setPower(Y2);
            braco2.setPower(Y2);

            if(gamepad2.left_bumper) {
                servo2.setPosition(0.23);
            }

            if(gamepad2.right_bumper) {
                servo2.setPosition(0);
            }


            if(gamepad2.b) {
                servo1.setPosition(1);
            } else if(gamepad2.a) {
                servo1.setPosition(0);
            } else {
                servo1.setPosition(0.5);
            }

            if(gamepad2.left_trigger > 0.3) {
                servo3.setPosition(0.2);
            }

            if(gamepad2.right_trigger > 0.3) {
                servo3.setPosition(0.58);
            }

            // Atualiza a telemetria
            telemetry.addData("Power: ", maxPower);
            telemetry.update();
        }
    }
}
