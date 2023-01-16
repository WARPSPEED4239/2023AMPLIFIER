package frc.robot.tools;

public class RobotMath {

    public static double solveLinearEquationForY(double A, double constant, double X) {
        double Y = 0;
        Y = (A * X) + constant;
        return Y;
    }

    public static double solveQuadraticEquationForY(double A, double B, double constant, double X) {
        double Y = 0;
        Y = (A * Math.pow(X, 2.0)) + (B * X) + constant;
        return Y;
    }

    public static double solveCubicEquationForY(double A, double B, double C, double constant, double X) {
        double Y = 0;
        Y = (A * Math.pow(X, 3.0)) + (B * Math.pow(X, 2.0)) + (C * X) + constant;
        return Y;
    }
}
