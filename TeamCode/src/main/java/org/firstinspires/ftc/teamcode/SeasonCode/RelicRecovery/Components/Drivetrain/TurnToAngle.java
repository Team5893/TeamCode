package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.Drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.internal.Core.Command;
import org.firstinspires.ftc.robotcontroller.internal.Core.Utility.Util;

import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.drivetrain;
import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.imu;


public class TurnToAngle extends Command
{
    private double _targetAngle;
    private double _maxSpeed;
    private long _timeout;

    private boolean _stop = false;


    public TurnToAngle(final double TARGET , final double SPEED)
    {
        _targetAngle = TARGET;
        _maxSpeed = SPEED;
        _timeout = 10_000;
    }


    public TurnToAngle(final double TARGET , final double SPEED, final long TIMEOUT)
    {
        _targetAngle = TARGET;
        _maxSpeed = SPEED;
        _timeout = TIMEOUT;
    }


    @Override
    protected void run()
    {
        if(drivetrain.encoderMode() != DcMotor.RunMode.RUN_USING_ENCODER)
        {
            drivetrain.encoderOn();
        }

        imu.pull();


        final double TOLERANCE = 5;
        final double MIN_SPEED = .3;
        final double SPEED_MULTIPLIER = 3;

        final double INIT_HEADING = imu.zAngle();
        final double INIT_ERROR = Util.angleError((int)INIT_HEADING , (int)_targetAngle);
        final double DISTANCE_MODFIER = Math.abs(INIT_ERROR) / 180;
        final double START_TIME = System.currentTimeMillis();

        double speed;
        double error = INIT_ERROR;


        while(Math.abs(error) > TOLERANCE && System.currentTimeMillis() - START_TIME < _timeout
                && !_stop && drivetrain.base().opMode().opModeIsActive())
        {
            imu.pull();

            error = Util.angleError((int)imu.zAngle() , (int)_targetAngle);

            speed = error / INIT_ERROR * SPEED_MULTIPLIER * DISTANCE_MODFIER;

            if(speed < MIN_SPEED)
            {
                speed = MIN_SPEED;
            }

            if(speed > _maxSpeed)
            {
                speed = _maxSpeed;
            }

            drivetrain.run(0.0 , Math.abs(error) / -error * speed , false);
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
