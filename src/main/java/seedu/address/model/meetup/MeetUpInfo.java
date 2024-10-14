package seedu.address.model.meetup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a meetup's details in the meetup list.
 */
public class MeetUpInfo {

    public static final String MESSAGE_CONSTRAINTS =
            "Meetup details should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the detail must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String meetUpInfo;

    /**
     * Constructs a {@code MeetUpInfo}.
     *
     * @param meetUpInfo A valid meetup detail.
     */
    public MeetUpInfo(String meetUpInfo) {
        requireNonNull(meetUpInfo);
        checkArgument(isValidMeetUpInfo(meetUpInfo), MESSAGE_CONSTRAINTS);
        this.meetUpInfo = meetUpInfo;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMeetUpInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return meetUpInfo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetUpInfo)) {
            return false;
        }

        MeetUpInfo otherInfo = (MeetUpInfo) other;
        return meetUpInfo.equals(otherInfo.meetUpInfo);
    }

    @Override
    public int hashCode() {
        return meetUpInfo.hashCode();
    }
}