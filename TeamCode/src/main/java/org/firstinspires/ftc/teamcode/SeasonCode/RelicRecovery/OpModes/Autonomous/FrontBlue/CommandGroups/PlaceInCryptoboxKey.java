package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.OpModes.Autonomous.FrontBlue.CommandGroups;


import org.firstinspires.ftc.robotcontroller.internal.Core.Command;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.DistanceDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TimeDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TurnToAngle;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.GlyphGrabber;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.TimedGlyphIO;


public class PlaceInCryptoboxKey extends Command
{
    private RelicRecoveryVuMark _vumark;


    /**
     * Creates a PlaceInCryptoboxKey command given a VuMark (the key)
     *
     * @param VUMARK The cryptobox key read by Vuforia
     */
    public PlaceInCryptoboxKey(final RelicRecoveryVuMark VUMARK)
    {
        _vumark = VUMARK;
    }


    @Override
    protected void start() throws InterruptedException
    {
        final double NEAR_COL_DISTANCE = 4;
        final double COL_SEPARATION = 8.5;


        switch(_vumark)
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
        new TimedGlyphIO(1_000 , GlyphGrabber.State.OUTPUT , .5).execute();
        new TimedGlyphIO(3_000 , GlyphGrabber.State.OUTPUT , .5).executeParallel();
        new TimeDrive(1_000 , .3).execute();
        new DistanceDrive(-10 , .3).execute();
    }


    @Override
    public void stop()
    {

    }
}
