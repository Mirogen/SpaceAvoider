package com.example.shashank.spaceavoider;

import android.content.Context;

public class Ship extends SpaceBody {
    public Ship(Context context) {
        super(context);
        setBitmapId(R.drawable.ship);
        setSize(5);
        setX(7);
        setY(GameView.maxY - getSize() - 1 + 7);
        setSpeed((float) 0.2);
        init(context);
    }

    @Override
    public void update() {
        if (MainActivity.isLeftPressed() && getX() >= 0) {
            setX(getX() - getSpeed());
        }
        if (MainActivity.isRightPressed() && getX() <= GameView.maxX - getSize()) {
            setX(getX() + getSpeed());
        }
    }
}
