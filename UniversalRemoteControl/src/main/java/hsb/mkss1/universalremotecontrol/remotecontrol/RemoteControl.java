package hsb.mkss1.universalremotecontrol.remotecontrol;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  Base implementation for the remote control.
 */
public class RemoteControl implements IRemoteControl {

    public static final int NO_OF_ACTION_BUTTONS = 3;

    private final ActionButton[] buttons = new ActionButton[NO_OF_ACTION_BUTTONS];

    private final Deque<Integer> history = new ArrayDeque<>();


    @Override
    public void configureButton(int buttonNumber, ICommand command) {
        if (buttonNumber < NO_OF_ACTION_BUTTONS && buttonNumber >= 0) {
            buttons[buttonNumber] = new ActionButton(command);
        }
    }

    /**
     * The action button was pressed.
     * Depending on its status, it will execute an activate or deactivate action.
     *
     * @param buttonNumber The number of the button.
     */
    @Override
    public void actionButtonPressed(int buttonNumber) {
        if (buttonNumber < NO_OF_ACTION_BUTTONS && buttonNumber >= 0) {
          ActionButton button = buttons[buttonNumber];
          if (button != null) {
              button.invoke();
              history.push(buttonNumber);
          }
          else {
              IO.println("ERROR: Button not configured!");
          }
        } else {
            IO.println("ERROR: Invalid button number!");
        }
    }

    /**
     * The undo button was pressed.
     * It will undo the previous action.
     */
    @Override
    public void undoButtonPressed() {
        if (!history.isEmpty()) {
            int buttonNumber = history.pop();
            buttons[buttonNumber].invoke();
        }

    }
}
