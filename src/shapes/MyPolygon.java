package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import point.MyPoint;
import point.MyVector;
import point.PointConverter;

public class MyPolygon {
	private static final double AmbientLight=0.05;
	public  Color color,lightColor;
	private MyPoint[] points;
	
	public MyPolygon(MyPoint... point) {
		this.color=this.lightColor=Color.WHITE;
		this.points=new MyPoint[point.length];
		for(int i=0;i<point.length;i++) {
			this.points[i]=point[i];
		}
	}
		
	public MyPolygon(Color color,MyPoint... point) {
		this.color=this.lightColor=color;
		this.points=new MyPoint[point.length];
		for(int i=0;i<point.length;i++) {
			this.points[i]=point[i];
		}
	}
	
	public void rotate(boolean CW,double xDegree,double yDegree,double zDegree,MyVector lightVector) {
		for(MyPoint p : this.points) {
			PointConverter.rotateZ(p, zDegree, CW);
			PointConverter.rotateX(p, xDegree, CW);
			PointConverter.rotateY(p, yDegree, CW);
		}
		this.updateLightingVector(lightVector);
	}
	
	public void render(Graphics g) {
		Polygon polygon=new Polygon();
		for(int i=0;i<this.points.length;i++) {
			Point point=PointConverter.convertPoint(points[i]);
			polygon.addPoint(point.x,point.y);
		}
		
		g.setColor(this.lightColor);
		g.fillPolygon(polygon);
	}
	
	public double AverageZ() {
		double avg=0;
		for(MyPoint p:this.points) {
			avg+=p.z3d;
		}
		return avg/this.points.length;
	}
	
	public void setColor(Color color) {
		this.color=color;
	}
	
	private void updateLightingVector(MyVector lightVector) {
		MyVector v1=new MyVector(this.points[0],this.points[1]);
		MyVector v2=new MyVector(this.points[1],this.points[2]);
		MyVector normal=MyVector.normalize(MyVector.cross(v1, v2));
		double dot=MyVector.dot(normal, lightVector);
		double sign=dot<0 ?-1:1;
		dot=sign*dot*dot;
		dot=(dot+1)/2*0.9;
		double lightRatio=Math.min(Math.max(this.AmbientLight+dot,0),1);
		this.updateLightingColor(lightRatio);
		
	}
	
	private void updateLightingColor(double lightRatio) {
		int red=(int) (this.color.getRed()*lightRatio);
		int blue=(int) (this.color.getBlue()*lightRatio);
		int green=(int) (this.color.getGreen()*lightRatio);
		this.lightColor=new Color(red,green,blue);
	}

}
