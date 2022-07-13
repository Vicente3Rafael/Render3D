package renderer.shapes;

import renderer.point.MyPoint;
import renderer.point.PointConverter;

import java.awt.*;
import java.util.*;
import java.util.List;

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

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPoint point: points){
            PointConverter.rotateAxisX(point, CW, xDegrees);
            PointConverter.rotateAxisY(point, CW, yDegrees);
            PointConverter.rotateAxisZ(point, CW, zDegrees);
        }
    }

    public double getAverageX(){
        double sum = 0;
        for(MyPoint point: this.points){
            sum += point.x;
        }

        return sum/points.length;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public static MyPolygon[] sortPolygons(MyPolygon[] polygons){
        List<MyPolygon> polygonList = new ArrayList<>(Arrays.asList(polygons));

        Collections.sort(polygonList, new Comparator<MyPolygon>() {
            @Override
            public int compare(MyPolygon p1, MyPolygon p2) {
                return p2.getAverageX() - p1.getAverageX() < 0 ? 1 : -1;
            }
        });

        for(int i = 0;i < polygons.length; i++){
            polygons[i] = polygonList.get(i);
        }

        return polygons;
    }
}
