package me.marcusslover.caverns.api.event;

import me.marcusslover.caverns.api.event.events.MoreDrops;
import me.marcusslover.caverns.api.event.events.MorePierce;
import me.marcusslover.caverns.api.event.events.ShopDiscount;
import me.marcusslover.caverns.api.utils.ColorUtil;
import me.marcusslover.caverns.api.utils.IManager;
import me.marcusslover.caverns.api.utils.NumberUtil;
import me.marcusslover.caverns.api.utils.TaskUtil;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements IManager<Event> {
    private static EventManager instance;
    private final List<Event> events;

    boolean happening;
    Event eventHappening;
    BossBar bossBar;

    private EventManager() {
        instance = this;
        this.events = new ArrayList<>();
        this.happening = false;
        this.initialize();
    }

    public static String getSidebarText() {
        EventManager instance = getInstance();
        if (instance.isHappening()) {
            return instance.eventHappening.getDisplayName();
        }

        return "%hex(#FF99FF)None";
    }

    @Override
    public void initialize() {
        this.register(new MoreDrops());
        this.register(new MorePierce());
        this.register(new ShopDiscount());
    }

    public void updateBossBar(Player player) {
        if (eventHappening == null) {
            return;
        }
        if (!bossBar.getPlayers().contains(player)) {
            bossBar.addPlayer(player);
        }
    }

    public void startEvent() {
        List<Event> all = getAll();
        eventHappening = all.get(NumberUtil.getRandomBetween(1, all.size()));
        eventHappening.timer = 60;
        setHappening(true); // This change will be detected by the EventRunnable

        String displayName = eventHappening.getDisplayName();
        String bossBarText = ColorUtil.toColor("%hex(#55C1FF)Ongoing event: " + displayName + "%hex(#55C1FF)!");
        bossBar = Bukkit.createBossBar(bossBarText, BarColor.BLUE, BarStyle.SOLID);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            updateBossBar(onlinePlayer);
        }
    }

    public void stopEvent(Event event) {
        setHappening(false);
        event.onEnd();
        eventHappening = null;
        bossBar.removeAll();
        bossBar = null;
    }

    public static void startTimer() {
        EventRunnable eventRunnable = new EventRunnable();
        TaskUtil.runTimer(eventRunnable, 1L);
    }

    @Override
    public void register(Event value) {
        events.add(value);
    }

    @Override
    public List<Event> getAll() {
        return events;
    }

    public void setHappening(boolean happening) {
        this.happening = happening;
    }

    public boolean isHappening() {
        return happening;
    }

    public Event getEventHappening() {
        return eventHappening;
    }

    public static EventManager getInstance() {
        return instance == null ? new EventManager() : instance;
    }
}
