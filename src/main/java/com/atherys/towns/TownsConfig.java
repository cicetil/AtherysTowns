package com.atherys.towns;

import com.atherys.core.utils.PluginConfig;
import com.atherys.towns.api.NationPermissions;
import com.atherys.towns.api.Permission;
import com.atherys.towns.api.TownPermissions;
import com.atherys.towns.api.WorldPermissions;
import com.google.common.collect.Sets;
import com.google.inject.Singleton;
import ninja.leaping.configurate.objectmapping.Setting;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.util.Set;

@Singleton
public class TownsConfig extends PluginConfig {

    @Setting("default_nation_leader_permissions")
    public Set<Permission> DEFAULT_NATION_LEADER_PERMISSIONS = Sets.newHashSet(
            NationPermissions.INVITE_TOWN,
            NationPermissions.KICK_TOWN,
            NationPermissions.ADD_PERMISSION,
            NationPermissions.REVOKE_PERMISSION,
            NationPermissions.WITHDRAW_FROM_BANK,
            NationPermissions.DEPOSIT_INTO_BANK,
            NationPermissions.SET_NAME,
            NationPermissions.SET_DESCRIPTION,
            NationPermissions.SET_FREELY_JOINABLE,
            NationPermissions.ADD_ALLY,
            NationPermissions.REMOVE_ALLY,
            NationPermissions.DECLARE_WAR,
            NationPermissions.DECLARE_PEACE,
            NationPermissions.TRANSFER_LEADERSHIP,
            NationPermissions.CHAT
    );

    @Setting("default_nation_town_permissions")
    public Set<Permission> DEFAULT_NATION_TOWN_PERMISSIONS = Sets.newHashSet(

    );

    @Setting("default_nation_resident_permissions")
    public Set<Permission> DEFAULT_NATION_RESIDENT_PERMISSIONS = Sets.newHashSet(

    );

    @Setting("default_town_leader_permissions")
    public Set<Permission> DEFAULT_TOWN_LEADER_PERMISSIONS = Sets.newHashSet(
            TownPermissions.INVITE_RESIDENT,
            TownPermissions.KICK_RESIDENT,
            TownPermissions.CLAIM_PLOT,
            TownPermissions.UNCLAIM_PLOT,
            TownPermissions.ADD_PERMISSION,
            TownPermissions.REVOKE_PERMISSION,
            TownPermissions.WITHDRAW_FROM_BANK,
            TownPermissions.DEPOSIT_INTO_BANK,
            TownPermissions.LEAVE_NATION,
            TownPermissions.JOIN_NATION,
            TownPermissions.SET_NAME,
            TownPermissions.SET_DESCRIPTION,
            TownPermissions.SET_MOTD,
            TownPermissions.SET_COLOR,
            TownPermissions.SET_FREELY_JOINABLE,
            TownPermissions.SET_SPAWN,
            TownPermissions.TRANSFER_LEADERSHIP,
            TownPermissions.CHAT,

            WorldPermissions.BUILD,
            WorldPermissions.DESTROY,
            WorldPermissions.DAMAGE_NONPLAYERS,
            WorldPermissions.DAMAGE_PLAYERS,
            WorldPermissions.INTERACT_CHESTS,
            WorldPermissions.INTERACT_DOORS,
            WorldPermissions.INTERACT_REDSTONE
    );

    @Setting("default_town_resident_permissions")
    public Set<Permission> DEFAULT_TOWN_RESIDENT_PERMISSIONS = Sets.newHashSet(
            TownPermissions.CHAT,

            WorldPermissions.BUILD,
            WorldPermissions.DESTROY,
            WorldPermissions.DAMAGE_NONPLAYERS,
            WorldPermissions.DAMAGE_PLAYERS,
            WorldPermissions.INTERACT_CHESTS,
            WorldPermissions.INTERACT_DOORS,
            WorldPermissions.INTERACT_REDSTONE
    );

    protected TownsConfig() throws IOException {
        super("config/" + AtherysTowns.ID, "config.conf");
    }
}
