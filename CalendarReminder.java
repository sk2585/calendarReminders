package calendarReminders;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

//xml file stuff:
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.xml.sax.SAXException;


public class CalendarReminder extends Application {
    private double paneWidth = 1000;
    private double paneHeight = 700;
    private GridPane root;
    private Scene gridScene;
    private final TextField userInputTextField = new TextField();
    private final TextField userInputTextFieldPass = new TextField();
    private ArrayList<String> userNames = new ArrayList<>();
    private  ArrayList eventInfo = new ArrayList<>();
    private String[] passwords= new String[]{};
    private Label infoText = new Label ("Welcome! Please enter your username and password, if you don't have an account" +
                                                "click 'New User' ");
    private HBox buttonHolder;
    private Button[] buttons;
    private HBox buttonHolder2;
    private Button[] buttons2;
    private String fileName = "Accounts.txt";
    private String path = "/Users/sophiekofsky/Documents/";
    private String document = "";
    private String[] accounts;
    private String[] users = new String[]{"user1.txt","user2.txt"};
    private Button newEvent = new Button();



    @Override
    public void start(Stage primaryStage) throws Exception {
        rootSetUp();
        sceneSetUp();
        stageSetUp(primaryStage);
        userInput();
        userInput2();
        buttonsetUp();
        xmlFileReader("user1.txt");
        xmlFileUser();
        xmlFileWriterNewUser("Lucy", "bon");
//        xmlFileWriterAddEvents("Christmas", "12/25/17", "NYC", "user3.txt");

    }//start

