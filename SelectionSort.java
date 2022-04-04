package tt3;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;

public class SelectionSort extends Sorts {

    private final ArrayList<Integer> data = new ArrayList<>();
    private final Duration timeElapsed;

    public SelectionSort(int size) {
        super(size);
        Instant start = Instant.now();  // time capture -- start
        // build an array
        for (int i = 0; i < size; i++) {
            data.add((int)(Math.random() * (size+1)));
        }
        // use Inheritance and Polymorphism to replace data.sort with your own algorithm
        int arraySize = data.size();
        for (int i = 0; i < arraySize; i++) {
            int min = data.get(i);
            int index = i;
            for (int j = i; j < arraySize; j++) {
                if (data.get(j) < min) {
                    min = data.get(j);
                    index = j;
                }
            }
            data.set(index, data.get(i));
            data.set(i, min);
        }
        Instant end = Instant.now();    // time capture -- end
        this.timeElapsed = Duration.between(start, end);
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public int getTimeElapsed() {
        return timeElapsed.getNano();
    }


    public static void main(String[] args) {
        int sum=0, time=0, TIMES=12, SIZE=5000;

        for(int i=0; i< TIMES; i++) {
            SelectionSort s = new SelectionSort(SIZE);
            for(int j = 0; j<s.getData().size(); j++) {
                // To see data, uncomment next line
                // System.out.println(s.getData());
                sum += s.getData().get(j);
            }
            //System.out.println("Average random: " + sum / ((i+1)*SIZE));
            System.out.println(s.getTimeElapsed());
            time += s.getTimeElapsed();
        }
        //System.out.println("Average random: " + sum / (TIMES*SIZE));
        System.out.println("Total Nanoseconds: " + time );
        System.out.println("Total Seconds: " + time /1000000000.0);
    }
}

