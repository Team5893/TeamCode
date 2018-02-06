package org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Components.GlyphGrabber;

import org.firstinspires.ftc.robotcontroller.internal.Core.Command;

import static org.firstinspires.ftc.teamcode.SeasonCode.RelicRecovery.Base.glyphGrabber;


public class TimedGlyphIO extends Command
{
    private long _activationTime;
    private GlyphGrabber.State _state;
    private double _speed;

    private boolean _stop = false;


    /**
     * Creates a new TimedGlyphIO command given a time in milliseconds and an GlyphGrabber state.
     * Sets the speed of the GlyphGrabber to 1.0
     *
     * @param TIME Time to run the grabber for in milliseconds
     * @param STATE State to set the grabber to for the duration of the command
     */
    public TimedGlyphIO(final long TIME , final GlyphGrabber.State STATE)
    {
        _activationTime = TIME;
        _state = STATE;
        _speed = 1.0;
    }


    /**
     * Creates a new TimedGlyph IO command given a time in milliseconds, GlyphGrabber state, and
     * a speed to run the grabber at.
     *
     * @param TIME Time to run the grabber for in milliseconds
     * @param STATE State to set the grabber to for the duration of the command
     * @param SPEED Speed at which to run the grabber at.
     */
    public TimedGlyphIO(final long TIME , final GlyphGrabber.State STATE , final double SPEED)
    {
        _activationTime = TIME;
        _state = STATE;
        _speed = SPEED;
    }


    @Override
    protected void run()
    {
        final long startTime = System.currentTimeMillis();

        glyphGrabber.setRunSpeed(_speed);
        glyphGrabber.setState(_state);

        while(System.currentTimeMillis() - startTime < _activationTime && !_stop &&
                glyphGrabber.base().opMode().opModeIsActive())
        {
            // Nothing
        }

        glyphGrabber.setState(GlyphGrabber.State.STOP);
    }


    @Override
    public void stop()
    {
        _stop = true;
    }
}
