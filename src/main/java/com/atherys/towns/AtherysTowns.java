package com.atherys.towns;

import com.atherys.core.event.AtherysHibernateConfigurationEvent;
import com.atherys.core.event.AtherysHibernateInitializedEvent;
import com.atherys.towns.entity.*;
import com.atherys.towns.facade.*;
import com.atherys.towns.persistence.*;
import com.atherys.towns.service.*;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import static com.atherys.towns.AtherysTowns.*;

@Plugin(
        id = ID,
        name = NAME,
        description = DESCRIPTION,
        version = VERSION,
        dependencies = {
                @Dependency(id = "atheryscore")
        }
)
public class AtherysTowns {

    final static String ID = "atherystowns";
    final static String NAME = "A'therys Towns";
    final static String DESCRIPTION = "A land-management plugin for the Atherys Horizons server";
    final static String VERSION = "1.0.0";

    private static AtherysTowns instance;

    private static boolean init = false;

    @Inject
    private Logger logger;

    @Inject
    private Injector spongeInjector;

    private static class Components {

        @Inject
        private TownsConfig config;

        @Inject
        private NationRepository nationRepository;

        @Inject
        private TownRepository townRepository;

        @Inject
        private PlotRepository plotRepository;

        @Inject
        private ResidentRepository residentRepository;

        @Inject
        private PermissionRepository permissionRepository;

        @Inject
        private NationService nationService;

        @Inject
        private TownService townService;

        @Inject
        private PlotService plotService;

        @Inject
        private ResidentService residentService;

        @Inject
        private PermissionService permissionService;

        @Inject
        private TownsMessagingService townsMessagingService;

        @Inject
        private NationFacade nationFacade;

        @Inject
        private TownFacade townFacade;

        @Inject
        private PlotFacade plotFacade;

        @Inject
        private ResidentFacade residentFacade;

        @Inject
        private PermissionFacade permissionFacade;

        @Inject
        private PlotSelectionFacade plotSelectionFacade;

    }

    private Components components;

    private Injector townsInjector;

    private void init() {
        instance = this;

        components = new Components();

        townsInjector = spongeInjector.createChildInjector(new AtherysTownsModule());
        townsInjector.injectMembers(components);

        getConfig().init();
    }

    private void start() {
    }

    private void stop() {

    }

    @Listener
    public void onHibernateInit(AtherysHibernateInitializedEvent event) {
        init();
    }

    @Listener
    public void onHibernateConfiguration(AtherysHibernateConfigurationEvent event) {
        event.registerEntity(Nation.class);
        event.registerEntity(Town.class);
        event.registerEntity(Plot.class);
        event.registerEntity(Resident.class);
        event.registerEntity(PermissionNode.class);
    }

    @Listener
    public void onStart(GameStartingServerEvent event) {
        if (init) start();
    }

    @Listener
    public void onStop(GameStoppingServerEvent event) {
        if (init) stop();
    }

    public static AtherysTowns getInstance() {
        return instance;
    }

    public TownsConfig getConfig() {
        return components.config;
    }

    public NationRepository getNationRepository() {
        return components.nationRepository;
    }

    public TownRepository getTownRepository() {
        return components.townRepository;
    }

    public PlotRepository getPlotRepository() {
        return components.plotRepository;
    }

    public ResidentRepository getResidentRepository() {
        return components.residentRepository;
    }

    public PermissionRepository getPermissionRepository() {
        return components.permissionRepository;
    }

    public NationService getNationService() {
        return components.nationService;
    }

    public TownService getTownService() {
        return components.townService;
    }

    public PlotService getPlotService() {
        return components.plotService;
    }

    public ResidentService getResidentService() {
        return components.residentService;
    }

    public PermissionService getPermissionService() {
        return components.permissionService;
    }

    public TownsMessagingService getTownsMessagingService() {
        return components.townsMessagingService;
    }

    public NationFacade getNationFacade() {
        return components.nationFacade;
    }

    public TownFacade getTownFacade() {
        return components.townFacade;
    }

    public PlotFacade getPlotFacade() {
        return components.plotFacade;
    }

    public ResidentFacade getResidentFacade() {
        return components.residentFacade;
    }

    public PermissionFacade getPermissionFacade() {
        return components.permissionFacade;
    }

    public PlotSelectionFacade getPlotSelectionFacade() {
        return components.plotSelectionFacade;
    }
}
