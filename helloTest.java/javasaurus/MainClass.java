package javasaurus;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.util.*;


public class MainClass extends JFrame{
	
	Toolkit testTools;
	Dimension testDim;
	TestArea tArea; 
	JPanel tPanel = new JPanel();
	JPanel tPanel2 = new JPanel();
	List<TestButton> buttonList;
	List<helloTest.WordManager> converted = new ArrayList();

	
	class CmndButton extends JButton{
		
		ListenForCmndButton l4b;
		
		class ListenForCmndButton implements ActionListener{
			CmndButton cButton;
			public ListenForCmndButton(CmndButton b){
				this.cButton = b;
			}
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == cButton)
					CmndButton.this.generateButton();
			}
		}
		
		public CmndButton(String text){
			super("complete action");
			l4b = new ListenForCmndButton(this);
			this.addActionListener(l4b);
			
		}
		
		void generateButton(){
			if ( !buttonList.isEmpty()){
				for (TestButton tb: buttonList)
					tPanel.remove(tb);
				System.gc();
				}
			converted = helloTest.SentenceManager.getWMs(tArea.getText(),"hypo");
			helloTest.WordManager.fixAs(converted);
					
			for( helloTest.WordManager wm: converted )
				MainClass.this.addAButton(wm);
			helloTest.WordManager.fixAs(converted);
			MainClass.this.setVisible(true);
			}
		}
		
	public MainClass(){
		this.setSize(800, 200);
		this.setLocationRelativeTo(null);
		Toolkit testTools = Toolkit.getDefaultToolkit();
		Dimension testDim = testTools.getScreenSize();
		this.setTitle("Test Window");
		this.setLayout(new GridLayout());
		this.add(tPanel2);
		this.add(tPanel);
		tArea = new TestArea("type here");
		tPanel2.add(new CmndButton("command"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JScrollPane scroller = new JScrollPane(tArea);
		tPanel2.add(scroller);
		buttonList = new ArrayList<TestButton>();

	}
	public void addAButton(helloTest.WordManager wm){
		TestButton tButton = new TestButton(wm, tArea);
		buttonList.add(tButton);
		tPanel.add(tButton);
	}
	public static void main(String[] args){
		MainClass test = new MainClass();
		test.setVisible(true);
	}
	


class TestButton extends JButton{
	
	helloTest.WordManager wm;
	ListenForButton l4b = new ListenForButton(this);
	TestArea source;
	
	class ListenForButton implements ActionListener{
		TestButton tButton;
		public ListenForButton(TestButton b){
			this.tButton = b;
		}
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == tButton)
				tButton.change();
		}
	}
	
	public TestButton(helloTest.WordManager wm, TestArea tf){
		this.wm = wm;
		setText(wm.retrieve());
		this.source = tf;
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.addActionListener(l4b);
	
		
	}
	public void change(){
		this.setText(wm.retrieve());
		//if ( wm.firstLetterHappend() && buttonList.indexOf(this) > 0 )
		//	buttonList.get(buttonList.indexOf(wm)-1).setText("!!!");;
	}
}


class TestArea extends JTextArea{
	
	
	public TestArea(String dflt){
		super(dflt,10,10);
		Dimension d = new Dimension(100,1000);
		setLineWrap(true);
		setWrapStyleWord(true);
	}
		
	}
	
}


