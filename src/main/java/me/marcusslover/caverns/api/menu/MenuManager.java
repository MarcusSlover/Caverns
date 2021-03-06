package me.marcusslover.caverns.api.menu;

import me.marcusslover.caverns.Caverns;
import me.marcusslover.caverns.api.item.Item;
import me.marcusslover.caverns.api.utils.ColorUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private static MenuManager instance;
    private final List<IMenu> menuList;

    private MenuManager() {
        instance = this;
        this.menuList = new ArrayList<>();
    }

    public void addMenu(IMenu menu) {
        Class<? extends IMenu> aClass = menu.getClass();
        MenuDetails[] annotationsByType = aClass.getAnnotationsByType(MenuDetails.class);
        if (annotationsByType.length == 0) {
            return;
        }
        menuList.add(menu);

        MenuDetails menuDetails = annotationsByType[0];
        String name = menuDetails.name();
        boolean cancelled = menuDetails.cancelled();
        boolean ignoreAir = menuDetails.ignoreAir();

        Caverns.getInstance().addListener(new Listener() {
            @EventHandler
            public void onClick(InventoryClickEvent event) {
                if (!event.getView().getTitle().equalsIgnoreCase(ColorUtil.toColor(name))) {
                    return;
                }
                event.setCancelled(cancelled);
                Item item = new Item(event.getCurrentItem());
                if (ignoreAir && !item.isValid()) {
                    return;
                }
                MenuContext context = new MenuContext(event);
                menu.onClick(context);

                if (!cancelled) {
                    event.setCancelled(context.isCancelled());
                }
            }
        });
    }

    public List<IMenu> getMenuList() {
        return menuList;
    }

    public static MenuManager getInstance() {
        return instance == null ? new MenuManager() : instance;
    }
}
