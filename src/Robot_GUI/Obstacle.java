package Robot_GUI;

/** Class that is like Robot class but for obstacles instead.
 */

public class Obstacle {
    private double dx, dy;
    private int robotID;
    private Direction.selectDirection facing;
    public static int robotCount = 1;
    private String name;
    

    //robot constructor
    public Obstacle(int x, int y) {
        dx = x;
        dy = y;
        robotID = robotCount++;
    }
    
    public double getX() {
        return dx;
    }
    public double getY() {
        return dy;
    }
    
    public String getName() {
        return name;
    }

    public String toString() {
        return "robot " + robotID + " at " + Math.round(dx) + "," + Math.round(dy) + " facing " + facing + ".";
    }
}
