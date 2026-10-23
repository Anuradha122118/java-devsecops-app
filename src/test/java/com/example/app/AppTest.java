package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void shouldReturnApplicationMessage() {
        assertEquals(
            "Java DevSecOps application is running!",
            App.message()
        );
    }
}
