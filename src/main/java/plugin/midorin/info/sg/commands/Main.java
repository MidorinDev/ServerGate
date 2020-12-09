package plugin.midorin.info.sg.commands;

import org.bukkit.command.CommandSender;
import plugin.midorin.info.sg.ServerGatePlugins;
import plugin.midorin.info.sg.command.BaseCommand;

public class Main extends BaseCommand {

    public Main(ServerGatePlugins plugin) {
        super(plugin, "name", "usage", true);
    }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        //ここに書く
    }
}