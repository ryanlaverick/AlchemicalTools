package io.github.ryanlaverick.framework.command;

import io.github.ryanlaverick.framework.utility.StringFormatter;
import org.bukkit.command.CommandSender;

public abstract class SubCommand implements ICommand {
    public abstract boolean execute(CommandSender commandSender, String[] args);

    protected void sendCommandHelp(CommandSender commandSender) {
        commandSender.sendMessage(StringFormatter.translateColorCodes(
                "&8&m         &2( &2&lAlchemicalTools &2) &8&m        "
        ));

        commandSender.sendMessage(StringFormatter.translateColorCodes(
                "&2{syntax} &8- {description}".replace("{syntax}", this.getSyntax()).replace("{description}", this.getDescription())
        ));

        commandSender.sendMessage(StringFormatter.translateColorCodes(
                "&8&m                                                 "
        ));
    }
}
