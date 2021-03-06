package com.atherys.towns.model;

import com.atherys.towns.util.Rectangle;
import com.flowpowered.math.vector.Vector2i;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.util.UUID;

public class BorderInfo implements Rectangle {

    private ParticleEffect effect;
    private Vector2i neCorner;
    private Vector2i swCorner;
    private UUID playerUUID;

    public BorderInfo(ParticleEffect effect, UUID playerUUID, Vector2i neCorner, Vector2i swCorner) {
        this.effect = effect;
        this.playerUUID = playerUUID;
        this.neCorner = neCorner;
        this.swCorner = swCorner;
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public Vector2i getNECorner() {
        return this.neCorner;
    }

    public void setNECorner(Vector2i neCorner) {
        this.neCorner = neCorner;
    }

    public Vector2i getSWCorner() {
        return this.swCorner;
    }

    public void setSWCorner(Vector2i swCorner) {
        this.swCorner = swCorner;
    }

    public ParticleEffect getEffect() {
        return this.effect;
    }

    public void setEffect(ParticleEffect effect) {
        this.effect = effect;
    }

    @Override
    public Vector2i getTopLeftCorner() {
        return swCorner;
    }

    @Override
    public Vector2i getBottomRightCorner() {
        return neCorner;
    }

    @Override
    public void setTopLeftCorner(Vector2i point) {
        swCorner = point;
    }

    @Override
    public void setBottomRightCorner(Vector2i point) {
        neCorner = point;
    }
}
