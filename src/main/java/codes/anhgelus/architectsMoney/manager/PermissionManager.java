package codes.anhgelus.architectsMoney.manager;

import org.bukkit.command.CommandSender;

import java.util.Objects;

public class PermissionManager {

    /**
     * Check if the player as the permission
     *
     * @param commandSent string command name
     * @param commandSender CommandSender sender of the command
     * @param commands string command label
     * @param permission string permission
     * @return bool true = has permission | false = don't have permission
     */
    public static boolean permissionChecker(String commandSent, CommandSender commandSender, String[] commands, String permission) {
        for (String command : commands) {
            if (Objects.equals(commandSent, command)) {
                if (commandSender.hasPermission(permission) || commandSender.isOp()) {
                    return true;
                }
            }
        }

        return false;
    }
}
