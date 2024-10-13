import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.sound.midi.*;
import java.util.Stack;

public class Tuixiangzi {
    public static void main(String[] args) {
        new mainFrame();
    }
}

class mainFrame extends JFrame implements ActionListener, ItemListener {
    JLabel lb;
    JLabel lb2;
    JButton btnrenew, btnlast, btnnext, btnchoose, btnfirst, btnover, btnmuc, btnback;
    mainpanel panel;
    Sound sound;
    JComboBox jc = new JComboBox();
    MenuItem renew = new MenuItem("Renew");
    MenuItem back = new MenuItem("Back");
    MenuItem last = new MenuItem("Last");
    MenuItem next = new MenuItem("Next");
    MenuItem choose = new MenuItem("Choose");
    MenuItem exit = new MenuItem("Exit");
    MenuItem qin = new MenuItem("2");
    MenuItem po = new MenuItem("3");
    MenuItem guang = new MenuItem("4");
    MenuItem nor = new MenuItem("1");
    MenuItem eye = new MenuItem("5");
    MenuItem about = new MenuItem("About...");
    
    mainFrame() {
        super("Push BOX");
        setSize(720, 720);
        setVisible(true);
        setResizable(false);
        setLocation(300, 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cont = getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.black);
        
        Menu choice = new Menu("Choice");
        choice.add(renew);
        choice.add(last);
        choice.add(next);
        choice.add(choose);
        choice.add(back);
        choice.addSeparator();
        choice.add(exit);
        renew.addActionListener(this);
        last.addActionListener(this);
        next.addActionListener(this);
        choose.addActionListener(this);
        exit.addActionListener(this);
        back.addActionListener(this);
        
        Menu setmuc = new Menu("Music");
        setmuc.add(nor);
        setmuc.add(qin);
        setmuc.add(po);
        setmuc.add(guang);
        setmuc.add(eye);
        nor.addActionListener(this);
        qin.addActionListener(this);
        po.addActionListener(this);
        guang.addActionListener(this);
        eye.addActionListener(this);
        
        Menu help = new Menu("Help");
        help.add(about);
        about.addActionListener(this);
        
        MenuBar bar = new MenuBar();
        bar.add(choice);
        bar.add(setmuc);
        bar.add(help);
        setMenuBar(bar);

        nor.setEnabled(false);
        lb = new JLabel("Push BOX", SwingConstants.CENTER);
        lb2 = new JLabel("Music", SwingConstants.CENTER);
        add(lb);
        add(lb2);
        lb.setBounds(100, 20, 400, 20);
        lb.setForeground(Color.white);
        lb2.setBounds(625, 500, 55, 20);
        lb2.setForeground(Color.white);

        btnrenew = new JButton("Renew");
        btnback = new JButton("Back");
        btnlast = new JButton("Last");
        btnnext = new JButton("Next");
        btnchoose = new JButton("Choise");
        btnfirst = new JButton("First");
        btnover = new JButton("Over");
        btnmuc = new JButton("Stop Music");
        
        add(btnrenew);
        add(btnlast);
        add(btnnext);
        add(btnchoose);
        add(btnfirst);
        add(btnover);
        add(btnmuc);
        add(btnback);
        
        btnrenew.setBounds(625, 100, 80, 30);
        btnrenew.addActionListener(this);
        btnback.setBounds(625, 150, 80, 30);
        btnback.addActionListener(this);
        btnfirst.setBounds(625, 200, 80, 30);
        btnfirst.addActionListener(this);
        btnlast.setBounds(625, 250, 80, 30);
        btnlast.addActionListener(this);
        btnnext.setBounds(625, 300, 80, 30);
        btnnext.addActionListener(this);
        btnover.setBounds(625, 350, 80, 30);
        btnover.addActionListener(this);
        btnchoose.setBounds(625, 400, 80, 30);
        btnchoose.addActionListener(this);
        btnmuc.setBounds(625, 450, 80, 30);
        btnmuc.addActionListener(this);

        jc.setBounds(625, 530, 80, 20);
        jc.addItem("1");
        jc.addItem("2");
        jc.addItem("3");
        jc.addItem("4");
        jc.addItem("5");
        jc.addItemListener(this);
        cont.add(jc);
        
        sound = new Sound();
        sound.loadSound();
        panel = new mainpanel();
        add(panel);
        panel.Tuixiangzi(panel.level);
        panel.requestFocus();
        validate();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnrenew || e.getSource() == renew) {
            panel.Tuixiangzi(panel.level);
            panel.requestFocus();
            panel.remove();
        } else if (e.getSource() == btnlast || e.getSource() == last) {
            panel.level--;
            if (panel.level < 1) {
                panel.level++;
                JOptionPane.showMessageDialog(this, "First level");
                panel.requestFocus();
            } else {
                panel.Tuixiangzi(panel.level);
                panel.requestFocus();
            }
            panel.remove();
        } else if (e.getSource() == btnnext || e.getSource() == next) {
            panel.level++;
            if (panel.level > panel.maxlevel()) {
                panel.level--;
                JOptionPane.showMessageDialog(this, "Final level");
                panel.requestFocus();
            } else {
                panel.Tuixiangzi(panel.level);
                panel.requestFocus();
            }
            panel.remove();
        } else if (e.getSource() == exit) System.exit(0);
        else if (e.getSource() == about) {
            JOptionPane.showMessageDialog(this, "JAVA Push Box");
        } else if (e.getSource() == btnchoose || e.getSource() == choose) {
            String lel = JOptionPane.showInputDialog(this, "Choose level (1~10)");
            panel.level = Integer.parseInt(lel);
            panel.remove();
            if (panel.level > panel.maxlevel() || panel.level < 1) {
                JOptionPane.showMessageDialog(this, "No this level,input again");
                panel.requestFocus();
            } else {
                panel.Tuixiangzi(panel.level);
                panel.requestFocus();
            }
        } else if (e.getSource() == btnfirst) {
            panel.level = 1;
            panel.Tuixiangzi(panel.level);
            panel.requestFocus();
            panel.remove();
        } else if (e.getSource() == btnover) {
            panel.level = panel.maxlevel();
            panel.Tuixiangzi(panel.level);
            panel.requestFocus();
            panel.remove();
        } else if (e.getSource() == btnmuc) {
            if (sound.isplay()) {
                sound.mystop();
                btnmuc.setLabel("Play a music");
            } else {
                sound.loadSound();
                btnmuc.setLabel("Stop music");
            }
            panel.requestFocus();
        } else if (e.getSource() == btnback || e.getSource() == back) {
            if (panel.isMystackEmpty()) JOptionPane.showMessageDialog(this, "No last step");
            else {
                switch (panel.back()) {
                    case 10:
                        panel.backup(10);
                        break;
                    case 11:
                        panel.backup(11);
                        break;
                    case 20:
                        panel.backdown(20);
                        break;
                    case 21:
                        panel.backdown(21);
                        break;
                    case 30:
                        panel.backleft(30);
                        break;
                    case 31:
                        panel.backleft(31);
                        break;
                    case 40:
                        panel.backright(40);
                        break;
                    case 41:
                        panel.backright(41);
                        break;
                }
            }
            panel.requestFocus();
        } else if (e.getSource() == nor) {
            jc.setSelectedIndex(0);
        } else if (e.getSource() == qin) {
            jc.setSelectedIndex(1);
        } else if (e.getSource() == guang) {
            jc.setSelectedIndex(3);
        } else if (e.getSource() == eye) {
            jc.setSelectedIndex(4);
        } else if (e.getSource() == po) {
            jc.setSelectedIndex(2);
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        int no = jc.getSelectedIndex();
        switch (no) {
            case 0:
                sound.setMusic("nor.mid");
                if (sound.isplay()) sound.mystop();
                sound.loadSound();
                btnmuc.setLabel("Stop music");
                nor.setEnabled(false);
                qin.setEnabled(true);
                guang.setEnabled(true);
                eye.setEnabled(true);
                po.setEnabled(true);
                panel.requestFocus();
                break;
            case 1:
                sound.setMusic("qin.mid");
				if (sound.isplay()) sound.mystop();
                sound.loadSound();
                btnmuc.setLabel("Stop music");
                qin.setEnabled(false);
                nor.setEnabled(true);
                guang.setEnabled(true);
                eye.setEnabled(true);
                po.setEnabled(true);
                panel.requestFocus();
                break;
            case 2:
                sound.setMusic("popo.mid");
				if (sound.isplay()) sound.mystop();
                sound.loadSound();
                btnmuc.setLabel("Stop music");
                qin.setEnabled(true);
                nor.setEnabled(true);
                guang.setEnabled(true);
                eye.setEnabled(true);
                po.setEnabled(false);
                panel.requestFocus();
                break;
            case 3:
                sound.setMusic("guang.mid");
				if (sound.isplay()) sound.mystop();
                sound.loadSound();
                btnmuc.setLabel("Stop music");
                qin.setEnabled(true);
                nor.setEnabled(true);
                guang.setEnabled(false);
                eye.setEnabled(true);
                po.setEnabled(true);
                panel.requestFocus();
                break;
            case 4:
                sound.setMusic("eyes om me.mid");
				if (sound.isplay()) sound.mystop();
                sound.loadSound();
                btnmuc.setLabel("Stop music");
                qin.setEnabled(true);
                nor.setEnabled(true);
                guang.setEnabled(true);
                eye.setEnabled(false);
                po.setEnabled(true);
                panel.requestFocus();
                break;
        }
    }
}


