package me.marcusslover.caverns.event;

import me.marcusslover.caverns.utils.IColorable;

public abstract class Event implements IColorable {
    private static final EventManager EVENT_MANAGER = EventManager.getInstance();
    private final String name;
    private String displayName;

    int timer = 0;

    public Event(String name) {
        this.name = name;
        this.displayName = this.toColor(name);
    }

    public void onEverySecond() {
        timer -= 1;
        if (timer <= 0) {
            this.onEnd();
            EVENT_MANAGER.stopEvent(this);
        } else {
            EVENT_MANAGER.bossBar.setProgress(timer * 100.0d / 60.0d);
        }
    }

    public abstract void onStart();
    public abstract void onEnd();

    public void setDisplayName(String displayName) {
        this.displayName = this.toColor(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }
}
