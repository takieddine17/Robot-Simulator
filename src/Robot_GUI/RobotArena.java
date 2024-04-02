package Robot_GUI;

/**
 * RobotArena creates the arena for the robots to be placed and animated on.
 */

import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Random;

public class RobotArena {
    private int arenaWidth;
    private int arenaHeight;
    private int posX, posY;
    Random randomCoords; // Random robot coordinates
    public ArrayList<Robot> numRobot; // Array list type robot that will include list all the robots that are added
    public int numRobotArena; // robot counter when adding robots
    public ArrayList<Obstacle> numObstacles;

    /**
     *  Initialises robot arena by assigning attributes like the x coordinate, y coordinate and random coordinates
     */
    public RobotArena(int x, int y){
        arenaWidth = x;
        arenaHeight = y;
        randomCoords = new Random();
        numRobot = new ArrayList<Robot>();
        numObstacles = new ArrayList<>();
    }

    /** robots added to the arena list, which is also added to ListView in RobotInterface. 
     */
    public void addRobot(ArenaCanvas mc, ListView<Robot> robotlist) {
        boolean CanbeAdded = true; 
        while(CanbeAdded) {
            posX = randomCoords.nextInt(arenaWidth);
            posY = randomCoords.nextInt(arenaHeight);
            if (posX > 0 && posX < arenaWidth - 20 && posY > 0 && posY < arenaHeight - 25 && !isHere(posX, posY)) {
                Direction.selectDirection x = Direction.selectDirection.getRandomDirection();
                numRobot.add(new Robot(posX, posY, x));
                numRobotArena++;
                CanbeAdded = false;
            }
            // new robot with unique X & Y and random direction
            mc.createRobot(this);
            RobotInterface.listRobots(robotlist);
        }
    }
    
    public void addObstacle(ArenaCanvas mc, ListView<Obstacle> obstaclelist) {
        boolean CanbeAdded = true; //function to add an obstacle to the arena when button pressed
        while(CanbeAdded) {
            posX = randomCoords.nextInt(arenaWidth);
            posY = randomCoords.nextInt(arenaHeight);
            if (posX > 0 && posX < arenaWidth - 20 && posY > 0 && posY < arenaHeight - 25 && !isHere(posX, posY)) {
                numObstacles.add(new Obstacle(posX, posY));
                numRobotArena++;
                CanbeAdded = false;
            }
            // new obstacle with unique X & Y
            mc.createObstacle(this);
            RobotInterface.listObstacle(obstaclelist);
        }
    }
    
    public void deleteRandomRobot(ArenaCanvas mc, ListView<Robot> robotlist) {
        if (!numRobot.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(numRobot.size());
            numRobot.remove(randomIndex);
            System.out.println("Deleted a random robot from the arena.");
        } else {
            System.out.println("No robots in the arena to delete.");
        }
    }
    
    // Moves all the robots in the arena
    public void moveAllRobots(ArenaCanvas mc){
        for(Robot d: numRobot){
            d.tryToMove(this) ;
        }
        mc.changeCanvas(this);
    }

    /** Checks if robot can move to given coordinates and checks if robot position might be out
   		be out of arena boundaries 
    */
    public boolean canMoveHere(double x, double y){
        return !isHere(x, y) && x > 0 && y > 0 && x < arenaWidth - 20 && y < arenaHeight - 25;
    }
    
    public boolean checkObstacleCollision(double x, double y) {
        for (Obstacle obstacle : numObstacles) {
            double obstacleX = obstacle.getX();
            double obstacleY = obstacle.getY();
            double obstacleRadius = ArenaCanvas.OB_RADIUS;

            // Check if the robot collides with the obstacle
            if (Math.sqrt(Math.pow(x - obstacleX, 2) + Math.pow(y - obstacleY, 2)) < obstacleRadius + ArenaCanvas.ROBOT_RADIUS) {
                System.out.println("Collision with obstacle!");
                return true;
            }
        }
        return false;
    }

    /**
     * Compares parameters with existing robots and sees if a robot occupies that position
     * if the robot collides with the wall or obstacle, the robot will change direction.
     */

    public boolean isHere (double lengthwise, double crosswise) {
        int a = 0, b = 0;
        for(Robot d: numRobot){
            if(crosswise == d.getY() || crosswise + 30 == d.getY() || crosswise - 30 == d.getY()){
                ++a;
            }
            if(lengthwise == d.getX() || lengthwise + 30 == d.getX() || lengthwise - 30 == d.getX()){
                ++b;
            } else {
                continue;
            }
        }

        if(a > 0 && b > 0){
            System.out.println("Path blocked!");
            return true;
        } else{
            return false;
        }
    }
}
