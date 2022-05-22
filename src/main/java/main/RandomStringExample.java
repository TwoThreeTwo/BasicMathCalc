package main;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomStringExample {

    public static void getAlphaNumericString(int n) {
        // length is bounded by 256 Chars as per the Ascii Table (2^8).
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        // Look and Feel is set to Windows 11 (Or your preferred OS)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a StringBuffer to store the result
        String randomString = new String(array, StandardCharsets.UTF_8);
        StringBuilder r = new StringBuilder();


        // Append first 20 alphanumeric characters
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    || (ch >= '!' && ch <= '+')
                    && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        // Print the generated String in the Dialog Box.
        JOptionPane.showMessageDialog(null, r + "\n" + "Click Ok to see your options...", "output", JOptionPane.INFORMATION_MESSAGE);

        // Use OptionPane to show the options
        int r2 = JOptionPane.showConfirmDialog(null, "Would you like it copied to your clipboard?", "Output", JOptionPane.YES_NO_OPTION);
        if (r2 == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Copying to clipboard...", "Output", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Copied to clipboard!", "Output", JOptionPane.INFORMATION_MESSAGE);
            sendToClipBoard(r);
        } else {
            JOptionPane.showMessageDialog(null, "Canceling...", "Output", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Cancelled!", "Output", JOptionPane.INFORMATION_MESSAGE);
        }

        // Ask if you want to see the amount of chars inside the file
        int r3 = JOptionPane.showConfirmDialog(null, "Would you like to see the amount of characters in the file before you create a file?", "Output", JOptionPane.YES_NO_OPTION);
        if (r3 == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "There are a total of " + r.length() + " characters in this string.", "Output", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Canceling...", "Output", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Cancelled!", "Output", JOptionPane.INFORMATION_MESSAGE);
        }

        int r1 = JOptionPane.showConfirmDialog(null, "Would you like it as a file?.", "Output", JOptionPane.YES_NO_OPTION);
        if (r1 == JOptionPane.YES_OPTION) {
            if(!new File("output-info.json").exists()) {
                JOptionPane.showMessageDialog(null, "File not Found, Creating file...", "Output", JOptionPane.INFORMATION_MESSAGE);
                try (PrintWriter o = new PrintWriter("output-info.json", String.valueOf(StandardCharsets.UTF_8))) {
                    o.println(r);
                    o.println("There are a total of " + r.length() + " characters in this string.");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "File found!", "Output", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "File already exists, please delete the file and try again....", "Output", JOptionPane.INFORMATION_MESSAGE);
            }
            // Ask if you want to see the location of output-info.json file.
            int r4 = JOptionPane.showConfirmDialog(null, "Would you like to see the location of the file?", "Output", JOptionPane.YES_NO_OPTION);
            if (r4 == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "The file is located at: " + new File("output-info.json").getAbsolutePath(), "Output", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Canceling...", "Output", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Cancelled!", "Output", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Canceling save...", "Output", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Canceled!", "Output", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        }




        // end of method getAlphaNumericString.
    }

    // Method to copy the generated String to the clipboard.
    private static void sendToClipBoard(StringBuilder stringBuilder) {
        StringSelection stringSelection = new StringSelection(stringBuilder.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    // Main method
    public static void main(String[] args) {
        getAlphaNumericString(20);
        // Thread sleeps after 142 nanoseconds it wakes up and prints the generated String.
        try {
            Thread.sleep(0, 142);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}