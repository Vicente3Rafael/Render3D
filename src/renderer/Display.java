package renderer;

import renderer.point.MyPoint;
import renderer.shapes.MyPolygon;
import renderer.shapes.Tetrahedron;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private Thread thread;
    private JFrame frame;
    private static String title = "3D Renderer";
    public static final int WIDHT = 800;
    public static final int HEIGHT = 600;
    private static boolean running = false;

    private Tetrahedron tetrahedron;

    public Display() {
        this.frame = new JFrame();

        Dimension size = new Dimension(WIDHT, HEIGHT);
        this.setPreferredSize(size);
    }

    public static void main(String[] args){
        Display display = new Display();
        display.frame.setTitle(title);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setResizable(false);
        display.frame.setVisible(true);

        display.start();
    }

    public synchronized void start() {
        running = true;
        this.thread = new Thread(this, "renderer.Display");
        this.thread.start();
    }

    public synchronized void stop() {
        running = false;

        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60; //numero de atualizações por segundo
        double delta = 0;//porcentagem entre uma atualizacao e outra
        int frames = 0;

        init();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){ //1s == 1000ms, entao isso atualiza o fps a cada s q o programa roda
                timer += 1000;
                this.frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
            }
        }

        stop();
    }

    private void init(){
        double size = 100;

        MyPoint p1 = new MyPoint(size/2, -size / 2, -size / 2);
        MyPoint p2 = new MyPoint(size/2, -size / 2, size/2);
        MyPoint p3 = new MyPoint(size/2, size/2, size/2);
        MyPoint p4 = new MyPoint(size/2, size/2, -size/2);
        MyPoint p5 = new MyPoint(-size/2, size/2, -size/2);
        MyPoint p6 = new MyPoint(-size/2, -size/2, -size/2);
        MyPoint p7 = new MyPoint(-size/2, -size/2, size/2);
        MyPoint p8 = new MyPoint(-size/2, size/2, size/2);

       this.tetrahedron = new Tetrahedron(Color.YELLOW,
               new MyPolygon(Color.BLACK, p1, p2, p3, p4),
               new MyPolygon(Color.RED, p1, p2, p7, p6),
               new MyPolygon(Color.YELLOW, p5, p6, p7, p8),
               new MyPolygon(Color.ORANGE, p5, p8, p3, p4),
               new MyPolygon(Color.GREEN, p1, p4, p5, p6),
               new MyPolygon(Color.BLUE, p2, p3, p8, p7));
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,WIDHT, HEIGHT);

        tetrahedron.render(graphics);

        graphics.dispose();

        bs.show();
    }

    private void update(){

    }
}
