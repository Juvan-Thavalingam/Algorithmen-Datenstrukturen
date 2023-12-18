package ch.zhaw.ads;

public class HilbertServer implements CommandExecutor{

    private void hilbert(Turtle turtle, int depth, double dist, double angle) {
        if (depth >= 0) {
            turtle.turn(-angle);
            hilbert(turtle, depth-1, dist, -angle);
            turtle.move(dist);
            turtle.turn(angle);
            hilbert(turtle, depth-1, dist, angle);
            turtle.move(dist);
            hilbert(turtle, depth-1, dist, angle);
            turtle.turn(angle);
            turtle.move(dist);
            hilbert(turtle, depth-1, dist, -angle);
            turtle.turn(-angle);
        }
    }

    @Override
    public String execute(String command) {
        int depth = Integer.parseInt(command);
        double dist = 0.8 / (Math.pow(2,depth+1)-1);
        Turtle turtle = new Turtle(0.1,0.1);
        hilbert(turtle, depth, dist, -90);

        return turtle.getTrace();
    }
}
