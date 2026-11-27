package exercise8_7;

import exercise8_6.Show;

/**
 * Dummy removal observer: pulls the shows and prints how many are left.
 */
public class ConcreteObserver3 implements ProgramRemovalObserver {

    @Override
    public void showRemoved(Iterable<Show> pShows) {
        int count = 0;
        for (Show show : pShows) {
            if (show.runningTime() > 0) {
                count++;
            }
        }
        System.out.println("Observer3: show removed, " + count + " show(s) left");
    }

    @Override
    public void programCleared() {
        System.out.println("Observer3: program cleared");
    }
}