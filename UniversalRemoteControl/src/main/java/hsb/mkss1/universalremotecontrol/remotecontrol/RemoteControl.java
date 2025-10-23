package hsb.mkss1.universalremotecontrol.remotecontrol;

/**
 *  Base implementation for the remote control.
 */
public class RemoteControl implements IRemoteControl {

    public final static int NO_OF_ACTION_BUTTONS = 3;

    // TODO: Data structure(s) for the action buttons
    // TODO: Data structure(s) for the undo button / history

    public RemoteControl() {
        // TODO: Initialize data structures for the action buttons
        // TODO: Initialize data structures for the undo button / history
    }

    // TODO: Implement method for configuration of action buttons (cf. TODO in interface IRemoteControl)

    /**
     * The action button was pressed.
     * Depending on its status, it will execute an activate or deactivate action.
     *
     * @param no The number of the button.
     */
    @Override
    public void actionButtonPressed(int no) {
        // TODO: Execute action according to button status
        // TODO: Update undo button / history
    }

    /**
     * The undo button was pressed.
     * It will undo the previous action.
     */
    @Override
    public void undoButtonPressed() {
        // TODO: Execute undo action
    }
}
