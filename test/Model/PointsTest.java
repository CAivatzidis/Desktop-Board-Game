package Model;

import Model.Tile.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointsTest {

    private Player createPlayer() {
        return new Player("TestPlayer", "Red", "");
    }


    @Test
    void mosaicFullSameColor() {
        Player p = createPlayer();
        for (int i = 0; i < 4; i++) {
            p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green"));
        }
        assertEquals(4, p.Calculate_MosaicPoints());
    }

    @Test
    void mosaicTwoFullSameColor() {
        Player p = createPlayer();
        for (int i = 0; i < 8; i++) {
            p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green"));
        }
        assertEquals(8, p.Calculate_MosaicPoints());
    }

    @Test
    void mosaicMixedColorsOneSet() {
        Player p = createPlayer();
        p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green"));
        p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Red"));
        p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Yellow"));
        p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green")); // extra
        assertEquals(2, p.Calculate_MosaicPoints());
    }

    @Test
    void mosaicIncomplete() {
        Player p = createPlayer();
        p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green"));
        p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green"));
        p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green"));
        assertEquals(0, p.Calculate_MosaicPoints());
    }


    @Test
    void amphoraAllSixColors() {
        Player p = createPlayer();
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Blue"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Brown"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Red"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Green"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Yellow"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Purple"));
        assertEquals(6, p.Calculate_AmphoraPoints());
    }

    @Test
    void amphoraFiveColors() {
        Player p = createPlayer();
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Blue"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Brown"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Red"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Green"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Yellow"));
        assertEquals(4, p.Calculate_AmphoraPoints());
    }

    @Test
    void amphoraFourColors() {
        Player p = createPlayer();
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Blue"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Brown"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Red"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Green"));
        assertEquals(2, p.Calculate_AmphoraPoints());
    }

    @Test
    void amphoraThreeColors() {
        Player p = createPlayer();
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Blue"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Brown"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Red"));
        assertEquals(1, p.Calculate_AmphoraPoints());
    }

    @Test
    void amphoraLessThanThree() {
        Player p = createPlayer();
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Blue"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Blue"));
        assertEquals(0, p.Calculate_AmphoraPoints());
    }


    @Test
    void skeletonOneAdult() {
        Player p = createPlayer();
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 0)); // big top
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 1)); // big bottom
        assertEquals(1, p.Calculate_SkeletonPoints());
    }

    @Test
    void skeletonOneChild() {
        Player p = createPlayer();
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 0)); // small top
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 1)); // small bottom
        assertEquals(1, p.Calculate_SkeletonPoints());
    }

    @Test
    void skeletonFamily() {
        Player p = createPlayer();
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 0));
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 1));
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 0));
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 1));


        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 0));
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 1));

        assertEquals(6, p.Calculate_SkeletonPoints());
    }

    @Test
    void skeletonTwoFamiliesPlusExtra() {
        Player p = createPlayer();
        for (int i = 0; i < 4; i++) {
            p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 0));
            p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 1));
        }
        for (int i = 0; i < 2; i++) {
            p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 0));
            p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 1));
        }
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 0));
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 1));

        assertEquals(13, p.Calculate_SkeletonPoints());
    }

    @Test
    void skeletonIncomplete() {
        Player p = createPlayer();
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 0)); // only top
        assertEquals(0, p.Calculate_SkeletonPoints());
    }

    // ====================== TOTAL POINTS ======================

    @Test
    void totalPointsCombination() {
        Player p = createPlayer();

        for (int i = 0; i < 4; i++) p.addPlayerTile(new MosaicTile("path", "Mosaic", 0, "Green"));

        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Blue"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Brown"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Red"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Green"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Yellow"));
        p.addPlayerTile(new AmphoraTile("path", "amphora", 0, "Purple"));

        for (int i = 0; i < 2; i++) {
            p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 0));
            p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 1, 1));
        }
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 0));
        p.addPlayerTile(new SkeletonTile("path", "skeleton", 0, 0, 1));

        p.SumPoints();


        assertEquals(16, p.getPoints());
    }
}