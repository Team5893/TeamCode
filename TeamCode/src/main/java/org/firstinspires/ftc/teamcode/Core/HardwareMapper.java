/*
    Team 5893 Direct Current

    Authors: Matthew Fan
    Date Created: 2017-09-??

    Please adhere to these units when working in this project:

    Time: Milliseconds
    Distance: Centimeters
    Angle: Degrees (mathematical orientation)
 */
package org.firstinspires.ftc.teamcode.Core;


import android.util.Log;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsAnalogOpticalDistanceSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsTouchSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;


/**
 * Class used to manage hardware mapping. It holds methods used to map components.
 */
@SuppressWarnings({"unused", "AccessStaticViaInstance", "ConstantConditions"})
public final class HardwareMapper
{
    // Robot base object we use to hardware map
    private RobotBase _robot;


    /**
     * Constructor- assigns robot to passed in robot
     *
     * @param myRobot Robot object to be passed in
     */
    public HardwareMapper(RobotBase myRobot)
    {
        _robot = myRobot;
    }


    /**
     * Attempts to map a motor with the provided name. Result is returned, allowing an assignment
     * operator (=) to map the desired motor. Sends a log error message if failed.
     *
     * @param NAME Name of the DcMotor as it appears in the configuration file
     * @return Returns a DcMotor, mapped if succeeded, null if failed
     */
    public DcMotor mapMotor(final String NAME , final DcMotorSimple.Direction DIRECTION)
    {
        DcMotor myMotor = null;             // Motor to be mapped

        try
        {
            myMotor = _robot.hardware.dcMotor.get(NAME);
            myMotor.setDirection(DIRECTION);
        }
        catch(Exception e)
        {
            Log.e("Error" , "Cannot map DcMotor with name " + NAME);
        }

        return myMotor;
    }


    /**
     * Attempts to map a Modern Robotics Integrating Gyroscope. Also attempts to set its heading
     * mode to cartesian. Result is returned, allowing an assignment operator to map the desired
     * gyro. Sends a log error message if failed.
     *
     * @param NAME Name of the Gyro as it appears in the configuration file
     * @return Returns a gyro, mapped if succeeded, null if failed
     */
    public ModernRoboticsI2cGyro mapMRGyro(final String NAME , ModernRoboticsI2cGyro.HeadingMode mode)
    {
        ModernRoboticsI2cGyro myGyro = null;            // MR gyro to be mapped

        try
        {
            myGyro = (ModernRoboticsI2cGyro)_robot.hardware.gyroSensor.get(NAME);
            myGyro.setHeadingMode(mode);
        }
        catch(Exception e)
        {
            Log.e("Error" , "Cannot map MR Gyroscope with name " + NAME);
        }

        return myGyro;
    }


    /**
     * Attempts to map a Modern Robotics Range sensor. Result is returned, allowing an assignment
     * operator to map the desired range sensor. Sends a log error message if failed.
     *
     * @param NAME Name of the Range sensor as it appears in the configuration file
     * @return Returns a range sensor, mapped if succeeded, null if failed
     */
    public ModernRoboticsI2cRangeSensor mapMRRange(final String NAME)
    {
        ModernRoboticsI2cRangeSensor myRange = null;        // MR Range to be mapped

        try
        {
            myRange = (ModernRoboticsI2cRangeSensor)_robot.hardware.get(NAME);
        }
        catch(Exception e)
        {
            Log.e("Error" , "Cannot map MR Range sensor with name " + NAME);
        }

        return myRange;
    }


    /**
     * Attempts to map a Modern Robotics color sensor. Result is returned, allowing an assignment
     * operator to map the desired color sensor. Sends a log error message if failed.
     *
     * @param NAME Name of the Color sensor as it appears in the configuration file
     * @return Returns a color sensor, mapped if succeeded, null if failed
     */
    public ModernRoboticsI2cColorSensor mapMRColor(final String NAME , final int ADDRESS)
    {
        ModernRoboticsI2cColorSensor myColor = null;        // MR color to be mapped

        try
        {
            myColor = (ModernRoboticsI2cColorSensor)_robot.hardware.colorSensor.get(NAME);
            myColor.setI2cAddress(I2cAddr.create8bit(ADDRESS));
        }
        catch(Exception e)
        {
            Log.e("Error" , "Cannot map MR Color sensor with name " + NAME);
        }

        return myColor;
    }


    /**
     * Attempts to map a Modern Robotics ODS. Result is returned, allowing an assignment
     * operator to map the desired ODS. Sends a log error message if failed.
     *
     * @param NAME Name of the ODS as it appears in the configuration file
     * @return Returns a ODS, mapped if succeeded, null if failed
     */
    public ModernRoboticsAnalogOpticalDistanceSensor mapMRODS(final String NAME)
    {
        ModernRoboticsAnalogOpticalDistanceSensor myODS = null;     // MR ODS to be mapped

        try
        {
            myODS = (ModernRoboticsAnalogOpticalDistanceSensor)
                    _robot.hardware.opticalDistanceSensor.get(NAME);
        }
        catch(Exception e)
        {
            Log.e("Error" , "Cannot map MR ODS with name " + NAME);
        }

        return myODS;
    }


    /**
     * Attempts to map a Modern Robotics Touch sensor. Result is returned, allowing an assignment
     * operator to map the desired range sensor. Sends a log error message if failed.
     *
     * @param NAME Name of the Touch sensor as it appears in the configuration file
     * @return Returns a touch sensor, mapped if succeeded, null if failed
     */
    public ModernRoboticsTouchSensor mapMRTouch(final String NAME)
    {
        ModernRoboticsTouchSensor myTouch = null;        // MR touch to be mapped

        try
        {
            myTouch = (ModernRoboticsTouchSensor)_robot.hardware.touchSensor.get(NAME);
        }
        catch(Exception e)
        {
            Log.e("Error" , "Cannot map MR Touch sensor with name " + NAME);
        }

        return myTouch;
    }


    /**
     * Attempts to map a Lego Ultrasonic sensor. Result is returned, allowing an assignment
     * operator to map the desired ultrasonic sensor. Sends a log error message if failed.
     *
     * @param NAME Name of the Ultrasonic sensor as it appears in the configuration file
     * @return Returns a ultrasonic sensor, mapped if succeeded, null if failed
     */
    public UltrasonicSensor mapLegoUltra(final String NAME)
    {
        UltrasonicSensor myUltra = null;                // LEGO ultrasonic to be mapped

        try
        {
            myUltra = _robot.hardware.ultrasonicSensor.get(NAME);
        }
        catch(Exception e)
        {
            Log.e("Error" , "Cannot map Lego Ultrasonic sensor with name " + NAME);
        }

        return myUltra;
    }
}