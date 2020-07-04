package sample;

import displayParser.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class Controller implements Initializable{

    @FXML
    public Label alertM;

    @FXML
    public Pane updatePane;

    @FXML
    private FlowPane fbox;
    @FXML
    public JFXButton genCheck;

    @FXML
    public HTextField[] tfxList;
    public int boxCountToDisplay=0;
    public int boxesLeft=0;
    public boolean done=true;

    public displayParser dsp;
    public String word;

    public int chances=0;

    @FXML
    public JFXButton dRaw;
    public static int draws;
    public String dRawText="DRAWS:"+draws;

    @FXML
    public Button genesis;

    @FXML
    public HTextField hxref=null;

    public byte perBox= 5;
    public char[][] used;

    @FXML
    public Label ChancesL;


    public Controller() throws Exception
    {
        System.out.println("===================================="+word);
    }

    @FXML
    public void run()
    {
        if(done) {
            alertM.setText("\n BINGO! Success !");
//            done = false;
//            genCheck.setText("C H E C K");
//            Generate();
        }
        else
        {
            if(hxref!=null) {
                for (int i = 0; i < boxCountToDisplay; i++) {
                    hxref = tfxList[i];
                    checkKey(hxref);
                }
            }
        }
    }

    @Override @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
       done=false;
       dsp=displayParser.generateWord();
       word=dsp.displayString();
        boxCountToDisplay=word.length();
        chances=boxCountToDisplay*2;
        ChancesL.setText(chances+"");
        boxesLeft=boxCountToDisplay;
        this.draws=(int) (boxCountToDisplay*0.3)+2;

        dRaw.setText(dRawText+draws);
        System.out.println("Start"+boxCountToDisplay);

        tfxList= new HTextField[boxCountToDisplay];

        for (int i=0; i<boxCountToDisplay; i++)
            tfxList[i]=new HTextField((byte)i);

        for (HTextField x: tfxList) {
            System.out.println(x.fetchText());
        };
        for (HTextField x: tfxList) {
            x.setPrefSize(50, 30);
            x.setOnAction(e -> checkKey(x));
            x.setOnKeyPressed(e -> {
                ObservableList<Node> child=fbox.getChildren();
                x.t1=(TextField) child.get((int)x.id);
                hxref = x;
            });
            x.setOnMouseClicked(e -> {
                ObservableList<Node> child=fbox.getChildren();
                x.t1=(TextField) child.get((int)x.id);
                hxref = x;
            });
            x.t1.focusedProperty().addListener((obs, oldV, newV) -> {
                System.out.println("INSIDE EVENT HXREF" + x.fetchText());
                ObservableList<Node> child=fbox.getChildren();
                x.t1=(TextField) child.get((int)x.id);
                hxref = x;
                if (newV) {
                    if (x.fetchText().matches("[a-zA-Z]")) {
                        System.out.println("INSIDE EVENT HXREF" + x.fetchText());
                        tfxList[x.id] = new HTextField(x.id);
                    } else {
                        hxref.setNewText("•");
                    }
                }

            });
        }


        dRaw.setOnAction(e->this.onOpenClick());
        genCheck.setOnAction(e->this.run());

        fbox.getChildren().addAll(tfxList);

        used=new char[boxCountToDisplay][perBox];

       //run();
    }

    public void Generate()
    {
        alertM.setText("Lets go! you got this =)");
        fbox.getChildren().clear();
        clean();
        done=false;
        dsp=displayParser.generateWord();
        word=dsp.displayString();
        System.out.println("===================================="+word);

        boxCountToDisplay=word.length();
        chances=boxCountToDisplay*2;
        ChancesL.setText(chances+"");
        System.out.println(chances+"  chances");
        boxesLeft=boxCountToDisplay;
        this.draws= (boxCountToDisplay/3) +2;

        dRaw.setText(dRawText+draws);
        System.out.println("Start"+boxCountToDisplay);

        tfxList= new HTextField[boxCountToDisplay];

        for (int i=0; i<boxCountToDisplay; i++)
            tfxList[i]=new HTextField((byte)i);

        for (HTextField x: tfxList) {
            x.setPrefSize(50, 30);
            x.setOnAction(e -> checkKey(x));
            x.setOnKeyPressed(e -> {
                ObservableList<Node> child=fbox.getChildren();
                x.t1=(TextField) child.get((int)x.id);
                hxref = x;
            });
            x.setOnMouseClicked(e -> {
                ObservableList<Node> child=fbox.getChildren();
                x.t1=(TextField) child.get((int)x.id);
                hxref = x;
            });
            x.t1.focusedProperty().addListener((obs, oldV, newV) -> {
                System.out.println("INSIDE EVENT HXREF" + x.fetchText());
                ObservableList<Node> child=fbox.getChildren();
                x.t1=(TextField) child.get((int)x.id);
                hxref = x;
                if (newV) {
                    if (x.fetchText().matches("[a-zA-Z]")) {
                        System.out.println("INSIDE EVENT HXREF" + x.fetchText());
                        tfxList[x.id] = new HTextField(x.id);
                    } else {
                        hxref.setNewText("•");
                    }
                }

            });
        }

        dRaw.setOnAction(e->this.onOpenClick());
        genCheck.setOnAction(e->this.run());

        fbox.getChildren().addAll(tfxList);

        //used=new char[boxCountToDisplay][perBox];
    }

    public void clean()
    {
        tfxList=null;
        used=null;
        done=false;
        draws = 0;
    }

    public void checkKey(HTextField x)
    {
        if(!x.checkBox()) return;
        else if(x.done) return;
        else if(dsp.checkLetter(x.fetchText().charAt(0),(byte)(x.id+1))) {
            x.done = true;
            boxesLeft--;
            ObservableList<Node> n=fbox.getChildren();
            TextField t1 =(TextField)n.get(x.id);
            t1.setEditable(false);
            x.t1=t1;

            System.out.println("correct in "+boxesLeft);

            if(boxesLeft <= 0) {
                done = true;
                alertM.setText("\n BINGO! Success !");
                clean();
            }

        }
        else
            {
                chances--;
                ChancesL.setText(chances+"");
                alertM.setText("\n Wrong! Try Again");
                x.setNewText("");
                if(chances <= 0)
                {
                    dsp.openAll();
                    this.openAllBox();
                }
            }
    }

    public void openAllBox()
    {
        dsp.openAll();

        TextField tmp;
        String xtr=dsp.displayString();
        ObservableList<Node> nod=fbox.getChildren();
        for (HTextField x: tfxList) {
            tmp=(TextField)nod.get(x.id);
            tmp.setText(xtr.charAt(x.id)+"");
            tmp.setEditable(false);
            x.t1=tmp;
            x.done=true;
        }

        done = true;
    }

    @FXML
    public void onOpenClick(){
        if(draws == 0) return;
        if(hxref==null) return;
        if(hxref.done) return;

        hxref.setNewText(""+dsp.openLetterAt(hxref.id));
        ObservableList<Node> t=fbox.getChildren();
        TextField t1=(TextField)t.get(hxref.id);
        t1.setText(hxref.fetchText());
        hxref.t1=t1;
        hxref.done=true;
        hxref.setEditable(false);
        draws--;
        dRaw.setText(dRawText+draws);
        boxesLeft--;
        //fbox.getChildren().addAll(tfxList);
    }
}
