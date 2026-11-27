package exercise8_6;

import exercise8_6.ObservableProgram.Day;

/**
 * Observer interface for an ObservableProgram. This interface follows the push
 * data-flow strategy: the program sends the relevant data (the show and the
 * day) as arguments of each notification.
 */
public interface ProgramObserver {

    /**
     * Called after a show was added to the program.
     *
     * @param pShow The show that was added.
     * @param pDay  The day on which the show was added.
     */
    void showAdded(Show pShow, Day pDay);

    /**
     * Called after a show was removed from the program.
     *
     * @param pShow The show that was removed.
     * @param pDay  The day from which the show was removed.
     */
    void showRemoved(Show pShow, Day pDay);

    /**
     * Called after all the shows were removed from the program.
     */
    void programCleared();
}