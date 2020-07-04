package displayParser;
import java.io.Serializable;

class dataFWORD implements Serializable {
    private static final long serialversionUID =
            129348938L;
    String dWord;
    int length;
    byte callCount;

    dataFWORD(String x)
    {
        this.dWord=x;
        this.length=this.dWord.length();
        this.callCount=0;
    }

    public String recall()
    {
        this.callCount++;
        return this.dWord;
    }
    public int length(){ return this.length;}
}