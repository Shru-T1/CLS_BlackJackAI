import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Controller extends View implements ActionListener {

	Model m = new Model();
	
	public Controller() {
		this.setVisible(false);
		m.playB.addActionListener(this);
		m.standB.addActionListener(this);
		m.hitB.addActionListener(this);
		m.openFile.addActionListener(this);
		m.saveFile.addActionListener(this);
		m.playAI100B.addActionListener(this);
		m.playAIB.addActionListener(this);
		m.printSTM.addActionListener(this);
		m.makeSTM.addActionListener(this);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object wb = e.getSource();
		if(wb == m.playB) {
			System.out.println("play");
			m.playFunc();
		}
		if(wb == m.hitB) {
			System.out.println("hit");
			m.hitFunc();
		}
		if(wb == m.standB) {
			System.out.println("stand");
			m.standFunc();
		}
		if(wb == m.openFile) {
			System.out.println("Open FIle");
			try {
				m.readFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(wb == m.saveFile) {
			System.out.println("Save File");
			try {
				m.saveFile();
			}catch (Exception e2){
				e2.printStackTrace();
				
			}
		}
		if(wb == m.playAIB) {
			System.out.println("PLAY AI");
			m.AIPlay();
		}
		if(wb == m.printSTM) {
			m.printSTM();
		}
		if(wb == m.makeSTM) {
			m.makeSTM();
		}
		if(wb == m.playAI100B) {
			m.AIPlay10();
		}
	}
	
	public static void main(String[] args) {
		new Controller();
	}

}
