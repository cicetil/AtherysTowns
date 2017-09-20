package com.atherys.towns;

import com.atherys.towns.base.TownsObject;
import com.atherys.towns.commands.TownsValues;
import com.atherys.towns.commands.nation.NationMasterCommand;
import com.atherys.towns.commands.plot.PlotMasterCommand;
import com.atherys.towns.commands.resident.ResidentCommand;
import com.atherys.towns.commands.town.TownMasterCommand;
import com.atherys.towns.db.TownsDatabase;
import com.atherys.towns.listeners.PlayerListeners;
import com.atherys.towns.managers.*;
import com.atherys.towns.resident.Resident;
import com.google.inject.Inject;
import com.mongodb.client.MongoDatabase;
import io.github.flibio.economylite.EconomyLite;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scheduler.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.atherys.towns.AtherysTowns.*;

@Plugin( id=AtherysTowns.ID, name=NAME, description = DESCRIPTION, version=VERSION)
public class AtherysTowns {

    final static String ID = "atherystowns";
    final static String NAME = "A'therys Towns";
    final static String DESCRIPTION = "A custom plugin responsible for agile land management. Created for the A'therys Horizons server.";
    final static String VERSION = "1.0.0a";

    @Inject
    private Game game;

    @Inject
    private Logger logger;

    private static AtherysTowns instance;

    private Map<Class<? extends TownsObject>,DatabaseManager<? extends TownsObject>> managers = new HashMap<>();

    //private TownsDatabase database;

    private Task townBorderTask;
    private Task wildernessRegenTask;

    public Task getTownBorderTask() {
        return townBorderTask;
    }

    private boolean init() {
        instance = this;

        Settings.getInstance();

        TownsDatabase.getInstance();

        NationManager.getInstance().loadAll();

        TownManager.getInstance().loadAll();

        PlotManager.getInstance().loadAll();

        ResidentManager.getInstance().loadAll();

        //WildernessManager.setup();

        // TODO: Loading mechanism
        // TODO: Configs

        return true;
    }

    private void start() {
        game.getEventManager().registerListeners(this, new PlayerListeners());

        ResidentCommand.register();
        PlotMasterCommand.getInstance().register();
        TownMasterCommand.getInstance().register();
        NationMasterCommand.getInstance().register();

        townBorderTask = Task.builder()
                .interval( Settings.TOWN_BORDER_UPDATE_RATE, TimeUnit.SECONDS )
                .execute(() -> {
                    for ( Player p : Sponge.getServer().getOnlinePlayers() ) {
                        if (TownsValues.get(p.getUniqueId(), TownsValues.TownsKey.TOWN_BORDERS).isPresent()){
                            Optional<Resident> resOpt = ResidentManager.getInstance().get(p.getUniqueId());
                            if ( resOpt.isPresent() ) {
                                if ( resOpt.get().getTown().isPresent() ) {
                                    resOpt.get().getTown().get().showBorders(p);
                                }
                            }
                        }
                    }
                })
                .name("atherystowns-town-border-task")
                .submit(this);

        //wildernessRegenTask = Task.builder()
        //        .delay(     Settings.WILDERNESS_REGEN_RATE, Settings.WILDERNESS_REGEN_RATE_UNIT )
        //        .interval(  Settings.WILDERNESS_REGEN_RATE, Settings.WILDERNESS_REGEN_RATE_UNIT )
        //        .execute(WildernessManager::task)
        //        .name("atherystowns-wilderness-regen-task")
        //        .submit(this);
    }

    private void stop() {
        ResidentManager.getInstance().saveAll();
        PlotManager.getInstance().saveAll();
        TownManager.getInstance().saveAll();
        NationManager.getInstance().saveAll();
        //database.saveAll();
        /*try {
            Settings.save();
        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("Settings file could not save.");
        }*/
    }

   /* @Listener
    public void onInit (GameInitializationEvent event) {
        init();
    }*/

    @Listener
    public void onStart (GameStartedServerEvent event) {
        if ( init() ) start();
    }

    @Listener
    public void onStop (GameStoppingServerEvent event ) {
        stop();
    }

    public static AtherysTowns getInstance() { return instance; }

    public static MongoDatabase getDb() {
        return TownsDatabase.getInstance().getDatabase();
    }

    public Game getGame() {
        return game;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public Optional<EconomyLite> getEconomyPlugin() {
        Optional<PluginContainer> econOpt = game.getPluginManager().getPlugin("economylite");
        if ( econOpt.isPresent() ) {
            return Optional.of(EconomyLite.getInstance());
        } else return Optional.empty();
    }

    //public TownsDatabase getDatabase() {
    //    return database;
    //}

    public Collection<DatabaseManager<? extends TownsObject>> getDbManagers() {
        return managers.values();
    }
}
