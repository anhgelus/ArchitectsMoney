package codes.anhgelus.architectsMoney.command;

import codes.anhgelus.architectsMoney.ArchitectsMoney;
import codes.anhgelus.architectsMoney.command.devise.CreateDevise;
import codes.anhgelus.architectsMoney.manager.DeviseManager;
import codes.anhgelus.architectsMoney.manager.PermissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeviseCommand implements CommandExecutor {

    private final ArchitectsMoney main;

    public DeviseCommand(ArchitectsMoney main) {

        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && label.equals("devise")) {
            if (PermissionManager.permissionChecker(args[0], sender, new String[]{"create"}, DeviseManager.PERMISSION)) {
                final CreateDevise createDevise = new CreateDevise();
                return createDevise.command(args, sender, main);
            }
            return true;
        }
        return false;
    }
}
