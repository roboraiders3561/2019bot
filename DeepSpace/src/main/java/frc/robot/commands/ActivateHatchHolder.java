package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/*
 * This command group executes both move and slide hatch holder simultaneously
 */
public class ActivateHatchHolder extends CommandGroup {

    /*
     * Requires boolean `extend` to determine wether to extend or retract the hatch holder, and run the correct commands
     */
    public ActivateHatchHolder(boolean extend) {
        if (extend) {
            /* Closes hatch holder, and slides the holder forwards */
            addSequential(new MoveHatchHolder(false));
            addSequential(new WaitCommand(.3));
            addSequential(new SlideHatchHolder(true));
        } else {
            /* Opens hatch holder, and slides the holder back */
            addSequential(new MoveHatchHolder(true));
            addSequential(new WaitCommand(.3));
            addSequential(new SlideHatchHolder(false));
        }
    }
}