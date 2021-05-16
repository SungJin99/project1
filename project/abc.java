package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class abc extends JFrame {
	private JPanel contentPane;
	private JTextArea text1;
	private JTextArea text2;
	String com;
	private String Inputs = " ";
	private StringBuffer save;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					abc frame = new abc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public abc() {
		setTitle("project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		String Title = getTitle();
		this.setTitle(getTitle());
		JFileChooser myFileChooser = new JFileChooser();

		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.setText("");
				abc.this.setTitle("무제.txt");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int intRet = myFileChooser.showOpenDialog(abc.this);
				if (intRet == JFileChooser.APPROVE_OPTION) {

					try {
						String strLine;
						File file = myFileChooser.getSelectedFile();
						abc.this.setTitle(getTitle() + "-" + file.getName());
						BufferedReader Reader = new BufferedReader(new FileReader(file.getPath()));
						com = file.getPath();
						Reader.readLine();
						text1.setText(Reader.readLine());
						while ((strLine = Reader.readLine()) != null) {
							text1.append("\n" + strLine);
						}
						Reader.close();
					} catch (IOException ie) {
						System.out.println(ie + "==> 입출력오류 발생");
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int intRet = myFileChooser.showSaveDialog(abc.this);
				if (intRet == JFileChooser.APPROVE_OPTION) {
					try {
						File file = myFileChooser.getSelectedFile();
						abc.this.setTitle(getTitle() + "-" + file.getName());
						PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getPath())));
						writer.write(text1.getText());
						writer.close();
					} catch (IOException ie) {
						System.out.println(ie + "==> 입출력오류 발생");
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Copy");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.copy();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Paste");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.paste();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cut");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.cut();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenu mnNewMenu_2 = new JMenu("Compile");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Compile");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = "C:\\Java\\";
				File Folder = new File(path);
				if (!Folder.exists()) {
					try {
						Folder.mkdir();
					} catch (Exception e1) {
						e1.getStackTrace();
					}
				}
				Inputs = text1.getText();
				save = new StringBuffer(Inputs);
				String fileName = (Class() + ".java");
				FileUtil.save(save, fileName);

				Cmd cmd = new Cmd();
				try {
					String command = cmd.inputCommand("cd C:\\Java\\" + " && " + "javac " + fileName);
					String result = cmd.execCommand(command);
					text2.setText("컴파일성공");
				} catch (Exception e1) {
					e1.printStackTrace();
					text2.setText("컴파일 에러");
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Run");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = Class();
				Cmd cmd = new Cmd();
				try {
					String command = cmd.inputCommand("cd C:\\Java\\" + " && " + "java " + fileName);
					System.out.print(command);
					String result = cmd.execCommand(command);
					text2.setText(result);
				} catch (Exception e1) {
					e1.printStackTrace();
					text2.setText("실행 에러");

				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);

		JMenu mnNewMenu_3 = new JMenu("\uAC1C\uBC1C\uC790");
		menuBar.add(mnNewMenu_3);

		JMenuItem menuItem = new JMenuItem("\uC815\uBCF4");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new project();
			}
		});
		mnNewMenu_3.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setResizeWeight(0.9);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		text1 = new JTextArea();
		text1.setTabSize(4);
		scrollPane.setViewportView(text1);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		text2 = new JTextArea();
		scrollPane_1.setViewportView(text2);
	}

	public String Class() {
		String className = text1.getText();
		String[] strArray = className.split(" ");
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i].equals("class")) {
				className = strArray[i + 1];
				break;
			}
		}
		return className;

	}

	public JTextArea getText2() {
		return text2;
	}

	public class project extends JFrame {
		project() {
			setTitle("개발자 정보");
			JPanel NewWindowContainer = new JPanel();
			setContentPane(NewWindowContainer);

			JLabel NewLabel = new JLabel("183916 진성");
			JLabel NewLabel1 = new JLabel("153307 김동현");

			NewWindowContainer.add(NewLabel);
			NewWindowContainer.add(NewLabel1);

			setSize(300, 100);
			setResizable(false);
			setVisible(true);
		}
	}
}
