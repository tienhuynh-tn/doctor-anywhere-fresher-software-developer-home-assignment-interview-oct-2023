package com.tienhuynhtn.util;

import com.tienhuynhtn.constant.DateTimeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtil.class);

    public static ZonedDateTime getZoneDateTimeNow() {
        return ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(DateTimeConstant.ZONE_ID));
    }
}
