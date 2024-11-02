package com.example.javafxgame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.util.*;

import static javafx.scene.paint.Color.*;

public class JavaFxGame extends Application {

    public final int screenWidth = 1920;
    public final int screenHeight = 1005;

    public final int centerx = screenWidth/2;
    public final int centery = screenHeight/2;

    public double r = 0.5;
    public double g = 0.5;
    public double b = 0.5;
    public boolean button = false;
    public boolean delete = false;
    public String time = "";
    public int start_time = 0000;
    public int end_time = 0000;

    public ArrayList<Task> tasklist = new ArrayList<>();

    Button test_button = new Button(0,300,50,50,Color.RED);
    public ArrayList<Button> deletelist = new ArrayList<>();




    @Override
    public void start(Stage stage)  {

        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Create a timeline, with a gap between newFrame() calls of 16.66_ seconds, meaning around 60 fps.
        // Pass the newFrame() function as the function to be called each frame.
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(16.0 + (2.0 / 3.0)), e -> newFrame(gc)));

        tl.setCycleCount(Animation.INDEFINITE);
        canvas.setFocusTraversable(true);

        // handle mouse and key events
        canvas.setOnKeyPressed(e -> {
            // KeyCode holds what key is being referred to
            KeyCode code = e.getCode();

            if (button && code.getChar().equals("1")) {
                time += "1";
            }
            if (button && code.getChar().equals("2")) {
                time += "2";
            }
            if (button && code.getChar().equals("3")) {
                time += "3";
            }
            if (button && code.getChar().equals("4")) {
                time += "4";
            }
            if (button && code.getChar().equals("5")) {
                time += "5";
            }
            if (button && code.getChar().equals("6")) {
                time += "6";
            }
            if (button && code.getChar().equals("7")) {
                time += "7";
            }
            if (button && code.getChar().equals("8")) {
                time += "8";
            }
            if (button && code.getChar().equals("9")) {
                time += "9";
            }
            if (button && code.getChar().equals("0")) {
                time += "0";
            }
            if (button && code.getChar().equals("-")) {
                time += "-";
            }
            if (button && code == KeyCode.BACK_SPACE) {
                time = time.substring(0,time.length() - 1);
            }
            if (button && code == KeyCode.ENTER){
                if (time.contains("-") && time.length()==9){
                    String[] tokens = time.split("-");
                    // {"0900","1000"}
                    int start_time = Integer.parseInt(tokens[0]);
                    int end_time = Integer.parseInt(tokens[1]);
                    time = "";
                    button = false;
                    if (end_time < start_time){
                        end_time+=2400;
                    }
                    this.start_time = start_time;
                    this.end_time = end_time;
                    tasklist.add(new Task("test", start_time, end_time, Color.color(r,g,b)));
                    System.out.println("tls:" + tasklist.size());
                }
                else{
                    time = "F";
                }
            }

        });
        canvas.setOnKeyReleased(e -> {
            KeyCode code = e.getCode();

        });
        canvas.setOnMouseDragged(e -> {
            double x = e.getX();
            double y = e.getY();
            if(x>screenWidth-360){
                if(y>0 && y<=45){
                    double new_r = (x - (screenWidth - 360))/360;
                    r = new_r;
                }
                if(y>46 && y<=91){
                    double new_g = (x - (screenWidth - 360))/360;
                    g = new_g;
                }
                if(y>92 && y<=137){
                    double new_b = (x - (screenWidth - 360))/360;
                    b = new_b;
                }
                if(y>150 && y<=200 && x<screenWidth-310){
                    button = true;
                }

            }
            // these coordinates represent the constant location of the mouse.
        });
        canvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();


            if (test_button.inside((int) x, (int) y)){
                delete = true;
            }

            for (int i = 0; i < deletelist.size(); i++) {
                if(deletelist.get(i).inside((int) x, (int) y)) {
                    tasklist.remove(i);
                    System.out.println(i);
                }
            }

            // these coordinates represent the locations the mouse has been clicked at.
            System.out.println("Mouse clicked at: " + x + "," + y);
            if(x>screenWidth-360){
                if(y>0 && y<=45){
                    double new_r = (x - (screenWidth - 360))/360;
                    r = new_r;
                }
                if(y>46 && y<=91){
                    double new_g = (x - (screenWidth - 360))/360;
                    g = new_g;
                }
                if(y>92 && y<=137){
                    double new_b = (x - (screenWidth - 360))/360;
                    b = new_b;
                }
                if(y>150 && y<=200 && x<screenWidth-310){
                    button = true;
                }
            }
        });

        stage.setTitle("JavaFxGame");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    long lastframetime = System.currentTimeMillis();
    double dt = 0;

    private void newFrame(GraphicsContext gc) {

        gc.setFill(BLACK);
        gc.fillRect(0,0, screenWidth, screenHeight);

        dt = (double)(System.currentTimeMillis() - lastframetime)/1000;
        lastframetime = System.currentTimeMillis();

        LocalDateTime time = LocalDateTime.now();
        double hr_ang = -(((time.getHour() + time.getMinute()/60.0)/24.0)*2*Math.PI)-0.5*Math.PI;
        double x2 = centerx + Math.cos(hr_ang)*(screenHeight/3);
        double y2 = centery - Math.sin(hr_ang)*(screenHeight/3);

//        gc.setFill(Color.color(Math.random(), Math.random(),Math.random()));
//        gc.fillArc(centerx-screenHeight/3, centery-screenHeight/3, 2*screenHeight/3, 2*screenHeight/3, 10, 1, ArcType.ROUND);
//        gc.setFill(Color.color(Math.random(), Math.random(),Math.random()));
//        gc.fillArc(centerx-screenHeight/3, centery-screenHeight/3, 2*screenHeight/3, 2*screenHeight/3, 31, 30, ArcType.ROUND);





        for (int i = 0; i<24; i++){
//            gc.setStroke(WHITE);
//            gc.setLineWidth(5.0);
            gc.setFill(Color.gray(i/24.0));
           // gc.fillArc(centerx-screenHeight/3, centery-screenHeight/3, 2*screenHeight/3, 2*screenHeight/3, 360*i/24.0-90, 15, ArcType.ROUND);
//            double hr_ang = -(((time.getHour() + time.getMinute()/60.0)/24.0)*2*Math.PI)-0.5*Math.PI;
//            double x2 = centerx + Math.cos(hr_ang)*(screenHeight/3);
//            double y2 = centery - Math.sin(hr_ang)*(screenHeight/3);
        }





        gc.setFill(Color.color(r,g,b));
        gc.fillRect(screenWidth-360, 0, 360, 45);
        gc.fillRect(screenWidth-360, 46, 360, 45);
        gc.fillRect(screenWidth-360, 92, 360, 45);

        gc.setFill(BLACK);
        gc.fillRect(screenWidth-360+(r*360), 0,3,45);
        gc.fillRect(screenWidth-360+(g*360), 46,3,45);
        gc.fillRect(screenWidth-360+(b*360), 92,3,45);
        gc.setFill(WHITE);
        gc.fillText(this.time, screenWidth-360,215);//text

        gc.fillRect(screenWidth-360, 150,50,50);//button
        test_button.render(gc);

        gc.setFill(Color.color(r,g,b));
//        double s = (360.0*(start_time/100.0))-90.0;
        double s = (start_time/100.0)*(1.0/24.0)*-360.0;
        double e = ((end_time-start_time)/100.0)*(1.0/24.0)*-360.0;// 0930 -> 0950

//        System.out.println(s + ":"  + start_time);
//        System.out.println(e + ":" + end_time);
        gc.fillText(s + ":"  + start_time + "-" + e + ":" + end_time, 50,50);
//        Task test = new Task("test task", s,e, Color.color(r,g,b));
//        test.draw(gc, centerx, screenHeight, centery);
        for (int i = 0; i < tasklist.size(); i++) {
            tasklist.get(i).draw(gc,centerx,screenHeight,centery);
            //create an object button that can delete systems from the array
            //convert :30 -> .50
        }


        // delete buttons are here
        if (delete) {
            deletelist.clear();
            for (int i = 0; i < tasklist.size(); i++) {
                deletelist.add(new Button(0,i*10,10,9,tasklist.get(i).c));
                deletelist.get(i).render(gc);
            }
        }



        gc.setStroke(WHITE);
        gc.setLineWidth(1.0);
        gc.strokeLine(centerx, centery, x2, y2); // the center line

        gc.setStroke(SADDLEBROWN);
        gc.setLineWidth(5.0);
        gc.strokeArc(centerx-screenHeight/3, centery-screenHeight/3, 2*screenHeight/3, 2*screenHeight/3, 0, 360, ArcType.OPEN);








    }

    public static void main(String[] args) {
        launch();
    }
}