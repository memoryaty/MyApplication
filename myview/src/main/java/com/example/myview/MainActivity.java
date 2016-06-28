package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyView.OnGameOverListener{

    private MyView myView;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.myview);
        myView.setOnGameOverListener(this);

        tv = (TextView) findViewById(R.id.displaytv);
    }

    @Override
    public void onGameOver(boolean whoWin) {
        Log.i("myview", "onGameOver");
        if (whoWin) {
            tv.setTextColor(0x99ff0000);
            tv.setText("白棋获胜");
        }else {
            tv.setTextColor(0x99ff0000);
            tv.setText("黑棋获胜");
        }
    }
}
