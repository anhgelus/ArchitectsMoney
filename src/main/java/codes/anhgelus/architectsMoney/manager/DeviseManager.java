package codes.anhgelus.architectsMoney.manager;

import codes.anhgelus.architectsMoney.ArchitectsMoney;
import codes.anhgelus.architectsMoney.exception.InvalidPercentage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DeviseManager {

    private final ArchitectsMoney main;

    private final File data;
    private final YamlConfiguration yml;

    private final File listData;
    private final YamlConfiguration listYml;

    private final DataManager dataManager;
    private final String name;
    private final Player author;

    public static String PERMISSION = ArchitectsMoney.PERMISSION + "devise.";

    public DeviseManager(String name, Player author, ArchitectsMoney main) {

        this.main = main;
        this.author = author;
        this.name = name.toLowerCase();

        this.data = FileManager.getDeviseData(main);
        this.yml = YamlConfiguration.loadConfiguration(this.data);

        this.listData = FileManager.getListData(main);
        this.listYml = YamlConfiguration.loadConfiguration(this.listData);

        this.dataManager = new DataManager("yml");
    }

    /**
     * Create a devise
     *
     * @param valueInMi Base value in MI
     */
    public void create(double valueInMi) {

        if (DeviseManager.exist(name, main)) {
            return;
        }

        yml.set(name + ".name", name);
        yml.set(name + ".value", valueInMi);
        yml.set(name + ".creator", author.getUniqueId());

        if (listYml.getString("devises") == null) {
            listYml.set("devises", name + ArchitectsMoney.SEPARATOR);
        } else {
            listYml.set("devises", listYml.getString("devises") + name + ArchitectsMoney.SEPARATOR);
        }

        dataManager.save(yml, data);
        dataManager.save(listYml, listData);
    }

    /**
     * Delete a devise
     */
    public void delete() {

        if (yml.getString(name) == null) {
            return;
        }

        if (!getAuthor().equals(String.valueOf(author.getUniqueId()))) {
            return;
        }

        yml.set(name, null);

        listYml.set("devises", removeDevise(listYml.getString("devises"), name));

        dataManager.save(yml, data);
    }

    /**
     * Set value of a devise
     *
     * @param valueInMi value
     */
    public void set(double valueInMi) {

        if (!getAuthor().equals(String.valueOf(author.getUniqueId()))) {
            return;
        }

        yml.set(name + ".value", valueInMi);

        dataManager.save(yml, data);
    }

    /**
     * Set value with percentage
     *
     * @param valueInPercentage percentage (100.0 = don't change | > decrement | < increase)
     */
    public void setInPercentage(double valueInPercentage) {

        if (!getAuthor().equals(String.valueOf(author.getUniqueId()))) {
            return;
        }

        double value = yml.getInt(name + ".value") * valueInPercentage;

        yml.set(name + ".value", value);

        dataManager.save(yml, data);
    }

    /**
     * Set value by percentage
     *
     * @param percentage percentage (0.0 = don't change | > decrement | < increase)
     */
    public void addToValueInPercentage(double percentage) {

        if (!getAuthor().equals(String.valueOf(author.getUniqueId()))) {
            return;
        }

        if (percentage <= -100.0) {
            try {
                throw new InvalidPercentage("The percentage is not valid");
            } catch (InvalidPercentage e) {
                e.printStackTrace();
            }
        }

        setInPercentage(100 + percentage);
    }

    /**
     * Add a value to the value
     *
     * @param valueInMi value
     */
    public void addToValue(double valueInMi) {

        if (!getAuthor().equals(String.valueOf(author.getUniqueId()))) {
            return;
        }

        set(yml.getDouble(name + ".value") + valueInMi);
    }

    /**
     * Check if a devise exist
     *
     * @param name name of the devise
     * @param main main plugin file
     * @return boolean (true = exist | false = do not exist)
     */
    public static boolean exist(String name, ArchitectsMoney main) {

        final File listData = FileManager.getListData(main);
        final YamlConfiguration listYml = YamlConfiguration.loadConfiguration(listData);

        name = name.toLowerCase();

        return listYml.getString(name + ".name") != null;
    }

    private String removeDevise(String devises, String deviseToRemove) {

        List<String> devisesList = Arrays.asList(devises.split(ArchitectsMoney.SEPARATOR));

        devisesList.remove(deviseToRemove);

        String toReturn = new String();

        for (String i : devisesList) {
            if (!toReturn.equals("")) {
                toReturn = toReturn + i + ArchitectsMoney.SEPARATOR;
            } else {
                toReturn = i + ArchitectsMoney.SEPARATOR;
            }
        }

        return toReturn;
    }

    public String getAuthor() {
        return yml.getString(name + ".creator");
    }

}
