package me.marcusslover.caverns.api.menu;

public @interface MenuDetails {
    String name();
    boolean cancelled() default false;
    boolean ignoreAir() default false;
}
