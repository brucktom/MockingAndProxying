package de.oth.mocker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static de.oth.mocker.MyInvocationHandler.verify;
import static de.oth.mocker.Frequency.*;

import static de.oth.mocker.Mocker.*;
import static org.junit.jupiter.api.Assertions.*;

class EnrollmentTest {
    @Test
    public void testEnrollmentDatabaseConnection() {
        DatabaseConnection dbcon = mock(DatabaseConnection.class);
        dbcon.close();
        dbcon.close();
        verify(dbcon, times(1)).connect();
        verify(dbcon, times(1)).query("INSERT INTO stud (id, name)"); verify(dbcon, times(1)).execute();
        verify(dbcon, never()).delete();
        verify(dbcon, atLeast(1)).close();
        verify(dbcon, atLeast(3)).close();
    }
}