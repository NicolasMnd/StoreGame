package render;

import game.GameObject;
import util.Pos;
import util.hitbox.Hitbox;

public class Save {

    Pos center;
    Hitbox camera;
    int residu, is, ie, js, je;
    GameObject[][] tiles;

    public Save(Pos p, Hitbox c, int r, int is, int ie, int js, int je) {
        this.center = p;
        this.camera = c;
        this.residu = r;
        this.is = is;
        this.ie = ie;
        this.js = js;
        this.je = je;
    }

    public Save(Pos p, Hitbox c, int r, int is, int ie, int js, int je, GameObject[][] t) {
        this.center = p;
        this.camera = c;
        this.residu = r;
        this.is = is;
        this.ie = ie;
        this.js = js;
        this.je = je;
        this.tiles = t;
    }

    public void printState() {
        System.out.println("Center: " + center.getFormat());
        System.out.println("Camera hitbox: " + camera.getPrint());
        System.out.println("Residu: " + residu);
        System.out.println("iStart: " + is);
        System.out.println("iEnd: " + ie);
        System.out.println("jStart: " + js);
        System.out.println("jEnd: " + je);
        printTiles(this.tiles);
    }

    private void printTiles(GameObject[][] l) {
        for (GameObject[] o : l) {
            for (GameObject oo : o)
                if (oo != null)
                    System.out.print(oo.getTexture() + ", ");
                else
                    System.out.print("null, ");
            System.out.println();
        }
    }

    public void saveTiles(GameObject[][] tiles) {
        this.tiles = tiles;
    }

}
