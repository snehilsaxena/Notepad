import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.io.*;
import java.awt.datatransfer.*;
import javax.swing.*;
//import javax.swing.event.*;

/*class FileOperation
{
	Notepad npd;

	boolean saved;
	boolean newFileFlag;
	String fileName;
	String applicationTitle="Javapad";

	File fileRef;
	JFileChooser chooser;
	/////////////////////////////
	boolean isSave(){return saved;}
	void setSave(boolean saved){this.saved=saved;}
	String getFileName(){return new String(fileName);}
	void setFileName(String fileName){this.fileName=new String(fileName);}
	/////////////////////////
	FileOperation(Notepad npd)
	{
		this.npd=npd;

		saved=true;
		newFileFlag=true;
		fileName=new String("Untitled");
		fileRef=new File(fileName);
		this.npd.f.setTitle(fileName+" - "+applicationTitle);

		chooser=new JFileChooser();
		chooser.addChoosableFileFilter(new MyFileFilter(".java","Java Source Files(*.java)"));
		chooser.addChoosableFileFilter(new MyFileFilter(".txt","Text Files(*.txt)"));
		chooser.setCurrentDirectory(new File("."));

	}
	//////////////////////////////////////

	boolean saveFile(File temp)
	{
		FileWriter fout=null;
		try
		{
			fout=new FileWriter(temp);
			fout.write(npd.ta.getText());
		}
		catch(IOException ioe){updateStatus(temp,false);return false;}
		finally
		{
			try{
				fout.close();
			}
			catch(IOException excp){}
		}
		updateStatus(temp,true);
		return true;
	}
	////////////////////////
	boolean saveThisFile()
	{

		if(!newFileFlag)
			{return saveFile(fileRef);}

		return saveAsFile();
	}
	////////////////////////////////////
	boolean saveAsFile()
	{
		File temp=null;
		chooser.setDialogTitle("Save As...");
		chooser.setApproveButtonText("Save Now");
		chooser.setApproveButtonMnemonic(KeyEvent.VK_S);
		chooser.setApproveButtonToolTipText("Click me to save!");

		do
		{
			if(chooser.showSaveDialog(this.npd.f)!=JFileChooser.APPROVE_OPTION)
				return false;
			temp=chooser.getSelectedFile();

			if(!temp.exists()) break;

			if(   JOptionPane.showConfirmDialog(
				this.npd.f,"<html>"+temp.getPath()+" already exists.<br>Do you want to replace it?<html>",
				"Save As",JOptionPane.YES_NO_OPTION
							)==JOptionPane.YES_OPTION)
				break;
		}while(true);


		return saveFile(temp);
	}

	////////////////////////
	boolean openFile(File temp)
	{
		FileInputStream fin=null;
		BufferedReader din=null;

		try
		{
			fin=new FileInputStream(temp);
			din=new BufferedReader(new InputStreamReader(fin));
			String str=" ";
			while(str!=null)
			{
				str=din.readLine();
				if(str==null)
				break;
				this.npd.ta.append(str+"\n");
			}

		}
		catch(IOException ioe){updateStatus(temp,false);return false;}

		finally
		{
			try{din.close();fin.close();}
			catch(IOException excp){}
		}
		updateStatus(temp,true);
		this.npd.ta.setCaretPosition(0);
		return true;
	}
	///////////////////////
	void openFile()
	{
		if(!confirmSave()) return;
		chooser.setDialogTitle("Open File...");
		chooser.setApproveButtonText("Open this");
		chooser.setApproveButtonMnemonic(KeyEvent.VK_O);
		chooser.setApproveButtonToolTipText("Click me to open the selected file.!");

		File temp=null;
		do
		{
			if(chooser.showOpenDialog(this.npd.f)!=JFileChooser.APPROVE_OPTION)
				return;
			temp=chooser.getSelectedFile();

			if(temp.exists())	break;

			JOptionPane.showMessageDialog(this.npd.f,
				"<html>"+temp.getName()+"<br>file not found.<br>"+
				"Please verify the correct file name was given.<html>",
				"Open",	JOptionPane.INFORMATION_MESSAGE);

		} while(true);

		this.npd.ta.setText("");

		if(!openFile(temp))
		{
			fileName="Untitled"; saved=true;
			this.npd.f.setTitle(fileName+" - "+applicationTitle);
		}
		if(!temp.canWrite())
			newFileFlag=true;

	}
	////////////////////////
	void updateStatus(File temp,boolean saved)
	{
		if(saved)
		{
			this.saved=true;
			fileName=new String(temp.getName());
			if(!temp.canWrite())
				{fileName+="(Read only)"; newFileFlag=true;}
			fileRef=temp;
			npd.f.setTitle(fileName + " - "+applicationTitle);
			npd.statusBar.setText("File : "+temp.getPath()+" saved/opened successfully.");
			newFileFlag=false;
		}
		else
		{
			npd.statusBar.setText("Failed to save/open : "+temp.getPath());
		}
	}
	///////////////////////
	boolean confirmSave()
	{
		String strMsg="<html>The text in the "+fileName+" file has been changed.<br>"+
			"Do you want to save the changes?<html>";
		if(!saved)
		{
			int x=JOptionPane.showConfirmDialog(this.npd.f,strMsg,applicationTitle,JOptionPane.YES_NO_CANCEL_OPTION);

			if(x==JOptionPane.CANCEL_OPTION)
				return false;
			if(x==JOptionPane.YES_OPTION && !saveAsFile())
				return false;
		}
		return true;
	}
	///////////////////////////////////////
	void newFile()
	{
		if(!confirmSave())
			return;

		this.npd.ta.setText("");
		fileName=new String("Untitled");
		fileRef=new File(fileName);
		saved=true;
		newFileFlag=true;
		this.npd.f.setTitle(fileName+" - "+applicationTitle);
	}
}*/







