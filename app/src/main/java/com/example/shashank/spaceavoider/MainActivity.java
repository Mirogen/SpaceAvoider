package com.example.shashank.spaceavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private static boolean isLeftPressed = false; // нажата левая кнопка
    private static boolean isRightPressed = false; // нажата правая кнопка

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameView gameView = new GameView(this); // создаем gameView
        LinearLayout gameLayout = findViewById(R.id.gameLayout); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView

        Button leftButton = findViewById(R.id.leftButton); // находим кнопки
        Button rightButton = findViewById(R.id.rightButton);

        leftButton.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)
        rightButton.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        int action = motion.getActionMasked(); // получаем основное действие

        switch (action) {
            case MotionEvent.ACTION_DOWN: // если это нажатие
            case MotionEvent.ACTION_POINTER_DOWN: // или нажатие нового пальца
                if (button.getId() == R.id.leftButton) {
                    isLeftPressed = true;
                } else if (button.getId() == R.id.rightButton) {
                    isRightPressed = true;
                }
                break;

            case MotionEvent.ACTION_UP: // если это отпускание
            case MotionEvent.ACTION_POINTER_UP: // или отпускание пальца
                if (button.getId() == R.id.leftButton) {
                    isLeftPressed = false;
                } else if (button.getId() == R.id.rightButton) {
                    isRightPressed = false;
                }
                break;
        }

        return true;
    }

    public static boolean isLeftPressed() {
        return isLeftPressed;
    }

    public static boolean isRightPressed() {
        return isRightPressed;
    }
}
