package renderer.shapes;

import renderer.point.MyPoint;
import renderer.point.PointConverter;

import java.awt.*;

public class MyPolygon {

    private Color color;
    private MyPoint[] points;

    public MyPolygon(Color color, MyPoint... points){
        this.color = color;
        this.points = new MyPoint[points.length];
        for(int i = 0; i < points.length; i++){
            MyPoint point = points[i];
            this.points[i] = new MyPoint(point.x, point.y, point.z);
        }
    }

    public void render(Graphics graphics){
        Polygon polygon = new Polygon();
        for(int i = 0;i < points.length; i++){
            Point point = PointConverter.convertPoint(points[i]);
            polygon.addPoint(point.x, point.y);
        }

        graphics.setColor(this.color);
        graphics.fillPolygon(polygon);
    }

    public void setColor(Color color){
        this.color = color;
    }
}
