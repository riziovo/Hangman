package displayParser;

import java.io.*;

/*==============================================================*/
/*===========PROTECTED KEY DATA STRUCT================================*/


/*==============================================================*/
/*======================KEY INTERFACE===========================*/


/*==============================================================*/
/*========SERIALIZED DATA STRUCTURE=========================*/


/*==============================================================*/
/*========DATA - INTERFACE=========================*/

class readO  extends ObjectInputStream{
    readO() throws IOException {
        super();
    }
    readO(InputStream is) throws IOException {
        super(is);
        this.readStreamHeader();
        System.out.println("INNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
    }

    @Override
    protected void readStreamHeader()
    {}
}

public class displayParser{
    private wordState wState;
    private dataFWORD dfWord;
    public int len;

    public displayParser(Object x)
    {
        this.dfWord=(dataFWORD)x;
        this.wState=wordState.create(dfWord.dWord);
        this.len= this.dfWord.length;
        this.dfWord=null;
    }

    public void openAll()
    {
        this.wState.openAll();
    }

    public boolean checkWord(String str)
    {
        if(str.equals(this.displayString()) ? true:false)
        {
            this.wState.openAll();
            return true;
        }
        return false;
    }

    public boolean openLetter(byte p)
    {
        if(p<=this.wState.len) return wState.openLetterAt(p) ? true:false;
        return false;

    }

    public char openLetterAt(byte p)
    {
        if(p<=this.wState.len)
        {
            wState.openAll();
            return this.displayString().charAt(p);
        }
        return ' ';
    }

    public String displayString() {
        return wState.getDisplayWord();
    }

    public boolean checkLetter(char p,byte at)
    {
        if (at <= this.wState.len)
        {
            if(wState.verifyLetterAt(at, p)) {
                this.wState.openLetterAt(at);
                return true;
            }
        }
        return false;
    }

    public static displayParser generateWord()
    {
        try(FileInputStream fis=new FileInputStream("C:/Users/Yash/Desktop/jav/repo.txt");
            readO ois=new readO(fis);)
        {
            int i=(int)(Math.random()*((100.00-1.00)+1))+1;

            for(int j=1; j<i; j++)
                ois.readObject();

            return new displayParser(ois.readObject());
        }
        catch(SecurityException e)
        {
            System.out.println("security security security:" + e);
        } catch (IOException e)
        {
            System.out.println("Exception:" + e);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("hiyaaaaaaaaaaaaaaaa");
            e.getCause();
        }
        return null;
    }
}