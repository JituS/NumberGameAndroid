package com.example.jitendrasachdeva.newtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NumberGame numberGame = new NumberGame(40);
		numberGame.initialize();
		numberGame.startGame(getAllButtons(), (TextView) findViewById(R.id.editText));
	}

	public ArrayList<Button> getAllButtons() {
		final ArrayList<Button> buttons = new ArrayList<>();
		buttons.add((Button) findViewById(R.id.button));
		buttons.add((Button) findViewById(R.id.button2));
		buttons.add((Button) findViewById(R.id.button3));
		buttons.add((Button) findViewById(R.id.button5));
		buttons.add((Button) findViewById(R.id.button6));
		buttons.add((Button) findViewById(R.id.button7));
		buttons.add((Button) findViewById(R.id.button8));
		buttons.add((Button) findViewById(R.id.button9));
		buttons.add((Button) findViewById(R.id.button10));
		buttons.add((Button) findViewById(R.id.button11));
		buttons.add((Button) findViewById(R.id.button12));
		buttons.add((Button) findViewById(R.id.button13));
		Button restart = (Button) findViewById(R.id.restart);
		assert restart != null;
		restart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View btn) {
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
		return buttons;
	}
}
