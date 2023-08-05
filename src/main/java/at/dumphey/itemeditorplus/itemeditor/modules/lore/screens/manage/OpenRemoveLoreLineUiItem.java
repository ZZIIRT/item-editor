package at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.remove.RemoveLoreLineUiScreen;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OpenRemoveLoreLineUiItem extends UiItem {

    public OpenRemoveLoreLineUiItem(UiScreen screen, Player player) {
        super(screen, player);
        require(new ItemHasLoreLineRequirement(player));
    }

    @Override
    protected ItemStack onRender() {
        return ItemBuilder.uiItem(Material.SPONGE, "Remove line", "Remove a line from the lore");
    }

    @Override
    protected void onClick(ClickType clickType) {
        if (clickType.isLeftClick()) {
            ItemEditor.INSTANCE.getUiManager().open(player, new RemoveLoreLineUiScreen(player));
        }
    }
}
