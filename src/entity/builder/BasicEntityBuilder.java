package entity.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import entity.IEntity;
import point.MyPoint;
import shapes.MyPolygon;
import shapes.MyPolyhedron;

public class BasicEntityBuilder {
	
	public static IEntity createCube(int size,double x3d,double y3d,double z3d) {
		
		MyPoint p1=new MyPoint(x3d-size/2,y3d-size/2,z3d-size/2);
		MyPoint p2=new MyPoint(x3d+size/2,y3d-size/2,z3d-size/2);
		MyPoint p3=new MyPoint(x3d+size/2,y3d+size/2,z3d-size/2);
		MyPoint p4=new MyPoint(x3d-size/2,y3d+size/2,z3d-size/2);
		MyPoint p5=new MyPoint(x3d-size/2,y3d-size/2,z3d+size/2);
		MyPoint p6=new MyPoint(x3d+size/2,y3d-size/2,z3d+size/2);
		MyPoint p7=new MyPoint(x3d+size/2,y3d+size/2,z3d+size/2);
		MyPoint p8=new MyPoint(x3d-size/2,y3d+size/2,z3d+size/2);
		
		MyPolyhedron poly=new MyPolyhedron(
				new MyPolygon(Color.cyan,p5,p6,p7,p8),
				new MyPolygon(Color.cyan,p1,p4,p3,p2),
				new MyPolygon(Color.cyan,p1,p5,p8,p4),
				new MyPolygon(Color.cyan,p1,p2,p6,p5),
				new MyPolygon(Color.cyan,p2,p3,p7,p6),
				new MyPolygon(Color.cyan,p3,p4,p8,p7));
		List<MyPolyhedron> polylist=new ArrayList<>();
		polylist.add(poly);
		
		return new Entity(polylist);
		
	}
	
	public static IEntity createDiamond(double size,int edges,double x3d,double y3d,double z3d) {
		
		//This method is NOT completed fully...
		double inFactor=0.8;
		MyPoint bottom=new MyPoint(x3d,y3d-(size/2),z3d);
		MyPoint[] outerPoints=new MyPoint[edges];
		MyPoint[] innerPoints=new MyPoint[edges];
		
		for(int i=0;i<edges;i++) {
			double theta=2*Math.PI/edges*i;
			double xpos=(size/2)*Math.cos(theta);
			double zpos=-((size/2)*Math.sin(theta));
			double ypos=size/2;
			outerPoints[i]=new MyPoint(x3d+xpos,y3d+ypos*inFactor,z3d+zpos);
			innerPoints[i]=new MyPoint(x3d+xpos*inFactor,y3d+ypos,z3d+zpos*inFactor);
		}
		
		MyPolygon[] polygon=new MyPolygon[(2*edges)+1];
		
		for(int i=0;i<edges;i++) {
			if(i==0) {
				polygon[i]=new MyPolygon(Color.CYAN,outerPoints[i],outerPoints[(i+1)%edges],bottom);
				polygon[i+edges]=new MyPolygon(Color.MAGENTA,outerPoints[i],outerPoints[(i+1)%edges],innerPoints[(i+1)%edges],innerPoints[i]);
			}else {
				polygon[i]=new MyPolygon(Color.GREEN,outerPoints[i],outerPoints[(i+1)%edges],bottom);
				polygon[i+edges]=new MyPolygon(outerPoints[i],outerPoints[(i+1)%edges],innerPoints[(i+1)%edges],innerPoints[i]);
			}
		}
		
		polygon[2*edges]=new MyPolygon(Color.RED,innerPoints);
		
		List<MyPolyhedron> polylist=new ArrayList<>();
		polylist.add(new MyPolyhedron(polygon));
		
		return new Entity(polylist);
		
	}

}
