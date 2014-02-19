package helloTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

//mostly borrowed from anonymous stack overflow blogger, changed for use as List istead of Map

public class PersistentList extends ArrayList<String> {
    private static final long serialVersionUID = 7127639025670585367L;
    private final File file;

    public static PersistentList open(File file) {
    PersistentList shelf = null;
    try {
        if (file.exists()) {
        final FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        shelf = (PersistentList) ois.readObject();
        ois.close();
        fis.close();
        } else {
        shelf = new PersistentList(file);
        shelf.sync();
        }
    } catch (Exception e) {
        // TODO: handle errors
    }
    return shelf;
    }

    // Shelf objects can only be created or opened by the Shelf.open method
    private PersistentList(File file) {
    this.file = file;
    sync();
    }

    public void sync() {
    try {
        final FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
        fos.close();
    } catch (Exception e) {
        // TODO: handle errors
    }
    }

    // Simple Test Case
    public static void main(String[] args) {
    PersistentList shelf = PersistentList.open(new File("test.obj"));
    if (shelf.contains("test")) {
        System.out.println(("test"));
    } else {
        System.out.println("Creating test string.  Run the program again.");
        shelf.add("test");
        shelf.sync();
    }
    }
}



