package displayParser;

class LetterKey{
    private char key;
    private boolean val;

    LetterKey(char x)
    {
        this.key=x;
        this.val=false;
    }
    @SuppressWarnings("unused")
    private void LK_setReadValue(boolean set)
    {
        this.val=set;
    }
    public boolean LK_isRead()
    {
        return this.val;
    }
    public void LK_setIsRead() {this.val=true;}
    public char LK_getLetter()
    {
        return this.key;
    }
}
