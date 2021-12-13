package codes.anhgelus.architectsMoney.command.devise;

import codes.anhgelus.architectsMoney.ArchitectsMoney;
import codes.anhgelus.architectsMoney.manager.DeviseManager;
import codes.anhgelus.architectsMoney.util.Static;
import codes.anhgelus.architectsMoney.util.SubCommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateDevise implements SubCommandBase {
    @Override
    public boolean command(String[] strings, CommandSender commandSender, ArchitectsMoney main) {

        if (strings.length < 3) {
            commandSender.sendMessage(Static.ERROR + "You must specify a name to create a devise!");
            return true;
        }

        final String name = strings[1].toLowerCase();

        if (DeviseManager.exist(name, main)) {
            commandSender.sendMessage(Static.ERROR + "This devises already exist!");
            return true;
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Static.ERROR + "You must be a player!");
            return true;
        }

        final DeviseManager deviseManager = new DeviseManager(name, (Player) commandSender, main);

        deviseManager.create(Double.parseDouble(strings[2]));

        commandSender.sendMessage(Static.SUCCESS + "Your devises was created!");

        return true;
    }
}
