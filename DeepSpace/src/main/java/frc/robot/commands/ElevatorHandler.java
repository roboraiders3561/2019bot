package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

/*
 * This is the command that handles POV controls to move elevator
 */
public class ElevatorHandler extends Command {

    /* Set default cooldown */
    int cooldown = 0;

    public ElevatorHandler() {
    }

    /*
     * Executes the command
     */
    protected void execute() {
        /* Makes sure the cooldown has ended to move elevator again */
        if (cooldown == 0) {
            if (OI.driver.getPOV() == 0) {
                /* Start cooldown*/
                cooldown++;

                /* POV is up, and moves elevator up */
                Command command = new MoveElevator(true);
                command.start();
                command.close();
            } else if (OI.driver.getPOV() == 180) {
                /* Start cooldown*/
                cooldown++;

                /* POV is down, and moves elevator down */
                Command command = new MoveElevator(false);
                command.start();
                command.close();
            }
        } else {
            /* Checks if cooldown is enough to reset, or keeps counting */
            if (cooldown >= 50) {
                cooldown = 0;
            } else {
                cooldown++;
            }
        }
    }

    /*
     * Makes command run forever
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

}