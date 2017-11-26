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
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.io.IOException;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

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
import org.w3c.dom.NamedNodeMap;
import javax.xml.parsers.ParserConfigurationException;



public class CalendarReminder extends Application {
    private double paneWidth = 1000;
    private double paneHeight = 700;
    private GridPane root;
    private Scene gridScene;
    private double canvasWidth = paneWidth*0.75;
    private double canvasHeight = paneHeight*0.7;
    private final TextField userInputTextField = new TextField();
    private final TextField userInputTextFieldPass = new TextField();
    private ArrayList<String> userNames = new ArrayList<>();
    private  ArrayList eventInfo = new ArrayList<>();
   // private String[] passwords= new String[]{};
    private Label infoText = new Label ("Welcome! Please enter your username and password, if you don't have an account" +
                                                "click 'New User' ");
    private HBox buttonHolder;
    private Button[] buttons;
    private HBox buttonHolder2;
    private Button[] buttons2;
    /*private String fileName = "Accounts.txt";
    private String path = "/Users/sophiekofsky/Documents/";
    private String document = "";
    private String[] accounts; */
    private String[] users = new String[]{"user1.txt","user2.txt"};
    private String currentUser = new String();
    private Button newEvent = new Button();
    private Button acceptNewEvent = new Button();
    private RowConstraints row1 = new RowConstraints();
    private RowConstraints row2 = new RowConstraints();
    private RowConstraints row3 = new RowConstraints();
    private RowConstraints row4 = new RowConstraints();
    private RowConstraints row5 = new RowConstraints();
    private RowConstraints row6 = new RowConstraints();
    private RowConstraints row7 = new RowConstraints();
    private ColumnConstraints column1 = new ColumnConstraints();
    private TextField descriptionText = new TextField();
    private TextField dateText = new TextField();
    private TextField locationText = new TextField();
    private TextField moreInfoText = new TextField();
    Canvas myCanvas = new Canvas(canvasWidth,canvasHeight);




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
       xmlFileWriterAddEvents("Christmas ","10/25/17","New York", "user3.txt");
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
                        for(int i = 0; i < userNames.size(); i = i +3) {
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
                                    ColumnConstraints column4 = new ColumnConstraints();
                                    column4.setPercentWidth(35);
                                    root.add(newEvent, 10, 0);
                                    canvasSetUp();
                                    currentUser = userNames.get(i+2);
                                    System.out.println(currentUser);

                                }

                            }
                        }
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

                    }//outer handle
                });//setOnAction
        newEvent.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                newEvent.setVisible(false);
                acceptNewEvent.setText("Accept");
                acceptNewEvent.setMinSize(80,30);
                acceptNewEvent.setMaxSize(80,30);
                infoText.wrapTextProperty();
                infoText.setText("Please fill in the description, and date of event, and optional location or more" +
                        " detail, then click 'Accept' ");
                Label Description = new Label ("Please Enter the description for your event");
                Description.wrapTextProperty();


                descriptionText.setPromptText("Description");
                descriptionText.setMaxSize(190,30);
                descriptionText.setMinSize(190,30);


                dateText.setPromptText("Date (mm/dd/yy)");
                dateText.setMaxSize(190,30);
                dateText.setMinSize(190,30);


                locationText.setPromptText("Location");
                locationText.setMaxSize(190,30);
                locationText.setMinSize(190,30);


                moreInfoText.setPromptText("More info");
                moreInfoText.setMaxSize(190,30);
                moreInfoText.setMinSize(190,30);

                //descriptionText.
                //root.add(Description, 1,0);
                row4.setPercentHeight(10);
                row3.setPercentHeight(10);
                row2.setPercentHeight(10);
                row1.setPercentHeight(10);
                row5.setPercentHeight(10);
                root.add(descriptionText, 1,1);
                root.add(dateText,1,2);
                root.add(locationText,1,3);
                root.add(moreInfoText,1,4);
                root.add(acceptNewEvent,1,5);

            }//Handle
        });//EventHandler class
        acceptNewEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] newEventInfo = new String[4];
                newEventInfo[0] = descriptionText.getText();
                newEvent.setVisible(true);
                acceptNewEvent.setVisible(false);
                newEventInfo[1] = dateText.getText();
                newEventInfo[2] = locationText.getText();
                newEventInfo[3] = moreInfoText.getText();
                descriptionText.setVisible(false);
                dateText.setVisible(false);
                locationText.setVisible(false);
                moreInfoText.setVisible(false);
                infoText.setVisible(false);
                System.out.println(newEventInfo[0] + newEventInfo[1]+newEventInfo[2]+newEventInfo[3]);
                myCanvas.setVisible(false);

            }
        });
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
        column1.setPercentWidth(35);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(1);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(35);
        root.getColumnConstraints().addAll(column1,column2,column3);
        row1.setPercentHeight(30);
        row2.setPercentHeight(5);
        row3.setPercentHeight(10);
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
                    File inputFile = new File(pathname);
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(inputFile);
                    doc.getDocumentElement().normalize();
                   // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                    NodeList nList = doc.getElementsByTagName("event");
                    //System.out.println(nList.getLength());
                    NodeList nList2 = doc.getElementsByTagName("username");
                    for(int temp = 0; temp <nList2.getLength(); temp++){
                        Node nNode2 = nList2.item(temp);
                        Element eElement2 = (Element) nNode2;
                        System.out.println((eElement2.getAttribute("username")));
                       // userNames.add((eElement2.getAttribute("username")));
                        //userNames.add(pathname);
                    }//end for loop
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        System.out.println("\nCurrent Element :" + nNode.getNodeName());
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            eventInfo.add((eElement.getAttribute("event")));
                            eventInfo.add((eElement.getElementsByTagName("date").item(0).getTextContent()));
                            eventInfo.add((eElement.getElementsByTagName("location").item(0).getTextContent()));
                            System.out.println(temp + " event info " + eventInfo);
                         }//if statement
                        // System.out.println("end inner for loop");
                    }//inner for loop

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
                    userNames.add(users[i]);
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
            Element rootElement = doc.createElement("user");
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

    public void xmlFileWriterAddEvents(String eventName,String dateInfo, String locationInfo, String pathName) throws IOException, SAXException {
        try {
            String filepath = pathName;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node user = doc.getFirstChild();

          

            // Get the staff element by tag name directly
            Node username = doc.getElementsByTagName("username").item(0);


            // append a new node to staff
            Element event = doc.createElement("event");

            event.setAttribute("event", eventName);
            username.appendChild(event);
            Element date = doc.createElement("date");
            date.appendChild(doc.createTextNode(dateInfo));
            event.appendChild(date);
            Element location = doc.createElement("location");
            date.appendChild(doc.createTextNode(locationInfo));
            event.appendChild(location);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Done");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }
    private void canvasSetUp(){

        GraphicsContext gc = myCanvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        row2.setPercentHeight(5);
        row1.setPercentHeight(10);
        column1.setPercentWidth(10);
        root.add(myCanvas,1,3);
        //root.setAlignment(Pos.CENTER);
        gc.setFill(Color.MAROON);
        gc.fillText("Event:", 10, 50);
        gc.fillText("Date:", 180, 50);
        gc.fillText("Location:", 350, 50);
        for (int i = 0; i < eventInfo.size(); i= i +3){
            for (int j = 0; j <3; j++){
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(eventInfo.get(i+j)), 10 + 170*(j), 50+30*(i/3+1));

            }
           // gc.fillText(String.valueOf(eventInfo.get(i)), 180, 80);

            Calendar cal = Calendar.getInstance();

            int d = cal.get(Calendar.DAY_OF_MONTH);



        }

    }
}//class
