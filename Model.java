import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Model extends View implements Serializable {

	BufferedWriter writer;
	BufferedReader reader;
	String filename;
	JFileChooser chooser = new JFileChooser();

	private int playerCardCount;
	private int dealerCardCount;
	private Deck deck = new Deck();

	int card1, card2, dealCard1;
	Random ran = new Random();

	int card3, card4, card5;

	int dealCard2, dealCard3, dealCard4, dealCard5;

	Card c1, c2, c3, c4, c5, dC1, dC2, dC3, dC4, dC5;

	int initialSum;
	int dealerSum;
	int hit1Sum, hit2Sum, hit3Sum;
	int currentSum;

	LinkedList<String> stm = new LinkedList<String>();

	String currentState = "";
	Double hitPercent;

	double punishment = 0.95;
	double reward = 1.07;

	double standReward = 0.93;
	double standPunishment = 1.05;
	
	boolean AIPlaying = false;

	public Model() {

	}

	public void playFunc() {
		currentState = "";
		initialSum = 0;
		dealerSum = 0;

		winOrLose.setText("");

		sysCard1.setText("");
		sysCard2.setText("");
		sysCard3.setText("");
		sysCard4.setText("");
		sysCard5.setText("");

		oppCard1.setText("");
		oppCard2.setText("");
		oppCard3.setText("");
		oppCard4.setText("");
		oppCard5.setText("");

		playerCardCount = 2;
		dealerCardCount = 1;

		playB.setEnabled(false);
		hitB.setEnabled(true);
		standB.setEnabled(true);
		playAIB.setEnabled(false);
		playAI100B.setEnabled(false);

		card1 = ran.nextInt(51);

		card2 = ran.nextInt(51);

		while (card2 == card1) { // Make Sure Card isnt duplicate
			card2 = ran.nextInt(51);
		}

		dealCard1 = ran.nextInt(51);
		while (dealCard1 == card1 | dealCard1 == card2) {
			dealCard1 = ran.nextInt(51);
		}

		// Assign cards

		c1 = deck.cards[card1];
		c2 = deck.cards[card2];

		dC1 = deck.cards[dealCard1];

		initialSum = c1.getValue() + c2.getValue();
		currentSum = initialSum;
		dealerSum = dC1.getValue();
		sumLab.setText(Integer.toString(initialSum));

		// CARD 1
		if (!c1.getName().equals(Integer.toString(c1.getValue()))) {
			oppCard1.setText("<html><h1>" + c1.getName() + "</h1><br/>" + Integer.toString(c1.getValue()) + "</html>");

		} else {
			oppCard1.setText("<html><h1>" + c1.getName() + "</h1></html>");
		}
		// CARD 2
		if (!c2.getName().equals(Integer.toString(c2.getValue()))) {
			oppCard2.setText("<html><h1>" + c2.getName() + "</h1><br/>" + Integer.toString(c2.getValue()) + "</html>");

		} else {
			oppCard2.setText("<html><h1>" + c2.getName() + "</h1></html>");
		}

		// DEALER CARD
		if (!dC1.getName().equals(Integer.toString(dC1.getValue()))) {
			sysCard1.setText(
					"<html><h1>" + dC1.getName() + "</h1><br/>" + Integer.toString(dC1.getValue()) + "</html>");

		} else {
			sysCard1.setText("<html><h1>" + dC1.getName() + "</h1></html>");
		}
		if (initialSum == 21) {
			winOrLose.setText("YOU WON! BLACK JACK");
			hitB.setEnabled(false);
			standB.setEnabled(false);
			playB.setEnabled(true);
		}
		if (initialSum > 21) {
			winOrLose.setText("YOU BUSTED!");
			hitB.setEnabled(false);
			standB.setEnabled(false);
			playB.setEnabled(true);

		}
		// WORKING ON GETTING AI TO PLAY 1 GAME, NEED TO RECORD GAME STATE
		// FOR EVERY MOVE THEN MAKE FUNCTION TO PLAY WHOLE GAME AUTOMATICALLY
		// THEN RUN THROUGH LOOP

		currentState = Integer.toString(initialSum) + "/" + Integer.toString(dealerSum);
		boolean stateExists = false;
		String[] state = currentState.split("/");
		if (initialSum != 21 || initialSum != 22) {
			for (int i = 0; i < stm.size(); i++) {
				String[] stmState = stm.get(i).split("/");
					if (currentState.equals(stmState[0] + "/" + stmState[1])) {
						stateExists = true;
						hitPercent = Double.parseDouble(stmState[2].substring(1)) / 100;
						currentState = stm.get(i);
					}
				
			}
		}

		if (!stateExists) {
			currentState += "/%50";
			stm.add(currentState);
		}
		System.out.println(currentState);
	}
	public void makeSTM() {
		for( int i = 4; i < 23; i ++ ) {
			for(int j = 2; j < 12; j ++ ) {
				stm.add(Integer.toString(i) + "/" + Integer.toString(j) + "/%50");
			}
		}
	}
	
	public void AIPlay10() {
		for(int i = 0; i < 100; i ++) {
			AIPlay();
		}
	}
	public void AIPlay() {
		
		AIPlaying = true;

		playFunc();
		
		String[] state = currentState.split("/");
		
		hitPercent = Double.parseDouble(state[2].substring(1))/100;
		System.out.println("HIT P = "+hitPercent.toString());
		Double standPercent = 1 - hitPercent;
		
		//MAX HIT 3 TIMES
		while(AIPlaying) {
			if(Math.random() <= hitPercent) {
				hitFunc();
			}else {
				standFunc();
			}
		}



	}

	public void printSTM() {
		System.out.println("Printing STM:");
		Iterator<String> iterator = stm.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public void hitFunc() {
		playerCardCount++;

		String[] state;
		switch (playerCardCount) {
		case 3:
			card3 = ran.nextInt(51);
			while (card3 == card2) { // Make Sure Card isnt duplicate
				card3 = ran.nextInt(51);
			}
			c3 = deck.cards[card3];
			hit1Sum = currentSum + c3.getValue();
			sumLab.setText(Integer.toString(hit1Sum));
			currentSum = hit1Sum;

			if (!c3.getName().equals(Integer.toString(c3.getValue()))) {
				oppCard3.setText(
						"<html><h1>" + c3.getName() + "</h1><br/>" + Integer.toString(c3.getValue()) + "</html>");

			} else {
				oppCard3.setText("<html><h1>" + c3.getName() + "</h1></html>");
			}
			// IF NEW SUM > 21 DISABLE BUTTON AND DO END FUNCTION
			if (!sumBelowTwentyOne()) {
				hitB.setEnabled(false);
				standB.setEnabled(false);
			}
			if (hit1Sum == 21) {
				AIPlaying = false;
				// WON FROM HITTING

				state = currentState.split("/");
				String p = state[2];
				Double d = Double.parseDouble(p.substring(1)) / 100;

				d *= reward;
				p = "%" + Double.toString(d * 100);
				state[2] = p;
				currentState = state[0] + "/" + state[1] + "/" + state[2];

				System.out.println(currentState + ": WON!");
				boolean statE1 = false;
				for (int i = 0; i < stm.size(); i++) {
					String[] state1 = stm.get(i).split("/");
					if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
						statE1 = true;
						System.out.println("Setting " + stm.get(i) + " to: " + currentState);
						stm.set(i, currentState);
					}
				}
				if (!statE1) {
					stm.add(currentState);
				}
				winOrLose.setText(currentState + ": WON!");
				hitB.setEnabled(false);
				standB.setEnabled(false);
				playB.setEnabled(true);
				gameOver();

				break;
			}
			if (hit1Sum > 21) {
				AIPlaying = false;

				// LOST FROM HITTING

				state = currentState.split("/");
				Double h = 0.5;

				if (state.length > 2) {
					h = Double.parseDouble(state[2].substring(1)) / 100;
				}
				h *= punishment;
				String p = "%" + Double.toString(h * 100);

				state[2] = p;

				currentState = state[0] + "/" + state[1] + "/" + state[2];

				System.out.println(currentState + ": LOST!");
				for (int i = 0; i < stm.size(); i++) {
					String[] state1 = stm.get(i).split("/");
					if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
						System.out.println("Setting " + stm.get(i) + " to: " + currentState);
						stm.set(i, currentState);
					}
				}

				winOrLose.setText(currentState + ": LOST!");
				hitB.setEnabled(false);
				standB.setEnabled(false);
				playB.setEnabled(true);
				gameOver();

				break;
			}

			state = currentState.split("/");
			hitPercent = Double.parseDouble(state[2].substring(1))/100;
			state[0] = Integer.toString(currentSum) + "/";
			String s = state[0] + state[1] + "/" + state[2];
			currentState = s;
			boolean statEE = false;
			for (int i = 0; i < stm.size(); i++) {
				String[] state1 = stm.get(i).split("/");
				if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
					System.out.println("Setting " + stm.get(i) + " to: " + currentState);
					statEE = true;
					stm.set(i, currentState);
				}
			}
			if (!statEE) {
				stm.add(currentState);
			}

			break;
		case 4:
			card4 = ran.nextInt(51);
			while (card4 == card3) { // Make Sure Card isnt duplicate
				card4 = ran.nextInt(51);
			}
			c4 = deck.cards[card4];
			hit2Sum = Integer.parseInt(sumLab.getText()) + c4.getValue();
			sumLab.setText(Integer.toString(hit2Sum));
			currentSum = hit2Sum;

			if (!c4.getName().equals(Integer.toString(c4.getValue()))) {
				oppCard4.setText(
						"<html><h1>" + c4.getName() + "</h1><br/>" + Integer.toString(c4.getValue()) + "</html>");

			} else {
				oppCard4.setText("<html><h1>" + c4.getName() + "</h1></html>");
			}
			// IF NEW SUM > 21 DISABLE BUTTON AND DO END FUNCTION
			if (!sumBelowTwentyOne()) {
				hitB.setEnabled(false);
				standB.setEnabled(false);
			}
			if (hit2Sum == 21) {
				AIPlaying = false;

				// WON FROM HITTING

				state = currentState.split("/");
				String p = state[2];
				Double d = Double.parseDouble(p.substring(1)) / 100;

				d *= reward;
				p = "%" + Double.toString(d * 100);
				state[2] = p;
				currentState = state[0] + "/" + state[1] + "/" + state[2];
				boolean stat123E = false;

				System.out.println(currentState + ": WON!");
				for (int i = 0; i < stm.size(); i++) {
					String[] state1 = stm.get(i).split("/");
					if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
						System.out.println("Setting " + stm.get(i) + " to: " + currentState);
						stat123E = true;
						stm.set(i, currentState);
					}
				}
				if (!stat123E) {
					stm.add(currentState);
				}
				winOrLose.setText(currentState + ": WON!");
				hitB.setEnabled(false);
				standB.setEnabled(false);
				playB.setEnabled(true);
				gameOver();

				break;
			}
			if (hit2Sum > 21) {
				AIPlaying = false;

				// LOST FROM HITTING

				state = currentState.split("/");
				Double h = 0.5;

				if (state.length > 2) {
					h = Double.parseDouble(state[2].substring(1)) / 100;
				}
				h *= punishment;
				String p = "%" + Double.toString(h * 100);

				state[2] = p;

				currentState = state[0] + "/" + state[1] + "/" + state[2];

				System.out.println(currentState + ": LOST!");

				for (int i = 0; i < stm.size(); i++) {
					String[] state1 = stm.get(i).split("/");
					if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
						System.out.println("Setting " + stm.get(i) + " to: " + currentState);
						stm.set(i, currentState);
					}
				}

				winOrLose.setText(currentState + ": LOST!");
				hitB.setEnabled(false);
				standB.setEnabled(false);
				playB.setEnabled(true);
				gameOver();

				break;
			}

			state = currentState.split("/");
			hitPercent = Double.parseDouble(state[2].substring(1))/100;

			state[0] = Integer.toString(currentSum) + "/";
			String ss = state[0] + state[1] + "/" + state[2];
			currentState = ss;
			boolean stat1E = false;
			for (int i = 0; i < stm.size(); i++) {
				String[] state1 = stm.get(i).split("/");
				if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
					System.out.println("Setting " + stm.get(i) + " to: " + currentState);
					stat1E = true;
					stm.set(i, currentState);
				}
			}
			if (!stat1E) {
				stm.add(currentState);
			}

			break;
		case 5:
			card5 = ran.nextInt(51);
			while (card5 == card4) { // Make Sure Card isnt duplicate
				card5 = ran.nextInt(51);
			}
			c5 = deck.cards[card5];
			hit3Sum = Integer.parseInt(sumLab.getText()) + c5.getValue();
			sumLab.setText(Integer.toString(hit3Sum));
			currentSum = hit3Sum;

			if (!c5.getName().equals(Integer.toString(c5.getValue()))) {
				oppCard5.setText(
						"<html><h1>" + c5.getName() + "</h1><br/>" + Integer.toString(c5.getValue()) + "</html>");

			} else {
				oppCard5.setText("<html><h1>" + c5.getName() + "</h1></html>");
			}
			// IF NEW SUM > 21 DISABLE BUTTON AND DO END FUNCTION
			if (!sumBelowTwentyOne()) {
				hitB.setEnabled(false);
				standB.setEnabled(false);
			}
			if (hit3Sum == 21) {
				AIPlaying = false;

				// WON FROM HITTING

				state = currentState.split("/");
				String p = state[2];
				Double d = Double.parseDouble(p.substring(1)) / 100;

				d *= reward;
				p = "%" + Double.toString(d * 100);
				state[2] = p;
				currentState = state[0] + "/" + state[1] + "/" + state[2];

				boolean statttE = false;

				for (int i = 0; i < stm.size(); i++) {
					String[] state1 = stm.get(i).split("/");
					if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
						System.out.println("Setting " + stm.get(i) + " to: " + currentState);
						statttE = true;
						stm.set(i, currentState);
					}
				}
				if (!statttE) {
					stm.add(currentState);
				}

				winOrLose.setText(currentState + ": WON!");
				hitB.setEnabled(false);
				standB.setEnabled(false);
				playB.setEnabled(true);
				gameOver();

				break;

			}
			if (hit3Sum > 21) {
				AIPlaying = false;

				// LOST FROM HITTING

				state = currentState.split("/");
				Double h = 0.5;

				if (state.length > 2) {
					h = Double.parseDouble(state[2].substring(1)) / 100;
				}
				h *= punishment;
				String p = "%" + Double.toString(h * 100);

				state[2] = p;

				currentState = state[0] + "/" + state[1] + "/" + state[2];
				System.out.println(currentState + ": LOST!");
				for (int i = 0; i < stm.size(); i++) {
					String[] state1 = stm.get(i).split("/");
					if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
						System.out.println("Setting " + stm.get(i) + " to: " + currentState);
						stm.set(i, currentState);
					}
				}

				winOrLose.setText(currentState + ": LOST!");
				hitB.setEnabled(false);
				standB.setEnabled(false);
				playB.setEnabled(true);
				gameOver();

				break;
			}
			break;
		}

	}

	public Double getHitPercent() {
		String[] tokens = currentState.split("/");
		String h = tokens[2].substring(1);
		Double hitP = Double.parseDouble(h) / 100;

		return hitP;
	}

	public void standFunc() {

		hitB.setEnabled(false);
		standB.setEnabled(false);

		int playerSum = Integer.parseInt(sumLab.getText());
		dealerSum = dC1.getValue();

		while (dealerSum < 17 | (dealerSum <= playerSum && dealerSum < 21)) {
			dealerCardCount++;

			switch (dealerCardCount) {
			case 2:
				dealCard2 = ran.nextInt(51);
				while (dealCard2 == dealCard1) { // Make Sure Card isnt duplicate
					dealCard2 = ran.nextInt(51);
				}
				dC2 = deck.cards[dealCard2];
				dealerSum = dealerSum + dC2.getValue();
				if (!dC2.getName().equals(Integer.toString(dC2.getValue()))) {
					sysCard2.setText(
							"<html><h1>" + dC2.getName() + "</h1><br/>" + Integer.toString(dC2.getValue()) + "</html>");

				} else {
					sysCard2.setText("<html><h1>" + dC2.getName() + "</h1></html>");
				}

				break;
			case 3:
				dealCard3 = ran.nextInt(51);
				while (dealCard3 == dealCard2) { // Make Sure Card isnt duplicate
					dealCard3 = ran.nextInt(51);
				}
				dC3 = deck.cards[dealCard3];
				dealerSum = dealerSum + dC3.getValue();
				if (!dC3.getName().equals(Integer.toString(dC3.getValue()))) {
					sysCard3.setText(
							"<html><h1>" + dC3.getName() + "</h1><br/>" + Integer.toString(dC3.getValue()) + "</html>");

				} else {
					sysCard3.setText("<html><h1>" + dC3.getName() + "</h1></html>");
				}

				break;
			case 4:
				dealCard4 = ran.nextInt(51);
				while (dealCard4 == dealCard3) { // Make Sure Card isnt duplicate
					dealCard4 = ran.nextInt(51);
				}
				dC4 = deck.cards[dealCard4];
				dealerSum = dealerSum + dC4.getValue();
				if (!dC4.getName().equals(Integer.toString(dC4.getValue()))) {
					sysCard4.setText(
							"<html><h1>" + dC4.getName() + "</h1><br/>" + Integer.toString(dC4.getValue()) + "</html>");

				} else {
					sysCard4.setText("<html><h1>" + dC4.getName() + "</h1></html>");
				}

				break;
			case 5:
				dealCard5 = ran.nextInt(51);
				while (dealCard5 == dealCard4) { // Make Sure Card isnt duplicate
					dealCard5 = ran.nextInt(51);
				}
				dC5 = deck.cards[dealCard5];
				dealerSum = dealerSum + dC5.getValue();
				if (!dC5.getName().equals(Integer.toString(dC5.getValue()))) {
					sysCard5.setText(
							"<html><h1>" + dC5.getName() + "</h1><br/>" + Integer.toString(dC5.getValue()) + "</html>");

				} else {
					sysCard5.setText("<html><h1>" + dC5.getName() + "</h1></html>");
				}

				break;
			}
		}
		checkIfWon();

	}

	public Boolean sumBelowTwentyOne() {
		if (Integer.parseInt(sumLab.getText()) <= 21) {
			return true;
		} else {
			return false;
		}

	}

	public void checkIfWon() {
		// If your sum > dealer sum and less than or equal to 21
		// If your sum == 21
		//
		String state[] = currentState.split("/");
		if (Integer.parseInt(sumLab.getText()) == 21
				|| (dealerSum < Integer.parseInt(sumLab.getText()) && Integer.parseInt(sumLab.getText()) < 21)
				|| dealerSum > 21) {
			AIPlaying = false;

			// STAND WON
			winOrLose.setText(currentState + ": WON!");
			state = currentState.split("/");
			String p = state[2];
			Double d = Double.parseDouble(p.substring(1)) / 100;

			d *= standReward;
			p = "%" + Double.toString(d * 100);
			state[2] = p;
			currentState = state[0] + "/" + state[1] + "/" + state[2];

			System.out.println(currentState + ": WON!");
			boolean statE1 = false;
			for (int i = 0; i < stm.size(); i++) {
				String[] state1 = stm.get(i).split("/");
				if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
					statE1 = true;
					System.out.println("Setting " + stm.get(i) + " to: " + currentState);
					stm.set(i, currentState);
				}
			}
			if (!statE1) {
				stm.add(currentState);
			}
			gameOver();


		} else {
			AIPlaying = false;

			// STAND LOST
			winOrLose.setText(currentState + ": LOST!");
			state = currentState.split("/");
			String p = state[2];
			Double d = Double.parseDouble(p.substring(1)) / 100;

			d *= standPunishment;
			p = "%" + Double.toString(d * 100);
			state[2] = p;
			currentState = state[0] + "/" + state[1] + "/" + state[2];

			System.out.println(currentState + ": LOST!");
			boolean statE1 = false;
			for (int i = 0; i < stm.size(); i++) {
				String[] state1 = stm.get(i).split("/");
				if ((state[0] + "/" + state[1]).equals(state1[0] + "/" + state1[1])) {
					statE1 = true;
					System.out.println("Setting " + stm.get(i) + " to: " + currentState);
					stm.set(i, currentState);
				}
			}
			if (!statE1) {
				stm.add(currentState);
			}
			gameOver();
		}

		playAI100B.setEnabled(true);
		playAIB.setEnabled(true);
		playB.setEnabled(true);

	}
	public void gameOver() {
		playAI100B.setEnabled(true);
		playAIB.setEnabled(true);
		playB.setEnabled(true);
		hitB.setEnabled(false);
		standB.setEnabled(false);
	}

	public void saveFile() {
		final JFileChooser SaveAs = new JFileChooser();
		SaveAs.setApproveButtonText("Save");
		int actionDialog = SaveAs.showSaveDialog(this);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File fileName = new File(SaveAs.getSelectedFile() + ".txt");
		BufferedWriter outFile = null;
		try {
			outFile = new BufferedWriter(new FileWriter(fileName, false));
			Iterator<String> iterator = stm.iterator();
			
			while (iterator.hasNext()) {
				outFile.write(iterator.next() + "\n");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (outFile != null) {
				try {
					outFile.close();
				} catch (IOException e) {

				}
			}
		}

	}

	public void readFile() throws FileNotFoundException {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT Files only", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(getParent());
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	filename = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            
            try {
				reader = new BufferedReader(new FileReader(chooser.getSelectedFile()));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					stm.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
        }
	}
}
