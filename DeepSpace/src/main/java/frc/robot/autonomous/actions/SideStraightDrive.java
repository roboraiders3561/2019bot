package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command moves the robot sideways a certain distance
 */
public class SideStraightDrive extends Command {

    /* Initialize variables */
    boolean rightMovement;
    double driveDistance;
    double startDistance;
    double endDistance;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public SideStraightDrive(boolean right, double userFeet, double userInches) {

        /* Require the Drive subsystem */
        requires(Robot.driveTrain);

        /* Sets wether the movement is right or not */
        rightMovement = right;

        /* Determine distance to move in feet */
        driveDistance = (userFeet + (userInches / 12));
    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {

        if (rightMovement) {
            /*
             * Set end distance by adding the distance to move to the current distance
             */
            endDistance = Robot.driveTrain.getStrafeDistance() + driveDistance;
        } else {
            /*
             * Set end distance by subtracting the distance to move to the current distance
             */
            endDistance = Robot.driveTrain.getStrafeDistance() - driveDistance;
        }

        /* Determine the starting (current) distance */
        startDistance = Robot.driveTrain.getStrafeDistance();

        /* Print debug information in console */
        System.out.println("Drive Distance: " + driveDistance);
        System.out.println("Starting Distance: " + Robot.driveTrain.getStrafeDistance());
        System.out.println("End Distance: " + endDistance);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        /* Get distance moved since command started */
        double currentDistance = Robot.driveTrain.getStrafeDistance() - startDistance;

        /* Set a strafe drive movement with speed returning from getSpeed() */
        Robot.driveTrain.strafeyBoisDrive(getSpeed(currentDistance, driveDistance));

        /* Print debug information in console */
        System.out.println("Distance: " + Robot.driveTrain.getStrafeDistance());
    }

    /*
     * Determines when to end the command
     */
    @Override
    protected boolean isFinished() {
        if (rightMovement) {
            /* Command ends if current distance is greater than the end distance */
            return (Robot.driveTrain.getStrafeDistance() >= endDistance);
        } else {
            /*
             * Command ends if current distance is less than the end distance, as it is
             * moving left
             */
            return (Robot.driveTrain.getStrafeDistance() <= endDistance);
        }
    }

    /*
     * Stops strafeybois when command ends
     */
    protected void end() {
        Robot.driveTrain.stopStrafeyBois();
    }

    /*
     * Ends the command of autonomous is stopped or interrupted
     */
    protected void interrupted() {
        end();
    }

    /*
     * Calculates speed based on distance to target. Demo of this function can be
     * found here: https://www.desmos.com/calculator/mjxmn8nmug
     */
    private double getSpeed(double current, double total) {
        double speed = (-1 / ((-1 - ((total - 1) / 2)) * (-1 - ((total - 1) / 2))));
        speed = speed * (current - ((total - 1) / 2)) * (current - ((total - 1) / 2));
        speed = speed + 1;
        if (speed < RobotMap.minDriveSpeed) {
            speed = RobotMap.minDriveSpeed;
        }
        return speed;
    }
}