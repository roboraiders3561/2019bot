package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/*
 * This is the command that slides the hatch holder forward and back
 */
public class SlideHatchHolder extends Command {

    /* Boolean that holds wether to slide the holder forward or back */
    boolean slideForward;

    /*
     * Require the `forward` parameter to slide the holder forward or back, and requires the HatchHolder subsystem
     */
    public SlideHatchHolder(boolean forward) {
        slideForward = forward;
		requires(Robot.hatchHolder);
    }

     /*
     * Executes the command
     */
    protected void execute() {

        /*
         * Checks if the holder should slide forward or back, and executes the right command
         */
        if (slideForward) {
            Robot.hatchHolder.slideForward();
        } else {
            Robot.hatchHolder.slideBack();
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