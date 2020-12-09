package plugin.midorin.info.sg.listeners;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.kyori.adventure.text.Component;
import plugin.midorin.info.sg.locale.Message;
import plugin.midorin.info.sg.locale.TranslationManager;

public class ConnectionListener implements Listener  {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(PlayerLoginEvent e)  {

        final Player player =  e.getPlayer();

        if (this.detectedCraftBukkitOfflineMode) {
            printCraftBukkitOfflineModeError();

            Component reason = TranslationManager.render(Message.LOADING_STATE_ERROR_CB_OFFLINE_MODE.build(), player.getLocale());
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, LegacyComponentSerializer.legacySection().serialize(reason));
            return;
        }
    }
}
