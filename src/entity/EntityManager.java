package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import entity.builder.BasicEntityBuilder;
import input.ClickedType;
import input.Mouse;
import point.MyVector;
import point.PointConverter;

public class EntityManager {
	
	List<IEntity> entities;
	private int initialX=-1;
	private int initialY=-1;
	private double sensitivity=2;
	private MyVector lightVector=MyVector.normalize(new MyVector(1,1,1));
	
	public EntityManager() {
		this.entities=new ArrayList<IEntity>();
	}
	
	public void init() {
		this.entities.add(BasicEntityBuilder.createDiamond(200,10, 0, 0, 0));
		this.rotate(true, 0, 0, 0);
	}
	
	public void update(Mouse mouse) {
		int x=mouse.getX();
		int y=mouse.getY();
		
		if(mouse.getB()==ClickedType.LeftClick) {
			int xdiff=x-initialX;
			int ydiff=y-initialY;
			this.rotate(true, ydiff/sensitivity,-xdiff/sensitivity,0);
		}else if(mouse.getB()==ClickedType.RightClick) {
			int xdiff=x-initialX;
			this.rotate(true, 0,0,-xdiff/sensitivity);
		}
		
		if(mouse.iszoomingIn()) {
			PointConverter.zoomIn();
			mouse.resetScroll();
		}else if(mouse.iszoomingOut()) {
			PointConverter.zoomOut();
			mouse.resetScroll();
		}
		
		initialX=x;
		initialY=y;
	}
	
	private void rotate(boolean CW, double xDegree, double yDegree, double zDegree) {
		for(IEntity e:this.entities)
			e.rotate(CW, xDegree, yDegree, zDegree,this.lightVector);
	}
	
	public void render(Graphics g) {
		for(IEntity e:this.entities) {
			e.render(g);
		}
	}

}
