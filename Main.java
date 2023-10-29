import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

interface Interface {
    void window(String x);

    void setTime(String t, String y);

    void Changer(String x);

    String getTime(String t, int i);
    
    String[] getArray(String word);
    
}

class MainSwitch extends JFrame {
    private JButton mainbtn;
    private JButton settingbtn;
    private JPanel btnPanel;
    private JPanel timePanel;
    private JSpinner spinnerHour;
    private JSpinner spinnerMin;
    private Controller controller;
    private Settings settings;

    MainSwitch() {
        setSize(350, 250);
        setTitle("Switch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(360,100);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(200, 200, 210));
        

        ////----- Creating the button panel -----////
        btnPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        btnPanel.setBackground(new Color(0, 0, 128));

        ////----- Creating the (ToggleButton) main button -----////
        mainbtn = new JButton("ON");
        mainbtn.setForeground(Color.WHITE);
        mainbtn.setFont(new Font("", 1, 15));
        mainbtn.setBackground(Color.RED);
        controller = Controller.getController();
        mainbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (mainbtn.getText().equals("ON")) {
                    timeConverter();
                    mainbtn.setForeground(Color.WHITE);
                    mainbtn.setBackground(Color.GREEN);
                    mainbtn.setText("OFF");
                } else {
                    controller.getdevices(0).window(mainbtn.getText());
                    controller.getdevices(1).window(mainbtn.getText());
                    controller.getdevices(2).window(mainbtn.getText());
                    controller.getdevices(3).window(mainbtn.getText());
                    mainbtn.setForeground(Color.WHITE);
                    mainbtn.setBackground(Color.RED);
                    mainbtn.setText("ON");
                }
            }
        });

        ////----- Creating the settings button -----////
        settingbtn = new JButton("Settings");
        settingbtn.setFont(new Font("", 1, 15));
        settingbtn.setBackground(new Color(128, 0, 0));
        settingbtn.setForeground(Color.WHITE);
        settingbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (settings == null) {
                    settings = new Settings();
                }
                settings.setVisible(true);
            }
        });

        ////----- Creating the time panel -----////
        timePanel = new JPanel(new FlowLayout());
        timePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		timePanel.setBackground(new Color(200, 200, 210));
        ////----- Creating the hour spinner -----////
        JLabel hour = new JLabel("Hour:");
        hour.setFont(new Font("", Font.BOLD, 15));
        SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
        spinnerHour = new JSpinner(hourModel);
        JSpinner.NumberEditor hourEditor = new JSpinner.NumberEditor(spinnerHour, "00");
        hourEditor.getTextField().setFont(new Font("", 1, 15));
        hourEditor.getTextField().setEnabled(true);
        spinnerHour.setEditor(hourEditor);
        spinnerHour.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                timeConverter();
            }
        });
        
        JLabel name = new JLabel("                                       @sahanKumarage");
        name.setLayout(new FlowLayout());

        ////----- Creating the minute spinner -----////
        JLabel min = new JLabel("  Minutes:");
        min.setFont(new Font("", Font.BOLD, 15));
        SpinnerNumberModel minModel = new SpinnerNumberModel(0, 0, 59, 1);
        spinnerMin = new JSpinner(minModel);
        JSpinner.NumberEditor minEditor = new JSpinner.NumberEditor(spinnerMin, "00");
        minEditor.getTextField().setFont(new Font("", 1, 15));
        minEditor.getTextField().setEnabled(false);
        spinnerMin.setEditor(minEditor);
        spinnerMin.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                timeConverter();
            }
        });
        
        

        ////----- Adding components to the time panel -----////
        timePanel.add(hour);
        timePanel.add(spinnerHour);
        timePanel.add(min);
        timePanel.add(spinnerMin);

        ////----- Adding buttons to the button panel -----////
        btnPanel.add(mainbtn);
        btnPanel.add(settingbtn);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        ////----- Adding panels to the main frame -----////
        add(btnPanel);
        add("North", timePanel);
        add("South", name);
        
        

    }

    public void timeConverter() {
        ////----- Retrieve the selected hour and minute values from the spinners -----////
        int Hour = (int) spinnerHour.getValue();
        int Min = (int) spinnerMin.getValue();

        ////----- Format the time string as HH:MM -----////
        String time = String.format("%02d:%02d", Hour, Min);

        ////----- Update the settings for all items controlled by the controller -----////
        for(int i = 0; i < 4; i++) {
            controller.getdevices(i).Changer(time); 
        }
    }
}

