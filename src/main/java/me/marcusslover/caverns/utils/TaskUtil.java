package me.marcusslover.caverns.utils;

import me.marcusslover.caverns.Caverns;
import org.bukkit.Bukkit;

import java.util.function.Consumer;

public class TaskUtil {
    private static final Caverns CAVERNS = Caverns.getInstance();

    public static void runTimer(Runnable runnable, long seconds) {
        Bukkit.getScheduler().runTaskTimer(CAVERNS, runnable, 0L, seconds);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(CAVERNS, runnable);
    }

    public static void runAsync(Runnable runnable, Consumer<Long> whenDone) {
        Bukkit.getScheduler().runTaskAsynchronously(CAVERNS, () -> {
            long currentTime = System.currentTimeMillis();
            runnable.run();
            runSync(() -> whenDone.accept(System.currentTimeMillis() - currentTime));
        });
    }

    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(CAVERNS, runnable);
    }
}
