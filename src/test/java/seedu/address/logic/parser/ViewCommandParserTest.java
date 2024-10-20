package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.buyer.ViewCommand;
import seedu.address.model.person.PersonFulfilsPredicate;

public class ViewCommandParserTest {

    private ViewPersonCommandParser parser = new ViewPersonCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        ViewCommand expectedViewCommand1 = new ViewCommand(new PersonFulfilsPredicate("buyer"));
        ViewCommand expectedViewCommand2 = new ViewCommand(new PersonFulfilsPredicate("seller"));

        assertParseSuccess(parser, "buyer", expectedViewCommand1);
        assertParseSuccess(parser, "seller", expectedViewCommand2);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "hello",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_returnsViewCommand() {
        ViewCommand expectedViewCommand = new ViewCommand(new PersonFulfilsPredicate(""));
        assertParseSuccess(parser, "", expectedViewCommand);
    }
}
