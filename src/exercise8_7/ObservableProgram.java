package exercise8_7;

import exercise8_6.Show;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

/**
 * Class responsible for managing a program that consists of various shows
 * presented on different days of one week. Each day of the week must be
 * associated with exactly one Show object. If there is not show on a given day,
 * a special object of type show is used to represent a "non-show".
 *
 * The program can be observed. Observers are notified using the pull data-flow
 * strategy: they receive the program as an Iterable<Show>, which does not let
 * them change the state of the program.
 */
public class ObservableProgram implements Iterable<Show> {

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
    private final List<ProgramAdditionObserver> aAdditionObservers = new ArrayList<>();
    private final List<ProgramRemovalObserver> aRemovalObservers = new ArrayList<>();

    public ObservableProgram() {
        clear();
    }

    /**
     * Registers an observer that will be notified when shows are added.
     *
     * @param pObserver The observer to register.
     */
    public void addObserver(ProgramAdditionObserver pObserver) {
        assert pObserver != null;
        aAdditionObservers.add(pObserver);
    }

    /**
     * Registers an observer that will be notified when shows are removed or when
     * the program is cleared.
     *
     * @param pObserver The observer to register.
     */
    public void addObserver(ProgramRemovalObserver pObserver) {
        assert pObserver != null;
        aRemovalObservers.add(pObserver);
    }

    /**
     * Clear the program by removing all existing shows.
     */
    public void clear() {
        for (Day day : Day.values()) {
            aShows.put(day, NULL_SHOW);
        }
        for (ProgramRemovalObserver observer : aRemovalObservers) {
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
        for (ProgramAdditionObserver observer : aAdditionObservers) {
            observer.showAdded(this);
        }
    }

    /**
     * Removes a show from the program.
     *
     * @param pDay The day when we want to zap the show.
     */
    public void remove(Day pDay) {
        assert pDay != null;
        aShows.put(pDay, NULL_SHOW);
        for (ProgramRemovalObserver observer : aRemovalObservers) {
            observer.showRemoved(this);
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

    /**
     * Iterates over all shows, covering the seven days of the week in a
     * predictable order. The iterator does not support removal.
     */
    @Override
    public Iterator<Show> iterator() {
        return Collections.unmodifiableCollection(aShows.values()).iterator();
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