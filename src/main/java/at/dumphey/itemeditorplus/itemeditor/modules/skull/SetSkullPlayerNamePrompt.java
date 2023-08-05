package at.dumphey.itemeditorplus.itemeditor.modules.skull;

import at.dumphey.itemeditorplus.ui.prompts.ChatPrompt;
import at.dumphey.itemeditorplus.utils.ItemBuilder;
import at.dumphey.itemeditorplus.utils.Messages;
import at.dumphey.itemeditorplus.itemeditor.modules.home.HomeUiScreen;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SetSkullPlayerNamePrompt extends ChatPrompt {

    public SetSkullPlayerNamePrompt(Player player) {
        super(player, Messages.CHAT_PREFIX + "Enter a player name for the skull.", true);
    }

    @Override
    protected void onCancelled() {
        open(new HomeUiScreen(player));
    }

    @Override
    protected void onFulfilled(String input) {
        modifyItem(item -> ItemBuilder.of(item).withSkullName(input).build());
        Messages.send(player, "Skull player name set to §o" + input + "§f.");
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
        open(new HomeUiScreen(player));
    }

    @Override
    protected boolean validateInput(String input) {
        return true;
    }
}
