package exercise8_6;

import exercise8_6.ObservableProgram.Day;

/**
 * Dummy concrete observer used to test the application of the pattern. It
 * simply prints the notifications that it receives.
 */
public class ConcreteProgramObserver implements ProgramObserver {

    @Override
    public void showAdded(Show pShow, Day pDay) {
        System.out.println("Show added on " + pDay + ": " + pShow.description());
    }

    @Override
    public void showRemoved(Show pShow, Day pDay) {
        System.out.println("Show removed from " + pDay + ": \"" + pShow.description() + "\"");
    }

    @Override
    public void programCleared() {
        System.out.println("Program cleared");
    }
}