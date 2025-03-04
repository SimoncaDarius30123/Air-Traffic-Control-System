package isp.lab11;

public class LandCommand extends AtcCommand{
    private int altitude;

    public LandCommand(int altitude){
        this.altitude = altitude;
    }

    public int getAltitude() {
        return altitude;
    }
}
