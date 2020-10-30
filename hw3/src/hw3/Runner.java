package hw3;

import speccheck.SpecCheck;

import javax.swing.SwingUtilities;

public class Runner
{
    public static void main(String[] args) {
        {
            // GUI dialogs are used, so we need to run on the event thread.
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    SpecCheck.testAndZip(SpecChecker.class, "SUBMIT_THIS_hw3", "hw3",
                            "hw3/src/hw3/Pearls.java","hw3/src/hw3/PearlUtil.java");
                }
            });
        }
    }
}
