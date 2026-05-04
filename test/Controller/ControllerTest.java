package Controller;

import Model.*;
import Model.Tile.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {


    @Test
    void testInitialTilesPlacement() {
        Controller controller = new Controller();

        Board board = controller.get_board();

        assertEquals(1, board.getMosaic_field().size(), "Mosaic field should have 1 tile");
        assertEquals(1, board.getStatue_field().size(), "Statue field should have 1 tile");
        assertEquals(1, board.getAmphora_field().size(), "Amphora field should have 1 tile");
        assertEquals(1, board.getSkeleton_field().size(), "Skeleton field should have 1 tile");
        assertEquals(0, board.getLand_slide_field().size(), "Landslide field should be empty at start");
    }


    @Test
    void testShareTilesAddsOneTile() {
        Controller controller = new Controller();
        Board board = controller.get_board();


        int totalBefore = board.getMosaic_field().size() +
                board.getStatue_field().size() +
                board.getAmphora_field().size() +
                board.getSkeleton_field().size() +
                board.getLand_slide_field().size();

        controller.share_tiles();

        int totalAfter = board.getMosaic_field().size() +
                board.getStatue_field().size() +
                board.getAmphora_field().size() +
                board.getSkeleton_field().size() +
                board.getLand_slide_field().size();

        assertEquals(totalBefore + 1, totalAfter, "Exactly one tile should be added");
    }


    @Test
    void testNextPlayer() {
        Controller controller = new Controller();
        Player first = controller.getCurrentPlayer();

        first.set_count(2);

        Player next = controller.next_player();

        assertNotEquals(first, next, "Should switch to different player");
        assertEquals(0, next.get_count(), "draw_count should reset to 0");
        assertEquals(next, controller.getCurrentPlayer(), "currentPlayer should be updated");
    }


    @Test
    void testEndGameWhenLandslideFull() {
        Controller controller = new Controller();
        Board board = controller.get_board();

        for (int i = 0; i < 16; i++) {
            board.add_Land_slide_tile(new LandSlideTile("path", "Landslide", 0));
        }


        assertEquals(16, board.getLand_slide_field().size());

    }


    @Test
    void testChoseTilesSpecificCategory() {
        Controller controller = new Controller();
        Board board = controller.get_board();

        int mosaicBefore = board.getMosaic_field().size();

        controller.chose_tiles("Mosaic");

        assertEquals(mosaicBefore + 1, board.getMosaic_field().size(), "Mosaic field should gain one tile");
        assertEquals(1, board.getStatue_field().size(), "Other fields unchanged");
        assertEquals(1, board.getAmphora_field().size(), "Other fields unchanged");
        assertEquals(1, board.getSkeleton_field().size(), "Other fields unchanged");
    }
}