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
    public static String loginFileName = "LOGINS.txt";
    public static String currentInventoryFileName = "INVENTORY.txt";
    public static String customerDataFileName = "CUSTOMERDATA.txt";

    public static String tempFileName = "TEMP.txt";

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

    //authority levels are: Manager, Employee, Customer
    public static void AddNewUserLogin(String username, String password, String authorityLevel, Context context) throws IOException{
        String newUserLogin = username + "," + password + "," + authorityLevel + "\n";

        FileOutputStream fos = context.openFileOutput(loginFileName, Context.MODE_APPEND);
        fos.write(newUserLogin.getBytes());
        fos.close();
    }

    public static void CreateNewCustomerAccount(String customerUserName, Context context) throws IOException{
        String filename = customerUserName + ".txt";
        File file = new File(context.getFilesDir(), filename);

        if (!file.exists()){
            file.createNewFile();
        }
    }

    public static void CheckoutBookToCustomer(String bookName, String customerUserName, Context context) throws IOException{
        String filename = customerUserName + ".txt";
        String book = bookName + "\n";

        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_APPEND);
        fos.write(book.getBytes());
        fos.close();

        RemoveBookFromInventory(bookName, context);
    }

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

    public static void AddBookToInventory(String bookName, Context context) throws IOException{
        String book = bookName + "\n";

        FileOutputStream fos = context.openFileOutput(currentInventoryFileName, Context.MODE_APPEND);
        fos.write(book.getBytes());
        fos.close();
    }

    public static void RemoveBookFromInventory(String bookName, Context context) throws IOException{
        //TODO
    }

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

    public static void AddCustomerData(String customerName,
                                           String idNumber,
                                           String age,
                                           String authorityLevel,
                                           String homeAddress,
                                           String emailAddress,
                                           Context context) throws IOException{
        String customerDataLine = customerName + "," +
                                    idNumber + "," +
                                    age + "," +
                                    authorityLevel + "," +
                                    homeAddress + "," +
                                    emailAddress + "," + "\n";

        FileOutputStream fos = context.openFileOutput(customerDataFileName, Context.MODE_APPEND);
        fos.write(customerDataLine.getBytes());
        fos.close();
    }

    public static void ModifyCustomerData(){
        //TODO
    }

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

        return new String[] {null, null, null, null, null, null};
    }
}
