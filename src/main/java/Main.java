import race.RaceHorse;
import race.common.RaceControl;

public class Main {

    private static final int COUNT_HORSES = 8;
    private static final int COUNT_LAPS = 6;
    private static final long LAP_TIME = 500;

    public static void main(String[] args) {

        RaceControl horseRaceControl = new RaceControl(COUNT_HORSES, COUNT_LAPS, LAP_TIME);

        for (int i = 0; i < COUNT_HORSES; i++) {
            horseRaceControl.addParticipant(new RaceHorse("Horse " + i));
        }

        horseRaceControl.startRace(() -> System.out.println("\n!!! HORSE RACE STARTED !!!\n"));
    }
}
