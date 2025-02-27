import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class TempFileCleaner extends JFrame {
    private JTextField directoryField;
    private JTextArea outputArea;
    private JCheckBox tmpCheck, logCheck, bakCheck, cacheCheck;

    public TempFileCleaner() {
        setTitle("Temp File Cleaner");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for directory selection
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JPanel dirPanel = new JPanel();
        JLabel label = new JLabel("Directory:");
        directoryField = new JTextField(25);
        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(this::browseDirectory);
        dirPanel.add(label);
        dirPanel.add(directoryField);
        dirPanel.add(browseButton);

        // Panel for checkboxes
        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new GridLayout(1, 4));
        tmpCheck = new JCheckBox(".tmp", true);
        logCheck = new JCheckBox(".log", true);
        bakCheck = new JCheckBox(".bak", true);
        cacheCheck = new JCheckBox(".cache", true);
        checkPanel.add(tmpCheck);
        checkPanel.add(logCheck);
        checkPanel.add(bakCheck);
        checkPanel.add(cacheCheck);

        panel.add(dirPanel);
        panel.add(checkPanel);

        // Output Area
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Delete Button
        JButton deleteButton = new JButton("Delete Temp Files");
        deleteButton.addActionListener(this::deleteTempFiles);

        // Add components to frame
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
    }

    private void browseDirectory(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            directoryField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void deleteTempFiles(ActionEvent e) {
        String dirPath = directoryField.getText();
        if (dirPath.isEmpty()) {
            outputArea.setText("Please select a directory first.");
            return;
        }

        File directory = new File(dirPath);
        if (!directory.exists() || !directory.isDirectory()) {
            outputArea.setText("Invalid directory! Please select a valid folder.");
            return;
        }

        // Get selected file extensions
        String[] extensions = getSelectedExtensions();
        if (extensions.length == 0) {
            outputArea.setText("Please select at least one file type to delete.");
            return;
        }

        outputArea.setText("Deleting files...\n");
        int deletedFiles = cleanTempFiles(directory, extensions);
        outputArea.append("\nCleanup complete! Deleted " + deletedFiles + " files.");
    }

    private String[] getSelectedExtensions() {
        return new String[]{
            tmpCheck.isSelected() ? ".tmp" : null,
            logCheck.isSelected() ? ".log" : null,
            bakCheck.isSelected() ? ".bak" : null,
            cacheCheck.isSelected() ? ".cache" : null
        };
    }

    private int cleanTempFiles(File directory, String[] extensions) {
        int deletedCount = 0;

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && shouldDelete(file.getName(), extensions)) {
                    if (file.delete()) {
                        outputArea.append("Deleted: " + file.getAbsolutePath() + "\n");
                        deletedCount++;
                    }
                }
            }
        }
        return deletedCount;
    }

    private boolean shouldDelete(String fileName, String[] extensions) {
        for (String ext : extensions) {
            if (ext != null && fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TempFileCleaner cleaner = new TempFileCleaner();
            cleaner.setVisible(true);
        });
    }
}
/*--------------------------------WITHOUT GUI------------------------------------------------
 * import java.io.File;
import java.util.Scanner;

public class TempFileCleaner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
      
        System.out.print("Enter the directory to clean (e.g., C:\\\\Windows\\\\Temp or /tmp): ");
        String dirPath = scanner.nextLine();
        
        File directory = new File(dirPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory! Exiting...");
            return;
        }

        // Scan and delete temporary files
        int deletedFiles = cleanTempFiles(directory);
        System.out.println("Cleanup complete! Deleted " + deletedFiles + " files.");

        scanner.close();
    }

    public static int cleanTempFiles(File directory) {
        int deletedCount = 0;

        // Define file extensions to delete
        String[] extensions = {".tmp", ".log", ".bak", ".cache"};

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && shouldDelete(file.getName(), extensions)) {
                    if (file.delete()) {
                        System.out.println("Deleted: " + file.getAbsolutePath());
                        deletedCount++;
                    } else {
                        System.out.println("Failed to delete: " + file.getAbsolutePath());
                    }
                }
            }
        }
        return deletedCount;
    }

    // Check if the file matches the extensions to delete
    public static boolean shouldDelete(String fileName, String[] extensions) {
        for (String ext : extensions) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
*/
