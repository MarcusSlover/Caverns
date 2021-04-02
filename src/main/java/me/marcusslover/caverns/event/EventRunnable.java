package me.marcusslover.caverns.event;

public class EventRunnable implements Runnable {
    private static final EventManager EVENT_MANAGER = EventManager.getInstance();
    private static EventRunnable instance;

    private byte seconds = 0;

    EventRunnable() {
        instance = this;
    }

    @Override
    public void run() {
        // If an event is happening
        if (EVENT_MANAGER.isHappening()) {
            // Call the event timer
            EVENT_MANAGER.eventHappening.onEverySecond();
            return;
        }

        seconds += 1;
        if (seconds >= 120) {
            if (Math.random() <= 0.333) {
                seconds = 0;
                EVENT_MANAGER.startEvent();
            }
        }
    }

    public static EventRunnable getInstance() {
        return instance;
    }
}
