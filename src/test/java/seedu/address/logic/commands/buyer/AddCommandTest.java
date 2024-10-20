package seedu.address.logic.commands.buyer;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyBuyerList;
import seedu.address.model.ReadOnlyMeetUpList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.meetup.MeetUp;
import seedu.address.testutil.BuyerBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBuyerAdded modelStub = new ModelStubAcceptingBuyerAdded();
        Buyer validBuyer = new BuyerBuilder().build();

        CommandResult commandResult = new AddCommand(validBuyer).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validBuyer)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBuyer), modelStub.buyersAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Buyer validBuyer = new BuyerBuilder().build();
        AddCommand addCommand = new AddCommand(validBuyer);
        ModelStub modelStub = new ModelStubWithBuyer(validBuyer);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Buyer alice = new BuyerBuilder().withName("Alice").build();
        Buyer bob = new BuyerBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different buyer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyBuyerList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBuyerList getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Buyer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBuyer(Buyer target, Buyer editedBuyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Buyer> getFilteredBuyerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBuyerList(Predicate<Buyer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMeetUpListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetUpListFilePath(Path meetUpListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetUpList(ReadOnlyMeetUpList meetUpList) {
            throw new AssertionError("This method should not be called.");
        }

        // ============================ MeetUp Model - To be refactored ==========================

        @Override
        public ReadOnlyMeetUpList getMeetUpList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<MeetUp> getFilteredMeetUpList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeetUp(MeetUp meetUp) {

        }

        @Override
        public void setMeetUp(MeetUp target, MeetUp editedMeetUp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeetUp(MeetUp target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetUpList(Predicate<MeetUp> meetUp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeetUp(MeetUp meetUp) {
            return false;
        }
    }

    /**
     * A Model stub that contains a single buyer.
     */
    private class ModelStubWithBuyer extends ModelStub {
        private final Buyer buyer;

        ModelStubWithBuyer(Buyer buyer) {
            requireNonNull(buyer);
            this.buyer = buyer;
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return this.buyer.isSameBuyer(buyer);
        }
    }

    /**
     * A Model stub that always accept the buyer being added.
     */
    private class ModelStubAcceptingBuyerAdded extends ModelStub {
        final ArrayList<Buyer> buyersAdded = new ArrayList<>();

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return buyersAdded.stream().anyMatch(buyer::isSameBuyer);
        }

        @Override
        public void addBuyer(Buyer buyer) {
            requireNonNull(buyer);
            buyersAdded.add(buyer);
        }

        @Override
        public ReadOnlyBuyerList getAddressBook() {
            return new AddressBook();
        }
    }

}
