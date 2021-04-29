package point;

public class MyVector {
	public double x,y,z;
	
	public MyVector(double x,double y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public MyVector() {
		this.x=this.y=this.z=0;
	}
	
	public MyVector(MyPoint p1,MyPoint p2) {
		this.x=p2.x3d-p1.x3d;
		this.y=p2.y3d-p1.y3d;
		this.z=p2.z3d-p1.z3d;
	}
	
	public static double dot(MyVector v1,MyVector v2) {
		return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
	}
	
	public static MyVector cross(MyVector v1,MyVector v2) {
		return new MyVector(v1.y*v2.z-v1.z*v2.y,
				v1.z*v2.x-v1.x*v2.z,
				v1.x*v2.y-v1.y*v2.x);
	}
	
	public static MyVector normalize(MyVector v) {
		double magnitude=Math.sqrt(v.x*v.x+v.y*v.y+v.z*v.z);
		return new MyVector(v.x/magnitude,v.y/magnitude,v.z/magnitude);
	}
}
