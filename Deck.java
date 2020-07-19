
public class Deck {

	Card[] cards = new Card[52];
	
	public Deck(){
		Card two = new Card("2", 2);
		cards[0] = two;
		cards[1] = two;
		cards[2] = two;
		cards[3] = two;
		Card three = new Card("3", 3);
		cards[4] = three;
		cards[5] = three;
		cards[6] = three;
		cards[7] = three;
		Card four = new Card("4", 4);
		cards[8] = four;
		cards[9] = four;
		cards[10] = four;
		cards[11] = four;
		Card five = new Card("5", 5);
		cards[12] = five;
		cards[13] = five;
		cards[14] = five;
		cards[15] = five;
		Card six = new Card("6", 6);
		cards[16] = six;
		cards[17] = six;
		cards[18] = six;
		cards[19] = six;
		Card seven = new Card("7", 7);
		cards[20] = seven;
		cards[21] = seven;
		cards[22] = seven;
		cards[23] = seven;
		Card eight = new Card("8", 8);
		cards[24] = eight;
		cards[25] = eight;
		cards[26] = eight;
		cards[27] = eight;
		Card nine = new Card("9", 9);
		cards[28] = nine;
		cards[29] = nine;
		cards[30] = nine;
		cards[31] = nine;
		Card ten = new Card("10", 10);
		cards[32] = ten;
		cards[33] = ten;
		cards[34] = ten;
		cards[35] = ten;
		Card jack = new Card("J", 10);
		cards[36] = jack;
		cards[37] = jack;
		cards[38] = jack;
		cards[39] = jack;
		Card queen = new Card("Q", 10);
		cards[40] = queen;
		cards[41] = queen;
		cards[42] = queen;
		cards[43] = queen;
		Card king = new Card("K", 10);
		cards[44] = king;
		cards[45] = king;
		cards[46] = king;
		cards[47] = king;
		Card ace = new Card("A", 11);
		cards[48] = ace;
		cards[49] = ace;
		cards[50] = ace;
		cards[51] = ace;
		
	}
}
