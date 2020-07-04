package sample;

import javafx.scene.control.TextField;

public class HTextField extends TextField {
    public TextField t1;
    public byte id;
    public boolean done;

    public HTextField(byte id)
    {
        t1=new TextField("•");
        //t1.setText("•");
        done=false;
        this.id=id;
    }
    public void setTxtField(TextField t)
    {
        t1=t;
    }

    public byte fetchId(){
        return this.id;
    }

    public HTextField(byte id, String str)
    {
        t1=new TextField();
        t1.setText(str);
        done=false;
        this.id=id;
    }

    public boolean checkBox()
    {
        if(this.done) return false;
        String str=t1.getText();
        if(str.matches("[a-zA-Z]")) return true;

        return false;
    }

    public String fetchText(){
        return t1.getText();
    }

    public void setNewText(String str)
    {
        t1.setText(str);
    }
}
