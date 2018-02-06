package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcontroller.internal.Core.RobotBase;
import org.firstinspires.ftc.robotcontroller.internal.Core.RobotComponent;


/**
 * Glyph Grabber of the Relic Recovery Robot
 */
@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class GlyphGrabber extends RobotComponent
{
    private double _wheelSpeed = 1;
    private double _conveyorSpeed = 1;
    private double _stopSpeed = 0;

    public DcMotor leftWheelMotor;
    public DcMotor rightWheelMotor;
    public DcMotor conveyorMotor;


    /**
     * States of the Glyph Grabber
     */
    public enum State
    {
        INPUT ,
        OUTPUT ,
        STOP
    }


    /**
     * Initializes the Glyph Grabber
     *
     * @param BASE The robot base used to create the hardware mapper
     */
    @Override
    public void init(final RobotBase BASE)
    {
        super.init(BASE);

        leftWheelMotor = mapper.mapMotor("lInMotor" , DcMotorSimple.Direction.REVERSE);
        rightWheelMotor = mapper.mapMotor("rInMotor" , DcMotorSimple.Direction.REVERSE);
        conveyorMotor = mapper.mapMotor("convMotor" , DcMotorSimple.Direction.FORWARD);
    }


    /**
     * Sets the run speed of both the conveyor belt and the intake wheels when the grabber is active
     *
     * @param SPEED Speed to set to the conveyor and the intake wheels
     */
    public void setRunSpeed(final double SPEED)
    {
        _conveyorSpeed = SPEED;
        _wheelSpeed = SPEED;
    }


    /**
     * Sets the state of the glyph grabber
     *
     * @param STATE GrabState to set the glyph grabber to
     */
    public void setState(final State STATE)
    {
        switch(STATE)
        {
            case INPUT:
                leftWheelMotor.setPower(_wheelSpeed);
                rightWheelMotor.setPower(_wheelSpeed);
                conveyorMotor.setPower(_conveyorSpeed);
                break;

            case OUTPUT:
                leftWheelMotor.setPower(-_wheelSpeed);
                rightWheelMotor.setPower(-_wheelSpeed);
                conveyorMotor.setPower(-_conveyorSpeed);
                break;

            case STOP:
                leftWheelMotor.setPower(_stopSpeed);
                rightWheelMotor.setPower(_stopSpeed);
                conveyorMotor.setPower(_stopSpeed);
                break;
        }
    }


    /**
     * Stops the Glyph Grabber
     */
    @Override
    public void stop()
    {
        setState(State.STOP);
    }
}
