package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.OpModes.Autonomous.FrontBlue.CommandGroups;


import org.firstinspires.ftc.robotcontroller.internal.Core.Command;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.DistanceDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TurnToAngle;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.GlyphGrabber;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.TimedGlyphIO;


public class GrabFromGlyphPit extends Command
{
    @Override
    protected void start() throws InterruptedException
    {
        new TurnToAngle(180 , .75).execute();
        new TimedGlyphIO(7 , GlyphGrabber.State.INPUT , .5).executeParallel();
        new DistanceDrive(25 , 1 , 2_000);

        // Sweep to increase Glyph grabbing chances
        new TurnToAngle(145 , 1 , 1_000).execute();
        new TurnToAngle(215 , 1 , 1_000).execute();
        new TurnToAngle(180 , .75 , 1_000).execute();

        new DistanceDrive( -25 , 1 , 2_000).execute();
        new TurnToAngle(0 , .75).execute();
    }


    @Override
    public void stop()
    {

    }
}
