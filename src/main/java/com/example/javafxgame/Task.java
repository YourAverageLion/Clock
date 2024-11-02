package com.example.javafxgame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Task {
    String name;
    double start;
    double end;
    Color c;

    public Task(String name, double start, double end, Color c) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.c = c;
    }

    public void draw(GraphicsContext gc, double centerx, double screenHeight, double centery) {
        gc.setFill(c);
        double s = (start/100.0)*(1.0/24.0)*-360.0;
        double e = ((end-start)/100.0)*(1.0/24.0)*-360.0;
        gc.fillArc(centerx-screenHeight/3, centery-screenHeight/3, 2*screenHeight/3, 2*screenHeight/3, s-90, e, ArcType.ROUND);
    }


//    int
}
//TODO: Define arcs (attributes, , define legend
