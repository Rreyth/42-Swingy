package swingy.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

import swingy.model.Artifact;
import swingy.model.entity.Player;
import swingy.model.map.GameMap;

public class GuiView
{
	JFrame	frame = null;
	// JLabel	label = null;
	JButton	quitButton = null;
	JButton	switchButton = null;
	JButton	northButton = null;
	JButton	southButton = null;
	JButton	eastButton = null;
	JButton	westButton = null;

	String	input = "";

	// TODO: add state ? (heroSelect/game)


	public GuiView() {}

	private void createButtons()
	{
		int	frameW = this.frame.getWidth();
		int frameH = this.frame.getHeight();

		this.quitButton = new JButton("QUIT");
		this.switchButton = new JButton("SWITCH");
		this.northButton = new JButton("North ⮝");
		this.southButton = new JButton("South ⮟");
		this.eastButton = new JButton("East ⮞");
		this.westButton = new JButton("West ⮜");
		// ⮝ ⮜ ⮞ ⮟ ⚔ ⏎ ⏏ ⭳ ⮿ ⏼ ☐ ⇵ ⛗ ⚙

		this.quitButton.addActionListener(e -> {
			this.input = "quit";
			this.frame.dispose();
		});

		this.switchButton.addActionListener(e -> {
			this.input = "switch";
		});

		this.quitButton.setSize(100, 50);
		this.quitButton.setLocation(100, 100);

		this.switchButton.setSize(100, 50);
		this.switchButton.setLocation(250, 100);

		this.northButton.setSize(100, 35);
		this.northButton.setLocation((int)(frameW * 0.35), (int)(frameH * 0.8));

		this.southButton.setSize(100, 35);
		this.southButton.setLocation((int)(frameW * 0.35), (int)(frameH * 0.9));

		this.eastButton.setSize(100, 35);
		this.eastButton.setLocation((int)(frameW * 0.425), (int)(frameH * 0.85));

		this.westButton.setSize(100, 35);
		this.westButton.setLocation((int)(frameW * 0.275), (int)(frameH * 0.85));

		this.frame.add(this.quitButton);
		this.frame.add(this.switchButton);
		this.frame.add(this.northButton);
		this.frame.add(this.southButton);
		this.frame.add(this.eastButton);
		this.frame.add(this.westButton);
	}

	public void createWindow() //TODO
	{
		FlatDarkPurpleIJTheme.setup();
		this.frame = new JFrame("Swingy");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: DONT DO ANYTHING -> add event listener for clean quit
		this.frame.setSize(1600, 900);
		this.frame.setLocationRelativeTo(null);
		this.frame.setLayout(null);

		this.createButtons();

		this.frame.setVisible(true);
	}

	public void	killWindow()
	{
		this.frame.dispose();
		this.frame = null;
	}

	public void	displayMap(GameMap map) //TODO
	{
	}

	public void	displayText(Object text) //TODO
	{
	}

	public void	displayStats(Player player) //TODO
	{
	}

	public void	displayLoot(Artifact loot, Player player) //TODO
	{
	}

	public String	getInput()
	{
		String	ret;

		ret = this.input;
		this.input = "";
		return (ret);
	}

	public String[]	heroSelect(List<Player> savedHeroes) //TODO
	{
		return (new String[] {""});
	}
}
