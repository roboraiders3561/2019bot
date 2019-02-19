package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/*
 * This is the command that opens and closes the hatch holder
 */
public class MoveHatchHolder extends Command {

    /* Boolean that holds wether to open or close the holder */
    boolean openHolder;

    /*
     * Require the `open` parameter to know wether to open or close the holder, and requires the HatchHolder subsystem
     */
    public MoveHatchHolder(boolean open) {
        openHolder = open;
		requires(Robot.hatchHolder);
    }

     /*
     * Executes the command
     */
    protected void execute() {

        /*
         * Checks if the holder should open or close, and executes the right command
         */
        if (openHolder) {
            Robot.hatchHolder.open();
        } else {
            Robot.hatchHolder.close();
        }
    }
    
    /*
     * Makes sure the command is only executed once
     */
    @Override
    protected boolean isFinished() {
        return true;
    }
}