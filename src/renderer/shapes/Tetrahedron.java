package renderer.shapes;

import java.awt.*;

public class Tetrahedron {

    private MyPolygon[] polygons;
    private Color color;

    public Tetrahedron(Color color, MyPolygon... polygons){
        this.color = color;
        this.polygons = polygons;
        //setPolygonColor();
    }

    public void render(Graphics graphics){
        for(MyPolygon polygon: this.polygons){
            polygon.render(graphics);
        }
    }

    private void sortPolygons() {

    }

    private void setPolygonColor() {
        for(MyPolygon polygon: this.polygons){
            polygon.setColor(color);
        }
    }
}
