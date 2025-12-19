package hsb.mkss1.mediapresentations.model;

public class Video extends MediaItem {

    private final String video;
    public Video(String video) {
        this.video = video;
    }

    @Override
    public void present(int indentationLevel) {
        IO.print("Playing video: %s".formatted(this.video).indent(indentationLevel));
    }
}
