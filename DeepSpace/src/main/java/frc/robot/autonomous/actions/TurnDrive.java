package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command turns the robot a certain amount of degrees over a certain radius
 */
public class TurnDrive extends Command {

    /* Initialize variables */
    boolean rightTurn;
    double turnRadius;
    double turnDegrees;
    double startDegrees;
    double endDegrees;

    /*
     * Declares public function that takes direction, degrees, and radius in inches
     */
    public TurnDrive(boolean right, double degrees, double radius) {

        /* Require the Drive subsystem */
        requires(Robot.driveTrain);

        /* Sets wether the movement is forward or not */
        rightTurn = right;

        /* Set turnDegrees variable to degrees called in function */
        turnDegrees = degrees;

        /* Set turnRadius variable to radius called in function */
        turnRadius = radius;
    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {

        if (rightTurn) {
            /*
             * Set end degrees by adding the degrees to move to the current degrees
             */
            endDegrees = RobotMap.gyro.getAngleY() + turnDegrees;
        } else {
            /*
             * Set end degrees by subtracting the degrees to move to the current degrees
             */
            endDegrees = RobotMap.gyro.getAngleY() - turnDegrees;
        }

        /* Determine the starting (current) degrees */
        startDegrees = RobotMap.gyro.getAngleY();

        /* Print debug information in console */
        System.out.println("Turn Degrees: " + turnDegrees);
        System.out.println("Starting Degrees: " + RobotMap.gyro.getAngleY());
        System.out.println("End Degrees: " + endDegrees);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        /* Get degrees moved since command started */
        double currentDegrees = RobotMap.gyro.getAngleY() - startDegrees;

        /*
         * Determine if the turn is right or left in order to determine the inner and
         * outer sides
         */
        if (rightTurn) {
            /*
             * Set a tank drive movement with speed returning from getSpeed() on left side
             * and reduced speed for right side
             */
            Robot.driveTrain.tankDrive(getSpeed(currentDegrees, turnDegrees),
                    getInnerSpeed(turnRadius, RobotMap.robotWidth, getSpeed(currentDegrees, turnDegrees)));
        } else {
            /*
             * Set a tank drive movement with reduced speed for left side and speed
             * returning from getSpeed() on right side
             */
            Robot.driveTrain.tankDrive(
                    getInnerSpeed(turnRadius, RobotMap.robotWidth, getSpeed(currentDegrees, turnDegrees)),
                    getSpeed(currentDegrees, turnDegrees));
        }

        /* Print debug information in console */
        System.out.println("Degrees: " + RobotMap.gyro.getAngleY());
    }

    /*
     * Determines when to end the command
     */
    @Override
    protected boolean isFinished() {
        if (rightTurn) {
            /* Command ends if current degrees is greater than the end degrees */
            return (RobotMap.gyro.getAngleY() >= endDegrees);
        } else {
            /*
             * Command ends if current degrees is less than the end degrees, as it is
             * turning left
             */
            return (RobotMap.gyro.getAngleY() <= endDegrees);
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
     * Calculates speed of inner side of turn
     */
    private double getInnerSpeed(Double radius, Double robotWidth, Double outsideSpeed) {
        double innerSpeed = (radius / (radius + robotWidth)) * outsideSpeed;
        return innerSpeed;
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