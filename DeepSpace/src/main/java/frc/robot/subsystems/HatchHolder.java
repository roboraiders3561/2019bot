package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the HatchHolder subsystem where anything related to hatches is found
 */
public class HatchHolder extends Subsystem {

    public HatchHolder() {}

    /*
     * Left empty as there is no default command to run
     */
    @Override
    protected void initDefaultCommand() {}

    /*
     * Opens the hatch holder piston
     */
    public void open() {
        RobotMap.hatchHolderPiston.set(true);
    }

    /*
     * Closes the hatch holder piston
     */
    public void close() {
        RobotMap.hatchHolderPiston.set(false);
    }

    /*
     * Moves the hatch holder mechanism forward
     */
    public void slideForward() {
        RobotMap.hatchSliderPiston.set(true);
    }

    /*
     * Moves the hatch holder mechanism back
     */
    public void slideBack() {
        RobotMap.hatchSliderPiston.set(false);
    }
}