package swingy.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

import swingy.model.Artifact;
import swingy.model.entity.Player;
import swingy.model.map.GameMap;

public class GuiView
{
	JFrame	frame = null;

	GamePanel	gamePanel;
	JPanel		startPanel;

	JTextArea	errorArea;

	Boolean		isInit = false;
	Boolean		isCreatePanel = false;

	int			selectedLoad = -1;
	String		selectedClass = "";

	// JLabel	label = null;

	String		input = "";
	String[]	startInputs = {"", "", ""};

	// TODO: add state ? (heroSelect/game)


	public GuiView() {}

	private JPanel initMiddleCreate()
	{
		int			frameW = this.frame.getWidth();
		int			frameH = this.frame.getHeight();
		JPanel		panel = new JPanel();
		JLabel		nameLabel = new JLabel("Name:");
		JLabel		classLabel = new JLabel("Class");
		JTextField	nameField = new JTextField();
		JButton		warButton = new JButton("Warrior");
		JButton		mageButton = new JButton("Mage");
		JButton		monkButton = new JButton("Monk");


		this.errorArea = new JTextArea();
		this.errorArea.setEditable(false);
		this.errorArea.setFocusable(false);
		this.errorArea.setLineWrap(true);
		this.errorArea.setWrapStyleWord(true);
		this.errorArea.setForeground(Color.RED);
		Font currentFont = this.errorArea.getFont();
		this.errorArea.setFont(new Font(currentFont.getName(), Font.BOLD, 16));

		JScrollPane scrollPane = new JScrollPane(this.errorArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds((int)(frameW * 0.33), (int)(frameH * 0.65), (int)(frameW * 0.34), 100);

		panel.setBounds(0, 0, frameW, frameH);
		panel.setLayout(null);

		currentFont = nameLabel.getFont();
		nameLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 25));
		nameLabel.setBounds((int)(frameW * 0.35), (int)(frameH * 0.35), 100, 50);

		currentFont = classLabel.getFont();
		classLabel.setFont(new Font(currentFont.getName(), Font.BOLD, 25));
		classLabel.setBounds((int)(frameW * 0.48) - 5, (int)(frameH * 0.45), 100, 50);

		nameField.setBounds((int)(frameW * 0.5) - 100, (int)(frameH * 0.35), 200, 50);

		warButton.setBounds((int)(frameW * 0.35) - 75, (int)(frameH * 0.525), 150, 75);
		currentFont = warButton.getFont();
		warButton.setFont(new Font(currentFont.getName(), Font.BOLD, 25));

		mageButton.setBounds((int)(frameW * 0.5) - 75, (int)(frameH * 0.525), 150, 75);
		currentFont = mageButton.getFont();
		mageButton.setFont(new Font(currentFont.getName(), Font.BOLD, 25));

		monkButton.setBounds((int)(frameW * 0.65) - 75, (int)(frameH * 0.525), 150, 75);
		currentFont = monkButton.getFont();
		monkButton.setFont(new Font(currentFont.getName(), Font.BOLD, 25));

		warButton.addActionListener(e -> {
			this.selectedClass = "Warrior";
		});

		mageButton.addActionListener(e -> {
			this.selectedClass = "Mage";
		});

		monkButton.addActionListener(e -> {
			this.selectedClass = "Monk";
		});

		panel.add(nameLabel);
		panel.add(classLabel);
		panel.add(nameField);
		panel.add(warButton);
		panel.add(mageButton);
		panel.add(monkButton);
		panel.add(scrollPane);

