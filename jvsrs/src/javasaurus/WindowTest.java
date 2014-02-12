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


public class WindowTest extends JFrame{
	
	Toolkit testTools;
	Dimension testDim;
	TestArea tArea; 
	JPanel tPanel = new JPanel();
	JPanel tPanel2 = new JPanel();


	
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
			for( String s: tArea.getText().split(" "))
			WindowTest.this.addAButton(s, Thesaurus.getWord(s));
			WindowTest.this.setVisible(true);
			}
		
		public void change(){
			this.setText("comd changed");
		}
	}
		
	public WindowTest(){
		this.setSize(800, 200);
		this.setLocationRelativeTo(null);
		Toolkit testTools = Toolkit.getDefaultToolkit();
		Dimension testDim = testTools.getScreenSize();
		this.setResizable(false);
		this.setTitle("Test Window");
		this.setLayout(new GridLayout());
		this.add(tPanel2);
		this.add(tPanel);
		tArea = new TestArea("type here");
		tPanel2.add(new CmndButton("command"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JScrollPane scroller = new JScrollPane(tArea);
		tPanel2.add(scroller);
		

	}
	public void addAButton(String tag, String text){
		TestButton tButton = new TestButton(tag, text, tArea);
		tPanel.add(tButton);
	}
	public static void main(String[] args){
		WindowTest test = new WindowTest();
		test.setVisible(true);
	}
}


class TestButton extends JButton{
	
	String tag;
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
	
	public TestButton(String tag, String text, TestArea tf){
		super(text);
		this.tag = tag;
		this.source = tf;
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.addActionListener(l4b);
	
		
	}
	public void change(){
		this.setText(Thesaurus.getWord(tag));
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
	


