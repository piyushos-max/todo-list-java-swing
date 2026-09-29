import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ToDoList extends JFrame implements ActionListener {
    JTextField taskField;
    JTextField removeField;
    JButton addButton;
    JButton removeButton;
    JTextArea taskArea;
    int taskCount;

    ToDoList() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        taskCount = 0;

        JLabel taskLabel = new JLabel("Enter Task:");
        taskField = new JTextField();
        addButton = new JButton("Add Task");
        addButton.addActionListener(this);

        JLabel removeLabel = new JLabel("Task Number to Remove:");
        removeField = new JTextField();
        removeButton = new JButton("Remove Task");
        removeButton.addActionListener(this);

        taskArea = new JTextArea();
        taskArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskArea);

        add(taskLabel);
        add(taskField);
        add(addButton);
        add(new JLabel());
        add(removeLabel);
        add(removeField);
        add(removeButton);
        add(new JLabel("Task List:"));

        setSize(450, 350);
        setVisible(true);

        JFrame listFrame = new JFrame("Tasks");
        listFrame.setLayout(new BorderLayout());
        listFrame.add(scrollPane, BorderLayout.CENTER);
        listFrame.setSize(400, 300);
        listFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                taskCount++;
                taskArea.append(taskCount + ". " + task + "\n");
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Task cannot be empty.",
                        "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == removeButton) {
            try {
                int lineToRemove = Integer.parseInt(removeField.getText());
                String[] lines = taskArea.getText().split("\n");
                if (lineToRemove > 0 && lineToRemove <= lines.length) {
                    taskArea.setText("");
                    for (int i = 0; i < lines.length; i++) {
                        if (i != lineToRemove - 1) {
                            taskArea.append(lines[i] + "\n");
                        }
                    }
                    removeField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid task number.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.",
                        "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] arr) {
        new ToDoList();
    }
}
