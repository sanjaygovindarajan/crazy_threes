package data_access;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RulesDataAccess implements RulesDataAccessInterface{
    private final File rulesFile = new File("src/data_access/rules.txt");
    String rules;
    /**
     * Scans an external file for the rules of the game
     * @return A String of the rules
     */
    public String scanRules() throws IOException{

        rules = Files.readString(Path.of(rulesFile.getPath()));
        return rules;
    }
}
