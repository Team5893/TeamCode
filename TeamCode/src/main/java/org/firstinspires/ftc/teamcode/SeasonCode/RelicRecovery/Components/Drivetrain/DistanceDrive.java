package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain;


import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.internal.Core.Command;

import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.drivetrain;



public class DistanceDrive extends Command
{
    private final double _COUNTS_PER_INCH = 64.3304;

    private double _distance;
    private double _speed;
    private long _timeout;

    private boolean _stop = false;


    /**
     * Creates a new DistanceDrive Command, given a distance to travel to and a speed to run at.
     * An automatic timeout is set at 10 seconds
     *
     * @param DISTANCE Distance to drive to in inches
     * @param SPEED Speed [-1.0 , 1.0] to drive at
     */
    public DistanceDrive(final double DISTANCE , final double SPEED)
    {
        _distance = DISTANCE;
        _speed = SPEED;
        _timeout = 10_000;
    }


    /**
     * Creates a new DistanceDrive Command, given a distance to travel to, a speed to run at, and
     * a timeout in case the distance is not reached.
     *
     * @param DISTANCE Distance to drive to in inches
     * @param SPEED Speed [-1.0 , 1.0] to drive at
     * @param TIMEOUT Time elapsed in milliseconds before the command auto-quits.
     */
    public DistanceDrive(final double DISTANCE , final double SPEED , final long TIMEOUT)
    {
        _distance = DISTANCE;
        _speed = SPEED;
        _timeout = TIMEOUT;
    }


    /**
     * Runs the drivetrain based on the parameters set by the constructor. Should the position
     * be met, the timeout expired, the command stopped, or the OpMode stopped, then execution will
     * stop.
     */
    @Override
    protected void run() {
        if (drivetrain.encoderMode() != DcMotor.RunMode.RUN_TO_POSITION) {
            drivetrain.encoderToPos();
        }

        drivetrain.leftMotor().setTargetPosition((int) (_distance * _COUNTS_PER_INCH + drivetrain.leftEncoderCount()));
        drivetrain.rightMotor().setTargetPosition((int) (_distance * _COUNTS_PER_INCH + drivetrain.rightEncoderCount()));

        drivetrain.leftMotor().setPower(_speed);
        drivetrain.rightMotor().setPower(_speed);

        final long startTime = System.currentTimeMillis();

        while (drivetrain.leftMotor().isBusy() && drivetrain.rightMotor().isBusy() && !_stop &&
                drivetrain.base().opMode().opModeIsActive() && System.currentTimeMillis() - startTime < _timeout)
        {
            // Nothing
        }

        drivetrain.leftMotor().setPower(0);
        drivetrain.rightMotor().setPower(0);

        drivetrain.encoderOn();
    }


    /**
     * Stops the command
     */
    @Override
    public void stop()
    {
        _stop = true;
    }
}