    private void buttonsetUp() {
        buttonHolder = new HBox();
        buttons = new Button[1];
        //buttonHolder.setAlignment(Pos.BASELINE_LEFT);
        buttonHolder.setAlignment(Pos.BOTTOM_LEFT);
        buttonHolder.setSpacing(10);
        buttons[0] = new Button();
        buttons[0].setPrefSize(paneWidth / 8, paneHeight / 24);
        buttons[0].setText("Enter");
        buttons[0].setId("button");
        buttonHolder.getChildren().add(buttons[0]);
        root.add(buttonHolder, 2, 5);

        buttons[0].setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for(int i = 0; i < userNames.size(); i = i +2) {
                            //System.out.println(userInputTextField.getText());
                            String name = userInputTextField.getText();
                            String user = userNames.get(i);
                            //System.out.println("user = " + user);
                            //System.out.println("name= " + name);
                           // System.out.println("username[i] = " + userNames.get(i));
                            if (name.equals(user)){
                                System.out.println("If true");
                                //xmlFileReader(userNames.get(i));
                               // if(userInputTextFieldPass.getText() == )
                                System.out.println("end of if one");
                                if (userNames.get(i + 1).equals(userInputTextFieldPass.getText())) {
                                    buttonHolder.setVisible(false);
                                    buttonHolder2.setVisible(false);
                                    //password = userInputTextFieldPass.getText();
                                    userInputTextFieldPass.setVisible(false);
                                    //userName = userInputTextField.getText();
                                    userInputTextField.setVisible(false);
                                    infoText.setText("");
                                    newEvent.setText(" + ");
                                    root.add(newEvent, 9, 0);
                                }
                                /*else{
                                    infoText.setText("Wrong username or password entered");
                                } */

                            }  /*else {
                                infoText.setText("Wrong username or password entered");
                            } */
                        }
                            /* System.out.println("end of if");
                            buttonHolder.setVisible(false);
                            buttonHolder2.setVisible(false);
                            //password = userInputTextFieldPass.getText();
                            userInputTextFieldPass.setVisible(false);
                            //userName = userInputTextField.getText();
                            userInputTextField.setVisible(false);
                            infoText.setText("");
                            newEvent.setText(" + ");
                            root.add(newEvent, 9, 0); */


                        //System.out.println(password);
                        //System.out.println(userName);

                    }
                }
        );

        buttonHolder2 = new HBox();
        buttons2 = new Button[1];
        //buttonHolder.setAlignment(Pos.BASELINE_LEFT);
        buttonHolder2.setAlignment(Pos.BOTTOM_LEFT);
        buttonHolder2.setSpacing(10);
        buttons2[0] = new Button();
        buttons2[0].setPrefSize(paneWidth / 8, paneHeight / 35);
        buttons2[0].setText("New User");
        buttons2[0].setId("button");
        buttonHolder2.getChildren().add(buttons2[0]);
        root.add(buttonHolder2, 2, 7);
        buttons2[0].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        buttonHolder.setVisible(false);
                        buttonHolder2.setVisible(false);
                        userInputTextFieldPass.setVisible(false);
                        userInputTextFieldPass.setText("");
                        userInputTextField.setText("");
                        /*Read r = new Read((path + fileName));
                        r.readFile();
                        document = r.getText();
                        infoText.setText("Type in what you want to be your username then hit the enter key");
                        userInputTextField.setOnAction(new EventHandler<ActionEvent>()  {
                            public void handle(ActionEvent e) {
                                    Writer w = new Writer((path + fileName));
                                    w.writeToFile(document + "\n User: " + userInputTextField.getText());

                                    Read r = new Read((path + fileName));
                                    r.readFile();
                                    document = r.getText();
                                    userInputTextField.clear();
                                    infoText.setText("Type in your password, then hit enter key.");
                                    userInputTextField.setOnAction(new EventHandler<ActionEvent>()  {
                                    public void handle(ActionEvent e) {
                                        w.writeToFile(document + "\n Password: " + userInputTextField.getText());
                                        userInputTextField.clear();
                                        infoText.setText("Thanks for Creating an account!");
                                        userInputTextField.setVisible(false);
                                    }//inner handle
                                });//EventHandler class
                            }//inner handle
                        });//EventHandler class */

                    }//outer handle
                });//setOnAction
        newEvent.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                infoText.setText("Please fill in the description, date and time of event, and optional location or more" +
                        " detail, then click 'Accept' ");
                Label Description = new Label ("Please Enter the description for your event");
                infoText.wrapTextProperty();
                TextField descriptionText = new TextField();
                //descriptionText.
                root.add(Description, 1,0);
                root.add(descriptionText, 1,1);
            }//Handle
        });//EventHandler class
    }//button set up

    private void userInput() {
        userInputTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            //if (userInputTextField.getText() = xmlFileReader(user);

            }//handle
        });//setOnAction
    }

    private void userInput2(){
        userInputTextFieldPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



            }//handle
        });//setOnAction


    }//userInput

    private void rootSetUp() {
        root = new GridPane();
        root.setPadding(new Insets(25, 25, 25, 25));
        infoText.setWrapText(true);
        userInputTextFieldPass.setPromptText("Password");
        userInputTextField.setPromptText("Username");
        root.add(userInputTextFieldPass, 2, 4);
        root.add(userInputTextField, 2, 2);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(35);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(1);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(35);
        root.getColumnConstraints().addAll(column1,column2,column3);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(30);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(5);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        RowConstraints row7 = new RowConstraints();
        row7.setPercentHeight(1);
        root.getRowConstraints().addAll(row1,row2,row3,row4,row5,row6,row7);
        root.add(infoText,2,0);
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        root.setId("background");
        //root.getChildren(newUser);

    }//rootSetUp

    public static void main(String[] args){
        Application.launch(args);
    }//main

    private void sceneSetUp() {
       gridScene = new Scene(root, paneWidth, paneHeight);
    }//sceneSetUp

    private void stageSetUp(Stage s) {
        s.setTitle("Calendar");
        s.setScene(gridScene);
        s.show();
        //xmlFileReader();
    }//stageSetUp


    private void xmlFileReader(String pathname){

            try {
                //array users = new Array[];
                //System.out.print(users[0] + " " + users[1]);
                for (int i = 0; i <users.length; i ++){
                   // System.out.println(users.length);
                   // System.out.println("system.out " + users[i]);
                    File inputFile = new File(users[i]);
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(inputFile);
                    doc.getDocumentElement().normalize();
                    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                    NodeList nList = doc.getElementsByTagName("event");
                    NodeList nList2 = doc.getElementsByTagName("username");
                    System.out.println("----------------------------");
                    for(int temp = 0; temp <nList2.getLength(); temp++){
                        Node nNode2 = nList2.item(temp);
                        Element eElement2 = (Element) nNode2;
                        System.out.println((eElement2.getAttribute("username")));
                        userNames.add((eElement2.getAttribute("username")));
                    }//end for loop
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            eventInfo.add((eElement.getAttribute("event")));
                            eventInfo.add((eElement.getElementsByTagName("date").item(0).getTextContent()));
                            eventInfo.add((eElement.getElementsByTagName("location").item(0).getTextContent()));
                            System.out.println(eventInfo);
                       /*System.out.println("Username : "
                                + eElement.getAttribute("username"));
                        System.out.println("password: "

                                + eElement
                                .getElementsByTagName("password")
                                .item(0)
                                .getTextContent()); */
                        /*    System.out.println("Event : "
                                    + eElement.getAttribute("event"));
                            System.out.println("date: "
                                    + eElement
                                    .getElementsByTagName("date")
                                    .item(0)
                                    .getTextContent());
                            System.out.println("location : "
                                    + eElement
                                    .getElementsByTagName("location")
                                    .item(0)
                                    .getTextContent()); */
                         }//if statement
                        // System.out.println("end inner for loop");
                    }//inner for loop
                    //System.out.println("end outer for loop");
                }//outer for loop
            } catch (Exception e) {
                e.printStackTrace();
            }//catch


    }//private void xml reader
    private void xmlFileUser(){
        try {
            //array users = new Array[];
           // System.out.print(users[0] + " " + users[1]);
            for (int i = 0; i <users.length; i ++){
                // System.out.println(users.length);
                // System.out.println("system.out " + users[i]);
                File inputFile = new File(users[i]);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                NodeList nList = doc.getElementsByTagName("username");
                for(int temp = 0; temp <nList.getLength(); temp++){
                    Node nNode2 = nList.item(temp);
                    Element eElement2 = (Element) nNode2;
                    //System.out.println((eElement2.getAttribute("username")));
                    userNames.add((eElement2.getAttribute("username")));
                    userNames.add((eElement2.getElementsByTagName("password").item(0).getTextContent()));
                    //System.out.println(userNames);
                    //System.out.println(userNames);
                }//end for loop
            }//outer for loop

        } catch (Exception e) {
            e.printStackTrace();
        }//catch

    }//private void xml reader

    public void xmlFileWriterNewUser(String username, String password){

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("class");
            doc.appendChild(rootElement);
            // username elements
            Element usernameE = doc.createElement("username");
            rootElement.appendChild(usernameE);

            // set attribute to username element
            Attr attr = doc.createAttribute("username");
            attr.setValue(username);
            usernameE.setAttributeNode(attr);


            // password elements
            Element passwordE = doc.createElement("password");
            passwordE.appendChild(doc.createTextNode(password));
            usernameE.appendChild(passwordE);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            int usernumber = userNames.size() + 1;
            StreamResult result = new StreamResult(new File("/Users/sophiekofsky/IdeaProjects/11thGrade/user" + usernumber + ".txt"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }//catch
    }//xmlWriter

    public void xmlFileWriterAddEvents(String eventName,String date, String location, String pathname) throws IOException, SAXException {
        try {
            File inputFile = new File("user1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.  parse(inputFile);
            doc.getDocumentElement().normalize();
            Element rootElement = doc.getElementById("class");

            // username elements
            Element eventE = doc.createElement("event");
            rootElement.appendChild(eventE);
            Attr attr = doc.createAttribute("event");
            attr.setValue(eventName);
            eventE.setAttributeNode(attr);
            // set attribute to username element
            attr.setValue(eventName);
            eventE.setAttributeNode(attr);

            // lastname elements
            Element dateE = doc.createElement("date");
            dateE.appendChild(doc.createTextNode(date));
            eventE.appendChild(dateE);
            // nickname elements
            Element locationE = doc.createElement("location");
            locationE.appendChild(doc.createTextNode(location));
            eventE.appendChild(locationE);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathname));

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }//catch

    }

}//class
