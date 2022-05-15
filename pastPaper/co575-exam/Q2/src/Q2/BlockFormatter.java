package Q2;

import java.util.List;

public interface BlockFormatter {
    List<String> startOfBlock();

    String endOfBlock();

    WhiteSpace tabsOrSpaces();
}
