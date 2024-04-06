package com.example.shashank.spaceavoider;

import android.content.Context;

import java.util.Random;

public class Asteroid extends SpaceBody {
    private int radius; // радиус

    private float minSpeed = (float) 0.1; // минимальная скорость
    private float maxSpeed = (float) 0.5; // максимальная скорость

    public Asteroid(Context context) {
        super(context);
        Random random = new Random();
        radius = 3; // Установим радиус по умолчанию
        setBitmapId(R.drawable.asteroid);
        setY(0);
        setX(random.nextInt(GameView.maxX) - radius);
        setSize(radius * 2);
        setSpeed(minSpeed + (maxSpeed - minSpeed) * random.nextFloat());
        init(context);
    }

    @Override
    public void update() {
        setY(getY() + getSpeed());
    }

    public int getRadius() {
        return radius;
    }
}
