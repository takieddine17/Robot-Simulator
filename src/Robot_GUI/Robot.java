package Robot_GUI;

import Robot_GUI.Direction.selectDirection;

/**
 * Class for the robot and getter functions with a function to check if it can move 
 */

public class Robot {
    private double dx, dy;
    private int robotID;
    private Direction.selectDirection facing;
    public static int robotCount = 1;
    private String name;
    

    //robot constructor
    public Robot(int x, int y, Direction.selectDirection f) {
        dx = x;
        dy = y;
        robotID = robotCount++;
        facing = f;
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
    
    public selectDirection getDirection() {
        return facing;
    }

    public String toString() {
        return "robot " + robotID + " at " + Math.round(dx) + "," + Math.round(dy) + " facing " + facing + ".";
    }
    
    /**
     * function that dictates a robots movement dependant on which direction it faces using horizontal and vertical 
     */
    public void tryToMove(RobotArena a) {
        double horizontal = 0, vertical = 0;
        if (facing == Direction.selectDirection.North) {
            horizontal = dx;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.NorthNorthEast) {
            horizontal = dx + 0.5;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.EastNorthEast) {
            horizontal = dx + 1;
            vertical = dy + 0.5;
        } else if (facing == Direction.selectDirection.NorthEast) {
            horizontal = dx + 1;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.NorthNorthWest) {
            horizontal = dx - 0.5;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.WestNorthWest) {
            horizontal = dx - 1;
            vertical = dy + 0.5;
        } else if (facing == Direction.selectDirection.NorthWest) {
            horizontal = dx + 1;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.East) {
            horizontal = dx + 1;
            vertical = dy;
        } else if (facing == Direction.selectDirection.EastSouthEast) {
            horizontal = dx + 1;
            vertical = dy - 0.5;
        } else if (facing == Direction.selectDirection.SouthSouthEast) {
            horizontal = dx + 0.5;
            vertical = dy - 1;
        }else if (facing == Direction.selectDirection.South) {
            horizontal = dx;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.SouthEast) {
            horizontal = dx - 1;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.WestSouthWest) {
            horizontal = dx - 1;
            vertical = dy - 0.5;
        } else if (facing == Direction.selectDirection.SouthSouthWest) {
            horizontal = dx - 0.5;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.SouthWest) {
            horizontal = dx - 1;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.West) {
            horizontal = dx - 1;
            vertical = dy;
        }

        boolean go = a.canMoveHere(horizontal, vertical);
        if (go) {
            boolean obstacleCollision = a.checkObstacleCollision(horizontal, vertical); // Check for collision with obstacles
            
            if (!obstacleCollision) {
                this.dx = horizontal;
                this.dy = vertical;
                System.out.println(this.toString());
            } else {  
                facing = facing.getNextDirection(facing);// Bounce off when there is an obstacle
            }
        } else {
            facing = facing.getNextDirection(facing);
        }

    }
}
