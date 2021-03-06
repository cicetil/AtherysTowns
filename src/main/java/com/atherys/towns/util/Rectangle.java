package com.atherys.towns.util;

import com.flowpowered.math.vector.Vector2i;

public interface Rectangle {
    Vector2i getTopLeftCorner();
    Vector2i getBottomRightCorner();

    void setTopLeftCorner(Vector2i point);
    void setBottomRightCorner(Vector2i point);

    default int maxX() {
        return getBottomRightCorner().getX();
    }

    default int minX() {
        return getTopLeftCorner().getX();
    }

    default int maxY() {
        return getTopLeftCorner().getY();
    }

    default int minY() {
        return getBottomRightCorner().getY();
    }
}
