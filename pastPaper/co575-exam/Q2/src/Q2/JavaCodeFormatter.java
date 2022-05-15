package Q2;

import java.util.List;

public class JavaCodeFormatter extends CodeFormatter {

  @Override
  protected List<String> startOfBlock() {
    return List.of("{");
  }

  @Override
  protected String endOfBlock() {
    return "}";
  }

  @Override
  protected WhiteSpace tabsOrSpaces() {
    return WhiteSpace.TWOSPACES;
  }

}
