package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("PMD.UnusedImports")  // because there are some false positives, like unused imports at line 8 ??
class MainControllerTest {

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMainPage() {
        String viewName = mainController.mainPage();
        assertEquals("homePage", viewName);
    }
}