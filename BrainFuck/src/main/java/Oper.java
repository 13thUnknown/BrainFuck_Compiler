public class Oper {
    public enum Type {
        FORWARD (">"),
        BACK ("<"),
        ADD ("+"),
        MINUS ("-"),
        IN (","),
        OUT ("."),
        WHILE("["),
        END("]");
        private String title;
        Type(String title) {
          this.title=title;
        }

    }
    private Type type;
    public int times=1;

    public Oper(Type type) {
        this.type=type;
    }

    public Type getType() {
        return this.type;
    }

}
