package io.github.ryanlaverick.command.subcommands;

import io.github.ryanlaverick.AlchemicalTools;
import io.github.ryanlaverick.framework.command.SubCommand;
import io.github.ryanlaverick.framework.item.Tool;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class GiveToolSubCommand extends SubCommand {
    private AlchemicalTools alchemicalTools;
    public GiveToolSubCommand(AlchemicalTools alchemicalTools) {
        this.alchemicalTools = alchemicalTools;
    }

    @Override
    public String getName() {
        return "alchemicaltools_give";
    }

    @Override
    public String getDescription() {
        return "Grants a given Player the power of an AlchemicalTool.";
    }

    @Override
    public String getSyntax() {
        return "/alchemicaltools give <player> <tool>";
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player) {
            if (! commandSender.hasPermission("alchemicaltools.commands.give")) {
                commandSender.sendMessage("Missing permission for give command");

                return true;
            }

            if (args.length != 3) {
                this.sendCommandHelp(commandSender);

                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(args[1]);

            if (targetPlayer == null) {
                commandSender.sendMessage("Player not found");

                return true;
            }

            Tool tool = Tool.tryFrom(args[2]);

            if (tool == null) {
                commandSender.sendMessage("Tool not found");

                return true;
            }

            ((Player) commandSender).getInventory().addItem(this.alchemicalTools.getToolCache().getItemStack(tool));
        }

        return true;
    }
}
