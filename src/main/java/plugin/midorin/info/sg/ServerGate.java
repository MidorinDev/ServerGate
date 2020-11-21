package plugin.midorin.info.sg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.midorin.info.sg.commands.Main;
import plugin.midorin.info.sg.commands.Nick;
import plugin.midorin.info.sg.util.CustomConfig;

import java.io.File;
import java.io.IOException;

public class ServerGate extends JavaPlugin
{
    public static JavaPlugin plugin;
    @Override
    public void onEnable()
    {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListeners());

        getCommand("servergate").setExecutor(new Main());
        getCommand("nick").setExecutor(new Nick());

        CustomConfig.create("config");


        if (CustomConfig.data.getBoolean("Nickname"))
        {
            Nick.nick = true;
            CustomConfig.data.set("Nickname", true);
        }
        else
        {
            Nick.nick = false;
            CustomConfig.data.set("Nickname", false);
        }
        CustomConfig.dataFile = new File(plugin.getDataFolder(), "config.yml");
        try { CustomConfig.data.save(CustomConfig.dataFile); }
        catch (IOException e) { e.printStackTrace(); }

        super.onEnable();
    }

    @Override
    public void onDisable()
    {
        CustomConfig.dataFile = new File(plugin.getDataFolder(), "config.yml");
        CustomConfig.data.set("Nickname", Nick.nick);
        try { CustomConfig.data.save(CustomConfig.dataFile); }
        catch (IOException e) { e.printStackTrace(); }
        super.onDisable();
    }

    public static JavaPlugin getPlugin()
    {
        return plugin;
    }
}
