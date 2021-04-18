// proj0

public class Body {
  // properties //
  public double xxPos; // x position
  public double yyPos;
  public double xxVel; // x velocity
  public double yyVel;
  public double mass; // mass
  public String imgFileName; // img that depict the body
  public static double G = 6.67e-11;

  // constructors //
  public Body(double xP, double yP, double xV,
              double yV, double m, String img) { // normal
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public Body(Body b) { // copy
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  // Physics Helper //
  public double calcDistance(Body b) {
    double dx = this.xxPos - b.xxPos;
    double dy = this.yyPos - b.yyPos;
    double res = Math.sqrt(dx*dx + dy*dy);
    return res;
  }
  public double calcForceExertedBy(Body b) {
    double mass1 = this.mass;
    double mass2 = b.mass;
    double distance = this.calcDistance(b);
    double res = (Body.G*mass1*mass2) / distance / distance;
    return res;
  }
  public double calcForceExertedByX(Body b) {
    double F = calcForceExertedBy(b);
    double dr = this.calcDistance(b);
    double dx = b.xxPos - this.xxPos;
    double res = F * dx / dr;
    return res;
  }
  public double calcForceExertedByY(Body b) {
    double F = calcForceExertedBy(b);
    double dr = this.calcDistance(b);
    double dy = b.yyPos - this.yyPos;
    double res = F * dy / dr;
    return res;
  }
  public double calcNetForceExertedByX(Body[] bodys) {
    double res = 0;
    for (Body currBody : bodys) {
      if (!currBody.equals(this)) {
        double tmpF = this.calcForceExertedByX(currBody);
        res += tmpF;
      }
    }
    return res;
  }
  public double calcNetForceExertedByY(Body[] bodys) {
    double res = 0;
    for (Body currBody : bodys) {
      if (!currBody.equals(this)) {
        double tmpF = this.calcForceExertedByY(currBody);
        res += tmpF;
      }
    }
    return res;
  }
  public void update(double dt, double Fx, double Fy) {
    double xAcc = Fx / this.mass;
    double yAcc = Fy / this.mass;
    double xVelNew = this.xxVel + xAcc*dt;
    double yVelNew = this.yyVel + yAcc*dt;
    double xPosNew = this.xxPos + xVelNew*dt;
    double yPosNew = this.yyPos + yVelNew*dt;
    // update
    this.xxVel = xVelNew;
    this.yyVel = yVelNew;
    this.xxPos = xPosNew;
    this.yyPos = yPosNew;
  }
  public void draw() {
    //StdDraw.enableDoubleBuffering();
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    //StdDraw.show();
    //StdDraw.pause(2000);
  }
}
