package game.quest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EscortQuest extends Quest {
    private final String npc;
    private final String destination;

    @JsonCreator
    public EscortQuest(@JsonProperty("name") String name,
                       @JsonProperty("npc") String npc,
                       @JsonProperty("destination") String destination,
                       @JsonProperty("reward") int reward) {
        super(name, reward);
        this.npc = npc;
        this.destination = destination;
    }

    @Override
    public boolean onArrive(String place) {
        if (isCompleted() || !destination.equalsIgnoreCase(place)) {
            return false;
        }
        complete();
        return true;
    }

    @Override
    public String describe() {
        return getName() + ": довести " +npc + " до места " + destination;
    }
}
