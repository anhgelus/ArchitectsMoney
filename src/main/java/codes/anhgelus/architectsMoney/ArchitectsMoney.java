package codes.anhgelus.architectsMoney;

import codes.anhgelus.architectsMoney.command.DeviseCommand;
import codes.anhgelus.architectsMoney.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ArchitectsMoney extends JavaPlugin {

    public static final Logger LOGGER = Logger.getLogger("Minecraft");

    public static final String PLUGIN_NAME = "Architects Money";
    public static final String PERMISSION = "architectsmoney.";
    public static final String SEPARATOR = ",";

    @Override
    public void onEnable() {
        LOGGER.info("[" + PLUGIN_NAME +  "] The plugin has been activated"); //info

        PluginManager plManager = Bukkit.getServer().getPluginManager();

        //config
        final ConfigManager configManager = new ConfigManager(this);
        final YamlConfiguration config = configManager.load();

        //command
        getCommand("devise").setExecutor(new DeviseCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LOGGER.info("[" + PLUGIN_NAME + "] The plugin has been desactivated");
    }
}
