package com.interfacesgraphiqueset3d.tp5;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Scene {
    // private ArrayList<Object3d> lstObject3d;
    private ArrayList<Sphere> lstSphere;
    private ArrayList<Light> lstLight;

    public void addObject3D(Sphere obj) {
        lstSphere.add(obj);
    }

    public void addObject3D(Light light) {
        lstLight.add(light);
    }

    public Scene() {
        lstSphere = new ArrayList<Sphere>();
        lstLight = new ArrayList<Light>();
        addObject3D(new Sphere("Sphere", new Vector3(0.0f, 0.0f, 0.0f), 1.0f, Color.GREEN));
        addObject3D(new Sphere("Sphere", new Vector3(2.0f, -1.0f, 0.0f), 1.5f, Color.BLUE));
        addObject3D(new Sphere("Sphere", new Vector3(-3.0f, 2.0f, 0.0f), 2.0f, Color.RED));
    }

    Color lauchnRay(Ray ray, int complexite) {
        Color color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        for (int i = 0; i < lstSphere.size(); i++) {
            Vector3 intersection = Vector3.Zero;
            boolean isIntersect = Intersector.intersectRaySphere(ray, lstSphere.get(i).getCentre(),
                    lstSphere.get(i).getRadius(), intersection);
            if (isIntersect) {
                color.add(lstSphere.get(i).getColor());
            }
        }
        return color;
    }
}