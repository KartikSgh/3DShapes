package entity;

import java.awt.Graphics;
import java.util.List;

import point.MyVector;
import shapes.MyPolyhedron;

public class Entity implements IEntity {
	
	private List<MyPolyhedron> poly;
	
	public Entity(List<MyPolyhedron> poly) {
		this.poly=poly;
	}

	@Override
	public void render(Graphics g) {
		for(MyPolyhedron p:this.poly) {
			p.render(g);
		}
		
	}

	@Override
	public void rotate(boolean CW, double xDegree, double yDegree, double zDegree,MyVector lightVector) {
		for(MyPolyhedron p:this.poly) {
			p.rotate(CW, xDegree, yDegree, zDegree,lightVector);
		}
		
	}

}
