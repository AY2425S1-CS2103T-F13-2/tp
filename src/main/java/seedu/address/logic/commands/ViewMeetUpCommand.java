package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETUPS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

/**
 * Lists all meetUps in the address book to the user.
 */
public class ViewMeetUpCommand extends Command {

    public static final String COMMAND_WORD = "viewm";

    public static final String MESSAGE_SUCCESS = "Viewing all meet-ups";

    private final Logger logger = LogsCenter.getLogger(ViewMeetUpCommand.class);


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetUpList(PREDICATE_SHOW_ALL_MEETUPS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
