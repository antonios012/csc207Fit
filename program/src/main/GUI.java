import Account.*;
import DateAndObject.DateAndExercise;
import DateAndObject.DateAndFood;
import OtherObjects.ModDate;
import static javax.swing.JOptionPane.showMessageDialog;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GUI {
    //set current account to be empty
    ManageAccount accman= new ManageAccount();

    Account current_account= accman.creatEmptyAcc();
    JFrame frame = new JFrame();

    public void switchPanel(JPanel panel){
        //frame.removeAll();
        //frame.add(panel);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

    }
    public GUI(){


        //Login panel
        JPanel Logpanel = new JPanel(null);



        //sign up panel
        JPanel SignPanel = new JPanel(null);
        //SignPanel.setBorder(BorderFactory.createEmptyBorder(30,30,50,50));
        //SignPanel.setLayout(new GridLayout(0,1));

        //main menu panel
        JPanel MainMenuPanel= new JPanel(null);

        //account info panel
        JPanel AccInfoPanel =new JPanel(null);

        //Food.Food Panel
        JPanel FoodPanel= new JPanel(null);


        //sign up switch button
        JButton SignUpSwitch = new JButton("Create Account");
        SignUpSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(SignPanel);
            }
        });
        SignUpSwitch.setBounds(10,200,150,25);
        Logpanel.add(SignUpSwitch);
        //////////////////////////////////////////////////
        //login switch button
        JButton LoginSwitch = new JButton("Already have account");
        LoginSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(Logpanel);
            }
        });
        LoginSwitch.setBounds(10,200,200,25);
        SignPanel.add(LoginSwitch);
        //////////////////////////////////////////

        //*********************************************************
        //*********************************************************
        //Sign up input
        JLabel userLabel = new JLabel("User Name");
        userLabel.setBounds(10,20,80,25);
        SignPanel.add(userLabel);
        /////////////////////////////////////////////
        JTextField CreateUserText= new JTextField(20);
        CreateUserText.setBounds(10,50,100,25);
        SignPanel.add(CreateUserText);
        //////////////////////////////////////////////////
        JLabel passLabel= new JLabel("Password");
        passLabel.setBounds(10,100,80,50);
        SignPanel.add(passLabel);
        //////////////////////////////////////////////////
        JPasswordField CreatePassField = new JPasswordField();
        //CreatePassField.setBounds(10,200,80,60);
        CreatePassField.setBounds(10,140,100,25);
        SignPanel.add(CreatePassField);
        /////////////////////////////////////////////
        JButton SaveAccount = new JButton("Register");
        SaveAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username= CreateUserText.getText();
                String passward = CreatePassField.getText();

                try {
                    if (accman.scan_has_account(username)){
                        showMessageDialog(null,"User already exists!");}
                    else{
                        //current_account=accman.creatEmptyAcc();
                        current_account.setUsername(username);
                        current_account.setPassword(passward);
                        current_account.register();
                        showMessageDialog(null,"Success!");
                        switchPanel(MainMenuPanel);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        SaveAccount.setBounds(10,230,150,25);
        SignPanel.add(SaveAccount);
        //*********************************************************
        //*********************************************************
        //Login panel
        JLabel LoguserLabel = new JLabel("User Name");
        LoguserLabel.setBounds(10,20,80,25);
        Logpanel.add(LoguserLabel);
        /////////////////////////////////////////////////////
        JLabel LogpassLabel= new JLabel("Password");
        LogpassLabel.setBounds(10,100,80,50);
        Logpanel.add(LogpassLabel);
        ///////////////////////////////////////////////////
        JTextField LogUserText= new JTextField(20);
        LogUserText.setBounds(10,50,100,25);
        Logpanel.add(LogUserText);
        //////////////////////////////////////////////////////////
        JPasswordField LogPassField = new JPasswordField();
        LogPassField.setBounds(10,140,100,25);
        Logpanel.add(LogPassField);
        ////////////////////////////////////////////////////////
        JButton LogInButton = new JButton("Login");
        LogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user=LogUserText.getText();
                String pass =LogPassField.getText();
                try {
                    if(accman.login(user,pass)){
                        current_account.setUsername(user);
                        current_account.setPassword(pass);
                        current_account.FillInfo();
                        showMessageDialog(null,"Success!");
                        switchPanel(MainMenuPanel);
                    }
                    else{
                        showMessageDialog(null,"Wrong username or password.");
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }



            }
        });
        LogInButton.setBounds(10,230,150,25);
        Logpanel.add(LogInButton);
        //*********************************************************
        //*********************************************************
        //Main menu
        JLabel MenuTitle = new JLabel("Main Menu");
        MenuTitle.setBounds(470,10,200,100);
        MainMenuPanel.add(MenuTitle);
        //////////////////////////////////////////////////
        JButton SwitchAccount = new JButton("Switch Account");
        SwitchAccount.setBounds(300,80,400,50);
        SwitchAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set current account to empty
                current_account=accman.creatEmptyAcc();
                switchPanel(Logpanel);
            }
        });
        MainMenuPanel.add(SwitchAccount);
        ///////////////////////////////////////////////////////////////////
        JButton GoToFood = new JButton("Food");
        GoToFood.setBounds(300,160,400,50);
        GoToFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(FoodPanel);
            }
        });
        MainMenuPanel.add(GoToFood);
        ///////////////////////////////////////////////////////////////////////
        JButton GoToExercise = new JButton("Exercise");
        GoToExercise.setBounds(300,240,400,50);
        GoToExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                //switchPanel();
            }
        });
        MainMenuPanel.add(GoToExercise);
        ////////////////////////////////////////////////////////////////////////
        JButton GoToAccInfo = new JButton("Account Info");
        GoToAccInfo.setBounds(300,320,400,50);
        GoToAccInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(AccInfoPanel);
            }
        });
        MainMenuPanel.add(GoToAccInfo);
        ///////////////////////////////////////////////////////////////////////
        JButton SaveButton = new JButton("Save");
        SaveButton.setBounds(300,400,400,50);
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    current_account.register();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        MainMenuPanel.add(SaveButton);
        ///////////////////////////////////////////////////////////////////////
        JTextArea Savewarning = new JTextArea("""
                Warning: Always save before\s
                you close the application.\s
                Anything unsaved will be lost.""");
        Savewarning.setBounds(750,400,200,55);
        MainMenuPanel.add(Savewarning);
        //*********************************************************
        //*********************************************************
        //account info panel
        JLabel AccInfoTitle = new JLabel("Account Info");
        AccInfoTitle.setBounds(470,10,200,100);
        AccInfoPanel.add(AccInfoTitle);
        /////////////////////////////////////////////////////////////////////
        JLabel lblSetGender = new JLabel("Set Gender");
        lblSetGender.setBounds(360,60,200,100);
        AccInfoPanel.add(lblSetGender);
        ///////////////////////////////////////////////////////////////////
        JLabel lblSetBirth = new JLabel("Set Birthday(year/month/day)");
        lblSetBirth.setBounds(360,100,200,100);
        AccInfoPanel.add(lblSetBirth);
        ////////////////////////////////////////////////////////////////
        JLabel lblSetWeight = new JLabel("Set Weight");
        lblSetWeight.setBounds(360,140,200,100);
        AccInfoPanel.add(lblSetWeight);
        ////////////////////////////////////////////////////////////////
        JButton SetMale = new JButton("Male");
        SetMale.setBounds(480,100,100,20);
        SetMale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_account.setGender("m");
            }
        });
        AccInfoPanel.add(SetMale);
        /////////////////////////////////////////////////////////////
        JButton SetFemale = new JButton("Female");
        SetFemale.setBounds(600,100,100,20);
        SetFemale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_account.setGender("f");
            }
        });
        AccInfoPanel.add(SetFemale);
        ///////////////////////////////////////////////////////////
        JTextField byearText = new JTextField();
        byearText.setBounds(540,140,60,20);
        AccInfoPanel.add(byearText);
        ///////////////////////////////////////////////////////
        JTextField bmonthText = new JTextField();
        bmonthText.setBounds(605,140,60,20);
        AccInfoPanel.add(bmonthText);
        ///////////////////////////////////////////////////////
        JTextField bdayText = new JTextField();
        bdayText.setBounds(670,140,60,20);
        AccInfoPanel.add(bdayText);
        ///////////////////////////////////////////////////////
        JButton SetBirth = new JButton("Set");
        SetBirth.setBounds(735,140,100,20);
        SetBirth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(accman.isInt(byearText.getText())&accman.isInt(bmonthText.getText())
                &accman.isInt(bdayText.getText())){
                    ModDate birth = new ModDate(Integer.parseInt(byearText.getText()),
                            Integer.parseInt(bmonthText.getText()),
                            Integer.parseInt(bdayText.getText()));
                    current_account.setBirthday(birth);
                    showMessageDialog(null,"Success!");
                }
                else{showMessageDialog(null,"Input must be integers.");}

            }
        });
        AccInfoPanel.add(SetBirth);
        /////////////////////////////////////////////////////////
        JTextField weightText = new JTextField();
        weightText.setBounds(440,180,90,20);
        AccInfoPanel.add(weightText);
        /////////////////////////////////////////////////////////
        JButton SetWeight = new JButton("Set");
        SetWeight.setBounds(535,180,100,20);
        SetWeight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(accman.isDouble(weightText.getText())){

                    current_account.setWeight(Double.parseDouble(weightText.getText()));
                    showMessageDialog(null,"Success!");
                }
                else{showMessageDialog(null,"Input must be a double.");}

            }
        });
        AccInfoPanel.add(SetWeight);
        /////////////////////////////////////////////////////////
        JButton BackMenuAccInfo = new JButton("Menu");
        BackMenuAccInfo.setBounds(360,400,100,30);
        BackMenuAccInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(MainMenuPanel);
            }
        });
        AccInfoPanel.add(BackMenuAccInfo);
        //////////////////////////////////////////////////////////
        //*********************************************************
        //*********************************************************
        //Food.Food panel
        JLabel FoodpanelTitle = new JLabel("Food");
        FoodpanelTitle.setBounds(470,10,200,100);
        FoodPanel.add(FoodpanelTitle);
        ///////////////////////////////////////////////////////////
        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(360,60,200,100);
        FoodPanel.add(lblDate);
        ///////////////////////////////////////////////////////////
        JTextField DateText = new JTextField();
        DateText.setBounds(400,100,120,20);
        FoodPanel.add(DateText);
        /////////////////////////////////////////////////////////
        JButton BackMenuFood = new JButton("Menu");
        BackMenuFood.setBounds(360,400,100,30);
        BackMenuFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(MainMenuPanel);
            }
        });
        FoodPanel.add(BackMenuFood);
        ///////////////////////////////////////////////////////////
