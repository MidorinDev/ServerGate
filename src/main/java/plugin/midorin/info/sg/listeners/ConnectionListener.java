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

        //closeならkickして何かしらのキックメッセージ
        if(ConnectState.CLOSE == connectState)  {

            //op持ちか権限持ちならバイパス(return)
            if(player.isOp() || player.hasPermission("test?")) return;

            Component reason = TranslationManager.render(Message.CLOSED_SERVER.build(), player.getLocale());
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, LegacyComponentSerializer.legacySection().serialize(reason));
            return;
        }
    }
}
