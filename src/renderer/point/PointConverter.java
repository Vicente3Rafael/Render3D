package renderer.point;

import renderer.Display;

import java.awt.*;

public class PointConverter {

    private static double scale = 1;

    public static Point convertPoint(MyPoint point3d){
        double x3d = point3d.y * scale;
        double y3d = point3d.z * scale;
        double depth = point3d.x * scale;

        double[] newValue = scale(x3d, y3d, depth);

        int x2d = (int) (Display.WIDHT / 2 + newValue[0]);
        int y2d = (int) (Display.HEIGHT / 2 - newValue[1]);

        return new Point(x2d, y2d);
    }

    public static double[] scale(double x3d, double y3d, double depth){
        double dist = Math.sqrt((x3d*x3d) + (y3d*y3d)); //distancia do vetor de origem até o encontro de x e y(z e y)
        double theta = Math.atan2(y3d, x3d);
        double cameraDepth = 15 - depth;
        double localScale = Math.abs(1400/(cameraDepth+1400));
        dist *= localScale;

        double[] newValue = new double[2];
        newValue[0] = dist * Math.cos(theta);
        newValue[1] = dist * Math.sin(theta);

        return newValue;
    }
}
