# 📒 NotesAppAdvanced

**AdvancedNotesApp** is a simple terminal-based note-taking application written in Java. It allows users to create, view, search, and delete notes stored in a single text file. Each note is saved with a title and timestamp.

---

## 🚀 Features

- 📝 Write a new note with a title and timestamp
- 📄 View all notes stored in `notes.txt`
- 🔍 Search for a note by its title
- ❌ Delete a note by its title
- 💾 Stores data persistently in plain text format

---

## 🛠️ Technologies Used

- Java (JDK 8+)
- File I/O (`FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`)
- Date formatting with `SimpleDateFormat`
- Console-based interaction using `Scanner`

---

## 📁 File Structure
-- AdvancedNotesApp.java

-- notes.txt ← Auto-created and used to store notes

-- README.md


---

## 💻 How to Compile and Run

### 1. Compile the code
```bash
javac AdvancedNoteApp.java
```
### 2. Run the code
```bash
java AdvancedNoteApp
```

## 🧑‍🏫 How to Use
When you run the program, you'll see this menu:

===== Notes App (Advanced) =====
1. Write a new note
2. View all notes
3. Search note by title
4. Delete note by title
5. Exit
   
-- Choose the appropriate number for the action.

-- When writing a note, type lines of your note and enter END on a new line to finish.

## 📝 Notes Format
Each note is saved in the following format:

=== Title ===

Timestamp: 2025-06-27 14:30:00

Line 1 of the note...

Line 2 of the note...

...
----END NOTE----


## ⚠️ Important
-- Ensure notes.txt is in the same directory or it will be created automatically.

-- Do not rename or modify the format manually unless necessary.


