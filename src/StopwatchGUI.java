// src/main/java/StopwatchGUI.java

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StopwatchGUI {
    private JLabel timerLabel;
    private JTextField lapDescriptionField;
    private DefaultTableModel tableModel;
    private Stopwatch stopwatch;
    private Timer guiTimer;

    public StopwatchGUI() {
        stopwatch = new Stopwatch();
        initializeGUI();
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());

        // Timer display
        timerLabel = new JLabel("0:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        frame.add(timerLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5)); // Add spacing between buttons
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton lapButton = new JButton("Lap");
        JButton resetButton = new JButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(lapButton);
        buttonPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Lap input field
        JPanel inputPanel = new JPanel(new BorderLayout());
        lapDescriptionField = new JTextField();
        inputPanel.add(new JLabel("Lap Description:"), BorderLayout.WEST);
        inputPanel.add(lapDescriptionField, BorderLayout.CENTER);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Lap table
        String[] columnNames = {"Lap #", "Time", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable lapTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(lapTable);

        frame.add(tableScrollPane, BorderLayout.EAST);

        // Add button actions
        startButton.addActionListener(e -> startStopwatch());
        stopButton.addActionListener(e -> stopStopwatch());
        lapButton.addActionListener(e -> recordLap());
        resetButton.addActionListener(e -> resetStopwatch());

        // GUI Timer for updating display
        guiTimer = new Timer(100, e -> updateTimerDisplay());

        frame.setVisible(true);
    }

    private void startStopwatch() {
        stopwatch.start();
        guiTimer.start();
    }

    private void stopStopwatch() {
        stopwatch.stop();
        guiTimer.stop();
    }

    private void recordLap() {
        String description = lapDescriptionField.getText().trim();
        if (description.isEmpty()) {
            description = "No Description";
        }
        stopwatch.lap(description);
        updateLapTable();
        lapDescriptionField.setText("");
    }

    private void resetStopwatch() {
        stopwatch = new Stopwatch(); // Reset the logic
        guiTimer.stop();
        timerLabel.setText("0:00");
        tableModel.setRowCount(0); // Clear the table
    }

    private void updateTimerDisplay() {
        timerLabel.setText(formatTime(stopwatch.getElapsedMilliseconds() / 1000));
    }

    private void updateLapTable() {
        List<Lap> laps = stopwatch.getLaps();
        tableModel.setRowCount(0); // Clear the table
        for (int i = 0; i < laps.size(); i++) {
            Lap lap = laps.get(i);
            tableModel.addRow(new Object[]{i + 1, formatTime(lap.getTime() / 1000), lap.getDescription()});
        }
    }

    private String formatTime(long totalSeconds) {
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchGUI::new);
    }

    // Stopwatch logic reused from original code
    static class Stopwatch {
        private long startTime;
        private long elapsedTime;
        private boolean isRunning;
        private List<Lap> laps;

        public Stopwatch() {
            laps = new ArrayList<>();
            isRunning = false;
            elapsedTime = 0;
        }

        public void start() {
            if (!isRunning) {
                startTime = System.currentTimeMillis();
                isRunning = true;
            }
        }

        public void stop() {
            if (isRunning) {
                elapsedTime += System.currentTimeMillis() - startTime;
                isRunning = false;
            }
        }

        public void lap(String description) {
            if (isRunning) {
                long lapTime = System.currentTimeMillis() - startTime + elapsedTime;
                laps.add(new Lap(lapTime, description));
            }
        }

        public long getElapsedMilliseconds() {
            return isRunning
                    ? elapsedTime + (System.currentTimeMillis() - startTime)
                    : elapsedTime;
        }

        public List<Lap> getLaps() {
            return new ArrayList<>(laps);
        }
    }

    static class Lap {
        private final long time;
        private final String description;

        public Lap(long time, String description) {
            this.time = time;
            this.description = description;
        }

        public long getTime() {
            return time;
        }

        public String getDescription() {
            return description;
        }
    }
}
