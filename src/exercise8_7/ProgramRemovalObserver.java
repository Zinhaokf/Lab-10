package exercise8_7;

import exercise8_6.Show;

/**
 * Observer interested in the removal of shows from a program, and in the
 * clearing of a program. This interface follows the pull data-flow strategy:
 * the observer retrieves the data it needs from the read-only view of the
 * program.
 */
public interface ProgramRemovalObserver {

    /**
     * Called after a show was removed from the program.
     *
     * @param pShows A read-only view of all the shows in the program.
     */
    void showRemoved(Iterable<Show> pShows);

    /**
     * Called after all the shows were removed from the program.
     */
    void programCleared();
}