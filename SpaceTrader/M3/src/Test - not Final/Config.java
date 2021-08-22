//Santiago Pozuelo
//spozuelo3
import javax.swing.*;
import java.awt.*;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Config extends JFrame{

    public Config(){
        JPanel config = new JPanel();
        BoxLayout box = new BoxLayout(config, BoxLayout.Y_AXIS);
        config.setLayout(box);

        //name with field
        JLabel label = new JLabel("Enter your name", JLabel.CENTER);
        JTextField name = new JTextField("name");

        //difficulty
        JLabel label2 = new JLabel("Difficulty", JLabel.CENTER);

        ButtonGroup difficulty = new ButtonGroup();
        JRadioButton easy = new JRadioButton();
        JRadioButton medium = new JRadioButton();
        JRadioButton hard = new JRadioButton();
        easy.setText("Easy");
        medium.setText("medium");
        hard.setText("hard");
        Button submit = new Button("next page");
        // Add the radio buttons to the button group
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);

        config.add(label);
        config.add(name);
        config.add(label2);
        config.add(easy);
        config.add(medium);
        config.add(hard);
        config.add(submit);
        this.add(config);

    }


    public static void main(String[] args) {
        Config demo = new Config();
        demo.setTitle("configuration page");
        demo.setVisible(true);


    }


}
