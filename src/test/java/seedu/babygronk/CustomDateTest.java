package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import babygronk.CustomDate;

public class CustomDateTest {

    @Test
    public void noDate() {
        assertEquals(new CustomDate(null).unparseDate(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")));
    }

    @Test
    public void invalidDate() {
        assertEquals(new CustomDate("212/213/213 21321").unparseDate(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")));
    }

}
