package com.management.hotel.util;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeAdjuster {
    public static Date checkinDate(Date date){
        Instant instant = date.toInstant();

        OffsetDateTime dateTime = instant.atOffset(ZoneId.of("UTC").getRules().getOffset(instant));

        // Adjust the time to 12:00:00
        OffsetDateTime newDateTime = dateTime.withHour(12).withMinute(0).withSecond(0).withNano(0);

        String formattedDateTime = newDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        return  Date.from(newDateTime.toInstant());
    }
    public static Date checkoutDate(Date date){
        Instant instant = date.toInstant();

        OffsetDateTime dateTime = instant.atOffset(ZoneId.of("UTC").getRules().getOffset(instant));

        // Adjust the time to 10:00:00
        OffsetDateTime newDateTime = dateTime.withHour(10).withMinute(0).withSecond(0).withNano(0);

        String formattedDateTime = newDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        return  Date.from(newDateTime.toInstant());
    }
}
