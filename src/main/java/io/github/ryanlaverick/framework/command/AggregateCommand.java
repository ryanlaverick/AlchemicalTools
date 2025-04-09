package io.github.ryanlaverick.framework.command;

import io.github.ryanlaverick.framework.command.exception.SubCommandAlreadyMappedException;
import io.github.ryanlaverick.framework.utility.StringFormatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public abstract class AggregateCommand implements ICommand, CommandExecutor {
    protected final Map<String, SubCommand> subCommands;

    protected AggregateCommand() {
        this.subCommands = new HashMap<>();
    }

    public boolean isMapped(String prefix) {
        return this.subCommands.containsKey(prefix);
    }

    public void registerSubCommand(String prefix, SubCommand subCommand) {
        if (this.isMapped(prefix)) {
            throw new SubCommandAlreadyMappedException(this.getName(), subCommand.getName());
        }

        this.subCommands.put(prefix, subCommand);
    }

    public SubCommand getSubCommand(String prefix) {
        return this.getSubCommands().get(prefix);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            this.sendCommandList(sender);
            return true;
        }

        String prefix = args[0];

        if (! this.isMapped(prefix)) {
            this.sendCommandList(sender);
            return true;
        }

        return this.getSubCommand(prefix).execute(sender, args);
    }

    protected void sendCommandList(CommandSender commandSender) {
        commandSender.sendMessage(StringFormatter.translateColorCodes(
                "&8&m         &2( &2&lAlchemicalTools &2) &8&m        "
        ));

        for (Map.Entry<String, SubCommand> subCommandEntry : this.getSubCommands().entrySet()) {
            String syntax = subCommandEntry.getValue().getSyntax();
            String description = subCommandEntry.getValue().getDescription();

            commandSender.sendMessage(StringFormatter.translateColorCodes(
                    "&2{syntax} &8- {description}".replace("{syntax}", syntax).replace("{description}", description)
            ));
        }

        commandSender.sendMessage(StringFormatter.translateColorCodes(
                "&8&m                                                 "
        ));
    }

    public Map<String, SubCommand> getSubCommands() {
        return subCommands;
    }
}
