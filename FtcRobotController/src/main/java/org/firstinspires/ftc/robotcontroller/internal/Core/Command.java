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
    protected abstract void run();


    /**
     * Executes this command
     */
    public final void execute()
    {
        run();
    }


    /**
     * Runs the defined Command on the main thread.
     *
     * @param command Command to be run
     */
    protected final synchronized void addSequential(Command command)
    {
        command.run();
    }


    /**
     * Runs the defined Command on a separate thread.
     *
     * For example, if you wish to run two Commands concurrently, add one of them to this method,
     * and run this method FIRST. Note that there is no need to place both methods on separate
     * threads.
     *
     * @param command Command to be run
     */
    protected final synchronized void addParallel(Command command)
    {
        if(_t != null)
        {
            _t = new Thread(command::run);
            _t.start();
        }
    }


    /**
     * Stops execution of the Command- it is up to the user of the class to make sure this method
     * works as intended.
     */
    public abstract void stop();
}
