package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import entity.EntityManager;
import input.Mouse;

public class Display extends Canvas implements Runnable{
	
	private static final long serialVersionUID=1L;
	private Thread thread;
	private JFrame frame;
	private static String title="3D Renderer";
	public static int WIDTH=800;
	public static int HEIGHT=600;
	private static boolean running=false;
	final int fps=60;
	private EntityManager entityManager;
	private Mouse mouse;
	
	
	public Display() {
		this.frame=new JFrame();
		Dimension size=new Dimension(WIDTH,HEIGHT);
		this.frame.setPreferredSize(size);
		this.frame.setTitle(title);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.mouse=new Mouse();
		this.entityManager=new EntityManager();
		this.addMouseListener(this.mouse);
		this.addMouseMotionListener(this.mouse);
		this.addMouseWheelListener(this.mouse);
		this.frame.setVisible(true);
	}

	public static void main(String[] args) {
		Display display=new Display();
		display.frame.add(display);
		display.start();

	}
	
	public synchronized void start() {
		running=true;
		this.thread=new Thread(this,"Display");
		this.thread.start();
	}
	
	public synchronized void stop() {
		running=false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime=System.nanoTime();
		long timer=System.currentTimeMillis();
		double ns=1000000000.0/fps;
		double delta=0;
		int frames=0;
		
		this.entityManager.init();
		
		while(running) {
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				update();
				render();
				delta--;
				frames++;
			}
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				this.frame.setTitle(title+" | "+frames+"fps");
				frames=0;
			}
		}
		stop();
		
	}
	
	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		this.entityManager.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	private void update() {
		
		this.entityManager.update(this.mouse);
		
	}

}
