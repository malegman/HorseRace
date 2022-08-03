package race.common;

import java.util.ArrayList;
import java.util.List;

public class RaceControl {

    private final int countLaps;
    private final long lapTime;
    private final int countParticipants;
    private final List<RaceParticipant> participants;
    private final List<RaceParticipant> startedParticipants;
    private final List<List<RaceParticipant>> lapParticipants;
    private final List<RaceParticipant> finishedParticipants;

    private int countFinishedParticipants;

    public RaceControl(int countParticipants, int countLaps, long lapTime) {
        this.countLaps = countLaps;
        this.lapTime = lapTime;
        this.countParticipants = countParticipants;
        this.participants = new ArrayList<>(countParticipants);
        this.startedParticipants = new ArrayList<>(countParticipants);
        this.lapParticipants = new ArrayList<>(countLaps);
        for (int lap = 0; lap < countLaps; lap++) {
            this.lapParticipants.add(new ArrayList<>(countParticipants));
        }
        this.finishedParticipants = new ArrayList<>(countParticipants);
    }

    public int getCountLaps() {
        return this.countLaps;
    }

    public long getLapTime() {
        return this.lapTime;
    }

    public void addParticipant(RaceParticipant participant) {
        participant.setRaceControl(this);
        this.participants.add(participant);
    }

    public void startRace(Runnable runnable) {
        runnable.run();
        this.countFinishedParticipants = 0;
        this.participants.forEach(RaceParticipant::start);
    }

    public void participantStart(RaceParticipant participant) {
        this.startedParticipants.add(participant);
    }

    public void participantLap(RaceParticipant participant, int lap) {
        this.lapParticipants.get(lap).add(participant);
    }

    public void participantFinish(RaceParticipant participant) {
        this.finishedParticipants.add(participant);
        this.countFinishedParticipants++;

        if (this.countFinishedParticipants == this.countParticipants) {
            this.printResult();
        }
    }

    public void printResult() {

        System.out.println("\n!!! Race Finish !!!\n");
        System.out.println("Race result");

        StringBuilder result = new StringBuilder();
        result.append("Positions | Start Positions | Finish Positions\n");

        for (int position = 0; position < this.countParticipants; position++) {
            result.append("----------+-----------------+-----------------\n")
                    .append(String.format("%9d | %15s | %16s\n",
                            position + 1,
                            this.startedParticipants.get(position).participantName,
                            this.finishedParticipants.get(position).participantName));
        }

        System.out.println(result);
    }
}
