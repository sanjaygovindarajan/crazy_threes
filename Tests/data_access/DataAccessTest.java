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
        assertEquals(mockGameStr, Files.readString(Path.of(file.getPath())));
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
        assertEquals(String.join(",,,",mockGameStr3,mockGameStr2,mockGameStr1), Files.readString(Path.of(file.getPath())));
    }

    @AfterEach
    void tearDown() throws IOException {
        init();
    }

}
