package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.OpModes.Autonomous.FrontBlue;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.internal.Core.Utility.Vuforia;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.OpModes.Autonomous.FrontBlue.CommandGroups.*;

import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.drivetrain;
import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.imu;


@Autonomous(name = "Front Side Blue")
@SuppressWarnings("unused")
public class AutoFrontSideBlue extends LinearOpMode
{
    private Base _base = new Base();
    private Vuforia _vuforia = new Vuforia();


    @Override
    public void runOpMode() throws InterruptedException
    {
        RelicRecoveryVuMark vuMark;

        // Some boilerplate initialization
        _base.init(hardwareMap , this);

        imu.calibrateTo(95);

        _vuforia.init(hardwareMap , VuforiaLocalizer.CameraDirection.BACK);
        _vuforia.activate();


        waitForStart();


        drivetrain.setZeroPowerMode(DcMotor.ZeroPowerBehavior.BRAKE);
        vuMark = _vuforia.currentMarker();
        _vuforia.deactivate();


        new KnockJewel().execute();
        new AlignWithStone().execute();
        new PlaceInCryptoboxKey(vuMark).execute();
        new GrabFromGlyphPit().execute();


        drivetrain.setZeroPowerMode(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}
