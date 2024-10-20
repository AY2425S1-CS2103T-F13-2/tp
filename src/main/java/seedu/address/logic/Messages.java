package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.meetup.MeetUp;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The buyer index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d buyers listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_MEETUP_DISPLAYED_INDEX = "The meetup index provided is invalid";

    // MeetUp Strings
    public static final String MESSAGE_MEETUPS_LISTED_OVERVIEW = "%1$d buyers listed!";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code buyer} for display to the user.
     */
    public static String format(Buyer buyer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(buyer.getName())
                .append("; Phone: ")
                .append(buyer.getPhone())
                .append("; Email: ")
                .append(buyer.getEmail())
                .append("; Address: ")
                .append(buyer.getAddress())
                .append("; Tags: ");
        buyer.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code meetup} for display to the user.
     */
    public static String format(MeetUp meetup) {
        final StringBuilder builder = new StringBuilder();
        builder.append(meetup.getName())
                .append("; Info: ")
                .append(meetup.getInfo())
                .append("; From: ")
                .append(meetup.getFrom())
                .append("; To: ")
                .append(meetup.getTo());
        return builder.toString();
    }
}