class NP implements KeyListener
{
	TextField tfFind,tfGoTo,tfReplace,tf2Replace;
	TextArea ta,tafont;
	Frame f,Help;
	Panel p,sb;
	Label statusBar;
	MenuBar mb;
	Dialog d,d3,find,findNext,replace,GoTo,save;
	MenuItem m11,m12,m13,m14,m15,m16,m17,m21,m22,m23,m24,m25,m26,m27,m28,m29,m210,m211,m31,m32,m41,m51,m52;
	Button bd,bd2,ok,cancel,findF,findC,Goto,GotoC,replaceF,replaceC,replaceA,replaceR,saveButton,cancelSave;
	Date date;
	BorderLayout bl = new BorderLayout();
	List Style,li,li1,li2;
	int c1=1,r1=1;
	Font thisFont;
	CheckboxMenuItem cb1,cb2;
	CheckboxGroup directionFind;
	Checkbox upFind,downFind,mtchFind,mtchReplace;
	Choice choice;
	JFileChooser chooser;

	int counter = 0,counter2 = 0;

	NP(String s)
	{
		init(s);
		createMenuBar();
		Listen();
		chooser = new JFileChooser();
	}


	//////////////////////////////////////////////

	void createMenuBar()
	{
		mb = new MenuBar();

				Menu m1 = new Menu("File");
				m11 = new MenuItem("New                       Ctrl+ N");
				m1.add(m11);
				m12 = new MenuItem("Open                     Ctrl+ O");
				m1.add(m12);
				m13 = new MenuItem("Save                      Ctrl+ S");
				m1.add(m13);
				save("Save");
				m14 = new MenuItem("Save As...");
				m1.add(m14);
				m1.addSeparator();
				m15 = new MenuItem("Page Setup...");
				m1.add(m15);
				m16 = new MenuItem("Print...                    Ctrl+ P ");
				m1.add(m16);
				m1.addSeparator();
				m17 = new MenuItem("Exit");
				m1.add(m17);
				mb.add(m1);

				Menu m2 = new Menu("Edit");
				m21 = new MenuItem("Undo                       Ctrl+ Z");
				m2.add(m21);
				m2.addSeparator();
				m22 = new MenuItem("Cut                          Ctrl+ X");
				m2.add(m22);
				m23 = new MenuItem("Copy                       Ctrl+ C");
				m2.add(m23);
				m24 = new MenuItem("Paste                      Ctrl+ V");
				m2.add(m24);
				m25 = new MenuItem("Delete                          Del");
				m2.add(m25);
				m2.addSeparator();

				m26 = new MenuItem("Find...                      Ctrl+ F");
				m2.add(m26);
				Find();

				m27 = new MenuItem("Find Next                       F3");
				m2.add(m27);

				m28 = new MenuItem("Replace...              Ctrl+ H");
				m2.add(m28);
				replace();

				m29 = new MenuItem("Go To...                  Ctrl+ G");
				m2.add(m29);
				goTo();

				m2.addSeparator();
				m210 = new MenuItem("Select All                Ctrl+ A");
				m2.add(m210);
				m211 = new MenuItem("Time/Date                    F5");
				date = new Date();
				m2.add(m211);
				mb.add(m2);

				Menu m3 = new Menu("Format");
				//m31 = new MenuItem("Word Wrap");
				cb1 = new CheckboxMenuItem("Word Wrap",false);
				//cb1.setState(false);
				m3.add(cb1);
				//m3.add(m31);

				cb1.addItemListener(new ItemListener() {
				         public void itemStateChanged(ItemEvent e) {
				            ta.setCaretPosition(0);
				         }
      			});

				//FONT
				m32 = new MenuItem("Font...");
				m3.add(m32);
				font();

				mb.add(m3);


				//STATUS BAR
				Menu m4 = new Menu("View");
				//m41 = new MenuItem("Status Bar");
				cb2 = new CheckboxMenuItem("Status Bar");
				cb2.setState(false);
				cb2.addItemListener(new ItemListener() {
								         public void itemStateChanged(ItemEvent e) {
								            //sb.add(statusBar);
								            System.out.println(666666666);
								         }
      			});
				m4.add(cb2);
				status();
				//m4.add(m41);
				mb.add(m4);



				//HELP
				Menu m5 = new Menu("Help");
				m51 = new MenuItem("View Help");
				m5.add(m51);
				help();

				m5.addSeparator();

				//ABOUT NOTEPAD
				m52 = new MenuItem("About Notepad");
				m5.add(m52);
				mb.add(m5);
				aboutNotepad();



				//INITIALIZE GUI
				f.setMenuBar(mb);

	}

