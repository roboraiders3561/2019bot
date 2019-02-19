package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the CargoShooter subsystem where anything related to the cargo shooter is found
 */
public class CargoShooter extends Subsystem {

    public CargoShooter() {}

    /*
     * Left empty as there is no default command to run
     */
    @Override
    protected void initDefaultCommand() {}

    /*
     * Shoots cargo
     */
    public void shoot() {
        RobotMap.cargoShooterPiston.set(false);
    }

    /*
     * Retracts cargo shooter
     */
    public void reset() {
        RobotMap.cargoShooterPiston.set(true);
    }
}