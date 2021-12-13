package codes.anhgelus.architectsMoney.util;

import codes.anhgelus.architectsMoney.ArchitectsMoney;
import org.bukkit.command.CommandSender;

public interface SubCommandBase {
    boolean command(String[] strings, CommandSender commandSender, ArchitectsMoney main);
}
