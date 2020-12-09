package plugin.midorin.info.sg;

import plugin.midorin.info.sg.commands.Main;
import plugin.midorin.info.sg.commands.ServerGate;
import plugin.midorin.info.sg.listeners.ConnectionListener;
import plugin.midorin.info.sg.locale.TranslationManager;
import plugin.midorin.info.sg.util.ConnectState;
import plugin.midorin.info.sg.util.CustomConfig;

import java.nio.file.Path;

public class ServerGatePlugins extends AbstractServerGatePlugin {

    public static ServerGatePlugins plugin;

    private TranslationManager translationManager;
    private CustomConfig configuration;

    private ConnectState connectState;

    public final void load() {
        this.translationManager = new TranslationManager(this);
        this.translationManager.reload();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        plugin = this;

        this.configuration = new CustomConfig(this, resolveConfig());
        this.connectState = ConnectState.OPEN;

        //ロード
        load();

        //リスナーを登録
        registerListeners(
                new ConnectionListener()
        );

        //コマンドを登録
        registerCommands(
                new Main(this),
                new ServerGate(this)
        );

    }

    @Override
    public void onDisable() {

        super.onDisable();

        //設定ファイルをセーブ
        this.configuration.save();

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

    public ConnectState getConnectState() {
        return connectState;
    }

    public TranslationManager getTranslationManager() {
        return this.translationManager;
    }

    public void setConnectState(ConnectState connectState) {
        this.connectState = connectState;
    }
}
