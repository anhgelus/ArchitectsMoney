package codes.anhgelus.architectsMoney.command;

import codes.anhgelus.architectsMoney.ArchitectsMoney;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EcoCommand implements CommandExecutor {

    public static String PERMISSION = ArchitectsMoney.PERMISSION + "economy";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && (label.equals("eco") || label.equals("economy"))) {
            final Player player = (Player) sender;
            return true;
        }
        return false;
    }
}
