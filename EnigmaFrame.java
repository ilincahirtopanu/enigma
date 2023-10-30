import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnigmaFrame extends JFrame {
    private JTextField startingChar;    
    private Enigma enigma;

    private JComboBox<String> inner;
    private JComboBox<String> middle;
    private JComboBox<String> outer;

    private JTextArea input;
    private JTextArea output;

    private JButton encryptButton;
    private JButton decryptButton;
    
    private String [] numbers = {"1", "2", "3", "4", "5"};

    public EnigmaFrame() {
        //create frame title
        setTitle("Enigma GUI");
        setSize(800, 200);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        input = new JTextArea(2, 5);
        output = new JTextArea(2, 5);
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        //top PANEL
        //making the top side panel
        JPanel top = new JPanel();
        inner = new JComboBox<>(numbers);
        middle= new JComboBox<>(numbers);
        outer = new JComboBox<>(numbers);
        startingChar = new JTextField(10); 
        
        //must add Inner, then Middle, then Outer
        top.setLayout(new FlowLayout());
        top.add(new JLabel("Inner"));
        top.add(inner);
        top.add(new JLabel("Middle"));
        top.add(middle);
        top.add(new JLabel("Outer"));
        top.add(outer);
        top.add(new JLabel("Initial Positions")); 
        top.add(startingChar); 
        top.add(encryptButton);
        top.add(decryptButton);

        //input output text
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(new JLabel("Input"));
        bottom.add(new JLabel("Output"));
        bottom.add(input);
        bottom.add(output);

        //sorts top and bottom into being the top and bottom of the enigma
        JPanel inputOutput = new JPanel();
        inputOutput.setLayout(new BorderLayout());
        inputOutput.add(top, BorderLayout.NORTH);
        inputOutput.add(bottom, BorderLayout.CENTER);
        
        //makes the container for it
        Container total = getContentPane();
        total.setLayout(new BorderLayout());
        total.add(inputOutput, BorderLayout.CENTER);



        // encrypts
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptOrDecrypt(inner, middle, outer, "e");
            }
        });
        // decrypts
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                encryptOrDecrypt(inner, middle, outer, "d");
            }
        });
    }

    public void encryptOrDecrypt(JComboBox <String> i, JComboBox <String> m, JComboBox <String> o, String eOrD) {
        int r1 = Integer.parseInt((String) inner.getSelectedItem());
        int r2 = Integer.parseInt((String) middle.getSelectedItem());
        int r3 = Integer.parseInt((String) outer.getSelectedItem());
        String startingText = startingChar.getText();
        String message = input.getText();
        enigma = new Enigma(r1, r2, r3, startingText);

        String result;
        //encrypt
        if (eOrD.equals("e")) {
            result = enigma.encrypt(message);
        }
        //decrpyt
        else {
            result = enigma.decrypt(message);
        }

        //sets the output to be the result
        output.setText(result);
    }


}

