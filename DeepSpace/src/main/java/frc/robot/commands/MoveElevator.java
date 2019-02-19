package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This is the command that moves the elevator up and down
 */
public class MoveElevator extends Command {

    /* Boolean that holds wether the elevator moves up or down */
    boolean moveUp;
    int startingPosition;

    /*
     * Require the `up` parameter to know wether to move the elevator up or down
     */
    public MoveElevator(boolean up) {
        /* Require the elevator subsystem */
        requires(Robot.elevator);

        /* Set the `moveUp` variable to the value which the command was called with */
        moveUp = up;

        /* Set the current (starting) position of the elevator to determine when to stop */
        startingPosition = Robot.elevator.getCurrentPosition();
    }

    /*
     * Executes the command
     */
    protected void execute() {

        /* Checks if the elevator should and can move up or down, and executes the right command */
        if (moveUp) {
            if (!RobotMap.topSensor.get()) {
               Robot.elevator.moveUp(); 
            }
        } else {
            if (!RobotMap.bottomSensor.get()) {
                Robot.elevator.moveDown(); 
            }
        }
    }

    /*
     * Determines if the command is finished or not
     */
    @Override
    protected boolean isFinished() {
        if (startingPosition == 0) {
            if (Robot.elevator.getCurrentPosition() != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (moveUp) {
                if (Robot.elevator.getCurrentPosition() > startingPosition && Robot.elevator.getCurrentPosition() != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (Robot.elevator.getCurrentPosition() < startingPosition && Robot.elevator.getCurrentPosition() != 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /*
     * Sets the elevator to hold once the command is finished
     */
    protected void end() {
		Robot.elevator.hold();
	}

    /*
     * Ends the command if the command is interrupted
     */
	protected void interrupted() {
		end();
	}
}