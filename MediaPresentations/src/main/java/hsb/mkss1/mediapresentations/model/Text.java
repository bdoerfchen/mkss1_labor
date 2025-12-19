package hsb.mkss1.mediapresentations.model;

public class Text extends MediaItem {

    private final String text;
    public Text(String text) {
        this.text = text;
    }

    @Override
    public void present(int indentationLevel) {
        IO.print("Displaying text: %s".formatted(this.text).indent(indentationLevel));
    }
}
