package game.quest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectQuest  extends Quest{
    private  final String item;
    private final int count;
    private int progress;

    @JsonCreator
    public CollectQuest(@JsonProperty("name") String name,
                        @JsonProperty("item") String item,
                        @JsonProperty("count") int count,
                        @JsonProperty("reward") int reward) {
        super(name, reward);
        this.item = item;
        this.count = count;
    }

    @Override
    public boolean inCollect(String item) {
        if (isCompleted() || !this.item.equalsIgnoreCase(item)) {
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
        return getName() + ": собрать " + item +" "+ progress + "/" + count;
    }
}
