package exercise8_6;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Class responsible for managing a program that consists of various shows
 * presented on different days of one week. Each day of the week must be
 * associated with exactly one Show object. If there is not show on a given day,
 * a special object of type show is used to represent a "non-show".
 *
 * The program can be observed: registered observers are notified every time a
 * show is added or removed, and when the program is cleared.
 */
public class ObservableProgram {

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private static final Show NULL_SHOW = createNullShow();

    private static Show createNullShow() {
        return new Show() {
            public String description() { return ""; }
            public int runningTime() { return 0; }

            public Show copy() { return createNullShow(); }
            public int hashCode() { return 0; }
            public boolean equals(Object pObject) {
                return pObject != null && pObject.getClass() == this.getClass(); }
        };
    }

    private final EnumMap<Day, Show> aShows = new EnumMap<>(Day.class);
    private final List<ProgramObserver> aObservers = new ArrayList<>();

    public ObservableProgram() {
        clear();
    }

    /**
     * Registers an observer that will be notified of the changes to the program.
     *
     * @param pObserver The observer to register.
     */
    public void addObserver(ProgramObserver pObserver) {
        assert pObserver != null;
        aObservers.add(pObserver);
    }

    /**
     * Clear the program by removing all existing shows.
     */
    public void clear() {
        for (Day day : Day.values()) {
            aShows.put(day, NULL_SHOW);
        }
        for (ProgramObserver observer : aObservers) {
            observer.programCleared();
        }
    }

    /**
     * Adds a new show to the program. Overrides any existing show on that day.
     *
     * @param pShow The show to add.
     * @param pDay  The day when the show takes place.
     */
    public void add(Show pShow, Day pDay) {
        assert pShow != null && pDay != null;
        aShows.put(pDay, pShow);
        for (ProgramObserver observer : aObservers) {
            observer.showAdded(pShow, pDay);
        }
    }

    /**
     * Removes a show from the program.
     *
     * @param pDay The day when we want to zap the show.
     */
    public void remove(Day pDay) {
        assert pDay != null;
        Show removed = aShows.put(pDay, NULL_SHOW);
        for (ProgramObserver observer : aObservers) {
            observer.showRemoved(removed, pDay);
        }
    }

    /**
     * @param pDay The day of the requested show.
     * @return A copy of the show on a given day.
     */
    public Show get(Day pDay) {
        assert pDay != null;
        return aShows.get(pDay);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Day day : aShows.keySet()) {
            if (aShows.containsKey(day)) {
                result
                        .append(String.format("%9s", day.name()))
                        .append(": ")
                        .append(aShows.get(day).description())
                        .append("\n");
            }
        }
        return result.toString();
    }
}