package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class Sphere extends Object3d {
    private float radius;

    public Sphere(String name, Vector3 positionCentre, float radius, Color color) {
        super(name, positionCentre, color);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public Vector3 getCentre() {
        return position;
    }
}