class Settings extends JFrame {
    private JButton btntvLivingRoom;
    private JButton btnspeakerLivingRoom;
    private JButton btnwindowLivingRoom;
    private JButton btntvDinningRoom;

    Settings() {
        setSize(380, 240);
        setTitle("Controller");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(720,100);
        setLayout(new BorderLayout());

        ////----- Creating the button panel -----////
        JPanel btnPanel = new JPanel(new GridLayout(4, 1));

        ////----- Creating the "TV Living Room" button -----////
        btntvLivingRoom = new JButton("TV Living Room");
        btntvLivingRoom.setFont(new Font("", 1, 15));
        btntvLivingRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getDeviceController(0).setVisible(true);
            }
        });

        ////----- Creating the "Speaker Living Room" button -----////
        btnspeakerLivingRoom = new JButton("Speaker Living Room");
        btnspeakerLivingRoom.setFont(new Font("", 1, 15));
        btnspeakerLivingRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getDeviceController(1).setVisible(true);
            }
        });

        ////----- Creating the "Window Living Room" button -----////
        btnwindowLivingRoom = new JButton("Window Living Room");
        btnwindowLivingRoom.setFont(new Font("", 1, 15));
        btnwindowLivingRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getDeviceController(2).setVisible(true);
            }
        });

        ////----- Creating the "TV Dinning Room" button -----////
        btntvDinningRoom = new JButton("TV Dinning Room");
        btntvDinningRoom.setFont(new Font("", 1, 15));
        btntvDinningRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getDeviceController(3).setVisible(true);
            }
        });

        ////----- Adding buttons to the button panel -----////
        btnPanel.add(btntvLivingRoom);
        btnPanel.add(btnspeakerLivingRoom);
        btnPanel.add(btnwindowLivingRoom);
        btnPanel.add(btntvDinningRoom);

        ////----- Adding the button panel to the frame -----////
        add(btnPanel);
    }
}

class DeviceController extends JFrame {
    private JPanel timePanel;
    private JLabel startHourLabel;
    private JSpinner startHourSpinner;
    private JLabel startMinLabel;
    private JSpinner startMinSpinner;
    private JLabel endHourLabel;
    private JSpinner endHourSpinner;
    private JLabel endMinLabel;
    private JSpinner endMinSpinner;
    private JButton btnSet;
    private String time;
    private int index;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JToggleButton[] btnArray = new JToggleButton[0];
    private ButtonGroup btngroup = new ButtonGroup();
    private String start;
    private String end;

