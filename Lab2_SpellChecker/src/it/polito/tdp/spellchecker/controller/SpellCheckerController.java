
	package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.EnglishDictionary;
import it.polito.tdp.spellchecker.model.ItalianDictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SpellCheckerController {

	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtArea1;

    @FXML
    private Button SpellCheck;

    @FXML
    private TextFlow txtflow;
    
    @FXML
    private TextField txtErrori;
    
    @FXML
    private TextField txtTempo;

    @FXML
    private Button ClearText;
    
    @FXML
    private ComboBox<String> tendinaLingue;
    
    
    @FXML
    void DoClearText(ActionEvent event) {

    	txtArea1.clear();
    	txtflow.getChildren().clear();
    	}

    @FXML
    void DoSpellCheck(ActionEvent event) {
    	long start = System.nanoTime();
    	String testo=txtArea1.getText().toLowerCase();
        List<String> testoLista= new LinkedList<String>();
    	StringTokenizer st=new StringTokenizer(testo, " ");
    	while(st.hasMoreTokens()){
    	String parola=st.nextToken();
    	Character c = parola.charAt(parola.length()-1);
    	if(!Character.isLetter(c)){
    		parola=parola.substring(0, parola.length()-1);
    	}
    	testoLista.add(parola);
    	}
    	
    	if(tendinaLingue.getValue().compareTo("English")==0){
    		EnglishDictionary dizionario= new EnglishDictionary();
    		dizionario.loadDictionary();
    		for(RichWord rw: dizionario.spellCheckText(testoLista)){
    		//txtArea2.setText(dizionario.toString(dizionario.spellCheckText(testoLista)));
    		//if(txtArea2.getText()==null){
    			//txtErrori.setText("Non ci sono errori!");
    		//} else{
    			//txtErrori.setText("Sono presenti errori");
    			if(rw.isCorretta()=="false"){
    				txtErrori.setText("Sono presenti errori");
    				Text t=new Text(""+rw.getParola()+"");
    				t.setFill(Color.GREEN);
    				txtflow.getChildren().add(t);
    			} else{
    				txtErrori.setText("Non ci sono errori!");
    				Text t=new Text(""+rw.getParola()+"");
    				t.setFill(Color.BLACK);
    				txtflow.getChildren().add(t);
    			}
    		}
    	}

    	if(tendinaLingue.getValue().compareTo("Italian")==0){
    		ItalianDictionary dizionario= new ItalianDictionary();
    		dizionario.loadDictionary();
    		for(RichWord rw: dizionario.spellCheckText(testoLista)){
    		//txtArea2.setText(dizionario.toString(dizionario.spellCheckText(testoLista)));
    		//if(txtArea2.getText()==null){
    			//txtErrori.setText("Non ci sono errori!");
    		//} else{
    			//txtErrori.setText("Sono presenti errori");
    		//}	
    			if(rw.isCorretta()=="false"){
    				txtErrori.setText("Sono presenti errori");
    				Text t=new Text(""+rw.getParola()+" ");
    				t.setFill(Color.GREEN);
    				txtflow.getChildren().add(t);
    			} else{
    				txtErrori.setText("Non ci sono errori!");
    				Text t=new Text(""+rw.getParola()+" ");
    				t.setFill(Color.BLACK);
    				txtflow.getChildren().add(t);
    			}
    		}
    	}
    	long end = System.nanoTime();
    	txtTempo.setText("It took " + (end - start) + " nanoseconds");
 
    }
   
    
    @FXML
    void initialize() {
        assert txtArea1 != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert SpellCheck != null : "fx:id=\"SpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtflow != null : "fx:id=\"txtflow\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert ClearText != null : "fx:id=\"ClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert tendinaLingue!=null : "fx:id=\"tendinaLingue\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrori!=null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTempo!=null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        

        tendinaLingue.getItems().addAll("English","Italian");
       
    }
}
	