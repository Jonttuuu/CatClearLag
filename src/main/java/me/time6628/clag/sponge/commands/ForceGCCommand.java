package me.time6628.clag.sponge.commands;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

/**
 * Created by TimeTheCat on 10/28/2016.
 */
public class ForceGCCommand implements CommandExecutor {

    public static CommandSpec getCommand() {
        return CommandSpec.builder()
                .description(Text.of("Force Garabage Collection"))
                .permission("catclearlag.command.forcegc")
                .executor(new ForceGCCommand())
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        src.sendMessage(Text.builder("Requesting Garbage Collection...").build());
        System.gc();
        src.sendMessage(Text.builder("Garbage Collection Requested.").build());
        return CommandResult.success();
    }
}
