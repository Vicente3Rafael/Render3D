package renderer.shapes;

import renderer.point.MyPoint;
import renderer.point.PointConverter;

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

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPolygon polygon: this.polygons){
            polygon.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    private void sortPolygons() {
        MyPolygon.sortPolygons(this.polygons);
    }

    private void setPolygonColor() {
        for(MyPolygon polygon: this.polygons){
            polygon.setColor(color);
        }
    }
}
