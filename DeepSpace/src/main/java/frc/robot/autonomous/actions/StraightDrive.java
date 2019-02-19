package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command moves the robot straight a certain distance
 */
public class StraightDrive extends Command {

    /* Initialize variables */
    boolean forwardMovement;
    double driveDistance;
    double startDistanceL;
    double startDistanceR;
    double endDistanceL;
    double endDistanceR;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public StraightDrive(boolean forward, double userFeet, double userInches) {

        /* Require the Drive subsystem */
        requires(Robot.driveTrain);

        /* Sets wether the movement is forward or not */
        forwardMovement = forward;

        /* Determine distance to move in feet */
        driveDistance = (userFeet + (userInches / 12));
    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {

        if (forwardMovement) {
            /*
             * Set end distance for both sides by adding the distance to move to the current
             * distance
             */
            endDistanceL = Robot.driveTrain.getLeftDistance() + driveDistance;
            endDistanceR = Robot.driveTrain.getRightDistance() + driveDistance;
        } else {
            /*
             * Set end distance for both sides by subtracting the distance to move to the
             * current distance
             */
            endDistanceL = Robot.driveTrain.getLeftDistance() - driveDistance;
            endDistanceR = Robot.driveTrain.getRightDistance() - driveDistance;
        }

        /* Determine the starting (current) distance for both sides */
        startDistanceR = Robot.driveTrain.getRightDistance();
        startDistanceL = Robot.driveTrain.getLeftDistance();

        /* Print debug information in console */
        System.out.println("Drive Distance: " + driveDistance);
        System.out.println("Starting Distance: " + Robot.driveTrain.getRightDistance());
        System.out.println("Left End Distance: " + endDistanceL);
        System.out.println("Right End Distance: " + endDistanceR);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        /* Get distance moved since command started */
        double currentL = Robot.driveTrain.getLeftDistance() - startDistanceL;
        double currentR = Robot.driveTrain.getRightDistance() - startDistanceR;

        /* Find an average of both distances moved to calculate an appropriate speed */
        double averageDistance = (currentL + currentR) / 2;

        /* Set a tank drive movement with speed returning from getSpeed() */
        Robot.driveTrain.tankDrive(getSpeed(averageDistance, driveDistance), getSpeed(averageDistance, driveDistance));

        /* Print debug information in console */
        System.out.println("Distance: " + Robot.driveTrain.getRightDistance());
    }

    /*
     * Determines when to end the command
     */
    @Override
    protected boolean isFinished() {
        if (forwardMovement) {
            /* Command ends if current distance is greater than the end distance */
            return (Robot.driveTrain.getRightDistance() >= endDistanceR
                    || Robot.driveTrain.getLeftDistance() >= endDistanceL);
        } else {
            /*
             * Command ends if current distance is less than the end distance, as it is
             * moving back
             */
            return (Robot.driveTrain.getRightDistance() <= endDistanceR
                    || Robot.driveTrain.getLeftDistance() <= endDistanceL);
        }
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.driveTrain.stopTank();
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