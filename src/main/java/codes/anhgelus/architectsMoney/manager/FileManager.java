package codes.anhgelus.architectsMoney.manager;

import codes.anhgelus.architectsMoney.ArchitectsMoney;

import java.io.File;

public class FileManager {

    /**
     * Get the players' data (player.yml)
     *
     * @return Players data file
     */
    public static File getPlayersData(ArchitectsMoney main) { return new File(main.getDataFolder(), "data/players.yml"); }

    /**
     * Get the lists' data (lists.yml)
     *
     * @return Lists data file
     */
    public static File getListData(ArchitectsMoney main) {
        return new File(main.getDataFolder(), "data/lists.yml");
    }

    /**
     * Get the devises' data (devises.yml)
     *
     * @return Lists data file
     */
    public static File getDeviseData(ArchitectsMoney main) {
        return new File(main.getDataFolder(), "data/devises.yml");
    }

    /**
     * Get the config (config.yml)
     *
     * @return Config file
     */
    public static File getConfig(ArchitectsMoney main) {
        return new File(main.getDataFolder(), "config.yml");
    }
}