	//////////

	void save(String s)
	{

	}


	//////////

	void replace()
	{
			replace = new Dialog(f,"Replace");
			replace.setSize(400,200);
			replace.setLayout(null);

			Label rFind = new Label("Replace What  :");
			rFind.setBounds(15,40,90,30);
			replace.add(rFind);

			Label r2Find = new Label("Replace With  :");
			r2Find.setBounds(15,75,90,30);
			replace.add(r2Find);

			tfReplace = new TextField();
			tfReplace.setBounds(105,45,200,25);
			replace.add(tfReplace);

			tf2Replace = new TextField();
			tf2Replace.setBounds(105,80,200,25);
			replace.add(tf2Replace);

			replaceF = new Button("Find Next");
			replaceF.setBounds(310,43,70,25);
			//replace.add(replaceF);

			replaceR = new Button("Replace");
			replaceR.setBounds(310,43,70,25);
			replace.add(replaceR);

			replaceA = new Button("Replace all");
			replaceA.setBounds(310,78,70,25);
			replace.add(replaceA);

			replaceC = new Button("Cancel");
			replaceC.setBounds(310,113,70,25);
			replace.add(replaceC);

			mtchReplace = new Checkbox("Match case");
			mtchReplace.setBounds(25,155,100,30);
			replace.add(mtchReplace);

			replace.setVisible(false);
			replace.setResizable(false);

			replace.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent){
					replace.dispose();
				}
	    	});

	}

	void replaceNext()
	{
		if(counter2==0)
			counter2 = ta.getCaretPosition();

		int z = ta.getText().length();

			while(counter2 != z)
			{
							ta.select(counter2,counter2+1);
							String r = ta.getSelectedText();

							if(mtchFind.getState())
							{
								if(r.equals(tfReplace.getText()))
								{
									System.out.println("equal");

									ta.replaceRange(tf2Replace.getText(),counter2,counter2+1);
									System.out.println("breaking");

									counter2++;
									break;
								}
							}
							else
							{
								if(r.equalsIgnoreCase(tfReplace.getText()))
								{
									System.out.println("equal");
									ta.replaceRange(tf2Replace.getText(),counter2,counter2+1);
									System.out.println("breaking");
									counter2++;
									break;
								}
							}

							counter2++;

							System.out.println(counter2);
			}

			if(counter2==z)
				counter2 = 0;
	}


	void replaceAllNext()
	{
		int idx = 0;
		int z = ta.getText().length();
		while(idx != z)
		{
			ta.select(idx,idx+1);
			String r = ta.getSelectedText();
			System.out.println(r);
			System.out.println(tfReplace.getText());
			if(mtchReplace.getState())
			{
				if(r.equals(tfReplace.getText()))
				{
					System.out.println("equal");
					ta.replaceRange(tf2Replace.getText(),idx,idx+1);
				}
			}
			else
			{
				if(r.equalsIgnoreCase(tfReplace.getText()))
				{
					System.out.println("equal");
					ta.replaceRange(tf2Replace.getText(),idx,idx+1);
				}
			}

			idx++;
		}

	}

	void goTo()
	{
		GoTo = new Dialog(f,"Go To Line");
		GoTo.setSize(280,140);
		GoTo.setLayout(null);

		Label lGoTo = new Label("Line Number :");
		lGoTo.setBounds(15,30,100,30);
		GoTo.add(lGoTo);

		tfGoTo = new TextField();
		tfGoTo.setBounds(15,60,250,25);
		GoTo.add(tfGoTo);

		Goto = new Button("Go To");
		Goto.setBounds(90,95,80,25);
		GoTo.add(Goto);

		GotoC = new Button("Cancel");
		GotoC.setBounds(180,95,80,25);
		GoTo.add(GotoC);

		GoTo.setVisible(false);

		GoTo.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				GoTo.dispose();
			}
	    });
	}

	void Find()
	{
		find = new Dialog(f,"Find");
		find.setSize(400,200);
		find.setLayout(null);

		Label lFind = new Label("Find What  :");
		lFind.setBounds(15,40,70,30);
		find.add(lFind);

		tfFind = new TextField();
		tfFind.setBounds(100,45,200,25);
		find.add(tfFind);

		findF = new Button("Find Next");
		findF.setBounds(310,43,70,25);
		find.add(findF);

		findC = new Button("Cancel");
		findC.setBounds(310,78,70,25);
		find.add(findC);

		Label dFind = new Label("Direction");
		dFind.setBounds(175,90,70,30);
		find.add(dFind);

		directionFind = new CheckboxGroup();

		upFind = new Checkbox("Up",directionFind,false);
		upFind.setBounds(165,115,40,30);
		find.add(upFind);

		downFind = new Checkbox("Down",directionFind,true);
		downFind.setBounds(215,115,60,30);
		find.add(downFind);

		mtchFind = new Checkbox("Match case");
		mtchFind.setBounds(25,155,100,30);
		find.add(mtchFind);

		find.setVisible(false);
		find.setResizable(false);

		find.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				find.dispose();
			}
	    });
	}

	/////////////////////////
	/*int findNext()
	{

		String s1=ta.getText();
		String s2=tfFind.getText();

		int lastIndex=ta.getCaretPosition();

		int selStart=ta.getSelectionStart();
		System.out.println(selStart);
		int selEnd=ta.getSelectionEnd();
		System.out.println(selEnd);

		if(upFind.getState())
		{
			if(selStart!=selEnd)
				lastIndex=selEnd-s2.length()-1;
			if(mtchFind.getState())
				lastIndex=s1.toUpperCase().lastIndexOf(s2.toUpperCase(),lastIndex);
			else
				lastIndex=s1.lastIndexOf(s2,lastIndex);
		}

		else
		{
			if(selStart!=selEnd)
				lastIndex=selStart+1;
			if(mtchFind.getState())
				lastIndex=s1.toUpperCase().indexOf(s2.toUpperCase(),lastIndex);
			else
				lastIndex=s1.indexOf(s2,lastIndex);
		}

		System.out.println(lastIndex);

		return lastIndex;
	}*/


	///////////////////////////////////////////////
	public void findNextWithSelection()
	{

		if(counter==0)
			counter = ta.getCaretPosition();

		int z = ta.getText().length();
		if(downFind.getState())
		{
			while(counter != z)
			{
				ta.select(counter,counter+1);
				String r = ta.getSelectedText();

				if(mtchFind.getState())
				{
					if(r.equals(tfFind.getText()))
					{
						System.out.println("equal");
						//System.out.println(ta.getCaretPosition());
						//ta.setCaretPosition(counter+1);
						ta.select(counter,counter+1);
						System.out.println("breaking");
						//System.out.println(ta.getCaretPosition());
						counter++;
						break;
					}
				}
				else
				{
					if(r.equalsIgnoreCase(tfFind.getText()))
					{
						System.out.println("equal");
						ta.select(counter,counter+1);
						System.out.println("breaking");
						counter++;
						break;
					}
				}

				counter++;

				System.out.println(counter);
			}
		}
		else
		{
			while(counter != 0)
			{
					ta.select(counter-1,counter);
					String r = ta.getSelectedText();
					if(mtchFind.getState())
					{
						if(r.equals(tfFind.getText()))
						{
							System.out.println("equal in up");
							ta.select(counter-1,counter);
							System.out.println("breaking");
							counter--;
							break;
						}
					}
					else
					{
						if(r.equalsIgnoreCase(tfFind.getText()))
						{
							System.out.println("equal in up");
							ta.select(counter-1,counter);
							System.out.println("breaking");
							counter--;
							break;
						}
					}

					counter--;

					System.out.println(counter);
			}
		}



		if(counter == z)
			counter = 0;
	}



	void font()
	{
		d3 = new Dialog(f,"Font");
		d3.setSize(500,500);

		tafont = new TextArea("Hey Look, $$ is here",5,30,TextArea.SCROLLBARS_VERTICAL_ONLY);

		//thisFont = ta.getFont();

		String[] fontNames= GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		//fontNames = fontNames.trim();
		li=new List();
		for(int i=0;i<fontNames.length;i++)
		{
			li.add(fontNames[i].trim());
		}
		li.select(0);
		/*li.addItemListener(new ItemListener()
							{public void itemStateChanged(ItemEvent ev)
								{
									thisFont = createFont();
									System.out.println(thisFont);
									/*tafont.setText("hello");
									tafont.setFont(thisFont);
									tafont.setText("hlo");
									tafont.setFont(thisFont);
								}
			});*/

			li.addItemListener(new ItemListener()
										{public void itemStateChanged(ItemEvent ev)
											{
												thisFont = createFont();
												//tafont.setFont("helloooooooooooooooooo");
												System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh");
											}
			});



		String[] fontStyle = {"Regular","Italic","Bold","Bold Italic"};
		li1=new List();
		for(int i=0;i<fontStyle.length;i++)
		{
			li1.add(fontStyle[i]);
		}
		li1.select(0);
		li1.addItemListener(new ItemListener()
							{public void itemStateChanged(ItemEvent ev)
								{
									thisFont = createFont();
									tafont.setFont(thisFont);
									//System.out.println(thisFont);
								}
			});

		String[] fontSizes = new String[30];
		for(int j=0; j<30; j++)
			fontSizes[j] = new String(10+j*2+"");
		li2=new List(6);
		for(int i=0;i<fontSizes.length;i++)
		{
			li2.add(fontSizes[i]);
		}
		li2.select(0);

		li2.addItemListener(new ItemListener()
					{public void itemStateChanged(ItemEvent ev)
						{
							thisFont = createFont();
							tafont.setFont(thisFont);
						}
			});

		Panel label = new Panel();
		label.setLayout(new GridLayout(1,3));

		label.add(new Label("Font",Label.CENTER));
		label.add(new Label("Font Style",Label.CENTER));
		label.add(new Label("Size",Label.CENTER));

		Panel c = new Panel();
		c.setLayout(new GridLayout(2,1));

		Panel list = new Panel();
		list.setBounds(50,100,400,300);
		list.setLayout(new GridLayout(1,3));
		list.add(li);
		list.add(li1);
		list.add(li2);
		c.add(list);

		c.add(tafont);

		Panel button = new Panel();
		button.setLayout(new FlowLayout());

		ok = new Button("OK");
		cancel = new Button("Cancel");
		button.add(ok);
		button.add(new Label("                                 "));
		button.add(cancel);


		d3.setLayout(new BorderLayout());
		d3.add(label,BorderLayout.NORTH);
		d3.add(c,BorderLayout.CENTER);
		d3.add(button,BorderLayout.SOUTH);
		d3.add(new Label("  "),BorderLayout.EAST);
		d3.add(new Label("  "),BorderLayout.WEST);
		d3.setResizable(false);
		d3.setVisible(false);

		d3.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				d3.dispose();}
		});
	}

	Font createFont()
	{
		Font fnt = ta.getFont(); ;

		//int y = li.getSelectedIndex();
		String fontname ;//li.getItem(y);
		fontname = li.getSelectedItem();

		int fontstyle = Font.PLAIN;
		int x = li1.getSelectedIndex();

		System.out.println(fnt.getFontName());

		switch(x)
		{
			case 0:
				fontstyle=Font.PLAIN;	break;
			case 1:
				fontstyle=Font.ITALIC;	break;
			case 2:
				fontstyle=Font.BOLD;	break;
			case 3:
				fontstyle=Font.BOLD+Font.ITALIC;	break;
		}

		int fontsize = Integer.parseInt((String)li2.getSelectedItem());

		fnt = new Font("ARIAL",fontstyle,fontsize);
		//System.out.println(fnt.getFontName());

		//f.add(z);

		/*TextArea z = new TextArea("ssssssssss",1,20);
		z.setFont(fnt);
		f.add(z,BorderLayout.EAST);*/

		System.out.println(fnt);



		return fnt;
	}

	void status()
	{

		//r1 = 1; c1 = 1;
		sb = new Panel();
		statusBar = new Label("    Line  " + r1 + "      Col  " + c1 + "  ", Label.CENTER);
		statusBar.setFont(new Font("Arial",0,25));
		sb.add(statusBar);
		f.add(sb,bl.SOUTH);
		//sb.setVisible(false);
		statusBar.addKeyListener(this);




		/*int lineNumber=0, column=0, pos=0;

		try
		{
		//pos=ta.getCaretPosition();
		lineNumber=ta.getRows();//ta.getLineOfOffset(pos);
		column=ta.getColumns();//pos-ta.getLineStartOffset(lineNumber);
		}catch(Exception excp){}
		if(ta.getText().length()==0){lineNumber=0; column=0;}
		statusBar.setText("       Line "+(lineNumber+1)+", Col "+(column+1));*/



	}

	void init(String s)
	{
			f = new Frame(s);
			f.setSize(1000,600);

			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent){
					            System.exit(0);
					         }
	      	});

	      	//TEXT AREA
			ta = new TextArea("",1,1,TextArea.SCROLLBARS_VERTICAL_ONLY);
			p = new Panel();
			p.setLayout(new GridLayout(1,1));
			p.add(ta);
			ta.addKeyListener(this);

			p.setBounds(0,0,1000,540);

			f.setLayout(bl);
			f.add(p,bl.CENTER);
			f.setVisible(true);
			f.setResizable(false);

	}


	void help()
	{
		Help = new Frame("Help");
		Help.setSize(500,500);
		Panel HelpPanel = new Panel();
		bd2 = new Button("OK");
		bd2.setBounds(210,350,60,40);
		HelpPanel.setLayout(new BorderLayout());
		HelpPanel.add(new Label("For any kind of help please send a mail to snehilsaxena077@gmail.com"),BorderLayout.CENTER);
		HelpPanel.add(bd2,BorderLayout.SOUTH);
		Help.add(HelpPanel);
		Help.setVisible(false);

		Help.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent windowEvent){
							            Help.dispose();
							         }
	      	});
	}

	void aboutNotepad()
	{
			d = new Dialog(f,"About Notepad");
			d.setSize(500,500);
			//d.setLayout(new GridLayout(2,1));
			d.setResizable(false);

			Panel tanp = new Panel();
			tanp.setBounds(0,0,200,350);

			Font ft = new Font("Calibri", Font.BOLD,30);
			Label lb1 = new Label("Your Notepad",Label.CENTER);
			lb1.setFont(ft);
			Font ft2 = new Font("Calibri", Font.BOLD,32);
			Label lb2 = new Label("$ Prepared by a MITSIAN $",Label.CENTER);
			lb2.setFont(ft);
			ft2 = new Font("Calibri", Font.BOLD,16);
			Label lb3 = new Label("I Used jdk1.8.0 to compile the source code",Label.CENTER);
			lb3.setFont(ft2);
			Label lb4 = new Label("Ur Comments as well as bug reports are very welcome at",Label.CENTER);
			lb4.setFont(ft2);
			Label lb5 = new Label("snehilsaxena077@gmail.com",Label.CENTER);
			lb5.setFont(ft);
			Label lb6 = new Label("Thanx 4 using $$ Notepad",Label.CENTER);
			lb6.setFont(ft);

			tanp.setLayout(new GridLayout(6,1));
			tanp.add(lb1);
			tanp.add(lb2);
			tanp.add(lb3);
			tanp.add(lb4);
			tanp.add(lb5);
			tanp.add(lb6);
			tanp.add(lb6);

			Panel pd = new Panel();
			bd = new Button("OK");
			bd.setBounds(200,400,200,80);
			pd.add(bd,new FlowLayout(FlowLayout.CENTER));
			pd.add(bd);
			d.add(tanp,BorderLayout.CENTER);
			d.add(pd,BorderLayout.SOUTH);

			d.setVisible(false);

			d.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent windowEvent){
						d.dispose();
					}
	      	});
	}


	public boolean action(Event evt, Object arg){
	         if(arg.equals("OK")){
	            d.setVisible(false);
	            return true;
	         }
	         return false;
      }

        public void keyPressed(KeyEvent ke)
	  	{
	  		int key = ke.getKeyCode();

	  		if(key==KeyEvent.VK_ENTER)
	  		{
	  			r1++;
	  			c1=1;
	  			statusBar.setText("    Line  " + r1 + "     Col  "   + c1 + "  ");
	  		}
	  		if(key>=32&&key<=126)
	  		{
	  			c1++;
	  			statusBar.setText("    Line  " + r1 + "     Col  "   + c1 + "  ");
	  		}
	  		if(key==8)
			{
				if(c1>1)
					c1--;
				System.out.println(c1);
				if(c1==1&&r1>1)
				{
					//c1=ta.getText().length()/r1;
					//System.out.println("rows"+ta.getRows());
					r1--;

				}
				statusBar.setText("    Line  " + r1 + "     Col  " +   (c1+1) + "  ");
	  		}

	  	}

	  	public void keyReleased(KeyEvent ke){}

		public void keyTyped(KeyEvent ke){}

