package entity;

import java.awt.Graphics;

import point.MyVector;

public interface IEntity {
	
	void render(Graphics g);
	
	void rotate(boolean CW,double xDegree,double yDegree,double zDegree,MyVector lightVector);
	
}
