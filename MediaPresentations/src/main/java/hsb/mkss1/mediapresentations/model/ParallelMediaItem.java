package hsb.mkss1.mediapresentations.model;

public class ParallelMediaItem extends GroupingMediaItem {

    @Override
    public void present(int indentationLevel) {
        IO.print("Playing in parallel:".indent(indentationLevel));
        super.present(indentationLevel);
    }

}
