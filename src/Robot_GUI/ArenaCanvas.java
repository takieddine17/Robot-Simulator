package Robot_GUI;

import Robot_GUI.Direction.selectDirection;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * ArenaCanvas contains info based on robot images and canvas size. 
 */

public class ArenaCanvas extends Application {
    //variables and declarations related to the file
    int canvasWidthSize; // constants for relevant sizes, default values set
    int canvasHeightSize;
    //Images loaded in at the start
    GraphicsContext gc;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int ROBOT_RADIUS = 20;
    public static final int OB_RADIUS = 30;

    /**
     * constructor for canvas
     */
    public ArenaCanvas(GraphicsContext g, int xcs, int ycs) {
        //Sets up the canvas
        gc = g;
        canvasWidthSize = xcs;
        canvasHeightSize = ycs;
    }

    /**
     * Will keep changing canvas to match the robot images position when moving
     * and will recreate the robot image for each canvas change.
     */
    public void changeCanvas(RobotArena Arena){
        //sets formatting and clears canvas
        gc.clearRect(0, 0, canvasWidthSize, canvasHeightSize);
        //draws all robots
        createRobot(Arena);
        createObstacle(Arena);
    }

    /**
     * Draws the robot
     */
    
    public void createRobot(RobotArena Arena) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        for (Robot robot : Arena.numRobot) {

            // Get the current position and direction of the robot
            double robotX = robot.getX();
            double robotY = robot.getY();
            selectDirection direction = robot.getDirection();

            // Map 16-point compass directions to angles
            double angle = ChangeDirectionToAngle(direction);

            // Set color for the robot circle and lines
            gc.setFill(Color.RED);
            gc.setStroke(Color.BLACK);

            // Sets the thickness for the lines
            double lineThickness = 5.0; 
            gc.setLineWidth(lineThickness);

            double lineReduction = 15.0; // Amount to reduce the line length by
            double lineLength = 2 * ROBOT_RADIUS - lineReduction;

            // Rotate the canvas to match the direction
            gc.save();
            gc.translate(robotX, robotY);

            if (angle != 0.0) {
                gc.rotate(angle);
            }

            // Draw the wheels
            double wheelStartYTop = -ROBOT_RADIUS - (lineThickness / 4);
            double wheelStartYBottom = ROBOT_RADIUS + (lineThickness / 2);
            gc.setFill(Color.RED);
            gc.strokeLine(-lineLength / 2, wheelStartYTop, lineLength / 2, wheelStartYTop);
            gc.strokeLine(-lineLength / 2, wheelStartYBottom, lineLength / 2, wheelStartYBottom);

            // Draw the circle (robot body)
            double circleDiameter = ROBOT_RADIUS * 2;
            gc.fillOval(-ROBOT_RADIUS, -ROBOT_RADIUS, circleDiameter, circleDiameter);

            gc.setFill(Color.BLACK);
            gc.fillText(robot.getName(), -ROBOT_RADIUS, -ROBOT_RADIUS - lineThickness - 5);

            gc.restore();
        }
    }
    /**
     * setting directions as degree angles so that when a robot collides, it rotates facing the new direction
     */
    private double ChangeDirectionToAngle(selectDirection direction) {
        switch (direction) {
            case North:
                return 0.0;
            case NorthNorthEast:
                return 22.5;
            case NorthEast:
                return 45.0;
            case EastNorthEast:
                return 67.5;
            case East:
                return 90.0;
            case EastSouthEast:
                return 112.5;
            case SouthEast:
                return 135.0;
            case SouthSouthEast:
                return 157.5;
            case South:
                return 180.0;
            case SouthSouthWest:
                return 202.5;
            case SouthWest:
                return 225.0;
            case WestSouthWest:
                return 247.5;
            case West:
                return 270.0;
            case WestNorthWest:
                return 292.5;
            case NorthWest:
                return 315.0;
            case NorthNorthWest:
                return 337.5;
            default:
                return 0.0;
        }
    }
    
    public void createObstacle(RobotArena Arena) {
	    
    	for (Obstacle obstacle : Arena.numObstacles) {
    		
    		gc.setFill(Color.BLUE);  // Set the fill color
            gc.fillOval(obstacle.getX() - OB_RADIUS, obstacle.getY() - OB_RADIUS, 2 * OB_RADIUS, 2 * OB_RADIUS); 
    	}	
	}

    @Override
    public void start (Stage primaryStage) throws Exception {
    	// TODO Auto-generated method stub

    }
}
