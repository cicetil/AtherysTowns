package com.atherys.towns;

import com.atherys.towns.api.chat.TownsChatService;
import com.atherys.towns.facade.*;
import com.atherys.towns.persistence.*;
import com.atherys.towns.service.*;
import com.atherys.towns.service.chat.SimpleTownsChatService;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.user.UserStorageService;

public class AtherysTownsModule extends AbstractModule {
    @Override
    protected void configure() {
        // Config
        bind(TownsConfig.class);

        // Repositories
        bind(NationRepository.class);
        bind(TownRepository.class);
        bind(PlotRepository.class);
        bind(ResidentRepository.class);
        bind(PermissionRepository.class);

        // Sponge Services
        bind(UserStorageService.class).toProvider(() -> {
            return Sponge.getServiceManager().provide(UserStorageService.class).orElse(null);
        }).in(Scopes.SINGLETON);
        bind(EconomyService.class).toProvider(() -> {
            return Sponge.getServiceManager().provide(EconomyService.class).orElse(null);
        }).in(Scopes.SINGLETON);

        // Services
        bind(NationService.class);
        bind(TownService.class);
        bind(PlotService.class);
        bind(ResidentService.class);
        bind(PermissionService.class);
        bind(TownsChatService.class).to(SimpleTownsChatService.class);

        // Facades
        bind(NationFacade.class);
        bind(TownFacade.class);
        bind(PlotFacade.class);
        bind(ResidentFacade.class);
        bind(PermissionFacade.class);
        bind(PlotSelectionFacade.class);
        bind(TownsMessagingFacade.class);
    }
}