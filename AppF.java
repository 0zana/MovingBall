package kolos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppF extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public JButton pauseButton = new JButton("Pause");
	public JButton startButton = new JButton("Start");
	public AminPan animArea;
	public JPanel panel;

	
	public AppF() {
		setTitle("Animation");
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initGUI();
	}

	public void initGUI() {
		this.setLayout(new BorderLayout());

		animArea = new AminPan();

		this.add(animArea, BorderLayout.CENTER);

		panel = new JPanel();
		this.add(panel, BorderLayout.SOUTH);
		
		panel.add(pauseButton);
		pauseButton.addActionListener(this);
		pauseButton.setActionCommand("stop");

		panel.add(startButton);
		startButton.addActionListener(this);
		startButton.setActionCommand("restart");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("stop".equals(e.getActionCommand())) {
			animArea.stop();
		}
		if ("restart".equals(e.getActionCommand())) {
			animArea.restart();
		}
		
	}
}