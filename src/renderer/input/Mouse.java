package renderer.input;

import renderer.input.enumeration.ClickType;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseB = -1;
    private int scrool = 0;

    private int initialX = -1;
    private int initialY = -1;
    private int finalX = -1;
    private int finalY = -1;

    public int getX(){
        return this.mouseX;
    }

    public int getY(){
        return this.mouseY;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public ClickType getButton(){

        if(this.mouseB == 1){
            return ClickType.LeftClick;
        }else if(this.mouseB == 2){
            return ClickType.ScrollClick;
        }else if(this.mouseB == 3){
            return ClickType.RightClick;
        }else if(this.mouseB == 4){
            return ClickType.ForwardPage;
        }else if(this.mouseB == 5){
            return ClickType.BackPage;
        }

        return ClickType.NoClick;
    }

    public void resetButton(){
        this.mouseB = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseB = e.getButton();
        if(this.getButton().equals(ClickType.LeftClick)){
            this.mouseX = e.getX();
            this.mouseY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseB = e.getButton();
        if(this.getButton().equals(ClickType.LeftClick)){
            this.mouseX = e.getX();
            this.mouseY = e.getY();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("MOUSE DRAGGED");
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("MOUSE MOVED");
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
