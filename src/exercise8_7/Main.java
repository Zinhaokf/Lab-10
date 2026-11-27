package exercise8_7;

import exercise8_7.ObservableProgram.Day;
import exercise8_6.Concert;
import exercise8_6.Movie;

public class Main {
    public static void main(String[] args) {
        ObservableProgram program = new ObservableProgram();
        program.addObserver(new ConcreteObserver1());
        program.addObserver(new ConcreteObserver2());
        program.addObserver(new ConcreteObserver3());

        program.add(new Movie("Casablanca", 1942, 102), Day.MONDAY);
        program.add(new Concert("Symphony No. 5", "Vienna Philharmonic", 75), Day.FRIDAY);
        program.remove(Day.MONDAY);
        program.clear();

        System.out.println(program);
    }
}