package io.github.ryanlaverick.framework.command.exception;

public final class SubCommandAlreadyMappedException extends RuntimeException {
    private final String aggregateCommand;
    private final String subCommand;

    public SubCommandAlreadyMappedException(String aggregateCommand, String subCommand) {
        this.aggregateCommand = aggregateCommand;
        this.subCommand = subCommand;
    }

    @Override
    public String getMessage() {
        return "Unable to register sub-command '%s' for command '%a'"
                .replace("%s", this.subCommand)
                .replace("%a", this.aggregateCommand);
    }
}
