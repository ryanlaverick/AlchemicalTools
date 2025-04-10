package io.github.ryanlaverick.command;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.command.AggregateCommand;
import io.github.ryanlaverick.command.subcommands.GiveToolSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class AlchemicalToolsCommand extends AggregateCommand {
    public AlchemicalToolsCommand(AlchemicalTools alchemicalTools) {
        this.registerSubCommand("give", new GiveToolSubCommand(alchemicalTools));
    }

    @Override
    public String getName() {
        return "alchemicaltools";
    }

    @Override
    public String getDescription() {
        return "Root command for AlchemicalTools.";
    }

    @Override
    public String getSyntax() {
        return "/alchemicaltools";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (! sender.hasPermission("alchemicaltools.commands.help")) {
                sender.sendMessage("No permission");

                return true;
            }
        }

        return super.onCommand(sender, command, label, args);
    }
}
