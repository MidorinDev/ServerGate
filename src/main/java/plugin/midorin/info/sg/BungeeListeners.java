package plugin.midorin.info.sg;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeListeners implements PluginMessageListener
{
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {
        if (!channel.equals("BungeeCord")) return;
        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String sub_channel = input.readUTF();

        if (sub_channel.equals("KickPlayer")) return;
    }

    public static void kick(Player player, String message)
    {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("KickPlayer");
        output.writeUTF(player.getName());
        output.writeUTF(message);
        player.sendPluginMessage(ServerGatePlugins.plugin, "BungeeCord", output.toByteArray());
    }
}
