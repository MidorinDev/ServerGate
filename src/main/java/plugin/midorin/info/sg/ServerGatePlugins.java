package plugin.midorin.info.sg;

import plugin.midorin.info.sg.listeners.ConnectionListener;
import plugin.midorin.info.sg.locale.TranslationManager;
import plugin.midorin.info.sg.util.CustomConfig;

import java.nio.file.Path;

public class ServerGatePlugins extends AbstractServerGatePlugin {

    public static ServerGatePlugins plugin;

    private TranslationManager translationManager;
    private CustomConfig configuration;

    public final void load() {
        this.translationManager = new TranslationManager(this);
        this.translationManager.reload();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        plugin = this;

        this.configuration = new CustomConfig(this, resolveConfig());

        //ロード
        load();

        //リスナーを登録
        registerListeners(
                new ConnectionListener()
        );

        //コマンドを登録
        registerCommands();

        /*Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListeners());*/

        /*getCommand("servergate").setExecutor(new Main());
        getCommand("nick").setExecutor(new Nick());*/

        /*CustomConfig.create("config");


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
        catch (IOException e) { e.printStackTrace(); }*/


    }

    @Override
    public void onDisable() {

        super.onDisable();

        //設定ファイルをセーブ
        this.configuration.save();
        /*CustomConfig.dataFile = new File(plugin.getDataFolder(), "config.yml");
        CustomConfig.data.set("Nickname", Nick.nick);
        try { CustomConfig.data.save(CustomConfig.dataFile); }
        catch (IOException e) { e.printStackTrace(); }*/

    }



    public static ServerGatePlugins getPlugin()
    {
        return plugin;
    }

    public CustomConfig getConfiguration() {
        return configuration;
    }

    public Path getConfigDirectory() {
        return getDataFolder().toPath().toAbsolutePath();
    }

    public TranslationManager getTranslationManager() {
        return this.translationManager;
    }
}
