package data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RulesDataAccessTest {
    @Test
    void scanRules() {
          RulesDataAccess rulesDataAccess = new RulesDataAccess();
        rulesDataAccess.scanRules();
        assert true;
    }
}