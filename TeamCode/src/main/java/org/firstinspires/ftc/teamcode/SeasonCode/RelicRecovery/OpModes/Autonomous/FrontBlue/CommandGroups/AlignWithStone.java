package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.OpModes.Autonomous.FrontBlue.CommandGroups;

import org.firstinspires.ftc.robotcontroller.internal.Core.Command;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.DistanceDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TimeDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TurnToAngle;


public class AlignWithStone extends Command
{
    @Override
    protected void start() throws InterruptedException
    {
        new DistanceDrive(-25 , 1 , 3_000).execute();

        // Space Jam into balancing stone
        new TurnToAngle(270 , .75 , 2_000).execute();
        new TimeDrive(750 , -.75).execute();

        new DistanceDrive(5 , .5 , 2_000).execute();
        new TurnToAngle(270 , .65 , 2_000).execute();
    }


    @Override
    public void stop()
    {

    }
}
