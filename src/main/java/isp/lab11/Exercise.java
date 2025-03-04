package isp.lab11;

import java.util.Scanner;

public class Exercise {

    public static boolean displayMenu(ATC atc) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================================");
        System.out.println("Welcome to Airtraffic Control System command center");
        System.out.println("1. Add aircraft");
        System.out.println("2. Take off command");
        System.out.println("3. Land command");
        System.out.println("4. Show aircrafts");
        System.out.println("5. Exit");

        System.out.print("Enter your command: ");
        String command = scanner.nextLine();
        String aid;
        boolean stop = false;

        switch (command) {
            case "1":
                System.out.print("Enter aircraft id: ");
                aid = scanner.nextLine();
                atc.addAircraft(aid);
                break;
            case "2":
                System.out.print("Send take off command to aircraft id: ");
                aid = scanner.nextLine();
                atc.sendCommand(aid, new AtcCommand(AtcCommandType.TAKEOFF_CMD));
                break;
            case "3":
                System.out.print("Send land command to aircraft id: ");
                aid = scanner.nextLine();
                atc.sendCommand(aid, new AtcCommand(AtcCommandType.LAND_CMD));
                break;
            case "4":
                atc.showAircrafts();
                break;
            case "5":
                stop = true;
        }

        return stop;
    }

    public static void main(String[] args) {
        ATC atc = new ATC();
        boolean stop = false;

        while (!stop) {
            stop = displayMenu(atc);
        }
    }
}
