package game.quest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PetQuest extends Quest {
    private final String target;
    private  final int count;
    private  int progress;

    @JsonCreator
    public PetQuest(@JsonProperty("name") String name,
                    @JsonProperty("target") String target,
                    @JsonProperty("count") int count,
                    @JsonProperty("reward") int reward) {
        super(name, reward);
        this.target = target;
        this.count = count;
    }

    @Override
    public boolean onPet(String target) {
        if (isCompleted() || !this.target.equalsIgnoreCase(target)) {
            return false;
        }
        progress++;
        if (progress >= count) {
            complete();
        }
        return true;
    }

    @Override
    public String describe() {
        return getName() + ": погладить " + target +" "+ progress + "/" + count;
    }
}