//        JButton AddDate = new JButton("Add");
//        AddDate.setBounds(520,100,70,20);
//        AddDate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //TODO
//            }
//        });
//        FoodPanel.add(AddDate);
        ///////////////////////////////////////////////////////////
        JLabel lblFood = new JLabel("Enter Food:");
        lblFood.setBounds(360,100,200,100);
        FoodPanel.add(lblFood);
        /////////////////////////////////////////////////////////////
        JTextField FoodText = new JTextField();
        FoodText.setBounds(450,140,120,20);
        FoodPanel.add(FoodText);
        ////////////////////////////////////////////////////////////
        JButton AddFood = new JButton("Add");
        AddFood.setBounds(580,140,70,20);
        AddFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        FoodPanel.add(AddFood);
        ////////////////////////////////////////////////////////////////
        JButton SearchFood = new JButton("Search");
        SearchFood.setBounds(660,140,90,20);
        SearchFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        FoodPanel.add(SearchFood);
        ///////////////////////////////////////////////////////////////
        JLabel lblWeight = new JLabel("Enter Food Weight:");
        lblWeight.setBounds(360,140,200,100);
        FoodPanel.add(lblWeight);
        /////////////////////////////////////////////////////////////
        JTextField WeightText = new JTextField();
        WeightText.setBounds(500,180,120,20);
        FoodPanel.add(WeightText);
        ////////////////////////////////////////////////////////////
        JButton deleteFood = new JButton("Delete");
        deleteFood.setBounds(760,140,90,20);
        deleteFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        FoodPanel.add(deleteFood);
        //*********************************************************
        //*********************************************************
        frame.add(Logpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Fitness");
        frame.setSize(1000,600);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new GUI();
    }
}