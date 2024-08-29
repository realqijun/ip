package BabyGronk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDate {
    private LocalDate localDate;
    private LocalTime localTime;

    public CustomDate(String date) {
        try {
            parseDate(date);
        } catch (DateTimeParseException e) {
            localDate = LocalDate.now();
        }
    }

    private void parseDate(String date) throws DateTimeParseException {
        String[] args = date.split(" ");
        DateTimeFormatter formatter = null;
        if (args.length == 2) {
            if (args[0].contains("/")) {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            } else if (args[0].contains("-")) {
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            } else {
                throw new DateTimeParseException("Invalid date format", date, 0);
            }
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            localDate = dateTime.toLocalDate();
            localTime = dateTime.toLocalTime();
        }
        throw new DateTimeParseException("Invalid date format", date, 0);
    }

    public String unparseDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return (localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + localTime.format(formatter));
    }

    @Override
    public String toString() {
        int day = localDate.getDayOfMonth();
        StringBuilder date = new StringBuilder();
        if (day % 10 < 4 && day / 10 != 1) {
            switch (day % 10) {
            case 1:
                date.append(day).append("st");
                break;
            case 2:
                date.append(day).append("nd");
                break;
            case 3:
                date.append(day).append("rd");
                break;
            }
        } else {
            date.append(day).append("th");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        return (date.append(" of ").append(localDate.format(formatter))
                .append(" at ").append(localTime.format(timeFormatter))).toString();
    }

}
