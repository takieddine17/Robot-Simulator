package Robot_GUI;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RobotInterface extends Application{
    private int CanvasWidth = 900, CanvasHeight = 600;
    private ArenaCanvas c;
    private static RobotArena Arena;
    private static AnimationTimer time;

    private void showMessage(String TStr, String CStr) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TStr);
        alert.setHeaderText(null);
        alert.setContentText(CStr);
        alert.showAndWait();
    }

    /**
     * Function to show the details about the program.
     */
    private void viewAbout() {
        showMessage("About", "Robot Simulator GUI." + "\n" +
                "31007640." + "\n" +
                "This program shows robots moving in an arena as an animation in JavaFX");
    }

    /**
     * Function to show how to use the simulator.
     */
    private void viewHelp() {
        showMessage("Help", "Click 'Add Robot' to add a robot to the arena. " + "\n" +
                "Click 'Start' for the robots move about in the arena. Click 'Stop' to stop the robots moving." + "\n" +
        		"Click 'Add Obstacle' to add a stationary circle in the arena.");
    }

    /**
     * Function to set up the menu
     */
    public MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();

        Menu Info = new Menu("Information");

        MenuItem About = new MenuItem("About");
        About.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                viewAbout();				// shows about message
            }
        });
        MenuItem Help = new MenuItem("Help");
        Help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewHelp();				//shows help message
            }
        });
        Info.getItems().addAll(About, Help); 

        Menu File = new Menu("File");	

        MenuItem Exit = new MenuItem("Exit");		// and Exit submenu
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {		
                System.exit(0);						//quit program
            }
        });
        
        File.getItems().addAll(Exit);	//Exit submenu to File

        menuBar.getMenus().addAll(File, Info);

        return menuBar;
    }

    /**
     * Function to add the robots to the list and display the info on their initial positions
     * when first placed, which updates when robots are added.
     */
    public static void listRobots(ListView<Robot> robots){
        robots.getItems().clear(); //clear the list view
        //checks over the robots in the list and adds them to the list view
        for (Robot d : Arena.numRobot) {
        	robots.getItems().add(d);
        }    
    }
    
    public static void listObstacle(ListView<Obstacle> Obstacles){
     
        Obstacles.getItems().clear(); //clear the list view
        
        
        for (Obstacle d : Arena.numObstacles) { //adds robots to the list view
        	Obstacles.getItems().add(d);
        }    
    }

    /**
     * Creates the stage for the app, so that all the features will be displayed on
     * the window.
     */
    public void start(Stage stagePrimary) throws Exception  {
        stagePrimary.setTitle("Robot Simulator");


        Group root = new Group(); //canvas details
        Canvas canvas = new Canvas(900, 500);
        root.getChildren().add(canvas);

        c = new ArenaCanvas(canvas.getGraphicsContext2D(), CanvasWidth, CanvasHeight); //creating canvas
        Arena = new RobotArena(900, 500);

        //timer used to start and stop the movements of all robots added
        
        time = new AnimationTimer() {
            public void handle(long now) {
                Arena.moveAllRobots(c);

            }
        };

        //List View robot contains variables 'robotlist'
        ListView<Robot> robotlist = new ListView<>();
        robotlist.setStyle(
                "-fx-border-color: #000000;" +
                "-fx-border-width: 2px;");
        
        ListView<Obstacle> obstaclelist = new ListView<>();
        obstaclelist.setStyle(
                "-fx-border-color: #00ff6a;" +
                "-fx-border-width: 2px;");
        

        //RList positioning and formatting are determined here, with a title on top for the list.

        VBox RList = new VBox();
        RList.getChildren().addAll(robotlist);
        RList.setAlignment(Pos.CENTER);
        RList.setPadding(new Insets(30, 50, 30, 50));

        //formatted button to add a robot in a random place in the arena.
        Button AddRobotButton = new Button("Add Robot");
        AddRobotButton.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        AddRobotButton.setTextFill(Color.BLUE);
        AddRobotButton.setOnAction(e -> {
        	Arena.addRobot(c, robotlist);
        });

        //Determining button size
        AddRobotButton.setMinSize(200, 60);
        AddRobotButton.setMaxSize(125, 60);


        //start button to start animation
        Button MoveRobotsButton = new Button("Start");
        MoveRobotsButton.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        MoveRobotsButton.setTextFill(Color.GREEN);
        //starts timer and calls the moveAllrobots method to move robots
        MoveRobotsButton.setOnAction(e -> time.start());

        MoveRobotsButton.setMinSize(135, 60);
        MoveRobotsButton.setMaxSize(125, 60);
        //viewStop.setFitHeight(40);
        //viewStop.setFitWidth(40);

        //stop button to stop animation
        Button StopRobotsButton = new Button("Stop");
        StopRobotsButton.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        StopRobotsButton.setTextFill(Color.RED);

        //stops timer and calls the moveAllrobots method to stop all robot movement
        StopRobotsButton.setOnAction(e -> time.stop());

        StopRobotsButton.setMinSize(125, 60);
        StopRobotsButton.setMaxSize(125, 60);


        //removes a random robot in the arena
        Button RemoveRobotButton = new Button("Remove Robot");
        RemoveRobotButton.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        RemoveRobotButton.setTextFill(Color.ORANGE);

        RemoveRobotButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                Arena.deleteRandomRobot(c, robotlist);
            }
        });
        
        RemoveRobotButton.setMinSize(225, 60);
        RemoveRobotButton.setMaxSize(125, 60);
        
        Button AddObstacleButton = new Button("Add Obstacle");
        AddObstacleButton.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        AddObstacleButton.setTextFill(Color.PURPLE);
        
        AddObstacleButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
            	Arena.addObstacle(c, obstaclelist);
            }
        });
        
        AddObstacleButton.setMinSize(225, 60);
        AddObstacleButton.setMaxSize(125, 60);
       
        HBox hbButtons = new HBox(20);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);
        hbButtons.setPadding(new Insets(0, 160, 50, 0));

        //adds all buttons to the console
        hbButtons.getChildren().addAll(AddRobotButton, MoveRobotsButton, StopRobotsButton, RemoveRobotButton, AddObstacleButton);

        //creates borderpane

        BorderPane bp = new BorderPane(); //formats borderpane adding items across the screen
        bp.setTop(setMenu());
        bp.setCenter(root);
        bp.setBottom(hbButtons);
        bp.setRight(RList);
        

        //sets the app scene
        Scene scene = new Scene(bp, 1300, 800);

        stagePrimary.setScene(scene); // Put the scene in the window
        stagePrimary.show();
    }

    /**
     * Launches the application.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
