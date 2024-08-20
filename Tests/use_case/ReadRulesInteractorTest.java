package use_case;

import interface_adapter.view_rules.ReadRulesOutputBoundary;
import interface_adapter.view_rules.ReadRulesPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.game_actions.read_rules.ReadRulesInteractor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReadRulesInteractorTest {
    ReadRulesOutputBoundary testOutput = new MockPresenter();

    @Test
    void testExecute() {
            ReadRulesInteractor testInteract = new ReadRulesInteractor(testOutput);
            testInteract.execute();
        }
        class MockPresenter implements ReadRulesOutputBoundary{

            @Override
            public void prepareSuccessView(String message) {
                assert message.startsWith("The rules are as follows");
                assert message.endsWith("out of cards wins!");
            }

            @Override
            public void prepareFailView() {

            }
        }
    }
