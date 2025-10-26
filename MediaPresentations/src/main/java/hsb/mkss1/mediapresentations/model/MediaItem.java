package hsb.mkss1.mediapresentations.model;

public abstract class MediaItem {

    public abstract void present(int indentationLevel);

    public MediaItem addChild(MediaItem item) {
        throw new UnsupportedOperationException();
    }
    public void removeChild(MediaItem item) {
        throw new UnsupportedOperationException();
    }
    public MediaItem getChild(int index) {
        throw new UnsupportedOperationException();
    }
}
