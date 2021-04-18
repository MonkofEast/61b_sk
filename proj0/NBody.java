// NBody.java

public class NBody {
  /* Helpers */
  public static double readRadius(String fileName) {
    In in = new In(fileName);
    int nplants = in.readInt();
    double radius = in.readDouble();
    return radius;
  }
  public static Body[] readbodys(String fileName) {
    In in = new In(fileName);
    int nplants = in.readInt();
    double trash2 = in.readDouble();

    Body[] bodys = new Body[nplants];
    for (int i = 1; i <= nplants; i += 1) {
      double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      bodys[i-1]  = new Body(xPos, yPos, xVel, yVel, mass, imgFileName);
    }
    return bodys;
  }
  // main //
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Body[] bodys = readbodys(filename);
    // draw initial canvas //
    String img = "images/starfield.jpg";
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.picture(0,0, img);
    //StdDraw.pause(2000);
    for (Body currBody : bodys) {
      currBody.draw();
      //;
      //System.out.println(currBody.imgFileName);
      //;
    }
    StdDraw.show();
    // animation //
    int nplanets = bodys.length;
    double[] xForces = new double[nplanets];
    double[] yForces = new double[nplanets];
    for (double time = 0; time <= T; time += dt) {
      for (int i = 0; i < nplanets; i += 1) {
        xForces[i] = bodys[i].calcNetForceExertedByX(bodys);
        yForces[i] = bodys[i].calcNetForceExertedByY(bodys);
      }
      for (int i = 0; i < nplanets; i += 1) {
        bodys[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.picture(0,0, img);
      for (Body currBody : bodys) {
        currBody.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
    }
    // print out data when it's over //
    StdOut.printf("%d\n", bodys.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodys.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodys[i].xxPos, bodys[i].yyPos, bodys[i].xxVel,
                  bodys[i].yyVel, bodys[i].mass, bodys[i].imgFileName);
    }
  }
}
