package Q2;

import java.util.List;

public class RubyCodeFormatter extends CodeFormatter {

  @Override
  protected List<String> startOfBlock() {
    return List.of("do", "if", "while");
  }

  @Override
  protected String endOfBlock() {
    return "end";
  }

  @Override
  protected WhiteSpace tabsOrSpaces() {
    return WhiteSpace.TABS;
  }
}
