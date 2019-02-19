package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command runs indefinetely, and controls the Drive subsystem from user input (joystick)
 */
public class ArcadeDrive extends Command {

    /*
     * State the required driveTrain subsystem
     */
    public ArcadeDrive() {
        requires(Robot.driveTrain);
    }

    /*
     * This function is what is done when the command is run
     */
    protected void execute() {

        /* Define joystick axis */
        double xAxis = OI.driver.getRawAxis(1);
        double yAxis = OI.driver.getRawAxis(2);
        double strafeAxis = OI.driver.getRawAxis(0);

        /* Sets the arcadeDrive to the 2 drive axis and strafe axis */
        Robot.driveTrain.arcadeDrive(xAxis, yAxis, strafeAxis);
    }

    /*
     * Makes command run forever
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

}