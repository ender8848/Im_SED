package ic.doc;

public enum Operation {
  PLUS("+"),
  MINUS("-");

  private final String value;

  Operation(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }
}
