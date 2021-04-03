package me.marcusslover.caverns.api.event.events;

import me.marcusslover.caverns.api.event.Event;

public class MorePierce extends Event {
    int pierce = 0;

    public MorePierce() {
        super("%hex(#59B3B3)+1 Pierce");
    }

    @Override
    public void onStart() {
        pierce = 1;
        if (Math.random() <= 0.2) {
            pierce += 50;
            this.setDisplayName("%hex(#59B3B3)Unlimited Pierce");
        }
    }

    @Override
    public void onEnd() {
        // Reset the name to the previous one
        this.setDisplayName(this.getName());
    }
}