		return (panel);
	}

	private String formatInfos(Player hero)
	{
		String infos = "<html>" +
				"<table>" +
				"<tr><td>" + hero.getName() + "</td></tr>" +
				"<tr><td>Class:</td><td>" + hero.getHeroClass() + "</td></tr>" +
				"<tr><td>Level:</td><td>" + hero.getLevel() + "</td></tr>" +
				"<tr><td>Attack:</td><td>" + hero.getFullAttack() + "</td></tr>" +
				"<tr><td>Defense:</td><td>" + hero.getFullDefense() + "</td></tr>" +
				"<tr><td>Hitpoints:</td><td>" + hero.getFullHitPoints() + "</td></tr>" +
				"</table>" +
				"</html>";

		return (infos);
	}

	private JScrollPane initMiddleLoad(List<Player> savedHeroes)
	{
		int	frameW = this.frame.getWidth();
		int frameH = this.frame.getHeight();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));

		int i = 0;
		for (Player hero : savedHeroes)
		{
			int index = i;
			JButton heroButton = new JButton(this.formatInfos(hero));
			Font currentFont = heroButton.getFont();
			heroButton.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 25));
			heroButton.addActionListener(e -> {
				this.selectedLoad = index;
			});
			panel.add(heroButton);
			i++;
		}
		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds((int)(frameW * 0.17), (int)(frameH * 0.3), (int)(frameW * 0.66), 300);
		return (scrollPane);
	}

	private void initStartScreen(List<Player> savedHeroes)
	{
		int	frameW = this.frame.getWidth();
		int frameH = this.frame.getHeight();

		JButton switchButton = new JButton("Switch ⛗");
		JButton quitButton = new JButton("Quit ⏼");
		JButton createButton = new JButton("Create");
		JButton loadButton = new JButton("Load");

		JLabel topText = new JLabel("Load or create a Hero?");

		JPanel middleCreatePanel = this.initMiddleCreate();
		JScrollPane middleLoadPanel = this.initMiddleLoad(savedHeroes);

		this.startPanel = new JPanel();
		this.startPanel.setBounds(0, 0, frameW, frameH);
		this.startPanel.setLayout(null);

		Font currentFont = topText.getFont();
		topText.setFont(new Font(currentFont.getName(), Font.BOLD, 30));
		topText.setBounds((int)(frameW / 2) - 200, 0, 400, 100);

		switchButton.setSize(110, 40);
		switchButton.setLocation((int)(frameW * 0.9), (int)(frameH * 0.82));

		quitButton.setSize(110, 40);
		quitButton.setLocation((int)(frameW * 0.9), (int)(frameH * 0.885));

		createButton.setSize(150, 75);
		createButton.setLocation((int)(frameW * 0.35) - 75, (int)(frameH * 0.8));
		currentFont = createButton.getFont();
		createButton.setFont(new Font(currentFont.getName(), Font.BOLD, 25));

		loadButton.setSize(150, 75);
		loadButton.setLocation((int)(frameW * 0.65) - 75, (int)(frameH * 0.8));
		currentFont = loadButton.getFont();
		loadButton.setFont(new Font(currentFont.getName(), Font.BOLD, 25));

		createButton.addActionListener(e -> {
			topText.setText("Creation");
			topText.setBounds((int)(frameW / 2) - 75, 0, 150, 100);

			this.startPanel.remove(middleLoadPanel);
			this.startPanel.add(middleCreatePanel);

			this.frame.repaint();

			this.selectedLoad = -1;

			if (this.isCreatePanel)
			{
				this.startInputs[0] = "fullCreate";
				for (Component comp : middleCreatePanel.getComponents())
				{
					if (comp instanceof JTextField)
					{
						JTextField textField = (JTextField) comp;
						this.startInputs[1] = textField.getText();
						break;
					}
				}
				this.startInputs[2] = this.selectedClass;
				if (this.startInputs[1].isBlank() || this.startInputs[2].isBlank())
					this.startInputs = new String[] {"", "", ""};
			}
			this.isCreatePanel = true;
		});

		loadButton.addActionListener(e -> {
			topText.setText("Loadable Heroes");
			topText.setBounds((int)(frameW / 2) - 150, 0, 300, 100);

			this.isCreatePanel = false;
			this.startPanel.remove(middleCreatePanel);
			this.startPanel.add(middleLoadPanel);

			this.frame.repaint();

			this.selectedClass = "";

			if (this.selectedLoad != -1)
			{
				this.startInputs[0] = "load";
				this.startInputs[1] = savedHeroes.get(this.selectedLoad).getName();
				this.frame.remove(this.startPanel);
				this.frame.add(this.gamePanel);
				this.frame.repaint();
			}
		});

		switchButton.addActionListener(e -> {
			this.startInputs[0] = "switch";
		});

		quitButton.addActionListener(e -> {
			this.startInputs[0] = "quit";
			this.frame.dispose();
		});

		this.startPanel.add(switchButton);
		this.startPanel.add(quitButton);
		this.startPanel.add(createButton);
		this.startPanel.add(loadButton);

		this.startPanel.add(topText);
	}

	private void initGameScreen()
	{
		int	frameW = this.frame.getWidth();
		int frameH = this.frame.getHeight();

		this.gamePanel = new GamePanel();
		this.gamePanel.setBounds(0, 0, frameW, frameH);
		this.gamePanel.setLayout(null);

		JButton saveButton = new JButton("Save ⚙");
		JButton switchButton = new JButton("Switch ⛗");
		JButton quitButton = new JButton("Quit ⏼");
		JButton northButton = new JButton("North ⮝");
		JButton southButton = new JButton("South ⮟");
		JButton eastButton = new JButton("East ⮞");
		JButton westButton = new JButton("West ⮜");
		JButton fightButton = new JButton("Fight ⚔");
		JButton runButton = new JButton("Run ⏎");
		// ⮝ ⮜ ⮞ ⮟ ⚔ ⏎ ⏏ ⭳ ⮿ ⏼ ☐ ⇵ ⛗ ⚙
		//TODO: add keep and leave buttons

		saveButton.setSize(110, 40);
		saveButton.setLocation((int)(frameW * 0.875), (int)(frameH * 0.755));

		switchButton.setSize(110, 40);
		switchButton.setLocation((int)(frameW * 0.875), (int)(frameH * 0.82));

		quitButton.setSize(110, 40);
		quitButton.setLocation((int)(frameW * 0.875), (int)(frameH * 0.885));

		northButton.setSize(100, 35);
		northButton.setLocation((int)(frameW * 0.35), (int)(frameH * 0.8));

		southButton.setSize(100, 35);
		southButton.setLocation((int)(frameW * 0.35), (int)(frameH * 0.9));

		eastButton.setSize(100, 35);
		eastButton.setLocation((int)(frameW * 0.425), (int)(frameH * 0.85));

		westButton.setSize(100, 35);
		westButton.setLocation((int)(frameW * 0.275), (int)(frameH * 0.85));

		fightButton.setSize(100, 35);
		fightButton.setLocation((int)(frameW * 0.55), (int)(frameH * 0.85));
		fightButton.setEnabled(false);

		runButton.setSize(100, 35);
		runButton.setLocation((int)(frameW * 0.625), (int)(frameH * 0.85));
		runButton.setEnabled(false);

		saveButton.addActionListener(e -> {
			this.input = "save";
		});

		switchButton.addActionListener(e -> {
			this.input = "switch";
		});

		quitButton.addActionListener(e -> {
			this.input = "quit";
			this.frame.dispose();
		});

		northButton.addActionListener(e -> {
			this.input = "north";
		});

		southButton.addActionListener(e -> {
			this.input = "south";
		});

		eastButton.addActionListener(e -> {
			this.input = "east";
		});

		westButton.addActionListener(e -> {
			this.input = "west";
		});

		fightButton.addActionListener(e -> {
			this.input = "fight";
		});

		runButton.addActionListener(e -> {
			this.input = "run";
		});

		this.gamePanel.add(saveButton);
		this.gamePanel.add(switchButton);
		this.gamePanel.add(quitButton);
		this.gamePanel.add(northButton);
		this.gamePanel.add(southButton);
		this.gamePanel.add(eastButton);
		this.gamePanel.add(westButton);
		this.gamePanel.add(fightButton);
		this.gamePanel.add(runButton);
	}

	public void createWindow() //TODO
	{
		this.isInit = false;
		FlatDarkPurpleIJTheme.setup();
		this.frame = new JFrame("Swingy");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: DONT DO ANYTHING -> add event listener for clean quit
		this.frame.setSize(1600, 900);
		this.frame.setLocationRelativeTo(null);
		this.frame.setLayout(null);

		this.initGameScreen();

		this.frame.add(this.gamePanel);

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

	public void displayError(String text)
	{
		this.errorArea.append(text + "\n");
		this.errorArea.setCaretPosition(this.errorArea.getDocument().getLength());
		this.frame.repaint();
	}

	public void	displayStats(Player player) //TODO
	{
	}

	public void	displayLoot(Artifact loot, Player player) //TODO
	{
	}

	public void displayGame()
	{
		this.frame.remove(this.startPanel);
		this.frame.add(this.gamePanel);
		this.frame.repaint();
	}

	public String	getInput()
	{
		String	ret;

		ret = this.input;
		this.input = "";
		try
		{
			Thread.sleep(10);
		}
		catch (InterruptedException e)
		{}

		return (ret);
	}

	public String[]	heroSelect(List<Player> savedHeroes) //TODO
	{
		if (!this.isInit)
		{
			this.initStartScreen(savedHeroes);
			this.frame.remove(this.gamePanel);
			this.frame.add(this.startPanel);
			this.frame.repaint();
			this.isInit = true;
		}

		String[]	ret = this.startInputs.clone();

		this.startInputs = new String[] {"", "", ""};
		try
		{
			Thread.sleep(10);
		}
		catch (InterruptedException e)
		{}

		return (ret);
	}
}
