package me.marcusslover.caverns.game.player;

import me.marcusslover.caverns.api.data.PlayerContainer;
import me.marcusslover.caverns.api.item.Item;
import me.marcusslover.caverns.api.menu.IMenu;
import me.marcusslover.caverns.api.menu.Menu;
import me.marcusslover.caverns.api.menu.MenuContext;
import me.marcusslover.caverns.api.menu.MenuDetails;
import me.marcusslover.caverns.api.utils.NumberUtil;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@MenuDetails(name = "Menu", cancelled = true, ignoreAir = true)
public class PlayerMenu implements IMenu {
    private static final Item EMPTY = new Item(Material.BLACK_STAINED_GLASS_PANE, 1, "&r");

    @Override
    public void openMenu(Player player) {
        PlayerContainer container = PlayerContainer.get(player);
        if (container == null) {
            return;
        }

        Menu menu = new Menu("Menu", 5*9);
        for (int i = 0; i < menu.size; i++) {
            menu.setItem(i, EMPTY.clone());
        }
        Item head = new Item(Material.PLAYER_HEAD, 1, "&e" + player.getName()+"'s Stats");
        head.setSkull(player);
        List<String> lore = new ArrayList<>();
        for (Statistics statistic : Statistics.values()) {
            String key = statistic.toString();
            int statisticValue = container.getInteger(key, 0);

            // Speed starts from 100%
            if (statistic.equals(Statistics.WALK_SPEED)) {
                statisticValue += 100;
            }

            String symbol = statistic.color + statistic.symbol + " &f";
            String name = WordUtils.capitalizeFully(key.replaceAll("_", " "));
            String value = NumberUtil.toFancyNumber(statisticValue);

            if (statistic.equals(Statistics.WALK_SPEED)) {
                value += "%";
            }
            if (statistic.equals(Statistics.SWING_SPEED)) {
                value += " ticks";
            }

            lore.add(" &r" + symbol + name + ": " + statistic.color + value);
        }
        head.setLore(lore);
        menu.setItem(13, head);


        menu.open(player);
    }

    @Override
    public void onClick(MenuContext context) {

    }
}
