package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.UltraSensor;

public class AutoClose extends CommandBase{
    private final Claw claw;
    private final UltraSensor sensor;
    private double sensorDist = 0.0;

    public AutoClose(Claw claw, UltraSensor sensor){
        this.claw = claw;
        this.sensor = sensor;

        addRequirements(claw);
    }

    @Override
    public void execute(){
        sensorDist = sensor.range();
        System.out.println("autoclosecommand is running");
        if(sensorDist < 140 && sensorDist > 100){
            //claw.crab(true);
        }
    }
    
}
