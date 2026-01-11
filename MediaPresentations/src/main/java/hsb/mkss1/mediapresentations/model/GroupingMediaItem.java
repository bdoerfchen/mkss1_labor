package hsb.mkss1.mediapresentations.model;

import java.util.ArrayList;
import java.util.List;

public abstract class GroupingMediaItem extends MediaItem {
    protected final List<MediaItem> children = new ArrayList<>();

    protected final int GROUPED_INDENTATION = 2;

    @Override
    public MediaItem addChild(MediaItem item) {
        if (item == null)
            return this;

        children.add(item);
        return this;
    }

    @Override
    public void removeChild(MediaItem item) {
        if (item == null)
            return;

        children.remove(item);
    }

    @Override
    public MediaItem getChild(int index) {
        return children.get(index);
    }

    @Override
    public void present(int indentationLevel) {
        for (MediaItem item : this.children) {
            item.present(indentationLevel + GROUPED_INDENTATION);
        }
    }
}
