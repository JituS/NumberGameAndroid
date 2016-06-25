package com.example.jitendrasachdeva.newtest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public class NumberGame {
	private int previousNumber;
	private long startTime;
	boolean gameStart;
	private int maxLimit;
	private TextView scoreMsgText;
	private ArrayList<Button> allButtons;
	private Iterator<Integer> numbersToShow;

	public NumberGame(int maxLimit) {
		this.maxLimit = maxLimit;
		previousNumber = 0;
		gameStart = false;
	}


	public List<Integer> addInList(int from, int to, List<Integer> list) {
		for(int i = from; i <= to; i++) {
			list.add(i);
		}
		return list;
	}

	public void initialize() {
		List<Integer> shuffledNumbers = shuffle(addInList(1, 12, new ArrayList<Integer>()));
		List<Integer> nextNumbers = addInList(13, maxLimit, shuffledNumbers);
		shuffledNumbers.addAll(nextNumbers);
		this.numbersToShow = shuffledNumbers.iterator();
	}


	protected void startGame(ArrayList<Button> allButtons, TextView scoreMsgText) {
		this.scoreMsgText = scoreMsgText;
		this.allButtons = allButtons;
		renderNumbersToButton();
		addEventsToButtons(allButtons);
	}

	private void addEventsToButtons(ArrayList<Button> buttons) {
		for (Button button : buttons) {
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View btn) {
					if (!gameStart) start();
					Button button = (Button) btn;
					CharSequence text = button.getText();
					Integer currentNumber = Integer.parseInt(text.toString());
					if (currentNumber == previousNumber + 1) {
						previousNumber = currentNumber;
						button.setText(isAnyNumberLeft(currentNumber) ? getNextNumber() : "");
					}
					if (currentNumber >= maxLimit) {
						gameOver();
					}
				}
			});
		}
	}


	private void start() {
		gameStart = true;
		startTime = new Date().getTime();
	}

	private void renderNumbersToButton() {
		for (int i = 0; i < allButtons.size(); i++) {
			if (numbersToShow.hasNext()) {
				Integer number = numbersToShow.next();
				allButtons.get(i).setText(number.toString());
			}
		}
	}

	private void gameOver() {
		int timeTaken = (int) (new Date().getTime() - startTime) / 1000;
		assert scoreMsgText != null;
		scoreMsgText.setText("You have taken " + timeTaken + " second to complete");
		for (Button button : allButtons) {
			button.setEnabled(false);
		}
	}

	protected ArrayList<Integer> shuffle(List<Integer> numbers) {
		ArrayList<Integer> integers = new ArrayList<>();
		for (int i = numbers.size() - 1; i >= 0; i--) {
			int random = (int) Math.round(Math.random() * i);
			integers.add(numbers.get(random));
			numbers.remove(random);
		}
		return integers;
	}

	public boolean isAnyNumberLeft(Integer currentNumber) {
		return (currentNumber <= maxLimit - allButtons.size());
	}

	private String getNextNumber() {
		if (numbersToShow.hasNext())
			return numbersToShow.next().toString();
		return null;
	}

}
