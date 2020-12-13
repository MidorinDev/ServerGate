package plugin.midorin.info.sg.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import plugin.midorin.info.sg.ServerGatePlugins;

import java.io.File;
import java.io.IOException;

public class DynamicDataSource {

    private HikariConfig config = new HikariConfig();

    public DynamicDataSource(boolean preferMySQL) throws ClassNotFoundException {
        if (preferMySQL) {
            String ip = ServerGatePlugins.getPlugin().getConfiguration().getString("MySQL.IP");
            String dbName = ServerGatePlugins.getPlugin().getConfiguration().getString("MySQL.DB-Name");
            String usrName = ServerGatePlugins.getPlugin().getConfiguration().getString("MySQL.Username");
            String password = ServerGatePlugins.getPlugin().getConfiguration().getString("MySQL.Password");
            String properties = ServerGatePlugins.getPlugin().getConfiguration().getString("MySQL.Properties");
            int port = ServerGatePlugins.getPlugin().getConfiguration().getInt("MySQL.Port");

            Class.forName("com.mysql.jdbc.Driver");
            config.setJdbcUrl("jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?"+properties);
            config.setUsername(usrName);
            config.setPassword(password);
        } else {
            String path = ServerGatePlugins.getPlugin().getDataFolder().getPath() + "/data/";

            File dataFolder = new File(path);
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

            File databaseFile = new File(dataFolder, "sqlite.db");
            if (!databaseFile.exists()) {
                try {
                    databaseFile.createNewFile();
                } catch (IOException e) {
                    //エラー
                }
            }

            String driverClassName = "org.sqlite.JDBC";
            Class.forName(driverClassName);
            config.setDriverClassName(driverClassName);
            config.setJdbcUrl("jdbc:sqlite:" + path + "sqlite.db");

        }
    }

    public HikariDataSource generateDataSource(){
        return new HikariDataSource(config);
    }

}
