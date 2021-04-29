package point;

import java.awt.Point;

import display.Display;

public class PointConverter {
	
	private static double scale=1;
	private static final double zoomFactor=1.2;
	private static double camaraDist=15;
	
	public static Point convertPoint(MyPoint point) {
		
		double x3d=point.x3d*scale;
		double y3d=point.y3d*scale;
		double depth=point.z3d*scale;
		
		double[] newVal=Scale(x3d,y3d,depth);
		
		int x2d=(int) (Display.WIDTH/2 + newVal[0]);
		int y2d=(int) (Display.HEIGHT/2 - newVal[1]);
		return new Point(x2d,y2d);
	}
	
	public static void zoomIn() {
		scale*=zoomFactor;
	}
	
	public static void zoomOut() {
		scale/=zoomFactor;
	}
	
	private static double[] Scale(double x3d,double y3d,double depth) {
		double dist=Math.sqrt(x3d*x3d+y3d*y3d);
		double theta=Math.atan2(y3d,x3d);
		double depth2=camaraDist-depth;
		double localScale=Math.abs(1400/(depth2+1400));
		dist*=localScale;
		double[] newVal=new double[2];
		newVal[0]=dist*Math.cos(theta);
		newVal[1]=dist*Math.sin(theta);
		return newVal;
	}
	
	public static void rotateZ(MyPoint p,double degree,boolean CW) {
		double radius=Math.sqrt(p.x3d*p.x3d+p.y3d*p.y3d);
		double theta=Math.atan2(p.y3d,p.x3d);
		theta+=2*(Math.PI/360)*degree*(CW ? -1:1);
		p.x3d=radius*Math.cos(theta);
		p.y3d=radius*Math.sin(theta);
	}
	
	public static void rotateX(MyPoint p,double degree,boolean CW) {
		double radius=Math.sqrt(p.z3d*p.z3d+p.y3d*p.y3d);
		double theta=Math.atan2(p.y3d,p.z3d);
		theta+=2*(Math.PI/360)*degree*(CW ? -1:1);
		p.y3d=radius*Math.sin(theta);
		p.z3d=radius*Math.cos(theta);
	}
	
	public static void rotateY(MyPoint p,double degree,boolean CW) {
		double radius=Math.sqrt(p.x3d*p.x3d+p.z3d*p.z3d);
		double theta=Math.atan2(p.x3d,p.z3d);
		theta+=2*(Math.PI/360)*degree*(CW ? -1:1);
		p.z3d=radius*Math.cos(theta);
		p.x3d=radius*Math.sin(theta);
	}

}