////////////////////

boolean saved = false;
boolean confirmSave()
{
	String strMsg=	"Do you want to save the changes?<html>";
	if(!saved)
	{
		int x=JOptionPane.showConfirmDialog(f,strMsg,"s",JOptionPane.YES_NO_CANCEL_OPTION);

		if(x==JOptionPane.CANCEL_OPTION)
			return false;
		//if(x==JOptionPane.YES_OPTION && !saveAsFile())
		//	return false;
	}
	return true;
}

/////////////////

boolean openFile(File temp)
{
	FileInputStream fin=null;
	BufferedReader din=null;

	try
	{
		fin=new FileInputStream(temp);
		din=new BufferedReader(new InputStreamReader(fin));
		String str=" ";
		while(str!=null)
		{
			str=din.readLine();
			if(str==null)
			break;
			ta.append(str+"\n");
		}

	}
	catch(IOException ioe){}

	finally
	{
		try{din.close();fin.close();}
		catch(IOException excp){}
	}
	//updateStatus(temp,true);
	ta.setCaretPosition(0);
	return true;
}


		void Listen()
		{
			Listener o = new Listener(this);

			m12.addActionListener(o);
			m13.addActionListener(o);

			m21.addActionListener(o);
			m22.addActionListener(o);
			m23.addActionListener(o);
			m24.addActionListener(o);
			m25.addActionListener(o);

			m26.addActionListener(o);
			findF.addActionListener(o);
			findC.addActionListener(o);

			m27.addActionListener(o);

			m28.addActionListener(o);
			replaceC.addActionListener(o);
			replaceF.addActionListener(o);
			replaceR.addActionListener(o);
			replaceA.addActionListener(o);

			m29.addActionListener(o);
			GotoC.addActionListener(o);

			m210.addActionListener(o);
			bd.addActionListener(o);

			m211.addActionListener(o);
			bd2.addActionListener(o);

			//cb2.addActionListener(o);

			m32.addActionListener(o);
			cancel.addActionListener(o);
			ok.addActionListener(o);

			m51.addActionListener(o);
			m52.addActionListener(o);
		}
}


