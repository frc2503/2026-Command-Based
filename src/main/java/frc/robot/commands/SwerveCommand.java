package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class SwerveCommand extends Command {
    private SwerveSubsystem swerveSubsystem;
    private DoubleSupplier x;
    private DoubleSupplier y;
    private DoubleSupplier rot;
    private BooleanSupplier fieldOriented;

    public SwerveCommand(SwerveSubsystem swerveSubsystem, DoubleSupplier x, DoubleSupplier y, DoubleSupplier rot, BooleanSupplier fieldOriented) {
        this.swerveSubsystem = swerveSubsystem;
        this.x = x;
        this.y = y;
        this.rot = rot;
        this.fieldOriented = fieldOriented;

        addRequirements(swerveSubsystem);
    }

    @Override
    public void execute() {
        swerveSubsystem.drive(x.getAsDouble(), y.getAsDouble(), rot.getAsDouble(), fieldOriented.getAsBoolean());
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.stop();
    }
}
