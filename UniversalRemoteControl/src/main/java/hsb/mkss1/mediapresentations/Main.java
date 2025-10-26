package hsb.mkss1.universalremotecontrol;

import hsb.mkss1.universalremotecontrol.commands.NextPreviousCommand;
import hsb.mkss1.universalremotecontrol.commands.OnOffCommand;
import hsb.mkss1.universalremotecontrol.commands.PauseUnpauseCommand;
import hsb.mkss1.universalremotecontrol.remotecontrol.RemoteControl;



/**
 * hsb.mkss1.universal_remote_control.Main for testing the functionality
 */
public class Main {
	 static void main() {
		RemoteControl mediaPlayerRemote = new RemoteControl();

        mediaPlayerRemote.configureButton(0, new OnOffCommand());
        mediaPlayerRemote.configureButton(1, new PauseUnpauseCommand());
        mediaPlayerRemote.configureButton(2, new NextPreviousCommand());


		mediaPlayerRemote.actionButtonPressed(0);   // Expected Output: On          [0]
		mediaPlayerRemote.actionButtonPressed(1);   // Expected Output: Pause       [0, 1]
		mediaPlayerRemote.actionButtonPressed(2);   // Expected Output: Next        [0, 1, 2]

		mediaPlayerRemote.undoButtonPressed();                  // Expected Output: Previous    [0, 1]

		mediaPlayerRemote.actionButtonPressed(1);   // Expected Output: Unpause     [0, 1, 1]

        mediaPlayerRemote.undoButtonPressed();                  // Expected Output: Pause       [0, 1]
        mediaPlayerRemote.undoButtonPressed();                  // Expected Output: Unpause     [0]

		mediaPlayerRemote.actionButtonPressed(0);   // Expected Output: Off         [0, 0]
	}

    private Main(){}
}

