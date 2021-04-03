package me.marcusslover.caverns.api.menu;

import org.bukkit.entity.Player;

public interface IMenu {
    void openMenu(Player player);
    void onClick(MenuContext context);

    static void open(Player player, Class<? extends IMenu> aClass) {
        MenuManager menuManager = MenuManager.getInstance();
        for (IMenu iMenu : menuManager.getMenuList()) {
            Class<? extends IMenu> aClass1 = iMenu.getClass();
            if (aClass1.equals(aClass)) {
                iMenu.openMenu(player);
            }
        }
    }
}