class Notepad{
	public static void main(String str[]){
		new NP("$$ NOTEPAD");
	}
}

class Listener implements ActionListener{
	NP n;

	Listener(NP n){
		this.n = n;
	}

	public void actionPerformed(ActionEvent e)
	{

		/*
					IMPLEMENTING FILE MENU
					...............................
					..................................
					.......................................
					........................................
		*/

		if(e.getSource()==n.m12) //OPEN
		{
			if(!n.confirmSave()) return;

			File file = null;
			do
			{

						int r = n.chooser.showOpenDialog(n.f);

						if (r == JFileChooser.APPROVE_OPTION)
						{
							file = n.chooser.getSelectedFile();

						    if(file.exists())
						     	break;
						}

						JOptionPane.showMessageDialog(this.n.f,
									"<html>"+file.getName()+"<br>file not found.<br>"+
									"Please verify the correct file name was given.<html>",
									"Open",	JOptionPane.INFORMATION_MESSAGE);

			} while(true);

			n.ta.setText("");

			n.openFile(file);

			n.f.setTitle(file.getName()+"  $$ Notepad");

		}

		////////////////////////
		if(e.getSource()==n.m13) //SAVE
		{
			do
			{

						int r = n.chooser.showSaveDialog(n.f);
						if (r == JFileChooser.APPROVE_OPTION)
						{


						     File file = n.chooser.getSelectedFile();

						     if(file.exists())
						     	break;

						     //statusLabel.setText("File Selected :" + file.getName());
						}
						else
						{
						     //statusLabel.setText("Open command cancelled by user." );
            }

			} while(true);
		}

		/*
					IMPLEMENTING VIEW MENU
					...............................
					..................................
					.......................................
					........................................
		*/

		if(e.getSource()==n.cb2) // STATUS BAR
		{
			byte b = 0;
			if(b==0)
			{
				//n.f.add(n.statusBar,BorderLayout.SOUTH);
				//n.statusBar.setVisible(true);
				n.bl.removeLayoutComponent(n.sb);
				//n.sb.setVisible(true);
				b++;
			}
			else
			{
				//n.statusBar.setVisible(false);
				n.bl.addLayoutComponent("",n.statusBar);
				//n.sb.setVisible(false);
				b=0;
			}

		}

		/*
					IMPLEMENTING FORMAT MENU
					...............................
					..................................
					.......................................
					........................................
		*/


		if(e.getSource()==n.m32) //FONT
			n.d3.setVisible(true);

		if(e.getSource()==n.ok) //FONT OK
		{
			n.ta.setFont(n.thisFont);
			n.d3.dispose();
		}

		if(e.getSource()==n.cancel) //FONT CANCEL
			n.d3.dispose();

		/*
					IMPLEMENTING HELP MENU
					...............................
					..................................
					.......................................
					........................................
		*/

		if(e.getSource()==n.m52) //ABOUT NOTEPAD
			n.d.setVisible(true);

		if(e.getSource()==n.m51) //HELP
			n.Help.setVisible(true);

		if(e.getSource()==n.bd) //ABOUT NOTEPAD BUTTON
		{
			//n.d.setVisible(false);
			n.d.dispose();
			n.f.repaint();
		}

		if(e.getSource()==n.bd2) //HELP BUTTON
		{
			n.Help.dispose();
			//n.f.repaint();
			Point p;
			p = n.ta.getMousePosition();
			System.out.println(p);
		}

		/*
			IMPLEMENTING EDIT MENU
			...............................
			..................................
			.......................................
			........................................
		*/

		Clipboard clipboard = n.ta.getToolkit().getSystemClipboard();

		if(e.getSource()==n.m23)//COPY
		{
			StringSelection data = new StringSelection(n.ta.getSelectedText());
			clipboard.setContents(data,data);
		}
		else if(e.getSource()==n.m24)//PASTE
		{
			Transferable clipdata = clipboard.getContents(n);
			String s;
			int pos = n.ta.getCaretPosition();
			try
			{
				s = (String)(clipdata.getTransferData(DataFlavor.stringFlavor));
			}
			catch(Exception ex)
			{
				s = ex.toString();
			}
			System.out.println(pos);
			n.ta.insert(s,pos);
		}

		else if(e.getSource()==n.m22)//CUT
		{
			int a = n.ta.getSelectionStart();
			int b = n.ta.getSelectionEnd();

			StringSelection data = new StringSelection(n.ta.getSelectedText());
			clipboard.setContents(data,data);

			n.ta.replaceRange("",a,b);
		}

		else if(e.getSource()==n.m25)//DELETE
		{
			int a = n.ta.getSelectionStart();
			int b = n.ta.getSelectionEnd();
			n.ta.replaceRange("",a,b);
		}

		else if(e.getSource()==n.m210)//SELECT ALL
		{
			n.ta.selectAll();
		}

		else if(e.getSource()==n.m211) // TIME & DATE
		{
			String str = new String(n.date.toString());
			n.ta.append(str);
		}

		// FIND

		else if(e.getSource()==n.m26)
			n.find.setVisible(true);

		else if(e.getSource()==n.findF)
			n.findNextWithSelection();

		else if(e.getSource()==n.findC)
			n.find.dispose();

		// Go to

		else if(e.getSource()==n.m29)
			n.GoTo.setVisible(true);

		//else if(e.getSource()==n.Goto)
			//n.findNextWithSelection();

		else if(e.getSource()==n.GotoC)
			n.GoTo.dispose();

		// REPLACE

		else if(e.getSource()==n.m28)
			n.replace.setVisible(true);

		else if(e.getSource()==n.replaceC)
			n.replace.dispose();

		else if(e.getSource()==n.replaceA)
			n.replaceAllNext();

		else if(e.getSource()==n.replaceR)
			n.replaceNext();

		// FIND NEXT
		else if(e.getSource()==n.m27)
			n.findNextWithSelection();

	}
}

