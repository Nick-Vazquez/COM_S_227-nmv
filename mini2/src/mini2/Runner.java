package mini2;

import speccheck.SpecCheck;

import javax.swing.*;

public class Runner
{
    public static void main(String[] args) {
        {
            // GUI dialogs are used, so we need to run on the event thread.
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    SpecCheck.testAndZip(SpecChecker.class, "SUBMIT_THIS_mini2", "mini2",
                            new String[]{"src/mini2/PearlUtil.java", "src/mini2/State.java"});
                }
            });
        }
    }
}
