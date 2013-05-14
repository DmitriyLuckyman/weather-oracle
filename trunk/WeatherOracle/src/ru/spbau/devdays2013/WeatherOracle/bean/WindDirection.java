package ru.spbau.devdays2013.WeatherOracle.bean;

/**
 * @author Dmitriy Bandurin
 *         Date: 13.05.13
 */
public enum WindDirection {
    NORTH(0, "C"),
    NORTH_NORTH_EAST(1, "CCВ"),
    NORTH_EAST(2, "CВ"),
    EAST_NORTH_EAST(3, "ВCВ"),
    EAST(4, "В"),
    EAST_SOUTH_EAST(5, "ВЮВ"),
    SOUTH_EAST(6, "ЮВ"),
    SOUTH_SOUTH_EAST(7, "ЮЮВ"),
    SOUTH(8, "Ю"),
    SOUTH_SOUTH_WEST(9, "ЮЮЗ"),
    SOUTH_WEST(10, "ЮЗ"),
    WEST_SOUTH_WEST(11, "ЗЮЗ"),
    WEST(12, "З"),
    WEST_NORTH_WEST(13, "ЗСЗ"),
    NORTH_WEST(14, "CЗ"),
    NORTH_NORTH_WEST(15, "CСЗ");

    private final int intValue;

    private final String description;

    WindDirection(int i, String description) {
        this.intValue = i;
        this.description = description;
    }

    public static WindDirection getByIntValue(int i){
        WindDirection d = NORTH;
        for (WindDirection windDirection : values()) {
            if(windDirection.intValue == i){
                d = windDirection;
                break;
            }
        }
        return d;
    }
    /**
     * Returns a string containing a concise, human-readable description of this
     * object. In this case, the enum constant's name is returned.
     *
     * @return a printable representation of this object.
     */
    @Override
    public String toString() {
        return description;
    }
}
