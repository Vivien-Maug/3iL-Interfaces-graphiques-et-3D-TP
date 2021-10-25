package com.interfacesgraphiqueset3d.tp5;

import java.util.ArrayList;

import com.Sphere;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.interfacesgraphiqueset3d.Light;
import com.interfacesgraphiqueset3d.Object3d;

public class Scene {
    private ArrayList<Object3d> lstObject3d;
    private ArrayList<Light> lstLight;

    public void addObject3D(Object3d obj) {
        lstObject3d.add(obj);
    }

    public void addObject3D(Light light) {
        lstLight.add(light);
    }

    public Scene() {
        addObject3D(new Sphere("Sphere", new Vector3(0.0f, 0.0f, 0.0f), Color.BLUE));
        addObject3D(new Sphere("Sphere", new Vector3(1.0f, 0.0f, 0.0f), Color.BLUE));
        addObject3D(new Sphere("Sphere", new Vector3(-1.0f, 0.0f, 0.0f), Color.BLUE));
    }

}
