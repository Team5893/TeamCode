package org.firstinspires.ftc.teamcode.TestCode.BNO055Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.Utility.UtilToggle;


@TeleOp(name = "IMU Calibration" , group = "Prototypes")
public class OpCalibrate extends LinearOpMode
{
    private Base _base = new Base();


    @Override
    public void runOpMode() throws InterruptedException
    {
        UtilToggle writeFile = new UtilToggle();

        boolean beenCalibrated = false;

        _base.init(hardwareMap);

        waitForStart();

        while(opModeIsActive())
        {
            telemetry.addData("Calibration" , _base.imu.calibrationStatus());


            if(writeFile.isPressed(gamepad1.a))
            {
                _base.imu.writeCalibrationFile();

                beenCalibrated = true;
            }

            if(beenCalibrated)
            {
                telemetry.addData("Calibration" , "Recorded in file");
            }

            telemetry.update();
        }
    }
}