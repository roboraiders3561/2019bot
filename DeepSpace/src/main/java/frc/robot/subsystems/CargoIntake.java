package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the CargoIntake subsystem where anything related to the cargo intake is found
 */
public class CargoIntake extends Subsystem {

    /* Initialize and define the cargo intake motor and solenoid from RobotMap */
    private final WPI_VictorSPX cargoIntake = RobotMap.cargoIntake;
    private final Solenoid cargoIntakePiston = RobotMap.cargoIntakePiston;

    public CargoIntake() {}

    /*
     * Left empty as there is no default command to run
     */
    @Override
    protected void initDefaultCommand() {}

    /*
     * Turns on the intake motor and piston
     */
    public void on() {
        cargoIntake.set(1);
        cargoIntakePiston.set(true);
    }

    /*
     * Turns off the intake motor and piston
     */
    public void off() {
        cargoIntake.set(0);
        cargoIntakePiston.set(false);
    }
}