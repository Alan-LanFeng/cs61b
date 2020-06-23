public class NBody {

	public static String imageToDraw = "images/starfield.jpg";

	public static double readRadius(String filename){
		In in = new In(filename);
		int num = in.readInt();
		return in.readDouble();
	}

	public static Body[] readBodies(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();

		Body[] bodies = new Body[num];
		for (int i=0;i<num;i++)
		{
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xP, yP, xV, yV, m, img);
	    }
	    return bodies;
	}

	public static void main(String args[]){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		Body[] bodies = readBodies(fileName);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);

		for(Body planet:bodies){
			planet.draw();
		}
		double[] xForces= new double[5];
		double[] yForces= new double[5];
		double time = 0;
		while(time<T)
		{
			for(int i=0;i<5;i++){
				xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
				yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
			}

			for(int i=0;i<5;i++){
				bodies[i].update(dt,xForces[i],yForces[i]);
			}

			StdDraw.picture(0, 0, imageToDraw);

			for(Body planet:bodies){
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time+=dt;
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}

	}

}