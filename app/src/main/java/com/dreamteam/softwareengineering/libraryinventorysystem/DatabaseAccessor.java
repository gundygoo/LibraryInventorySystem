package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 10/18/2016.
 */

public class DatabaseAccessor {
    private static String loginFileName = "LOGINS.txt";
    private static String currentInventoryFileName = "INVENTORY.txt";
    private static String customerDataFileName = "CUSTOMERDATA.txt";

    private static String tempFileName = "TEMP.txt";

    //returns the Authority Level if the user credentials match, otherwise returns null
    public static String AuthenticateUserLogin(String username, String password, Context context) throws IOException{
        //if username == admin and password = admin, allow admin access
        //else, check text file for matching credentials and authority level
        if (username.equals("Admin") && password.equals("Admin")){
            return "Admin";
        }

        FileInputStream fis = context.openFileInput(loginFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String currentLine = reader.readLine();
        while (currentLine != null){
            String[] credentials = currentLine.split(",");
            if (credentials[0].equals(username)){
                if (credentials[1].equals(password)){
                    return credentials[2];
                }
            }
        }
        fis.close();

        return null;
    }

    //returns the password that matches the username, otherwise returns null if the username is not found
    public static String RecoverPassword(String username, Context context) throws IOException{
        FileInputStream fis = context.openFileInput(loginFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String currentLine = reader.readLine();
        while (currentLine != null){
            String[] credentials = currentLine.split(",");
            if (credentials[0].equals(username)){
                return credentials[1];
            }
        }
        fis.close();

        return null;
    }

    //adds a new user to the system
    //authority levels are: Admin, Manager, Employee, Customer
    public static void AddNewUserLogin(String username, String password, String authorityLevel, Context context) throws IOException{
        String newUserLogin = username + "," + password + "," + authorityLevel + "\n";

        FileOutputStream fos = context.openFileOutput(loginFileName, Context.MODE_APPEND);
        fos.write(newUserLogin.getBytes());
        fos.close();
    }

    //adds a new customer to the system
    public static void CreateNewCustomerAccount(String customerUserName, Context context) throws IOException{
        String filename = customerUserName + ".txt";
        File file = new File(context.getFilesDir(), filename);

        if (!file.exists()){
            file.createNewFile();
        }
    }

    //checks out a book to a customer, and removes the book from inventory
    public static void CheckoutBookToCustomer(String bookName, String customerUserName, Context context) throws IOException{
        String filename = customerUserName + ".txt";
        String book = bookName + "\n";

        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_APPEND);
        fos.write(book.getBytes());
        fos.close();

        RemoveBookFromInventory(bookName, context);
    }

    //check in a book from a customer, and add that book to inventory
    public static void CheckinBookFromCustomer(String bookName, String customerUserName, Context context) throws IOException{
        String filename = customerUserName + ".txt";

        FileInputStream fis = context.openFileInput(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        FileOutputStream fos = context.openFileOutput(tempFileName, Context.MODE_APPEND);

        //read lines from the customer text file into the temp file, excluding the line with the book to be returned
        String currentLine = reader.readLine();
        while(currentLine != null){
            if (!currentLine.contains(bookName)){
                String currentWrite = currentLine + "\n";
                fos.write(currentWrite.getBytes());
            }
        }
        fis.close();
        fos.close();

        File customerFile = new File(context.getFilesDir(), filename);
        customerFile.delete();

        FileInputStream fis2 = context.openFileInput(tempFileName);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(fis2));

        FileOutputStream fos2 = context.openFileOutput(filename, Context.MODE_APPEND);

        //read lines from the temp file into the customer text file
        String currentLine2 = reader2.readLine();
        while (currentLine2 != null){
            String currentWrite2 = currentLine2 + "\n";
            fos2.write(currentWrite2.getBytes());
        }
        fis2.close();
        fos2.close();

        //delete the temp file to clear old data
        File tempFile = new File(context.getFilesDir(), tempFileName);
        tempFile.delete();

        AddBookToInventory(bookName, context);
    }

    //view the book list for a customer
    public static List<String> ViewCustomerBookList(String customerUserName, Context context) throws IOException{
        String filename = customerUserName + ".txt";
        List<String> customerBookList = new ArrayList<>();

        FileInputStream fis = context.openFileInput(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String currentLine = reader.readLine();
        while(currentLine != null){
            customerBookList.add(currentLine);
        }
        fis.close();

        return customerBookList;
    }

    //add a book to inventory
    public static void AddBookToInventory(String bookName, Context context) throws IOException{
        String book = bookName + "\n";

        FileOutputStream fos = context.openFileOutput(currentInventoryFileName, Context.MODE_APPEND);
        fos.write(book.getBytes());
        fos.close();
    }

    //remove a book from inventory
    public static void RemoveBookFromInventory(String bookName, Context context) throws IOException{
        FileInputStream fis = context.openFileInput(currentInventoryFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        FileOutputStream fos = context.openFileOutput(tempFileName, Context.MODE_APPEND);

        //read lines from the current inventory text file into the temp file, excluding the line with the book to be returned
        String currentLine = reader.readLine();
        while(currentLine != null){
            if (!currentLine.contains(bookName)){
                String currentWrite = currentLine + "\n";
                fos.write(currentWrite.getBytes());
            }
        }
        fis.close();
        fos.close();

        File inventoryFile = new File(context.getFilesDir(), currentInventoryFileName);
        inventoryFile.delete();

        FileInputStream fis2 = context.openFileInput(tempFileName);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(fis2));

        FileOutputStream fos2 = context.openFileOutput(currentInventoryFileName, Context.MODE_APPEND);

        //read lines from the temp file into the customer text file
        String currentLine2 = reader2.readLine();
        while (currentLine2 != null){
            String currentWrite2 = currentLine2 + "\n";
            fos2.write(currentWrite2.getBytes());
        }
        fis2.close();
        fos2.close();

        //delete the temp file to clear old data
        File tempFile = new File(context.getFilesDir(), tempFileName);
        tempFile.delete();
    }

    //view library inventory
    public static List<String> ViewInventory(Context context) throws IOException{
        List<String> inventoryList = new ArrayList<>();

        FileInputStream fis = context.openFileInput(currentInventoryFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String currentLine = reader.readLine();
        while(currentLine != null){
            inventoryList.add(currentLine);
        }
        fis.close();

        return inventoryList;
    }

    //add customer data to the system
    public static void AddCustomerData(String customerName,
                                           String idNumber,
                                           String username,
                                           String age,
                                           String authorityLevel,
                                           String homeAddress,
                                           String emailAddress,
                                           Context context) throws IOException{
        String customerDataLine = customerName + "," +
                                    idNumber + "," +
                                    username + "," +
                                    age + "," +
                                    authorityLevel + "," +
                                    homeAddress + "," +
                                    emailAddress + "," + "\n";

        FileOutputStream fos = context.openFileOutput(customerDataFileName, Context.MODE_APPEND);
        fos.write(customerDataLine.getBytes());
        fos.close();
    }

    //modify existing customer data in the system
    public static void ModifyCustomerData(String customerName,
                                              String idNumber,
                                              String username,
                                              String age,
                                              String authorityLevel,
                                              String homeAddress,
                                              String emailAddress,
                                              Context context) throws IOException{
        FileInputStream fis = context.openFileInput(customerDataFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        FileOutputStream fos = context.openFileOutput(tempFileName, Context.MODE_APPEND);

        //read lines from the current inventory text file into the temp file, excluding the line with the book to be returned
        String currentLine = reader.readLine();
        while(currentLine != null){
            String customerData[] = currentLine.split(",");
            if (!customerData[0].equals(customerName)){
                if (!customerData[1].equals(idNumber)){
                    String currentWrite = currentLine + "\n";
                    fos.write(currentWrite.getBytes());
                }
            }
        }
        fis.close();
        fos.close();

        File customerDataFile = new File(context.getFilesDir(), customerDataFileName);
        customerDataFile.delete();

        FileInputStream fis2 = context.openFileInput(tempFileName);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(fis2));

        FileOutputStream fos2 = context.openFileOutput(customerDataFileName, Context.MODE_APPEND);

        //read lines from the temp file into the customer text file
        String currentLine2 = reader2.readLine();
        while (currentLine2 != null){
            String currentWrite2 = currentLine2 + "\n";
            fos2.write(currentWrite2.getBytes());
        }
        fis2.close();
        fos2.close();

        //delete the temp file to clear old data
        File tempFile = new File(context.getFilesDir(), tempFileName);
        tempFile.delete();

        AddCustomerData(customerName, idNumber, username, age, authorityLevel, homeAddress, emailAddress, context);
    }

    //view existing customer data in the system
    public static String[] ViewCustomerData(String customerName, String idNumber, Context context) throws IOException{
        FileInputStream fis = context.openFileInput(customerDataFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String currentLine = reader.readLine();
        while (currentLine != null){
            String[] customerData = currentLine.split(",");
            if (customerData[0].equals(customerName)){
                if (customerData[1].equals(idNumber)){
                    return customerData;
                }
            }
        }
        fis.close();

        return new String[] {null, null, null, null, null, null, null};
    }

    //get the username for a customer name, for use in our code to get the username required for other calls
    public static String GetCustomerUsername(String customerName, String idNumber, Context context) throws IOException{
        FileInputStream fis = context.openFileInput(customerDataFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String currentLine = reader.readLine();
        while (currentLine != null){
            String customerData[] = currentLine.split(",");
            if (customerData[0].equals(customerName)){
                if (customerData[1].equals(idNumber)){
                    return customerData[2];
                }
            }
        }

        return null;
    }
}
