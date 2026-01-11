package hsb.mkss1.mediapresentations;

import hsb.mkss1.mediapresentations.model.*;

public class Main {

    static void main() {
        MediaItem rootItem = new SequenceMediaItem()
                .addChild(new Text("A vacation to remember…"))
                .addChild(new ParallelMediaItem()
                        .addChild(new Music("Some lovely music (https://www.youtube.com/watch?v=dQw4w9WgXcQ)"))
                        .addChild(new SequenceMediaItem()
                                .addChild(new Picture("pic1"))
                                .addChild(new Picture("pic2"))
                                .addChild(new Picture("pic3"))
                                .addChild(new Picture("pic4"))
                                .addChild(new Picture("pic5"))
                                .addChild(new Video("vid1 (https://www.youtube.com/watch?v=8BCdKJ4ObZ4)"))
                                .addChild(new Picture("pic6"))
                                .addChild(new Picture("pic7"))
                                .addChild(new Picture("pic8"))
                                .addChild(new Picture("pic9"))
                        )
                )
                .addChild(new Text("We'll go there again"));

        rootItem.present(0);
    }
}
