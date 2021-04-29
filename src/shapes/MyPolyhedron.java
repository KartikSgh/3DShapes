package shapes;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Comparator;

import point.MyVector;


public class MyPolyhedron{
	
	private MyPolygon[] polys;
	
	public MyPolyhedron(MyPolygon... poly) {
		this.polys=new MyPolygon[poly.length];
		for(int i=0;i<poly.length;i++) {
			this.polys[i]=poly[i];
		}
	}
	
	public void rotate(boolean CW,double xDegree,double yDegree,double zDegree,MyVector lightVector) {
		for(MyPolygon p : this.polys) {
			p.rotate(CW, xDegree, yDegree, zDegree,lightVector);
		}
	}
	
	public void render(Graphics g) {
		sortPolyhedron();
		for(int i=0;i<this.polys.length;i++) {
			polys[i].render(g);
		}
	}
	
	private void sortPolyhedron() {
		Arrays.sort(this.polys, new Comparator<MyPolygon>() {

			@Override
			public int compare(MyPolygon p1, MyPolygon p2) {
				double diff=p1.AverageZ()-p2.AverageZ();
				if(diff>0) {
					return 1;
				}else {
					if(diff<0) {
						return -1;
					}else {
						return 0;
					}
				}
			}		
			
		});
	}
}
