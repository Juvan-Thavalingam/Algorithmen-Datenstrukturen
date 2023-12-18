package ch.zhaw.ads;

public class SnowflakeServer implements CommandExecutor{


    void snowflake(Turtle turtle,int stufe, double dist) {
        if (stufe == 0) {
            turtle.move(dist);
        } else {
            stufe --;
            dist = dist / 3;
            snowflake(turtle, stufe, dist);
            turtle.turn(60);
            snowflake(turtle, stufe, dist);
            turtle.turn(-120);
            snowflake(turtle, stufe, dist);
            turtle.turn(60);
            snowflake(turtle, stufe, dist);
        }
    }

    @Override
    public String execute(String command) {
        Turtle turtle = new Turtle(0.1,0.7);
        for (int i = 0; i < 3; i++){
            snowflake(turtle, Integer.parseInt(command), 0.7);
            turtle.turn(-120);
        }

        return turtle.getTrace();
    }
}
