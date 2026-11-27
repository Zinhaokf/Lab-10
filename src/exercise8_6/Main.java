package exercise8_6;

import exercise8_6.ObservableProgram.Day;

public class Main {
    public static void main(String[] args) {
        ObservableProgram program = new ObservableProgram();
        program.addObserver(new ConcreteProgramObserver());

        program.add(new Movie("Casablanca", 1942, 102), Day.MONDAY);
        program.add(new Concert("Symphony No. 5", "Vienna Philharmonic", 75), Day.FRIDAY);
        program.remove(Day.MONDAY);
        program.clear();

        System.out.println(program);
    }
}