package race.common;

import java.util.Objects;

public abstract class RaceParticipant extends Thread {

    private static final Object shredObject = new Object();

    protected final String participantName;
    protected RaceControl raceControl;

    public RaceParticipant(String participantName) {
        this.participantName = participantName;
    }

    public void setRaceControl(RaceControl raceControl) {
        this.raceControl = Objects.requireNonNull(raceControl);
    }

    public abstract void processStart() throws Exception;

    public abstract void processLap(int lap) throws Exception;

    public abstract void processFinish() throws Exception;

    private void modifySleep(long lapTime) {
        synchronized (shredObject) {
            try {
                shredObject.wait(lapTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

        try {
            this.processStart();

            final var countLaps = this.raceControl.getCountLaps();
            final var lapTime = this.raceControl.getLapTime();
            for (int lap = 1; lap < countLaps; lap++) {
                modifySleep(lapTime);
                this.processLap(lap);
            }

            modifySleep(lapTime);
            this.processFinish();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
