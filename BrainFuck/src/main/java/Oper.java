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
    public Oper(Type type,int times)
    {
        this.times=times;
    }
    public Oper clone()
    {
        return new Oper(type,times);
    }
    public Type getType()
    {
        return this.type;
    }

}
