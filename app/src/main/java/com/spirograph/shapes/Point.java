package com.spirograph.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Point {
    private float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void drawPoint(Paint paint, Canvas canvas) {
        canvas.drawPoint(this.x, this.y, paint);
    }
}
