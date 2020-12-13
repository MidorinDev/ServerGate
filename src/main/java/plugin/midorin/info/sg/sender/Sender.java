package plugin.midorin.info.sg.sender;

import plugin.midorin.info.sg.ServerGatePlugins;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

public class Sender {
    private final BukkitAudiences audiences;
    private Player player;
    private ConsoleCommandSender sender;

    public static Sender  stream(ConsoleCommandSender sender){
        return new Sender(sender);
    }
    public static Sender  stream(Player player){
        return new Sender(player);
    }

    public Sender(Player player) {
        this.audiences = BukkitAudiences.create(ServerGatePlugins.getPlugin());
        this.player  = player;
    }

    public Sender(ConsoleCommandSender sender) {
        this.audiences = BukkitAudiences.create(ServerGatePlugins.getPlugin());
        this.sender  = sender;
    }

    public void sendMessage(Component message) {
        // プレイヤーとコンソールには安全に非同期で送ることができます - それ以外の場合は同期で送ります
        if (player instanceof Player || player instanceof ConsoleCommandSender || player instanceof RemoteConsoleCommandSender) {
            this.audiences.player(player).sendMessage(Identity.nil(),message);
        } else {
            this.audiences.sender(player).sendMessage(Identity.nil(),message);
           // getPlugin().getBootstrap().getScheduler().executeSync(() -> this.audiences.sender(sender).sendMessage(message));
        }
    }
}
