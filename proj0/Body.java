


public class Body{
	public double xxPos;/*: Its current x position*/
	public double yyPos;/*Its current y position*/
	public double xxVel;/* Its current velocity in the x direction*/
	public double yyVel; /*Its current velocity in the y direction*/
	public double mass; /*Its mass*/
	public String imgFileName;/* The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif)*/
	public static final double G = 6.67e-11;
	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		return Math.sqrt(Math.pow((xxPos-b.xxPos),2)+Math.pow((yyPos-b.yyPos),2));
	}

	public double calcForceExertedBy(Body b){
		return G*mass*b.mass/(Math.pow(this.calcDistance(b),2));
	}

	public double calcForceExertedByX(Body b){
		return this.calcForceExertedBy(b)*(b.xxPos-xxPos)/this.calcDistance(b);
	}

	public double calcForceExertedByY(Body b){
		return this.calcForceExertedBy(b)*(b.yyPos-yyPos)/this.calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] b){
		double sum=0;
		for (Body planet : b){
			if(!planet.equals(this)){
				sum+=this.calcForceExertedByX(planet);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Body[] b){
		double sum=0;
		for (Body planet : b){
			if(!planet.equals(this)){
				sum+=this.calcForceExertedByY(planet);
			}
		}
		return sum;
	}

	public void update(double dt,double fX, double fY)
	{
		xxVel += dt*fX/mass;
		yyVel += dt*fY/mass;
		xxPos += dt*xxVel;
		yyPos += dt*yyVel;
	}	

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}


}