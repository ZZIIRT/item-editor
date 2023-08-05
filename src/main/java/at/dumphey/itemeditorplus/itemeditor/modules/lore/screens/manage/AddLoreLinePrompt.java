package at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage;

import at.dumphey.itemeditorplus.ui.prompts.ChatPrompt;
import at.dumphey.itemeditorplus.utils.Messages;
import at.dumphey.itemeditorplus.itemeditor.ItemEditor;
import at.dumphey.itemeditorplus.itemeditor.modules.lore.screens.manage.ManageLoreUiScreen;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AddLoreLinePrompt extends ChatPrompt {
    public AddLoreLinePrompt(Player player) {
        super(player, Messages.CHAT_PREFIX + "Enter the line that will be added to the item lore.", true);
    }

    @Override
    protected void onCancelled() {
        ItemEditor.INSTANCE.getUiManager().reopen(player);
    }

    @Override
    protected void onFulfilled(String input) {
        ItemEditor.INSTANCE.modifyItem(player, (item) -> {

            List<String> lore = item.getItemMeta().getLore();
            if (lore == null) lore = new ArrayList<>();
            lore.add(input);
            return ItemBuilder.of(item).withLore(lore).build();
        });
        ItemEditor.INSTANCE.getUiManager().open(player, new ManageLoreUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        return true;
    }
}
