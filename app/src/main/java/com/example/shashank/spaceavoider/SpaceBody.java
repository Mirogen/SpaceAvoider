package com.example.shashank.spaceavoider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SpaceBody {
    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка

    public SpaceBody(Context context) {
        // Конструктор класса SpaceBody
        // Инициализация
        x = 1.5f;
        y = 1.5f;
        size = 1.5f;
        speed = 5;
        bitmapId = 1;
        bitmap = null;
    }

    public void init(Context context) {
        // Инициализация
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int) (size * GameView.unitW), (int) (size * GameView.unitH), false);
        cBitmap.recycle();
    }

    public void update() {
        // Обновление состояния
    }

    public void draw(Paint paint, Canvas canvas) {
        // Рисование
        canvas.drawBitmap(bitmap, x * GameView.unitW, y * GameView.unitH, paint);
    }

    // Геттеры и сеттеры для всех полей класса
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

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getBitmapId() {
        return bitmapId;
    }

    public void setBitmapId(int bitmapId) {
        this.bitmapId = bitmapId;
    }
}
