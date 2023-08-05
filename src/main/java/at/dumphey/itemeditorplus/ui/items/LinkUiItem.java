package at.dumphey.itemeditorplus.ui.items;

import at.dumphey.itemeditorplus.ui.UiManager;
import at.dumphey.itemeditorplus.ui.template.UiScreen;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class LinkUiItem extends ActionUiItem {

    private final Supplier<UiScreen> linkTo;

    public LinkUiItem(UiScreen screen, Player player, UiManager uiManager, ItemStack itemStack, Supplier<UiScreen> linkTo) {
        super(screen, player, itemStack, () -> uiManager.open(player, linkTo.get()));
        this.linkTo = linkTo;
    }

    @Override
    public ItemStack onRender() {
        return itemStack;
    }

}
