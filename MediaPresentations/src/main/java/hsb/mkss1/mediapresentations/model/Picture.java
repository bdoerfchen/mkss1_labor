package hsb.mkss1.mediapresentations.model;

public class Picture extends MediaItem {

    private final String picture;
    public Picture(String picture) {
        this.picture = picture;
    }

    @Override
    public void present(int indentationLevel) {
        IO.print("Displaying picture: %s".formatted(this.picture).indent(indentationLevel));
    }
}
