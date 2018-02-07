package org.firstinspires.ftc.robotcontroller.internal.Core;


/**
 * Class for managing commands of subsystems. Commands have the capability of running either on the
 * main thread or on a separate thread.
 *
 * Additionally, Commands hold the ability to be grouped together in a sequence of sequential,
 * parallel, or both Commands. Chain enough of these together, and you end up with an autonomous
 * program.
 */
public abstract class Command
{
    private Thread _t;


    /**
     * Define this in your child class as what the command should do. Be sure to allow a mechanism
     * for the command to stop upon will.
     */
    protected abstract void start() throws InterruptedException;


    /**
     * Executes this command on the main thread
     */
    public final void execute() throws InterruptedException
    {
        start();

        Thread.sleep(50);
    }


    /**
     * Runs the defined Command on a separate thread.
     *
     * For example, if you wish to start two Commands concurrently, add one of them to this method,
     * and start this method FIRST. Note that there is no need to place both methods on separate
     * threads.
     */
    public final void executeParallel() throws InterruptedException
    {
        if(_t == null)
        {
            _t = new Thread(() ->
            {
                try
                {
                    start();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
            _t.start();
        }
    }


    /**
     * Stops execution of the Command- it is up to the user of the class to make sure this method
     * works as intended.
     */
    public abstract void stop();
}
