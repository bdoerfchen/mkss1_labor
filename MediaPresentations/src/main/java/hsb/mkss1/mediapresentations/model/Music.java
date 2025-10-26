package hsb.mkss1.mediapresentations.model;

public class Music extends MediaItem {

    private final String music;
    public Music(String music) {
        this.music = music;
    }

    @Override
    public void present(int indentationLevel) {
        IO.print("Playing music: %s".formatted(this.music).indent(indentationLevel));
    }
}
