package exercise8_7;

import exercise8_6.Show;

/**
 * Dummy addition observer: pulls the shows and prints how many are scheduled.
 */
public class ConcreteObserver1 implements ProgramAdditionObserver {

    @Override
    public void showAdded(Iterable<Show> pShows) {
        int count = 0;
        for (Show show : pShows) {
            if (show.runningTime() > 0) {
                count++;
            }
        }
        System.out.println("Observer1: show added, " + count + " show(s) scheduled");
    }
}