class mainpanel extends JPanel implements KeyListener
{
	int max=10;
	int[][] map,maptmp;
	int manX,manY,boxnum;
	Image[] myImage;
	Readmap Levelmap;
	Readmap Levelmaptmp;
	int len=30;
	public int level=1;
	Stack mystack=new Stack();
	mainpanel()
	{	
		setBounds(15,50,600,600);
		setBackground(Color.white);
		addKeyListener(this);
		myImage=new Image[10];
		for(int i=0; i<10; i++)
		{
		    myImage[i] = Toolkit.getDefaultToolkit().getImage("pic\\"+i+".gif");
		}
		
		setVisible(true);
	}

	void Tuixiangzi(int i)
	{
		Levelmap=new Readmap(i);
		Levelmaptmp=new Readmap(i);
		map=Levelmap.getmap();
		manX=Levelmap.getmanX();
		manY=Levelmap.getmanY();
		maptmp=Levelmaptmp.getmap();
		repaint();
	}
	int maxlevel(){return max;}

	public void paint(Graphics g)
	{
		for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
		    {
			    g.drawImage(myImage[map[j][i]],i*len,j*len,this);
			}		
		g.setColor(new Color(0,0,0));
		g.setFont(new Font("ST",Font.BOLD,30));
		g.drawString("Level ",240,40);
		g.drawString(String.valueOf(level),310,40);
		g.drawString("Complete",360,40);
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP){moveup();}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){movedown();}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){moveleft();}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){moveright();}
		if(iswin())
		{
			if(level==max){JOptionPane.showMessageDialog(this, "Congratulations! You've completed all levels.");}
			else
			{
				String msg= "Congratulations! You've completed level " + level + "!!!\nDo you want to proceed to the next level?";
				int type=JOptionPane.YES_NO_OPTION;
				String title="Level Complete";
				int choice=0;
				choice=JOptionPane.showConfirmDialog(null,msg,title,type);
				if(choice==1)System.exit(0);
				else if(choice==0)
				{
					level++;
					Tuixiangzi(level);
				}
			}
			mystack.removeAllElements();
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}

	boolean isMystackEmpty(){return mystack.isEmpty();}
	
	int  back(){return (Integer)mystack.pop();}

	void remove(){mystack.removeAllElements();}
	
	void moveup()
	{
		if(map[manY-1][manX]==2||map[manY-1][manX]==4)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			map[manY-1][manX]=8;
			repaint();manY--;mystack.push(10);
		}
		else if(map[manY-1][manX]==3)
		{
			if(map[manY-2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=9;
				repaint();manY--;mystack.push(11);
			}
			else if(map[manY-2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=3;
				repaint();manY--;mystack.push(11);
			}
			else {map[manY][manX]=8;repaint();}
		}
		else if(map[manY-1][manX]==9)
		{
			if(map[manY-2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=9;
				repaint();manY--;mystack.push(11);
			}
			else if(map[manY-2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=3;
				repaint();manY--;mystack.push(11);
			}
			else {map[manY][manX]=8;repaint();}
		}
		if(map[manY-1][manX]==1)
		{
			map[manY][manX]=8;repaint();
		}
	}
	
	void backup(int t)
	{
		int n=t;
		if(n==10)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==11)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY-1][manX]==4||maptmp[manY-1][manX]==9)
			{
				map[manY-1][manX]=4;
			}
			else map[manY-1][manX]=2;
		}
		map[manY+1][manX]=8;
		repaint();manY++;
	}

	void movedown()
	{
		if(map[manY+1][manX]==2||map[manY+1][manX]==4)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			map[manY+1][manX]=5;
			repaint();manY++;mystack.push(20);
		}
		else if(map[manY+1][manX]==3)
		{
			if(map[manY+2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=9;
				repaint();manY++;mystack.push(21);
			}
			else if(map[manY+2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=3;
				repaint();manY++;mystack.push(21);
			}
			else {map[manY][manX]=5;repaint();}
		}
		else if(map[manY+1][manX]==9)
		{
			if(map[manY+2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=9;
				repaint();manY++;mystack.push(21);
			}
			else if(map[manY+2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=3;
				repaint();manY++;mystack.push(21);
			}
			else {map[manY][manX]=5;repaint();}
		}
		else if(map[manY+1][manX]==1)
		{
			map[manY][manX]=5;repaint();
		}
	}

	void backdown(int t)
	{
		int n=t;
		if(n==20)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==21)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY+1][manX]==4||maptmp[manY+1][manX]==9)
			{
				map[manY+1][manX]=4;
			}
			else map[manY+1][manX]=2;
		}
		map[manY-1][manX]=5;
		repaint();manY--;
	}

	void moveleft()
	{
		if(map[manY][manX-1]==2||map[manY][manX-1]==4)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			map[manY][manX-1]=6;			
			repaint();manX--;mystack.push(30);
		}
		else if(map[manY][manX-1]==3)
		{
			if(map[manY][manX-2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=9;
				repaint();manX--;mystack.push(31);
			}
			else if(map[manY][manX-2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=3;
				repaint();manX--;mystack.push(31);
			}
			else {map[manY][manX]=6;repaint();}
		}
		else if(map[manY][manX-1]==9)
		{
			if(map[manY][manX-2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=9;
				repaint();manX--;mystack.push(31);
			}
			else if(map[manY][manX-2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=3;
				repaint();manX--;mystack.push(31);
			}
			else {map[manY][manX]=6;repaint();}
		}
		else if(map[manY][manX-1]==1)
		{
			map[manY][manX]=6;repaint();
		}
	}

	void backleft(int t)
	{
		int n=t;
		if(n==30)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==31)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY][manX-1]==4||maptmp[manY][manX-1]==9)
			{
				map[manY][manX-1]=4;
			}
			else map[manY][manX-1]=2;
		}
		map[manY][manX+1]=6;
		repaint();manX++;
	}

	void moveright()
	{
		if(map[manY][manX+1]==2||map[manY][manX+1]==4)
		{			
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			map[manY][manX+1]=7;			
			repaint();manX++;mystack.push(40);
		}
		else if(map[manY][manX+1]==3)
		{
			if(map[manY][manX+2]==4)
			{
				if(maptmp[manY][manX]==4)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=9;
				repaint();manX++;mystack.push(41);
			}
			else if(map[manY][manX+2]==2)
			{
				if(maptmp[manY][manX]==4)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=3;
				repaint();manX++;mystack.push(41);
			}
			else {map[manY][manX]=7;repaint();}
		}
		else if(map[manY][manX+1]==9)
		{
			if(map[manY][manX+2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=9;
				repaint();manX++;mystack.push(41);
			}
			else if(map[manY][manX+2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=3;
				repaint();manX++;mystack.push(41);
			}
			else {map[manY][manX]=7;repaint();}
		}
		else if(map[manY][manX+1]==1)
		{
			map[manY][manX]=7;repaint();
		}
	}

	void backright(int t)
	{
		int n=t;
		if(n==40)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==41)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY][manX+1]==4||maptmp[manY][manX+1]==9)
			{
				map[manY][manX+1]=4;
			}
			else map[manY][manX+1]=2;
		}
		map[manY][manX-1]=7;
		repaint();manX--;
	}

	boolean iswin()
	{
		boolean num=false;
		out:for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
		{
			if(maptmp[i][j]==4||maptmp[i][j]==9)
				if(map[i][j]==9)num=true;
			    else {num=false;break out;}
		}
		return num;
	}
}

