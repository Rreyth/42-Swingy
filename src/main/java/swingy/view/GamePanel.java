package swingy.view;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

class GamePanel extends JPanel
{
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setStroke(new BasicStroke(5));

		g2d.drawLine(1300, 0, 1300, 900);
		g2d.drawLine(300, 0, 300, 900);
		g2d.drawLine(300, 700, 1300, 700);
	}
}
