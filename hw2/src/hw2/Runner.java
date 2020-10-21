package hw2;

import speccheck.SpecCheck;

import javax.swing.SwingUtilities;

public class Runner
{
    public static void main(String[] args) {
        {
            // GUI dialogs are used, so we need to run on the event thread.
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    SpecCheck.testAndZip(SpecChecker.class, "SUBMIT_THIS_hw2", "hw2",
                            new String[]{
                                    "hw2/src/hw2/ExitGate.java",
                                    "hw2/src/hw2/RateUtil.java",
                                    "hw2/src/hw2/PayStation.java",
                                    "hw2/src/hw2/TimeClock.java",
                                    "hw2/src/hw2/ParkingCard.java",
                                    "hw2/src/hw2/CardDispenser.java"});
                }
            });
        }
    }
}
