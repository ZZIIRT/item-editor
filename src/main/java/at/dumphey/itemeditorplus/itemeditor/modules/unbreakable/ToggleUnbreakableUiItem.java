package at.dumphey.itemeditorplus.itemeditor.modules.unbreakable;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.ui.template.UiItem;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ToggleUnbreakableUiItem extends UiItem {
    public ToggleUnbreakableUiItem(UiScreen screen, Player player) {
        super(screen, player);
        requirePermission("iep.edit.unbreakable");
    }

    @Override
    public ItemStack onRender() {
        final String name = isUnbreakable() ? "Make breakable" : "Make unbreakable";
        final String lore = isUnbreakable() ? "Make the item breakable." : "Make the item unbreakable.";
        final Material material = isUnbreakable() ? Material.COBBLESTONE : Material.BEDROCK;

        return ItemBuilder.uiItem(material, name, lore);
    }

    private boolean isUnbreakable() {
        return ItemEditor.INSTANCE.getItem(player).getItemMeta().isUnbreakable();
    }

    @Override
    public void onClick(ClickType clickType) {
        ItemEditor.INSTANCE.modifyItem(player, (item) -> ItemBuilder.of(item).setUnbreakable(!isUnbreakable()).build());
        Messages.send(player, "Made item §o" + (isUnbreakable() ? "unbreakable" : "breakable") + "§f.");
    }
}
