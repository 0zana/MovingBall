package kolos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class AminPan extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private Thread animThr;
	private double x, y, i = 0, frameH = 700, frameW = 750, kwx = frameW / 2, kwy = frameH / 2;
	private int k;
	private final int refreshRate = 10;
	private boolean isPaused = false;
	private boolean notEneded = true;
	public boolean isStart = true;

	public AminPan() {
		setBackground(Color.WHITE);
		setDoubleBuffered(true);

		animThr = new Thread(this);
		animThr.start();
	}

	public void zmienKier() {
		k = (int) (Math.random() * (4) + 1);
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect((int) kwx, (int) kwy, 80, 80);
		g.setColor(Color.GREEN);
		g.fillOval((int) x, (int) y, 40, 40);
		g2.drawOval((int) x, (int) y, 40, 40);

	}

	public void movement() {
		if (!isStart) {
			if (k == 1) {
				if (i != frameW) {
					x += 3;
					if (x > frameW) {
						i = frameW;
						zmienKier();
					}
				} else {
					x -= 3;
					if (x <= 0) {
						i = 0;
						zmienKier();
					}
				}
			} else if (k == 2) {
				if (i != frameH) {
					y += 3;
					if (y > frameH) {
						i = frameH;
						zmienKier();
					}

				} else {
					y -= 3;
					if (y <= 0)
						i = 0;
					zmienKier();
				}

			} else if (k == 3) {
				if (i != frameH) {
					y += 3;
					x += 3;
					if (y > frameH || x > frameW) {
						i = frameH;
						zmienKier();
					}
				} else {
					y -= 3;
					x -= 3;
					if (y <= 0 || x <= 0) {
						i = 0;
						zmienKier();
					}
				}
			} else {
				if (i != frameH) {
					y += 3;
					x -= 3;
					if (y > frameH || x <= 0) {
						i = frameH;
						zmienKier();
					}
				} else {
					y -= 3;
					x += 3;
					if (y <= 0 || x > frameW) {
						i = 0;
						zmienKier();
					}
				}
			}

			if (x >= kwx && y >= kwy && x <= kwx + 40 && y <= kwy + 40) {
				isStart = true;
				animThr.interrupt();
			}
		} else {
			x = (Math.random() * (frameW) - 0);
			y = (Math.random() * (frameH) - 0);
			zmienKier();
			isStart = false;
		}
	}

	public void run() {
		while (notEneded) {
			if (!isPaused) {
				movement();
				repaint();
			}
			try {
				Thread.sleep(refreshRate);
			} catch (InterruptedException e) {
				notEneded=false;
			}
		}
	}
	public void stop() {
		isPaused = true;
	}
	public void restart() {
		isPaused = false;
	}
}