package plugin.midorin.info.sg.listeners;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.kyori.adventure.text.Component;
import plugin.midorin.info.sg.ServerGatePlugins;
import plugin.midorin.info.sg.locale.Message;
import plugin.midorin.info.sg.locale.TranslationManager;
import plugin.midorin.info.sg.util.ConnectState;

public class ConnectionListener implements Listener  {



    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(PlayerLoginEvent e)  {

        final Player player =  e.getPlayer();
        final ConnectState connectState = ServerGatePlugins.getPlugin().getConnectState();

        if(player.isOp() || player.hasPermission("test?") /*|| isRegisterd(player)*/) return;

        Component reason = connectState == ConnectState.CLOSE ? TranslationManager.render(Message.CLOSED_SERVER_KICK_MESSAGE.build(), player.getLocale()) : TranslationManager.render(Message.PRIVATE_SERVER_KICK_MESSAGE.build(), player.getLocale());

        switch (connectState) {

            case CLOSE:
                //op持ちか権限持ちならバイパス(return)
                if(player.isOp() || player.hasPermission("test?")) return;

                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, LegacyComponentSerializer.legacySection().serialize(reason));
                break;
            case PRIVATE:

                //登録されているか
                if(player.isOp()) return;

                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, LegacyComponentSerializer.legacySection().serialize(reason));
                break;

            default:
                break;
        }
    }
}
