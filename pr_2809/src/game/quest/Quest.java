package game.quest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PetQuest.class, name = "pet"),
        @JsonSubTypes.Type(value = CollectQuest.class, name = "collect"),
        @JsonSubTypes.Type(value = EscortQuest.class, name = "escort")
})

public abstract class Quest {
    private final String name;
    private  final  int reward;
    private  boolean completed;

    protected  Quest(String name, int reward) {
        this.name = name;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public int getReward() {
        return reward;
    }

    public boolean isCompleted() {
        return  completed;
    }

    protected  void complete() {
        completed = true;
    }

    public boolean onPet(String target) {
        return false;
    }

    public boolean inCollect(String item) {
        return false;
    }

    public boolean onArrive(String place) {
        return  false;
    }

    public abstract String describe();
}
