package me.marcusslover.caverns.event;

import me.marcusslover.caverns.event.events.MoreDrops;
import me.marcusslover.caverns.event.events.MorePierce;
import me.marcusslover.caverns.event.events.ShopDiscount;
import me.marcusslover.caverns.utils.ColorUtil;
import me.marcusslover.caverns.utils.IManager;
import me.marcusslover.caverns.utils.NumberUtil;
import me.marcusslover.caverns.utils.TaskUtil;
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
        bossBar.setProgress((60.0d - eventHappening.timer) * 100.0d / 60.0d);
    }

    public void startEvent() {
        List<Event> all = getAll();
        eventHappening = all.get(NumberUtil.getRandomBetween(1, all.size()));
        eventHappening.timer = 60;
        setHappening(true); // This change will be detected by the EventRunnable

        String displayName = eventHappening.getDisplayName();
        String bossBarText = ColorUtil.toColor("%hex(#0080FF)Ongoing event: " + displayName + "%hex(#0080FF)!");
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
