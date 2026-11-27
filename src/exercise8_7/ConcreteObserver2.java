package exercise8_7;

import exercise8_6.Show;

/**
 * Dummy addition observer: pulls the shows and prints the total running time.
 */
public class ConcreteObserver2 implements ProgramAdditionObserver {

    @Override
    public void showAdded(Iterable<Show> pShows) {
        int total = 0;
        for (Show show : pShows) {
            total += show.runningTime();
        }
        System.out.println("Observer2: show added, total running time " + total + " minutes");
    }
}