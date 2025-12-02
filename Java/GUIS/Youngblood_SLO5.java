import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;


public class Youngblood_SLO5 {
    //parts that are modified by buttons
    private static JTextField input;
    private static JLabel errorLabel;
    private static JTextField listDisplay;
    private static LinkedList<Double> numberList;

    public static void main(String[] args) {
        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 215;
        final int NUMBER_INPUT_WIDTH = 10;
        final int NUMBER_DISPLAY_WIDTH = 50;

        //prepare the linked list
        numberList = new LinkedList<>();

        //prepare the frame
        JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Youngblood SLO#5 - List Modification with GUIs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //prepare the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,1));


        //make the input panel components
        JLabel inputLabel = new JLabel("Input number: ");
        input = new JTextField(NUMBER_INPUT_WIDTH);
        JButton inputButton = new JButton("Add Number");
        inputButton.addActionListener(new ProcessInput());
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        //make the input panel and add all components
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(input);
        inputPanel.add(inputButton);
        inputPanel.add(errorLabel);
        mainPanel.add(inputPanel);


        //make and add list output panel
        JPanel listPanel = new JPanel();
        JLabel listLabel = new JLabel("Numbers:");
        listDisplay = new JTextField(NUMBER_DISPLAY_WIDTH);
        JButton listClear = new JButton("Clear List");
        listClear.addActionListener(new ClearList());

        listPanel.add(listLabel);
        listPanel.add(listDisplay);
        listPanel.add(listClear);
        mainPanel.add(listPanel);


        //make and add the modification panel
        JPanel modificationPanel = new JPanel();
        JButton sortButton = new JButton("Sort List");
        JButton shuffleButton = new JButton("Shuffle List");
        JButton reverseButton = new JButton("Reverse List");
        sortButton.addActionListener(new SortList());
        shuffleButton.addActionListener(new ShuffleList());
        reverseButton.addActionListener(new ReverseList());

        modificationPanel.add(sortButton);
        modificationPanel.add(shuffleButton);
        modificationPanel.add(reverseButton);
        mainPanel.add(modificationPanel);


        //add everything to the frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static void addNumberToList(Double newNumber){
        if(!numberList.contains(newNumber)){
            numberList.addLast(newNumber);

            if(listDisplay.getText().isEmpty()) {
                listDisplay.setText("" + numberList.peekLast());

            } else {
                listDisplay.setText(listDisplay.getText() + ",  " + numberList.peekLast());
            }
        } else {
            errorLabel.setText(newNumber + " is already in the list.");
        }


    }

    private static class ProcessInput implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                errorLabel.setText("");
                Double inputNumber = Double.parseDouble(input.getText());
                addNumberToList(inputNumber);

            } catch(Exception ex) {
                errorLabel.setText(input.getText() + " is not a valid number.");

            } finally {
                input.setText("");
            }
        }
    }

    private static class ClearList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            numberList.clear();
            listDisplay.setText("");
        }
    }

    private static class SortList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(numberList.size() > 1){
                numberList.sort(null);//natural ordering

                StringBuilder result = new StringBuilder();
                for(Double number : numberList){
                    result.append(",  ").append(number);
                }
                listDisplay.setText(result.substring(3));
            }

        }
    }

    private static class ShuffleList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Random rand = new Random();
            //uses fisher-yates shuffle
            for(int i = numberList.size()- 1; i > 0; i--){
                int swapIndex = rand.nextInt(i + 1);
                //swap i with random number between 0 and i
                Double temp = numberList.get(i);
                numberList.set(i, numberList.get(swapIndex));
                numberList.set(swapIndex, temp);
            }

            //changes label
            StringBuilder result = new StringBuilder();
            for(Double number : numberList){
                result.append(",  ").append(number);
            }
            listDisplay.setText(result.substring(3));
        }
    }

    private static class ReverseList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            for(int i = 1; i < numberList.size(); i++){
                numberList.addFirst(numberList.remove(i));
            }
            //changes label
            StringBuilder result = new StringBuilder();
            for(Double number : numberList){
                result.append(",  ").append(number);
            }
            listDisplay.setText(result.substring(3));
        }
    }
}

