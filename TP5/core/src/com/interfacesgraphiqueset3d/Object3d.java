package com.interfacesgraphiqueset3d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public abstract class Object3d {
    String name;
    Vector3 position;
    Color color;

    public Object3d(String name, Vector3 position, Color color) {
        this.name = name;
        this.position = position;
        this.color = color;

    }
}
