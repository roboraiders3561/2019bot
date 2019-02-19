package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/*
 * This is the command that shoots and resets the cargo shooter
 */
public class MoveCargoShooter extends Command {

    /* Boolean that holds wether to shoot or reset the shooter */
    boolean shootCargo;

    /*
     * Require the `shoot` parameter to know wether to open or close the holder, and requires the CargoShooter subsystem
     */
    public MoveCargoShooter(boolean shoot) {
        shootCargo = shoot;
		requires(Robot.cargoShooter);
    }

     /*
     * Executes the command
     */
    protected void execute() {

        /*
         * Checks if the shooter should shoot or reset, and executes the right command
         */
        if (shootCargo) {
            Robot.cargoShooter.shoot();
        } else {
            Robot.cargoShooter.reset();
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