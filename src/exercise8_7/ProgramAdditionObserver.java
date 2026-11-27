package exercise8_7;

import exercise8_6.Show;
/**
 * Observer interested in the addition of shows to a program. This interface
 * follows the pull data-flow strategy: the observer is only told that
 * something happened, and it retrieves the data it needs from the program. The
 * program is exposed as an Iterable<Show> so that the observer has no access
 * to the methods that can change the state of the program.
 */
public interface ProgramAdditionObserver {

    /**
     * Called after a show was added to the program.
     *
     * @param pShows A read-only view of all the shows in the program.
     */
    void showAdded(Iterable<Show> pShows);
}