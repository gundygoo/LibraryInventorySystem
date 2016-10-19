package com.dreamteam.softwareengineering.libraryinventorysystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 10/18/2016.
 */

public class DatabaseAccessor {
    public static String loginFileName = "LOGINS.txt";
    public static String currentInventoryFileName = "INVENTORY.txt";
    public static String customerDataFileName = "CUSTOMERDATA.txt";

    public static boolean AuthenticateUserLogin(String username, String password){
       return true;
    }

    public static void CreateNewCustomerAccount(String customerUserName){

    }

    public static void CheckoutBookToCustomer(String bookName, String customerUserName){

    }

    public static void CheckinBookFromCustomer(String bookname, String customerUserName){

    }

    public static List<String> ViewCustomerBookList(String customerUserName){
        return new ArrayList<>();
    }

    public static void AddBookToInventory(String bookName){

    }

    public static void RemoveBookFromInventory(String bookName){

    }

    public static List<String> ViewInventory(){
        return new ArrayList<>();
    }

    public static void ModifyCustomerData(){

    }
}
