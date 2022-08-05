package com.norab.show.crossed;

public enum SearchLocation {
    ALL(0), TITLE(1), ORIGTITLE(2);

    int location;

    SearchLocation(int i) {
        location = i;
    }

    SearchLocation(String value) {
        try {
            int i = Integer.parseInt(value);
            location = i;
        } catch (NumberFormatException e) {
            location = 0;
        }
    }
}
