package org.openjfx.fx;
import org.openjfx.database.DbApi;

import java.util.List;

public class Constants {

    public static final List<String> DIGITS = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    public static final List<String> OPERANDS = List.of("+", "-", "*", "/");

    public static final String HISTORY = "History";
    public static final String CLEAR_HISTORY = "Clear History";
    public static final String SAVE_HISTORY_TO_DATABASE = "Save History to DB";
    public static final String DOWNLOAD_10_LATEST = "Download 10 Latest";
    public static final String RESULT_SYMBOL = "=";
    public static final String CLEAR_DISPLAY = "C";

    public static final String TITLE = "Calculator";

    public static DbApi db = new DbApi();
}