class Sound
{
	String path=new String("musics\\");
	String  file=new String("guang.mid");
	Sequence seq;
    Sequencer midi;
	boolean sign;
	void loadSound()
	{
		try {
            seq=MidiSystem.getSequence(new File(path+file));
            midi=MidiSystem.getSequencer();
            midi.open();
            midi.setSequence(seq);
			midi.start();
			midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        }
        catch (Exception ex) {ex.printStackTrace();}
		sign=true;
	}
	void mystop(){midi.stop();midi.close();sign=false;}
	boolean isplay(){return sign;}
	void setMusic(String e){file=e;}
}

class Readmap
{
	private int level,mx,my;
	private int[][] mymap=new int[20][20];
	FileReader r;
	BufferedReader br;
	String bb="";
	int[] x;int c=0;
	Readmap(int k)
	{
		level=k;
		String s;
		try
		{
			File f=new File("maps\\"+level+".map");
			r=new FileReader(f);
			br=new BufferedReader(r);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		try
		{
			while ((s=br.readLine())!=null)
			{
				bb=bb+s;
				
			}
		}
		catch (IOException g)
		{
			System.out.println(g);
		}
		byte[] d=bb.getBytes();
		int len=bb.length();
		int[] x=new int[len];
		for(int i=0;i<bb.length();i++)x[i]=d[i]-48;
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<20;j++)
		    {
				mymap[i][j]=x[c];
		        if(mymap[i][j]==5)
		        {
					mx=j;my=i;
		        }
		        c++;
		    }
	    }
	}
	int[][] getmap(){return mymap;}
	int getmanX(){return mx;}
	int getmanY(){return my;}
}