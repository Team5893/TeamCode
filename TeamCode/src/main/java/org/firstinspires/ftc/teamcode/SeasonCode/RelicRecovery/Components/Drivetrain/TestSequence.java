package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain;

import org.firstinspires.ftc.robotcontroller.internal.Core.Command;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.GlyphGrabber;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber.TimedGlyphIO;


public class TestSequence extends Command
{
    @Override
    protected void run()
    {
        addParallel(new TimedGlyphIO(500 , GlyphGrabber.State.INPUT));
        addSequential(new TimeDrive(3_000 , 1.0));
        addSequential(new TimeDrive(3_000 , -1.0));
    }


    @Override
    public void stop()
    {

    }
}
