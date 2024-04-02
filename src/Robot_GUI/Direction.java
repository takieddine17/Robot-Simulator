package Robot_GUI;

/**
 * Direction dictates the robots direction, including which way the robot should
 * go next if it collides with an obstacle or the arena wall.
 */

import java.util.Random;

public class Direction {

    /**
     * selectDirection sets all the possible directions that the robot can take.
     */

    public enum selectDirection {
        North,
        NorthNorthEast,
        NorthEast,
        EastNorthEast,
        NorthNorthWest,
        NorthWest,
        WestNorthWest,
        South,
        EastSouthEast,
        SouthEast,
        SouthSouthEast,
        WestSouthWest,
        SouthWest,
        SouthSouthWest,
        East,
        West;

        public static selectDirection getRandomDirection() { // getRandomDirection selects the next random direction from selectDirection
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
        /**
         * getNextDirection checks if robot in direction given and if it can't, the robot will change to the
         * next appropriate direction.
         */

        public selectDirection getNextDirection(selectDirection n) {
            if (n == selectDirection.North) {
                return selectDirection.NorthNorthEast;
            }
            if (n == selectDirection.NorthNorthEast) {
                return selectDirection.NorthEast;
            }
            if (n == selectDirection.NorthEast) {
                return selectDirection.EastNorthEast;
            }
            if (n == selectDirection.WestNorthWest) {
                return selectDirection.NorthWest;
            }
            if (n == selectDirection.NorthWest) {
                return selectDirection.NorthNorthWest;
            }
            if (n == selectDirection.NorthNorthWest) {
                return selectDirection.North;
            }
            if (n == selectDirection.EastNorthEast) {
                return selectDirection.East;
            }
            if (n == selectDirection.East) {
                return selectDirection.EastSouthEast;
            }
            if (n == selectDirection.EastSouthEast) {
                return selectDirection.SouthEast;
            }
            if (n == selectDirection.South) {
                return selectDirection.SouthSouthWest;
            }
            if (n == selectDirection.SouthEast) {
                return selectDirection.SouthSouthEast;
            }
            if (n == selectDirection.SouthSouthEast) {
                return selectDirection.South;
            }
            if (n == selectDirection.SouthSouthWest) {
                return selectDirection.SouthWest;
            }
            if (n == selectDirection.SouthWest) {
                return selectDirection.WestSouthWest;
            }
            if (n == selectDirection.WestSouthWest) {
                return selectDirection.West;
            }
            if (n == selectDirection.West) {
                return selectDirection.WestNorthWest;
            }
            return selectDirection.North;
        }
    }
}
