package plugin.midorin.info.sg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.midorin.info.sg.BungeeListeners;

public class Listeners implements Listener
{
    public static boolean onJoin = true;

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        if (!player.isOp())
            BungeeListeners.kick(player, "");
        e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "Join" + ChatColor.GRAY + "] " + player.getName());
        if (player.getWorld().getName().equals("Lobby"))
            player.teleport(new Location(player.getWorld(), 757 ,25 ,-176));
        else if (player.getWorld().getName().equals("CannonEscape"))
            player.teleport(new Location(player.getWorld(), 908, 6 ,-172));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Quit" + ChatColor.GRAY + "] " + e.getPlayer().getName());
    }

}
