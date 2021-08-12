import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    public JButton btn;
    public WebDriver driver;

    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);

    }



    Container container = getContentPane();
    JLabel labelXpath = new JLabel("Enter Xpath :");
    JLabel labelValue = new JLabel("Value to set :");
    JLabel labelPort = new JLabel("Debugger Port :");


    JTextField xpath = new JTextField();
    JTextField value = new JTextField();
    JTextField port = new JTextField();

    JButton ClickButton=new JButton("Click");
    JButton SetText=new JButton("SetText");
    JButton InitializeBrowser=new JButton("InitializeDriver");
    JButton KillSession=new JButton("KillSession");


    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        labelXpath.     setBounds(40, 40, 300, 30);
        labelValue.     setBounds(40, 70, 150, 30);
        labelPort.      setBounds(40, 100, 100, 30);

        xpath.          setBounds(140, 40, 150, 30);
        value.          setBounds(140, 70, 150, 30);
        port.           setBounds(140, 100, 100, 30);

        ClickButton.          setBounds(130, 140, 100, 30);
        SetText.        setBounds(130, 170, 100, 30);
        InitializeBrowser.setBounds(130, 200, 150, 30);
        KillSession.setBounds(130, 230, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(labelXpath);
        container.add(labelValue);
        container.add(labelPort);
        container.add(xpath);
        container.add(value);
        container.add(port);
        container.add(ClickButton);
        container.add(SetText);
        container.add(InitializeBrowser);
        container.add(KillSession);
    }

    public void addActionEvent() {
        ClickButton.addActionListener(this);
        SetText.addActionListener(this);
        InitializeBrowser.addActionListener(this);
        KillSession.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName= ((JButton) e.getSource()).getText();

        switch (btnName){
            case "Click":
                this.ClickButton();
                break;
            case "SetText":
                this.SetText();
                break;
            case "InitializeDriver":
                this.InitializeBrowser();
                break;
            case "KillSession":
                this.KillSession();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid Click","",JOptionPane.INFORMATION_MESSAGE);
                break;
        }

    }

    public void ClickButton(){
        try {
            driver.findElement(By.xpath(xpath.getText().trim())).click();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.toString().substring(0,400),"",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void SetText(){ try {
        driver.findElement(By.xpath(xpath.getText().trim())).sendKeys(value.getText().trim());
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(this, e.toString().substring(0,400),"",JOptionPane.INFORMATION_MESSAGE);
    }
    }

    public void InitializeBrowser(){
        if(driver==null) {
            String portnum = port.getText().trim();
            WebDriverManager.chromedriver().version("90.0.4430.24").setup();
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("debuggerAddress", "localhost:" + portnum + "");
            driver = new ChromeDriver(options);
//            driver.get("https://www.toolsqa.com/selenium-webdriver/webdrivermanager/");
            JOptionPane.showMessageDialog(this, "Driver initialized successfully.","",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Driver already initialized.","",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public  void KillSession(){
        System.exit(1);
    }

}