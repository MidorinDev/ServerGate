package plugin.midorin.info.sg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.midorin.info.sg.ServerGatePlugins;
import plugin.midorin.info.sg.util.CustomConfig;
import plugin.midorin.info.sg.util.Messages;

import java.io.File;

public class Nick implements CommandExecutor
{
    public static boolean nick = true;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player) sender;
        try
        {
            if (args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("enable"))
            {
                if (sender.hasPermission("ServerGate.nick.enable"))
                {
                    if (!nick)
                    {
                        nick = true;
                        CustomConfig.dataFile = new File(ServerGatePlugins.plugin.getDataFolder(), "config.yml");
                        CustomConfig.data.set("Nickname", nick);
                        CustomConfig.data.save(CustomConfig.dataFile);
                        Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GREEN + "Nick機能が有効になりました。");
                    }
                    else
                        sender.sendMessage(Messages.PREFIX + ChatColor.RED + "Nick機能は既に有効化されています。");
                }
                else
                    Messages.sendPermissionError(sender);
            }
            else if (args[0].equalsIgnoreCase("false") || args[0].equalsIgnoreCase("disable"))
            {
                if (sender.hasPermission("ServerGate.nick.disable"))
                {
                    if (nick)
                    {
                        nick = false;
                        CustomConfig.dataFile = new File(ServerGatePlugins.plugin.getDataFolder(), "config.yml");
                        CustomConfig.data.set("Nickname", nick);
                        CustomConfig.data.save(CustomConfig.dataFile);
                        Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.YELLOW + "Nick機能が無効になりました。");
                    }
                    else
                        sender.sendMessage(Messages.PREFIX + ChatColor.RED + "Nick機能は既に無効化されています。");
                }
                else
                    Messages.sendPermissionError(sender);
            }
            else
            {
                if (nick)
                    setNickname(sender, p, args);
                else
                {
                    if (sender.hasPermission("ServerGate.nick"))
                        setNickname(sender, p, args);
                    else
                        sender.sendMessage(Messages.PREFIX + ChatColor.RED + "現在Nick機能が管理者により無効化されています。");
                }
            }
        }
        catch (Exception ex)
        {
            sender.sendMessage(Messages.PREFIX + ChatColor.RED + "変更したい名前を指定してください。");
        }
        return true;
    }

    private static void setNickname(CommandSender sender, Player player, String[] args)
    {
        String msg = null;
        for (String s : args)
        {
            if (msg == null)
            {
                msg = s;
                continue;
            }
            msg = msg + "" + s;
        }
        msg = ChatColor.translateAlternateColorCodes('&', msg);
        player.setPlayerListName(msg);
        player.setDisplayName(msg);
        sender.sendMessage(Messages.PREFIX + ChatColor.YELLOW + "あなたのニックネームが " + ChatColor.RESET + msg + ChatColor.YELLOW + " に変更されました。");
    }
}
