/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafici;

import java.awt.Canvas;

/**
 *
 * @author Erli
 */
public class Punto {
    
    private int canvasX;
    private int canvasY;
    
    private int x;
    private int y;
    
    private int canvasWidth;
    private int canvasHeigh;
    
    public Punto(int x, int y, Canvas canvas) {
        
        canvasHeigh = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        
        this.x = x;
        this.y = y;
        
        canvasX = (int) (canvasWidth/2) + x;
        canvasY = (int) (canvasHeigh/2) + y * -1;
    }

    public int getCanvasX() {
        return canvasX;
    }

    public void setCanvasX(int canvasX) {
        this.canvasX = canvasX;
    }

    public int getCanvasY() {
        return canvasY;
    }

    public void setCanvasY(int canvasY) {
        this.canvasY = canvasY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeigh() {
        return canvasHeigh;
    }

    public void setCanvasHeigh(int canvasHeigh) {
        this.canvasHeigh = canvasHeigh;
    }

    @Override
    public String toString() {
        return "Punto{" + "canvasX=" + canvasX + ", canvasY=" + canvasY + ", x=" + x + ", y=" + y + ", canvasWidth=" + canvasWidth + ", canvasHeigh=" + canvasHeigh + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.canvasX;
        hash = 13 * hash + this.canvasY;
        hash = 13 * hash + this.x;
        hash = 13 * hash + this.y;
        hash = 13 * hash + this.canvasWidth;
        hash = 13 * hash + this.canvasHeigh;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Punto other = (Punto) obj;
        if (this.canvasX != other.canvasX) {
            return false;
        }
        if (this.canvasY != other.canvasY) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.canvasWidth != other.canvasWidth) {
            return false;
        }
        if (this.canvasHeigh != other.canvasHeigh) {
            return false;
        }
        return true;
    }
    
}
