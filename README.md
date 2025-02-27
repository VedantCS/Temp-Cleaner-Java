# Temp-Cleaner-Java
🧹 Temp File Cleaner (Java Swing)

A simple Java Swing-based GUI application that helps users clean up temporary files like .tmp, .log, .bak, and .cache files from a selected directory.
📌 Features

✅ Graphical Interface (Swing UI) – No need to manually type directory paths.
✅ File Selection – Browse and select a directory for cleaning.
✅ Checkbox Options – Choose which file types to delete.
✅ Output Console – Displays deleted files and total count.
✅ Real-time Status Updates – Users get instant feedback on deleted files.
📂 File Types Removed

This program detects and deletes the following file types:

    .tmp – Temporary files
    .log – Log files
    .bak – Backup files
    .cache – Cached files

🛠 How to Use

    Run the program in your Java IDE (Eclipse, IntelliJ, NetBeans) or compile it using javac.
    Click "Browse" to select a folder containing temp files.
    Check the file types you want to remove.
    Click "Delete Temp Files" to clean up unnecessary files.
    The output window will show the deleted files and total count.


Compile and run the program:

    javac TempFileCleanerGUI.java
    java TempFileCleanerGUI

📌 Future Enhancements

🔹 Delete files older than X days
🔹 Auto-schedule cleanups
🔹 Support for additional file extensions
🔹 Dark mode UI
🤝 Contributing



This project is open-source and available under the MIT License.
