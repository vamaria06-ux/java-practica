package game.hero;

import game.quest.Quest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Hero {
    private Position position;
    private MovingType moving;
    private int gold;
    private final List<Quest> activeQuests = new ArrayList<>();
    private final List<Quest> doneQuests = new ArrayList<>();

    public Hero(Position start, MovingType moving) {
        this.position = start;
        this.moving = moving;
    }

    public void setMoving(MovingType moving) {
        this.moving = moving;
    }

    public void move(int dx, int dy) {
        position = moving.move(position, dx, dy);
    }

    public Position getPosition() {
        return position;
    }

    public int getGold() {
        return gold;
    }

    public void addQuest(Quest quest) {
        activeQuests.add(quest);
    }

    public List<Quest> getActiveQuests() {
        return Collections.unmodifiableList(activeQuests);
    }

    public List<Quest> getDoneQuests() {
        return Collections.unmodifiableList(doneQuests);
    }

    public boolean pet(String target) {
        boolean counted = false;
        for (Quest quest : activeQuests) {
            counted |= quest.onPet(target);
        }
        return counted;
    }

    public boolean collect(String item) {
        boolean counted = false;
        for (Quest quest : activeQuests) {
            counted |= quest.inCollect(item);
        }
        return counted;
    }

    public boolean arrive(String place) {
        boolean counted = false;
        for (Quest quest : activeQuests) {
            counted |= quest.onArrive(place);
        }
        return counted;
    }

    public List<Quest> claimRewards() {
        List<Quest> finished = new ArrayList<>();
        Iterator<Quest> it = activeQuests.iterator();
        while (it.hasNext()) {
            Quest quest = it.next();
            if (quest.isCompleted()) {
                gold += quest.getReward();
                it.remove();
                doneQuests.add(quest);
                finished.add(quest);
            }
        }
        return finished;
    }
}