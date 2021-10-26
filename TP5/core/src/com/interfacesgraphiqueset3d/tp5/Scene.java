package com.interfacesgraphiqueset3d.tp5;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Scene {
    private ArrayList<Object3d> lstObject3d;
    private ArrayList<Light> lstLight;

    public void addObject3D(Object3d obj) {
        lstObject3d = new ArrayList<Object3d>();
        lstLight = new ArrayList<Light>();
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

    public Boolean drawImage(Pixmap pixels, PerspectiveCamera camera, int complexite) {
        boolean isDrawEndProperly = false;
        
        Vector3 foyer; // Foyer optique de la camera
        Vector3 droite; // Vecteur partant sur la droite dans le plan de l'ecran
        float dx, dy; // dimension des macro-pixels
        int x, y; // Position dans l'image du pixel en cours de calcul
        Vector3 hg; // Position du pixel au centre du premier macro-pixel de l'ecran (en haut a gauche)
        Vector3 pt; // Position de l'intersection entre le rayon a lancer et l'ecran
        Ray ray = new Ray(); // Rayon a lancer
        Vector3 vect; // Vecteur directeur du rayon a lancer
        // On calcule la position du foyer de la camera
        
        
        foyer = camera.position.add(camera.direction.scl(-camera.far));
        
        // On calcule le vecteur unitaire "droite" du plan
        droite = camera.direction.crs(camera.up);

        // On calcule le deltaX et le deltaY
        dx = camera.viewportWidth / pixels.getWidth();
        dy = camera.viewportHeight / pixels.getHeight();

        // On calcule la position du premier point de l'ecran que l'on calculera
        hg = camera.position.add(droite.scl((dx / 2) - (camera.viewportWidth / 2))).add(camera.up.scl((camera.viewportHeight / 2) - (dy / 2))); 


        // Pour chaque pixel de l'image à calculer
        Color cFound = Color.BLACK;
        Color cFinale = Color.BLACK;
        float decalageX[] = {0.0f, dx/2.0f, dx/2.0f, 0.0f   , -dx/2.0f, -dx/2.0f, -dx/2.0f, 0.0f    , dx/2.0f};
        float decalageY[] = {0.0f, 0.0f   , dy/2.0f, dy/2.0f, dy/2.0f , 0.0f    , -dy/2.0f, -dy/2.0f, dy/2.0f};
        
        int antiAlia = 2; // [0, 8] facteur d'anti-alisasing à renseigner ici
        for (y = 0; y < pixels.getWidth(); y++) {
            for (x = 0; x < pixels.getHeight(); x++) {
                for (int i = 0; i <= antiAlia; i++) {
                        // On calcule la position dans l'espace de ce point
                        pt = hg.add(droite.scl(dx * x + decalageX[i % 9])).add(camera.up.scl(dy * y + decalageY[i % 9]).scl(-1));
                        
                        // On prepare le rayon qui part du foyer et qui passe par ce point
                        vect = pt.add(foyer.scl(-1));
                        vect = vect.nor();
                        ray.set(pt, vect);
                        
                        // Couleur trouvée :
                        cFound = cFound.add(this.lauchnRay(complexite));
                }

                // On fais la moyenne des couleurs trouvées :
                cFinale = cFound.mul(1 / (antiAlia + 1.0f)); 
                System.out.println(cFinale.r);

                // Et on enregistre la couleur du rayon dans l'image
                pixels.setColor(cFinale);
                pixels.drawPixel(x, y);
                cFound = Color.BLACK;
            }
            // System.out.println(((y+1) * 100) / pixels.getHeight());
        }

        isDrawEndProperly = true;
        return isDrawEndProperly;
    }

    Color lauchnRay(int complexite){

        return Color.RED;
    }
}
