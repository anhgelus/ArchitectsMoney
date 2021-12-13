package codes.anhgelus.architectsMoney.manager;

import codes.anhgelus.architectsMoney.ArchitectsMoney;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    public static final String VERSION = "0.0.1";
    public static final String PERMISSION_CREATE_MONEY = "permission-to-create-money";

    //private final ArchitectsLand main;
    private final File config;
    private final YamlConfiguration yml;
    private final DataManager data;

    public ConfigManager(ArchitectsMoney main) {
        //this.main = main;
        this.config = FileManager.getConfig(main);
        this.yml = YamlConfiguration.loadConfiguration(this.config);
        this.data = new DataManager("yml");
    }

    /**
     * Load the main configuration
     *
     * @return Main configuration
     */
    public YamlConfiguration load() {
        final String version = yml.getString("version");

        if (version == null) {
            yml.set("version", VERSION + " #DO NOT CHANGE THIS");
            yml.set(PERMISSION_CREATE_MONEY, DeviseManager.PERMISSION + "create");

            final String keyMi = "mi.";
            yml.set(keyMi + "base-material", "DIAMOND");
            yml.set(keyMi + "under-material", "EMERALD");
            yml.set(keyMi + "one-base-material-equals-to-under-material", 25);

            data.save(yml, config);

            ChatManager.sendConsoleMessage("The configuration has been created");
        } else {
            ChatManager.sendConsoleMessage("The configuration has been loaded");
        }

        return yml;
    }

}
