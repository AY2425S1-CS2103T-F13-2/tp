package seedu.address.model.meetup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ModelManager;
import seedu.address.model.meetup.exceptions.DuplicateMeetUpException;
import seedu.address.model.meetup.exceptions.MeetUpNotFoundException;
import seedu.address.model.person.Person;

/**
 * TODO JAVADOC + ENTIRE DOCUMENTATION
 * A list of meet ups that enforces uniqueness between its elements and does not allow nulls.
 * A meet ups is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating
 * of persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueMeetUpList implements Iterable<MeetUp> {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ObservableList<MeetUp> internalList = FXCollections.observableArrayList();
    private final ObservableList<MeetUp> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent MeetUp as the given argument.
     */
    public boolean contains(MeetUp toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMeetUp);
    }

    /**
     * Adds a MeetUp to the list.
     * The MeetUp must not already exist in the list.
     */
    public void add(MeetUp toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetUpException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the meetUp {@code target} in the list with {@code editedMeetUp}.
     * {@code target} must exist in the list.
     * The meetUp identity of {@code editedMeetUp} must not be the same as another existing meetUp in the list.
     */
    public void setMeetUp(MeetUp target, MeetUp editedMeetUp) {
        requireAllNonNull(target, editedMeetUp);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetUpNotFoundException();
        }

        if (!target.isSameMeetUp(editedMeetUp) && contains(editedMeetUp)) {
            throw new DuplicateMeetUpException();
        }

        internalList.set(index, editedMeetUp);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(MeetUp toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetUpNotFoundException();
        }
    }

    public void setMeetUps(UniqueMeetUpList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setMeetUps(List<MeetUp> meetUps) {
        requireAllNonNull(meetUps);
        if (!meetUpsAreUnique(meetUps)) {
            throw new DuplicateMeetUpException();
        }

        internalList.setAll(meetUps);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<MeetUp> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<MeetUp> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueMeetUpList)) {
            return false;
        }

        UniqueMeetUpList otherUniquePersonList = (UniqueMeetUpList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code meetUps} contains only unique meetUps.
     */
    private boolean meetUpsAreUnique(List<MeetUp> meetUps) {
        for (int i = 0; i < meetUps.size() - 1; i++) {
            for (int j = i + 1; j < meetUps.size(); j++) {
                if (meetUps.get(i).isSameMeetUp(meetUps.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
