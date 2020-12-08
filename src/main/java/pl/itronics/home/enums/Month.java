package pl.itronics.home.enums;

import java.util.HashMap;
import java.util.Map;

public enum Month {
    JANUARY("Styczeń", 1),
    FEBRUARY("Luty", 2),
    MARCH("Marzec", 3),
    APRIL("Kwiecień", 4),
    MAY("Maj", 5),
    JUNE("Czerwiec", 6),
    JULY("Lipiec", 7),
    AUGUST("Sierpień", 8),
    SEPTEMBER("Wrzesień", 9),
    OCTOBER("Październik", 10),
    NOVEMBER("Listopad", 11),
    DECEMBER("Grudzień", 12);

    private static final Map<Integer, Month> BY_MONTH_NUMBER = new HashMap<>();

    static {
        for (Month m : values()) {
            BY_MONTH_NUMBER.put(m.monthNumber, m);
        }
    }

    public final String monthName;
    public final Integer monthNumber;

    Month(String monthName, Integer monthNumber) {
        this.monthName = monthName;
        this.monthNumber = monthNumber;
    }

    public static Month valueOfNumber(Integer number) {
        return BY_MONTH_NUMBER.get(number);
    }

    @Override
    public String toString() {
        return monthName;
    }
}
