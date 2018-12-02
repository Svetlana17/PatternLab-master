package adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamAdapter extends FileOutputStream {
    public OutputStreamAdapter(File file) throws FileNotFoundException {
        super(file);
    }
//записывает в байты
    public void write(String[] s) throws IOException {
        for (String str : s) {
            write(str.getBytes());
        }
    }
}
