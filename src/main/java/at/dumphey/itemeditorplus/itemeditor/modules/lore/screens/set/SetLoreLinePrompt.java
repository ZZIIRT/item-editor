package at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.set;

import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage.ManageLoreUiScreen;
import at.dumphey.itemeditorplus.ui.prompts.ChatPrompt;
import at.dumphey.itemeditorplus.utils.ColorUtils;
import at.dumphey.itemeditorplus.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SetLoreLinePrompt extends ChatPrompt {

    private final int index;

    public SetLoreLinePrompt(Player player, int index) {
        super(player, Messages.CHAT_PREFIX + "Enter a new text for line " + (index + 1) + ".", true);
        this.index = index;
    }

    @Override
    protected void onCancelled() {
        ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player));
    }

    @Override
    protected void onFulfilled(String input) {
        ItemEditor.INSTANCE.modifyItem(player, item -> {
            final ItemMeta meta = item.getItemMeta();
            final List<String> lore = meta.getLore();
            lore.set(index, ColorUtils.translateColors(input));
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        });
        ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        return true;
    }
}
