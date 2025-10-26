package hsb.mkss1.universalremotecontrol;

import hsb.mkss1.universalremotecontrol.remotecontrol.RemoteControl;

/**
 * hsb.mkss1.universal_remote_control.Main for testing the functionality
 */
public class Main {
	public static void main(String[] args) {
		RemoteControl mediaPlayerRemote = new RemoteControl();

		// TODO: configure the buttons for the media player remote with activate / deactivate actions

		// TODO: test the functionality by pressing different buttons similar to below
		mediaPlayerRemote.actionButtonPressed(0);
		mediaPlayerRemote.actionButtonPressed(1);
		mediaPlayerRemote.actionButtonPressed(2);

		mediaPlayerRemote.undoButtonPressed();

		mediaPlayerRemote.actionButtonPressed(1);

        mediaPlayerRemote.undoButtonPressed();
        mediaPlayerRemote.undoButtonPressed();

		mediaPlayerRemote.actionButtonPressed(0);
	}
}

