package isp.lab11;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATC {
    private Map<String, Aircraft> aircraftById = new HashMap<>();

    public void addAircraft(String id) {
        Aircraft aircraft = new Aircraft(id);
        aircraft.start();
        aircraftById.put(id, aircraft);
        System.out.println("Aircraft with id " + id + " added");
    }

    public void sendCommand(String id, AtcCommand command) {
        Scanner scanner = new Scanner(System.in);
        Aircraft aircraft = aircraftById.get(id);
        if(command.getCommand().equals(AtcCommandType.TAKEOFF_CMD)) {
            if(aircraft.isFlying()){
                System.out.println("The airplane is already flying");
            }
            else {
                System.out.println("Insert the altitude in meters(ex:1000): ");
                int altitude = scanner.nextInt();
                aircraft.setTakeOffCommand(new TakeOffCommand(altitude));
                aircraft.setFlying(true); // daca a decolat doar atunci va putea ateriza
                aircraft.receiveAtcCommand(command);
                System.out.println("Aircraft with id " + id + " received command " + command);
            }
        }

       else {
           if(!aircraft.isFlying()){
               System.out.println("The airplane need to take off first!");
           }
           else {
               if (command.getCommand().equals(AtcCommandType.LAND_CMD)) {
                   // Presupun ca avionul va ramane la aceeasi altitudine la care a decolat cand
                   // voi calcula timpul in care aterizeaza
                   aircraft.setLandCommand(new LandCommand(aircraft.getTakeOffCommand().getAltitude()));
                   aircraft.receiveAtcCommand(command);
                   aircraft.setFlying(false);
                   System.out.println("Aircraft with id " + id + " received command " + command);
               } else {
                   aircraft.receiveAtcCommand(command);
                   System.out.println("Aircraft with id " + id + " received command " + command);
               }
           }
        }
    }

    public void showAircrafts() {
        aircraftById.forEach((id, aircraft) ->
                System.out.println("Aircraft ID: " + id + ", State: " + aircraft.getState()));
    }
}

class AtcCommand {
    private AtcCommandType command;


    public AtcCommand(AtcCommandType command) {
        this.command = command;
    }
    public AtcCommand(){}

    public AtcCommandType getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "AtcCommand{" +
                "command='" + command + '\'' +
                '}';
    }
}



enum AtcCommandType {
    TAKEOFF_CMD,
    LAND_CMD
}


