package displayParser;
import displayParser.LetterKey;

class wordState{
    private LetterKey lkWord[];
    public byte len;

    private wordState(String theWord)
    {
        if(theWord.length() > 0)
        {
            this.len=(byte)theWord.length();
            lkWord=new LetterKey[this.len];
        }
        for(Byte i=0; i< this.len; i++)
            lkWord[i] = new LetterKey(theWord.charAt(i));
    }
    public static wordState create(String theWord)
    {
        return new wordState(theWord);
    }
    void openAll()
    {
        for(Byte i=0; i< this.len; i++)
            this.lkWord[i].LK_setIsRead();
    }

    public boolean verifyLetterAt(byte i,char chk)
    {
        if(i > this.len) return false;

        i--;
        if(this.lkWord[i].LK_getLetter() == chk)
        {
            this.lkWord[i].LK_setIsRead();
            return true;
        }

        return false;
    }

    public boolean openLetterAt(byte i)
    {
        i--;
        if(i<=this.len) {

            if (this.lkWord[i].LK_isRead()) return false;

            this.lkWord[i].LK_setIsRead();
            return true;
        }
        return false;
    }

    public String getDisplayWord()
    {
        String str="";
        for(Byte i=0; i< this.len; i++)
        {
            if(lkWord[i].LK_isRead()) str+=lkWord[i].LK_getLetter();
            else str+="•";
        }
        return str;
    }
}
