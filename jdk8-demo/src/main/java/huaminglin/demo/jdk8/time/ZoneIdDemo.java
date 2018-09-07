package huaminglin.demo.jdk8.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneRules;
import java.time.zone.ZoneRulesException;
import java.util.Set;
import java.util.TreeSet;

public class ZoneIdDemo  {
    public static void main(String[] args) {
        try {
            ZoneId.of("Unknown");
        } catch (ZoneRulesException e) {
            System.out.println("ZoneRulesException happens.");
        }
        Set<String> availableIDs = new TreeSet<String>(ZoneId.getAvailableZoneIds());
        System.out.println("Zone ID, TimeZone(January), Daylight Savings First Shift, TimeZone(After Shift), Daylight Savings Second Shift");
        Instant january = ZonedDateTime.parse("2018-02-08T15:33:00+08:00[Asia/Shanghai]").toInstant();
        Instant afterFirstShift;
        for (String strtimezone : availableIDs) {
            ZoneId zoneId = ZoneId.of(strtimezone);
            ZoneRules rules = zoneId.getRules();
            ZoneOffset januaryOffset = rules.getOffset(january);

            ZoneOffsetTransition zoneOffsetTransition = rules.nextTransition(january);
            ZonedDateTime firstTransition = null;
            if (zoneOffsetTransition != null) {
                firstTransition = ZonedDateTime.ofInstant(zoneOffsetTransition.getInstant(), zoneId);
                afterFirstShift = ZonedDateTime.ofInstant(zoneOffsetTransition.getInstant(), zoneId).plusDays(1).toInstant();
            } else {
                afterFirstShift = january;
            }
            zoneOffsetTransition = rules.nextTransition(afterFirstShift);
            ZonedDateTime secondTransition = null;
            if (zoneOffsetTransition != null) {
                secondTransition = ZonedDateTime.ofInstant(zoneOffsetTransition.getInstant(), zoneId);
            }
            System.out.println(strtimezone + ", " + januaryOffset + ", " + firstTransition + ", " + rules.getOffset(afterFirstShift) + ", " + secondTransition);
        }
        // Save the output to a csv file
    }
}
