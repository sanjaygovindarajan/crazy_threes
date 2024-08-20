package data_access;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;


class DataAccessTest {

    private DataAccess dataAccess;
    private File file;

    @BeforeEach
    void init() throws IOException {
        file = new File("src/data_access/database.txt");
        dataAccess = new DataAccess(file);
        Files.writeString(Path.of(file.getPath()), "");

    }

    @Test
    void testSingleEntry() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String mockGameStr = "AB,AB,AB:AB,AB:AB,AB:AB,AB:AB,AB:AB";
        dataAccess.saveGame(mockGameStr, now);
        String expectedGameStr = Files.readString(Path.of(file.getPath())).split("&")[0];
        assertEquals(mockGameStr, expectedGameStr);
    }

    @Test
    void testMultipleEntries() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String mockGameStr1 = "AB,AB,AB:AB,AB:AB,AB:AB,AB:AB,AB:AB";
        String mockGameStr2 = "CD,CD,CD:CD,CD:CD,CD:CD,CD:CD,CD:CD";
        String mockGameStr3 = "randomTestStr";
        dataAccess.saveGame(mockGameStr1, now);
        dataAccess.saveGame(mockGameStr2, now);
        dataAccess.saveGame(mockGameStr3, now);
        String expectedGame1 = Files.readString(Path.of(file.getPath())).split(",,,")[2].split("&")[0];
        String expectedGame2 = Files.readString(Path.of(file.getPath())).split(",,,")[1].split("&")[0];
        String expectedGame3 = Files.readString(Path.of(file.getPath())).split(",,,")[0].split("&")[0];
        Assertions.assertEquals(expectedGame2, mockGameStr2);
        Assertions.assertEquals(expectedGame1, mockGameStr1);
        Assertions.assertEquals(expectedGame3, mockGameStr3);
    }

    @AfterEach
    void tearDown() throws IOException {
        init();
    }

}
