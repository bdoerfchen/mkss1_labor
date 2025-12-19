package hsb.mkss1.mediapresentations.model;

public class SequenceMediaItem extends GroupingMediaItem {

    @Override
    public void present(int indentationLevel) {
        IO.print("Playing in sequence:".indent(indentationLevel));
        super.present(indentationLevel);
    }

}
