package me.marcusslover.caverns.event.events;

import me.marcusslover.caverns.event.Event;

public class ShopDiscount extends Event {
    double discount = 0.0d;

    public ShopDiscount() {
        super("&e10% Shop Discount");
    }

    @Override
    public void onStart() {
        discount = 0.1d;
        if (Math.random() <= 0.2) {
            discount = 0.3d;
            this.setDisplayName("&e30% Shop Discount");
        }
        if (Math.random() <= 0.1) {
            discount = 0.6d;
            this.setDisplayName("&e60% Shop Discount");
        }
    }

    @Override
    public void onEnd() {
        // Reset the name to the previous one
        this.setDisplayName(this.getName());
    }
}
