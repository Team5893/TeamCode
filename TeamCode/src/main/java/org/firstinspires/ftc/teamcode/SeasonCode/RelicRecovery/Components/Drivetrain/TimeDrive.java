package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.internal.Core.Command;

import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.drivetrain;


public class TimeDrive extends Command
{
    private long _driveTime;
    private double _leftPower;
    private double _rightPower;

    private boolean _stop = false;


    /**
     * Creates a TimeDrive command given a time in milliseconds and a forwards-backwards power
     * to drive at.
     *
     * @param TIME Time to drive for in milliseconds
     * @param FORWARD_POWER Power [-1.0 , 1.0] to run the drivetrain at (both sides)
     */
    public TimeDrive(final long TIME , final double FORWARD_POWER)
    {
        _driveTime = TIME;
        _leftPower = FORWARD_POWER;
        _rightPower = FORWARD_POWER;
    }


    /**
     * Creates a TimeDrive command given a time in milliseconds and the powers to set to each side
     * of the drivetrain.
     *
     * @param TIME Time to drive for in milliseconds
     * @param LEFT_POWER Power [-1.0 , 1.0] to be set to the left side of the drivetrain
     * @param RIGHT_POWER Power [-1.0 , 1.0] to be set to the right side of the drivetrain
     */
    public TimeDrive(final long TIME , final double LEFT_POWER , final double RIGHT_POWER)
    {
        _driveTime = TIME;
        _leftPower = LEFT_POWER;
        _rightPower = RIGHT_POWER;
    }


    @Override
    protected void run()
    {
        if(drivetrain.encoderMode() != DcMotor.RunMode.RUN_USING_ENCODER)
        {
            drivetrain.encoderOn();
        }

        final long startTime = System.currentTimeMillis();

        drivetrain.leftMotor().setPower(_leftPower);
        drivetrain.rightMotor().setPower(_rightPower);

        while(System.currentTimeMillis() - startTime < _driveTime &&
                drivetrain.base().opMode().opModeIsActive() && !_stop)
        {
            // Nothing
        }

        drivetrain.leftMotor().setPower(0);
        drivetrain.rightMotor().setPower(0);
    }


    @Override
    public void stop()
    {
        _stop = true;
    }
}
