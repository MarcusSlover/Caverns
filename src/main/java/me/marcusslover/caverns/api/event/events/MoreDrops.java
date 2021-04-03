package me.marcusslover.caverns.api.event.events;

import me.marcusslover.caverns.api.event.Event;

public class MoreDrops extends Event {
    double multiplier = 0.0d;

    public MoreDrops() {
        super("%hex(FF9933)+30% Drops");
    }

    @Override
    public void onStart() {
        multiplier = 0.3d;
        if (Math.random() <= 0.2) {
            multiplier = 0.6;
            this.setDisplayName("%hex(FF9933)+60% Drops");
        }
        if (Math.random() <= 0.1) {
            multiplier = 1;
            this.setDisplayName("%hex(FF9933)+100% Drops");
        }
    }

    @Override
    public void onEnd() {
        // Reset the name to the previous one
        this.setDisplayName(this.getName());
    }
}
