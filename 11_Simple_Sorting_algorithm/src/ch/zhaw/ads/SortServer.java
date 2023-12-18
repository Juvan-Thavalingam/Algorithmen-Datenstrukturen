package ch.zhaw.ads;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SortServer implements CommandExecutor {
    private final int DATARANGE = 10000000;
    public int dataElems; // number of data

    public void swap(int[] a, int i, int j) {
        int h = a[i];
        a[i] = a[j];
        a[j] = h;
    }

    public void bubbleSort(int[] a) {
        // Implement Aufgabe 1
        while (!isSorted(a)){
            for (int i = 0; i < a.length-1; i++){
                if (a[i] > a[i+1])swap(a, i, i+1);
            }
        }
    }

    public void insertionSort(int[] a) {
        // Implement Aufgabe 3
        for (int i = 0; i < a.length; i++){
            int x = a[i];
            int k = i;
            while (k > 0 && x < a[k-1]){
                swap(a, k, k-1);
                k--;
            }
        }
    }

    /**
     * Suche jeweils das kleinste der verbleibenden Elemente und ordne es am Ende der bereits sortierten Elemente ein.
     * • In einem Array a mit dem Indexbereich 0..h sei k die Position des ersten Elements im noch nicht sortierten Bereich und i die Position des kleinsten Elementes in diesem Bereich.
     * • Wenn wir nun a[k] und a[i] vertauschen, dann haben wir den sortierten Bereich um ein Element vergrössert.
     * • Wenn wir diesen Vorgang so lange wiederholen, bis k = a.length gilt, ist das ganze Array sortiert.
     */
    public void selectionSort(int[] a) {
        // Implement Aufgabe 3
        for (int x = 0; x < a.length; x++){
            int pos = x;
            for (int i = x; i < a.length; i++){
                if (a[i] < a[pos])pos = i;
            }
            swap(a, pos, x);
        }
    }

    public void streamSort(int[] a) {
        // zum Vergleichen (falls Sie Zeit und Lust haben)
        int[] b = Arrays.stream(a).sorted().toArray();
        System.arraycopy(b, 0, a, 0, a.length);
    }

    public boolean isSorted(int[] a) {
        // Implement Aufgabe 1
        for (int i = 0; i < a.length-1; i++){
            if (a[i] > a[i+1]){
                return false;
            }
        }
        return true;
    }

    public int[] randomData() {
        int[] a = new int[dataElems];
        // Implement Aufgabe 1
        for (int i = 0; i < a.length; i++){
            a[i] = new Random().nextInt(10000)+1;
        }
        return a;
    }

    public int[] ascendingData() {
        int[] a = new int[dataElems];
        // Implement Aufgabe 1
        for (int i = 0; i < a.length-1; i++){
            if (a[i] > a[i+1]){
                swap(a, i, i+1);
                i = 0;
            }
        }
        return a;
    }

    public int[] descendingData() {
        int[] a = new int[dataElems];
        // Implement Aufgabe 1
        for (int i = 0; i < a.length-1; i++){
            if (a[i] < a[i+1]){
                swap(a, i, i+1);
                i = 0;
            }
        }
        return a;
    }

    // measure time of sorting algorithm
    // generator to generate the data
    // consumer sorts the data
    // return elapsed time in ms
    // if data is not sorted an exception is thrown
    public double measureTime(Supplier<int[]> generator, Consumer<int[]> sorter) throws Exception {
        double elapsed = 0;

        int[] a = generator.get();
        int[] b = new int[dataElems];

        long startTime = System.currentTimeMillis();
        long endTime = startTime;

        // Implement Aufgabe 1 und 2 (Tipp: siehe Consumer für Aufruf von Sortiermethode)

        int count = 0;
        while (endTime < startTime + 1000) {
            System.arraycopy(a, 0,b,0,dataElems);
            sorter.accept(b);
            count++;
            endTime = System.currentTimeMillis();
        }

        elapsed = (double)((endTime - startTime)/count);
        if (!isSorted(b)) throw new Exception ("ERROR not sorted");
        return elapsed;
    }

    public String execute(String arg)  {
        Map<String, Consumer<int[]>> sorter = Map.of(
                "BUBBLE", this::bubbleSort,
                "INSERTION", this::insertionSort,
                "SELECTION", this::selectionSort,
                "STREAM", this::streamSort
        );
        Map<String, Supplier<int[]>> generator =  Map.of(
                "RANDOM", this::randomData,
                "ASC", this::ascendingData,
                "DESC", this::descendingData
        );

        String[] args = arg.toUpperCase().split(" ");
        dataElems = Integer.parseInt(args[2]);
        try {
            double time = measureTime(generator.get(args[1]), sorter.get(args[0]));
            return arg + " " + time + " ms\n";
        } catch (Exception ex) {
            return arg + " " + ex.getMessage();
        }
    }

    public static void main(String[] args) {
        SortServer sorter = new SortServer();
        String sort;
        sort = "BUBBLE RANDOM 10000"; System.out.println(sorter.execute(sort));
        sort = "SELECTION RANDOM 10000"; System.out.println(sorter.execute(sort));
        sort = "INSERTION RANDOM 10000"; System.out.println(sorter.execute(sort));

        sort = "BUBBLE ASC 10000"; System.out.println(sorter.execute(sort));
        sort = "SELECTION ASC 10000"; System.out.println(sorter.execute(sort));
        sort = "INSERTION ASC 10000"; System.out.println(sorter.execute(sort));
    }
}
