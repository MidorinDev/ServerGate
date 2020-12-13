package plugin.midorin.info.sg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import plugin.midorin.info.sg.ServerGatePlugins;
import plugin.midorin.info.sg.command.BaseCommand;
import plugin.midorin.info.sg.locale.Message;
import plugin.midorin.info.sg.util.ConnectState;

import java.util.Arrays;
import java.util.List;

public class ServerGate extends BaseCommand  {

    public ServerGate(ServerGatePlugins plugin) {
        super(plugin, "servergate","usage", "description", "servergate",1,false);
    }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        try {
            final ConnectState connectState = ConnectState.valueOf(args[0].toUpperCase());

            if(connectState == ServerGatePlugins.getPlugin().getConnectState()) return;

            ServerGatePlugins.getPlugin().setConnectState(connectState);

            sender.sendMessage(args[0] + "に変更しました");
        }catch (Exception e){
            sender.sendMessage("エラー");
        }
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {

        if(args.length == 1) {
            return Arrays.asList("close", "private", "open");
        }

        return null;
    }
}