    public DeviceController(String name) {
        setSize(820, 240);
        setTitle(name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(360,360);
        setLayout(new BorderLayout());

        ////----- Time panel -----////
        timePanel = new JPanel(new FlowLayout());
        timePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        ////----- Creating the "Start Hour" label and spinner -----////
        startHourLabel = new JLabel("Start->  Hour:");
        startHourLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel startHourModel = new SpinnerNumberModel(0, 0, 23, 1);
        startHourSpinner = new JSpinner(startHourModel);
        JSpinner.NumberEditor startHourEditor = new JSpinner.NumberEditor(startHourSpinner, "00");
        startHourEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        startHourEditor.getTextField().setEnabled(false);
        startHourSpinner.setEditor(startHourEditor);

        ////----- Creating the "Start Minute" label and spinner -----////
        startMinLabel = new JLabel("  Minutes:");
        startMinLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel startMinModel = new SpinnerNumberModel(0, 0, 59, 1);
        startMinSpinner = new JSpinner(startMinModel);
        JSpinner.NumberEditor startMinEditor = new JSpinner.NumberEditor(startMinSpinner, "00");
        startMinEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        startMinEditor.getTextField().setEnabled(false);
        startMinSpinner.setEditor(startMinEditor);

        ////----- Creating the "End Hour" label and spinner -----////
        endHourLabel = new JLabel("   End - Hour:");
        endHourLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel endHourModel = new SpinnerNumberModel(0, 0, 23, 1);
        endHourSpinner = new JSpinner(endHourModel);
        JSpinner.NumberEditor endHourEditor = new JSpinner.NumberEditor(endHourSpinner, "00");
        endHourEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        endHourEditor.getTextField().setEnabled(false);
        endHourSpinner.setEditor(endHourEditor);

        ////----- Creating the "End Minute" label and spinner -----////
        endMinLabel = new JLabel("  Minutes:");
        endMinLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel endMinModel = new SpinnerNumberModel(0, 0, 59, 1);
        endMinSpinner = new JSpinner(endMinModel);
        JSpinner.NumberEditor endMinEditor = new JSpinner.NumberEditor(endMinSpinner, "00");
        endMinEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        endMinEditor.getTextField().setEnabled(false);
        endMinSpinner.setEditor(endMinEditor);

        ////----- Determining the index based on the item name -----////
        if (name.equals("TV Living Room")){
            index = 1;
        } else if(name.equals("Speaker Living Room")){
            index = 0;
        } else if (name.equals("Window Living Room")){
            index = 2;
        } else {
            index = 3;
        }

        ////----- Creating the button panel for toggling items -----////
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(800, 160));

        ////----- Creating a scroll pane for the button panel -----////
        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        ////----- Creating the "Set" button -----////
        btnSet = new JButton("Set");
        btnSet.setFont(new Font("", 1, 15));
        btnSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (btnSet.getText().equals("Set")) {
                    ////----- Getting the selected start and end times from the spinners -----////
                    int startHour = (int) startHourSpinner.getValue();
                    int startMin = (int) startMinSpinner.getValue();
                    int endHour = (int) endHourSpinner.getValue();
                    int endMin = (int) endMinSpinner.getValue();
                    time = String.format("Start Time: %02d:%02d      End Time: %02d:%02d%n", startHour, startMin,endHour, endMin);
                    start = String.format("%02d:%02d", startHour, startMin);
                    end = String.format("%02d:%02d", endHour, endMin);

                    ////----- Creating a new toggle button for the time range and adding it to the button panel -----////
                    JToggleButton btn = new JToggleButton(time);
                    btngroup.add(btn);
                    btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            ////----- Handling the "Edit" button click to update the spinners with the selected time range -----////
                            btnSet.setText("Edit");
                            for (int i = 0; i < btnArray.length; i++) {
                                if (btnArray[i].isSelected()) {
                                    ////----- Retrieving the start and end times for the selected time range -----////
                                    String[] startnewtime = Controller.getController().getdevices(index).getTime("start",i).split(":");
                                    System.out.println(Arrays.toString(startnewtime));
                                    String[] endnewtime = Controller.getController().getdevices(index).getTime("end",i).split(":");
                                    System.out.println(Arrays.toString(endnewtime));
                                    ////----- Updating the spinners with the selected start and end times -----////
                                    startHourSpinner.setValue(Integer.parseInt(startnewtime[0]));
                                    startMinSpinner.setValue(Integer.parseInt(startnewtime[1]));
                                    endHourSpinner.setValue(Integer.parseInt(endnewtime[0]));
                                    endMinSpinner.setValue( Integer.parseInt(endnewtime[1]));
                                }
                            }
                        }
                    });
                    btn.setFont(new Font("", 1, 15));
                    btn.setBackground(new Color(255, 255, 255));
                    btn.setBorder(null);
                    btnArray = extendToggleButtonArray(btnArray);
                    btnArray[btnArray.length - 1] = btn;
                    buttonPanel.add(btnArray[btnArray.length - 1]);

                    ////----- Updating the item's start and end times in the controller -----////
                    Controller.getController().getdevices(index).setTime(start, end);

                    ////----- Updating the view -----////
                    revalidate();
                    repaint();
                } else if (btnSet.getText().equals("Edit")) {
                    ////----- Handling the "Edit" button click to update the time range in the controller -----////
                    for (int i = 0; i < btnArray.length; i++) {
                        if (btnArray[i].isSelected()) {
                            ////----- Getting the selected start and end times from the spinners -----////
                            int startHour = (int) startHourSpinner.getValue();
                            int startMin = (int) startMinSpinner.getValue();
                            int endHour = (int) endHourSpinner.getValue();
                            int endMin = (int) endMinSpinner.getValue();
                            time = String.format("Start Time: %02d:%02d      End Time: %02d:%02d%n", startHour,startMin, endHour, endMin);
                            start = String.format("%02d:%02d", startHour, startMin);
                            end = String.format("%02d:%02d", endHour, endMin);

                            ////----- Updating the start and end times for the selected time range in the controller -----////
                            Controller.getController().getdevices(index).getArray("start")[i] = start;
                            Controller.getController().getdevices(index).getArray("end")[i] = end;

                            ////----- Updating the button text with the updated time range -----////
                            btnArray[i].setText(time);
                            btnSet.setText("Set");

                            ////----- Updating the view -----////
                            revalidate();
                            repaint();
                            break;
                        }
                    }
                }
            }
        });

        ////----- Adding components to the time panel -----////
        timePanel.add(startHourLabel);
        timePanel.add(startHourSpinner);
        timePanel.add(startMinLabel);
        timePanel.add(startMinSpinner);
        timePanel.add(endHourLabel);
        timePanel.add(endHourSpinner);
        timePanel.add(endMinLabel);
        timePanel.add(endMinSpinner);
        timePanel.add(btnSet);

        ////----- Adding the scroll pane and time panel to the frame -----////
        add(scrollPane, BorderLayout.NORTH);
        add(timePanel, BorderLayout.SOUTH);
    }

    public void deselectButtons() {
        ////----- Clearing the selection of toggle buttons in the button group -----////
        btngroup.clearSelection();
    }

    private JToggleButton[] extendToggleButtonArray(JToggleButton[] array) {
        ////----- Extending the toggle button array by creating a new array with increased length and copying the existing elements -----////
        JToggleButton[] newArray = new JToggleButton[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
}

class Controller implements Interface{
     Devices[] devices = {new Devices("SPEAKER",10,100), new Devices("TV",10,250), new Devices("WINDOW",10,400), new Devices("TV",10,550)};
     DeviceController [] deviceControllers = {new DeviceController("TV Living Room"), new DeviceController("Speaker Living Room"),new DeviceController("Window Living Room"),new DeviceController("TV Dining Room")};
    
     static Controller controller;

    private Controller(){
    
    }

    public static Controller getController(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public Interface getdevices(int i){
        return devices[i];
    }
    
    public DeviceController getDeviceController(int i){
        return deviceControllers[i];
    } 

    public void window(String x) {    }

    public void setTime(String t, String y){ 
    }

    public void Changer(String x){ }

    public String getTime(String t, int i){
        return null;
    }
    public String[] getArray(String word){
        return null;
    }

}


class Devices extends JFrame implements Interface {
    private JLabel Label;
    private String[] starttime = new String[0];
    private String[] endtime = new String[0];

    public Devices(String name, int x, int y) {
        setVisible(true);
        setSize(300, 150);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(x, y);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.RED);

        Label = new JLabel("OFF");
        Label.setFont(new Font("", Font.BOLD, 30));
        Label.setForeground(Color.WHITE);
        add(BorderLayout.CENTER, Label);
    }

    private void updateBackgroundColor() {
        if (Label.getText().equals("OFF")) {
            getContentPane().setBackground(Color.RED);
        } else if (Label.getText().equals("ON")) {
            getContentPane().setBackground(Color.GREEN);
        }
        // Repaint the JFrame to apply the background color changes
        repaint();
    }

    public void window(String word) {
        this.Label.setText(word);
        updateBackgroundColor();
    }

    public void setTime(String x, String y) {
        starttime = addTime(starttime, x);
        endtime = addTime(endtime, y);
    }

    public void Changer(String time) {
        String[] currentTime = time.split(":");
        int currentHour = Integer.parseInt(currentTime[0]);
        int currentMinute = Integer.parseInt(currentTime[1]);

        for (int i = 0; i < endtime.length; i++) {
            int startH = Integer.parseInt(starttime[i].split(":")[0]);
            int startM = Integer.parseInt(starttime[i].split(":")[1]);
            int endH = Integer.parseInt(endtime[i].split(":")[0]);
            int endM = Integer.parseInt(endtime[i].split(":")[1]);

            if (currentHour > startH || (currentHour == startH && currentMinute > startM)) {
                Label.setText("ON");
            }
            if (currentHour > endH || (currentHour == endH && currentMinute > endM)) {
                Label.setText("OFF");
            }
        }
        updateBackgroundColor();
    }

    public String[] addTime(String[] ar, String time) {
        String[] temp = new String[ar.length + 1];
        for (int i = 0; i < ar.length; i++) {
            temp[i] = ar[i];
        }
        temp[ar.length] = time;
        return temp;
    }

    public String getTime(String word, int i) {
        if (word.equals("start")) {
            return starttime[i];
        } else {
            return endtime[i];
        }
    }

    public String[] getArray(String word) {
        if (word.equals("start")) {
            return starttime;
        } else {
            return endtime;
        }
    }
}



public class Main {
    public static void main(String[] args){
        MainSwitch s1=new MainSwitch();
        s1.setVisible(true);
    }
    
}
