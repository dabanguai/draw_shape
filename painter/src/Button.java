import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public interface Button {
    JButton createButton(String args);
    void addListener(JButton jButton, ActionListener actionListener);
}
