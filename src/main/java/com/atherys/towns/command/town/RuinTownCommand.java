package com.atherys.towns.command.town;

import com.atherys.core.command.PlayerCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.core.command.annotation.Permission;
import com.atherys.towns.AtherysTowns;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

@Aliases("ruin")
@Description("Deletes your town.")
@Permission("atherystowns.town.ruin")
public class RuinTownCommand implements PlayerCommand {
    @Override
    public CommandResult execute(Player src, CommandContext args) throws CommandException {
        AtherysTowns.getInstance().getTownFacade().ruinPlayerTown(src);
        return CommandResult.success();
    }
}
