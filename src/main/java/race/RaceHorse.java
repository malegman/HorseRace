package race;

import race.common.RaceParticipant;

public class RaceHorse extends RaceParticipant {

    public RaceHorse(String name) {
        super(name);
    }

    @Override
    public void processStart() {

        System.out.printf("!! START !! Horse %s started!\n", this.participantName);
        this.raceControl.participantStart(this);
    }

    @Override
    public void processLap(int lap) {

        System.out.printf("-- RUNNING -- Horse %s ran %d lap!\n", this.participantName, lap);
        this.raceControl.participantLap(this, lap);
    }

    @Override
    public void processFinish() {

        System.out.printf("!! FINISH !! Horse %s finished!\n", this.participantName);
        this.raceControl.participantFinish(this);
    }
}
