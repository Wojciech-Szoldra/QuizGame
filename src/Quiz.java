import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class Quiz implements ActionListener {

    String[] questions_1 = {
            "Kto był reżyserem filmu \"Nienawistna ósemka\"?",
            "Jaki film w 2023 roku otrzymał oskara w kategorii \"Najlepszy film\"?",
            "W którym z wymienionych filmów zagrała Scarlett Johansson?",
            "Jaki polski serial powstał na podstawie książki Jakuba Żulczyka?",
            "Kto wyreżyserował film \"The Godfather\"?"
    };

    String[][] answersOptions_1 = {
            {"Wes Anderson", "Joon-ho Bong", "Quentin Tarantino", "Paweł Pawlikowski"},
            {"John Wick 4", "Wszystko wszędzie naraz", "Elvis", "Duchy Inisherin"},
            {"Na lodzie", "Strażnicy Galaktyki", "Birdman", "Jojo Rabbit"},
            {"Belfer", "Ślepnąc od świateł", "W głębi lasu", "Minuta ciszy"},
            {"Steven Spielberg", "Martin Scorsese", "Francis Ford Coppola", "Stanley Kubrick"}
    };

    char[] correctAnswers_1 = {
            'C',
            'B',
            'D',
            'B',
            'C'
    };

    String[] questions_2 = {
            "Który aktor nigdy nie otrzymał Oscara za najlepszą rolę?",
            "Który film nakręcony został w technologii 3D?",
            "Która aktorka zagrała Hermione Granger w serii filmów o Harrym Potterze?",
            "Kto jest autorem popularnego podcastu o tematyce filmowej \"SpoilerMaster\"",
            "Jaki film w 2019 roku otrzymał Oscara w kategorii \"Najlepszy film" +
            " nieanglojęzyczny\"?"
    };

    String[][] answersOptions_2 = {
            {"Tom Hanks", "Leonardo DiCaprio", "Brad Pitt", "Johnny Depp"},
            {"Jurassic Park", "Avatar", "Matrix", "Mroczny Rycerz"},
            {"Emma Stone", "Emma Thompson", "Emma Watson", "Emma Roberts"},
            {"Jakub Dębski", "Tomasz Raczek", "Kaja Klimek", "Michał Oleszczyk"},
            {"Roma", "Zimna wojna", "Obrazy bez autora", "Kafarnaum"}
    };

    char[] correctAnswers_2 = {
            'D',
            'B',
            'C',
            'D',
            'A'
    };

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions_1 = questions_1.length;
    int total_questions_2 = questions_2.length;
    int result;
    int seconds = 20;

    int counterNextQuestion1 = 0;
    int counterNextQuestion2 = 0;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds <= 0) {
                displayAnswer();
            }
        }
    });

    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color (50,50,50));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0,0,633,50);
        textField.setBackground(new Color (25,25,25));
        textField.setForeground(new Color (109, 154, 194));
        textField.setFont(new Font ("Segoe UI", Font.PLAIN, 25));
        textField.setBorder(BorderFactory.createLineBorder(new Color(109,154,194), 2));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        textArea.setBounds(0, 48, 633, 80);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,25,25));
        textArea.setForeground(new Color (109, 154, 194));
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(109,154,194), 2));
        textArea.setEditable(false);

        buttonA.setBounds(0,130,100,100);
        buttonA.setFont(new Font("Segoe UI", Font.PLAIN, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,230,100,100);
        buttonB.setFont(new Font("Segoe UI", Font.PLAIN, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0,330,100,100);
        buttonC.setFont(new Font("Segoe UI", Font.PLAIN, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0,430,100,100);
        buttonD.setFont(new Font("Segoe UI", Font.PLAIN, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125, 130, 500, 100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(109,154,194));
        answer_labelA.setFont(new Font("Segoe UI", Font.PLAIN, 35));

        answer_labelB.setBounds(125, 230, 500, 100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(109,154,194));
        answer_labelB.setFont(new Font("Segoe UI", Font.PLAIN, 35));

        answer_labelC.setBounds(125, 330, 500, 100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(109,154,194));
        answer_labelC.setFont(new Font("Segoe UI", Font.PLAIN, 35));

        answer_labelD.setBounds(125, 430, 500, 100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(109,154,194));
        answer_labelD.setFont(new Font("Segoe UI", Font.PLAIN, 35));

        seconds_left.setBounds(533,510,100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(219,80,37));
        seconds_left.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        seconds_left.setBorder(BorderFactory.createLineBorder(new Color(109,154,194), 2));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JLabel.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535,480,100,25);
        time_label.setBackground(new Color(25,25,25));
        time_label.setForeground(new Color(219,80,37));
        time_label.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        time_label.setHorizontalAlignment(JLabel.CENTER);
        time_label.setText("CZAS:");

        number_right.setBounds(225,225,200,100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color (61, 163, 42));
        number_right.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        number_right.setBorder(BorderFactory.createLineBorder(new Color(109,154,194), 2));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225,323,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color (61, 163, 42));
        percentage.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        percentage.setBorder(BorderFactory.createLineBorder(new Color(109,154,194), 2));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        int randomInt = (int) (Math.random() * 2) + 1;

        switch (randomInt) {
            case 1:
                nextQuestion1();
                break;
            case 2:
                nextQuestion2();
                break;
        }
    }

    public void nextQuestion1() {

        if(index >= total_questions_1) {
            results();
        } else {
            textField.setText("Pytanie " + (index + 1));
            textArea.setText(questions_1[index]);
            answer_labelA.setText(answersOptions_1[index][0]);
            answer_labelB.setText(answersOptions_1[index][1]);
            answer_labelC.setText(answersOptions_1[index][2]);
            answer_labelD.setText(answersOptions_1[index][3]);
            timer.start();
            counterNextQuestion1++;
        }
    }

    public void nextQuestion2() {

        if(index >= total_questions_2) {
            results();
        } else {
            textField.setText("Pytanie " + (index + 1));
            textArea.setText(questions_2[index]);
            answer_labelA.setText(answersOptions_2[index][0]);
            answer_labelB.setText(answersOptions_2[index][1]);
            answer_labelC.setText(answersOptions_2[index][2]);
            answer_labelD.setText(answersOptions_2[index][3]);
            timer.start();
            counterNextQuestion2++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(counterNextQuestion1 > 0) {

            if (e.getSource() == buttonA) {
                answer = 'A';
                if (answer == correctAnswers_1[index]) {
                    correct_guesses++;
                }
            }

            if (e.getSource() == buttonB) {
                answer = 'B';
                if (answer == correctAnswers_1[index]) {
                    correct_guesses++;
                }
            }

            if (e.getSource() == buttonC) {
                answer = 'C';
                if (answer == correctAnswers_1[index]) {
                    correct_guesses++;
                }
            }

            if (e.getSource() == buttonD) {
                answer = 'D';
                if (answer == correctAnswers_1[index]) {
                    correct_guesses++;
                }
            }
        }

        if(counterNextQuestion2 > 0) {

            if (e.getSource() == buttonA) {
                answer = 'A';
                if (answer == correctAnswers_2[index]) {
                    correct_guesses++;
                }
            }

            if (e.getSource() == buttonB) {
                answer = 'B';
                if (answer == correctAnswers_2[index]) {
                    correct_guesses++;
                }
            }

            if (e.getSource() == buttonC) {
                answer = 'C';
                if (answer == correctAnswers_2[index]) {
                    correct_guesses++;
                }
            }

            if (e.getSource() == buttonD) {
                answer = 'D';
                if (answer == correctAnswers_2[index]) {
                    correct_guesses++;
                }
            }
        }
        displayAnswer();
    }
    public void displayAnswer() {

        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(counterNextQuestion1 > 0) {

            if (correctAnswers_1[index] != 'A') {
                answer_labelA.setForeground(new Color(255, 0, 0));
            }
            if (correctAnswers_1[index] != 'B') {
                answer_labelB.setForeground(new Color(255, 0, 0));
            }
            if (correctAnswers_1[index] != 'C') {
                answer_labelC.setForeground(new Color(255, 0, 0));
            }
            if (correctAnswers_1[index] != 'D') {
                answer_labelD.setForeground(new Color(255, 0, 0));
            }

            Timer pause = new Timer(2000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    answer_labelA.setForeground(new Color(109, 154, 194));
                    answer_labelB.setForeground(new Color(109, 154, 194));
                    answer_labelC.setForeground(new Color(109, 154, 194));
                    answer_labelD.setForeground(new Color(109, 154, 194));

                    answer = ' ';
                    seconds = 20;
                    seconds_left.setText(String.valueOf(seconds));
                    buttonA.setEnabled(true);
                    buttonB.setEnabled(true);
                    buttonC.setEnabled(true);
                    buttonD.setEnabled(true);
                    index++;
                    nextQuestion1();
                }
            });
            pause.setRepeats(false);
            pause.start();
        }

        if(counterNextQuestion2 > 0) {

            if (correctAnswers_2[index] != 'A') {
                answer_labelA.setForeground(new Color(255, 0, 0));
            }
            if (correctAnswers_2[index] != 'B') {
                answer_labelB.setForeground(new Color(255, 0, 0));
            }
            if (correctAnswers_2[index] != 'C') {
                answer_labelC.setForeground(new Color(255, 0, 0));
            }
            if (correctAnswers_2[index] != 'D') {
                answer_labelD.setForeground(new Color(255, 0, 0));
            }

            Timer pause = new Timer(2000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    answer_labelA.setForeground(new Color(109, 154, 194));
                    answer_labelB.setForeground(new Color(109, 154, 194));
                    answer_labelC.setForeground(new Color(109, 154, 194));
                    answer_labelD.setForeground(new Color(109, 154, 194));

                    answer = ' ';
                    seconds = 20;
                    seconds_left.setText(String.valueOf(seconds));
                    buttonA.setEnabled(true);
                    buttonB.setEnabled(true);
                    buttonC.setEnabled(true);
                    buttonD.setEnabled(true);
                    index++;
                    nextQuestion2();
                }
            });
            pause.setRepeats(false);
            pause.start();
        }
    }
    public void results() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions_1) * 100);

        textField.setText("WYNIKI!");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("("+correct_guesses + "/" + total_questions_1+")");
        percentage.setText(result + "%");

        frame.add(number_right);
        frame.add(percentage);
    }

}
