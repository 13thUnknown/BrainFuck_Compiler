public class Oper {
    public enum Type
    {
        FORWARD,
        BACK,
        ADD,
        MINUS,
        IN,
        OUT,
        WHILE,
        END
    }

    private Type type=null;
    public int times=1;
    public Oper(Type type)
    {
        this.type=type;
    }
    public Type getType()
    {
        return this.type;
    }

}
