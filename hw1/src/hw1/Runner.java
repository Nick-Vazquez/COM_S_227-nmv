package hw1;

import speccheck.SpecCheck;

import javax.swing.SwingUtilities;

public class Runner
{
    public static void main(String[] args) {
        {
            // GUI dialogs are used, so we need to run on the event thread.
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    SpecCheck.testAndZip(SpecChecker.class, "SUBMIT_THIS_hw1", "hw1",
                            new String[]{"src/hw1/hw1.Location.java","src/hw1/hw1.Backpacker.java"});

                }
            });
        }
    }
}
