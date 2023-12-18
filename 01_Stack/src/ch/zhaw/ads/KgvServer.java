package ch.zhaw.ads;

public class KgvServer implements CommandExecutor {
    public int kgv(int x, int y) {
        return Math.abs(x*y)/ggt(x,y);
    }

    private static int ggt(int a, int b){
        if (a == 0) return b;
        while (b != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    @Override
    public String execute(String command) throws Exception {
        String[] numbers = command.split("[ ,]+");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        return Integer.toString(kgv(a,b));
    }
}
