package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.OpModes.Autonomous.FrontBlue.CommandGroups;


import org.directcurrent.opencv.CVBridge;
import org.directcurrent.season.relicrecovery.jewelarm.JewelArm;
import org.firstinspires.ftc.robotcontroller.internal.Core.Command;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.DistanceDrive;
import org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain.TurnToAngle;

import static java.lang.Thread.sleep;
import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.jewelArm;


public class KnockJewel extends Command
{
    @Override
    @SuppressWarnings("ConstantConditions")
    protected void start() throws InterruptedException
    {
        new TurnToAngle(75 , .4 , 2_000).execute();

        CVBridge.openCvRunner.toggleShowHide();
        CVBridge.openCvRunner.toggleAnalyze();

        // Sleep this long to give OpenCV a chance to see the jewels
        sleep(1_000);

        for(;;)
        {
            try
            {
                // Jewel color decisions
                if (CVBridge.redJewelPoints.size() == 0 && CVBridge.blueJewelPoints.size() == 0)
                {
                    new TurnToAngle(90 , .4 , 3_000).execute();
                }
                // It's backwards because the phone is upside down
                else if (CVBridge.redJewelPoints.get(0).y() < CVBridge.blueJewelPoints.get(0).y())
                {
                    jewelArm.setState(JewelArm.State.DOWN);
                    sleep(750);

                    new TurnToAngle(100 , .3 , 4_000).execute();

                    jewelArm.setState(JewelArm.State.UP);
                    sleep(750);

                    new TurnToAngle(90 , .5 , 2_000).execute();
                }
                else
                {
                    jewelArm.setState(JewelArm.State.DOWN);
                    sleep(750);

                    new DistanceDrive(-3 , .25 , 3_000).execute();

                    jewelArm.setState(JewelArm.State.UP);
                    sleep(750);

                    new TurnToAngle(90 , .5 , 3_000).execute();
                    break;
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
    }


    @Override
    public void stop()
    {

    }
}
