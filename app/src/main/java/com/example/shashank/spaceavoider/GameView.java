package com.example.shashank.spaceavoider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    private Thread gameThread = null;
    private SurfaceHolder surfaceHolder;
    private volatile boolean gameRunning;
    private Paint paint;
    private Canvas canvas;
    private Ship ship;
    private ArrayList<Asteroid> asteroids = new ArrayList<>();

    public static int maxX = 40; // размер по горизонтали
    public static int maxY = 56; // размер по вертикали
    public static float unitW = 24; // пикселей в юните по горизонтали
    public static float unitH = 24; // пикселей в юните по вертикали

    private final int ASTEROID_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)
    private int currentTime = 0;

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();

        gameRunning = true;

        ship = new Ship(context);

        Random random = new Random();
        Asteroid asteroid = new Asteroid(context);
        asteroid.setX(random.nextInt(maxX) - asteroid.getRadius());
        asteroids.add(asteroid);

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewAsteroid();
            control();
        }
    }

    private void update() {
        ship.update();
        for (Asteroid asteroid : asteroids) {
            asteroid.update();
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            ship.draw(paint, canvas);
            for (Asteroid asteroid : asteroids) {
                asteroid.draw(paint, canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void checkCollision() {
        for (Asteroid asteroid : asteroids) {
            if (asteroid.getX() + asteroid.getSize() > ship.getX() &&
                    asteroid.getX() < ship.getX() + ship.getSize() &&
                    asteroid.getY() + asteroid.getSize() > ship.getY() &&
                    asteroid.getY() < ship.getY() + ship.getSize()) {
                gameRunning = false;
            }
        }
    }

    private void checkIfNewAsteroid() {
        currentTime++;
        if (currentTime >= ASTEROID_INTERVAL) {
            Random random = new Random();
            Asteroid asteroid = new Asteroid(getContext());
            asteroid.setX(random.nextInt(maxX) - asteroid.getRadius());
            asteroids.add(asteroid);
            currentTime = 0;
        }
    }

    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
