package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.OpModes.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.directcurrent.opencv.CVBridge;
import org.directcurrent.season.relicrecovery.jewelarm.JewelArm;
import org.firstinspires.ftc.robotcontroller.internal.Core.Utility.Vuforia;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.DistanceDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TimeDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TurnToAngle;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.GlyphGrabber;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.TimedGlyphIO;

import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.drivetrain;
import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.imu;
import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.jewelArm;


@Autonomous(name = "Front Side Blue")
public class AutoFrontSideBlue extends LinearOpMode
{
    private Base _base = new Base();
    private Vuforia _vuforia = new Vuforia();


    /**
     * Status of jewel detection
     */
    private enum JewelStatus
    {
        GO_FORWARD ,
        GO_BACKWARD ,
        UNCERTAIN
    }


    @Override
    public void runOpMode() throws InterruptedException
    {
        RelicRecoveryVuMark vuMark;
        JewelStatus jewelStatus;


        // Some boilerplate initialization
        _base.init(hardwareMap , this);

        imu.calibrateTo(95);

        _vuforia.init(hardwareMap , VuforiaLocalizer.CameraDirection.BACK);
        _vuforia.activate();


        waitForStart();


        drivetrain.setZeroPowerMode(DcMotor.ZeroPowerBehavior.BRAKE);
        vuMark = _vuforia.currentMarker();
        _vuforia.deactivate();

        // Turn to get the jewels
        new TurnToAngle(75.0 , .4 , 2_000).execute();

        CVBridge.openCvRunner.toggleShowHide();
        CVBridge.openCvRunner.toggleAnalyze();

        // Sleep this long to give OpenCV a chance to see the jewels
        sleep(1_000);


        /*
            Keep trying this until it works- because it sometimes won't work.

            This determines which jewel to knock off the stand thing
         */
        for(;;)
        {
            try
            {
                // Jewel color decisions
                if (CVBridge.redJewelPoints.size() == 0 && CVBridge.blueJewelPoints.size() == 0)
                {
                    jewelStatus = JewelStatus.UNCERTAIN;
                }
                // It's backwards because the phone is upside down
                else if (CVBridge.redJewelPoints.get(0).y() < CVBridge.blueJewelPoints.get(0).y())
                {
                    jewelStatus = JewelStatus.GO_FORWARD;
                }
                else
                {
                    jewelStatus = JewelStatus.GO_BACKWARD;
                }

                break;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }   // End for loop

        // Close OpenCV for battery saving
        CVBridge.openCvRunner.toggleShowHide();


        switch(jewelStatus)
        {
            /*
                Straighten out robot
             */
            case UNCERTAIN:
                new TurnToAngle(90 , .4 , 3_000).execute();
                break;


            /*
                Lower arm, rotate counter-clockwise to knock jewel off, raise arm, straighten
                out
             */
            case GO_FORWARD:
                jewelArm.setState(JewelArm.State.DOWN);
                sleep(750);

                new TurnToAngle(100 , .3 , 4_000).execute();

                jewelArm.setState(JewelArm.State.UP);
                sleep(750);

                new TurnToAngle(90 , .5 , 2_000).execute();
                break;


            /*
                Lower arm, drive slightly backward to knock jewel off, drive back to original spot,
                raise arm, straighten out
             */
            case GO_BACKWARD:
                jewelArm.setState(JewelArm.State.DOWN);
                sleep(750);

                new DistanceDrive(-3 , .25 , 3_000).execute();

                jewelArm.setState(JewelArm.State.UP);
                sleep(750);

                new TurnToAngle(90 , .5 , 3_000).execute();
                break;
        }   // End switch


        new DistanceDrive(-25 , 1 , 3_000).execute();

        // Space Jam into balancing stone
        new TurnToAngle(270 , .75 , 2_000).execute();
        new TimeDrive(750 , -.75).execute();

        new DistanceDrive(5 , .5 , 2_000).execute();
        new TurnToAngle(270 , .65 , 2_000).execute();


        final double NEAR_COL_DISTANCE = 4;
        final double COL_SEPARATION = 8.5;


        switch(vuMark)
        {
            case LEFT:
                new DistanceDrive(NEAR_COL_DISTANCE , .5 , 3_000).execute();
                break;

            case CENTER:
                new DistanceDrive(NEAR_COL_DISTANCE + COL_SEPARATION, .5 , 3_000).execute();
                break;

            case RIGHT:
                // Fall through intentional

            case UNKNOWN:
                new DistanceDrive(NEAR_COL_DISTANCE + 2 * COL_SEPARATION, .5 , 3_000).execute();
                break;
        }

        new TurnToAngle(0 , .6 , 3_000).execute();
        new TimedGlyphIO(1_000 , GlyphGrabber.State.OUTPUT , .25).execute();
        new TimeDrive(1_000 , .3).execute();
        new DistanceDrive(-10 , .3).execute();
        new TurnToAngle(180 , .75).execute();


/*
        // Multi-glyph part
        _base.glyphGrabber.activateForTime.setParams(7_000 , GlyphGrabber.State.INPUT);
        _base.glyphGrabber.activateForTime.runParallel();

        drivetrain.driveTo.setParams(25 , 1 , 2_000);
        drivetrain.driveTo.runSequentially();
        sleep(100);


        // Sweep back and forth to increase chances of glyph collection
        drivetrain.turnTo.setParams(145 , 1 , 1_000);
        drivetrain.turnTo.runSequentially();
        sleep(100);

        drivetrain.turnTo.setParams(215 , 1 , 1_000);
        drivetrain.turnTo.runSequentially();
        sleep(100);

        drivetrain.turnTo.setParams(180 , .75 , 3_000);
        drivetrain.turnTo.runSequentially();
        sleep(100);

        drivetrain.driveForTime.setParams(750 , -1);
        drivetrain.driveForTime.runSequentially();
        sleep(100);

        drivetrain.turnTo.setParams(0 , .75 , 3_000);
        drivetrain.turnTo.runSequentially();
        sleep(100);


        drivetrain.driveForTime.setParams(500 , 1);
        drivetrain.driveForTime.runSequentially();
        sleep(100);


        drivetrain.driveForTime.setParams(200 , -1);
        drivetrain.driveForTime.runSequentially();
        sleep(100);
*/

//        _base.drivetrain.driveTo.setParams(28 , 1 , 3_000);
//        _base.drivetrain.driveTo.runSequentially();
//        sleep(100);
//
//        _base.drivetrain.turnTo.setParams(0 , .75 , 2_000);
//        _base.drivetrain.turnTo.runSequentially();
//        sleep(100);

        drivetrain.setZeroPowerMode(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}
