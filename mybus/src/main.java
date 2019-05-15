import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String [] args){

       System.out.println("Loading...");
       new FindRoute();
       System.out.println("Hello! This is Smart Bus system terminal.");
       new FindRoute().findPath("南京邮电大学","南京站·南广场东");
       start();
    }
    static Scanner input = new Scanner(System.in);
    static String name;
    public static void start(){
        System.out.println("You are\n1)User\n2)Driver\n3)Administrator");
        int temp=input.nextInt();
        System.out.print("Please insert your name:");
        name = input.next();
        System.out.print("Password:");
        String pw = input.next();
        int ID;
        switch (temp){
            case 1:{
                ID=mysql.authentication(1,name,pw);
                if (ID<0) {
                    System.out.println("Wrong username or password.");
                    start();
                }else{
                    showUser(ID);
                }
                break;
            }
            case 2:{
                ID=mysql.authentication(2,name,pw);
                if (ID<0) {
                    System.out.println("Wrong username or password.");
                    start();
                }else{
                    showDriver(ID);
                }
                break;
            }
            case 3:{
                ID=mysql.authentication(3,name,pw);
                if (ID<0) {
                    System.out.println("Wrong username or password.");
                    start();
                }else{
                    showAdmin(ID);
                }
            }
        }
    }
    public static void showUser(int ID){
        System.out.println("Hello, "+name+"! Select one option:\n1)View record\n2)Plan my route\n3)Check balance\n4)Exit");
        int temp=input.nextInt();
        switch (temp){
            case 1:{


            }
            case 2:{
                System.out.println("Where are you traveling from?");
                String from=input.next();
                System.out.println("What's your destination?");
                String to = input.next();
                new FindRoute().findPath(from,to);
                showUser(ID);
                break;
            }
            case 3:{
                String sql="select balance from user where uid="+ID;
                ResultSet rs=mysql.getResult(sql);
                try{
                    rs.next();
                    System.out.println("Your balance is "+rs.getString("balance"));
                    mysql.close();
                }catch (Exception e){
                }
                showUser(ID);
                break;
            }
            case 4:{
                start();
            }
        }
    }
    public static void showDriver(int ID){
        System.out.println("Hello, "+name+"! Select one option:\n1)Check my schedule\n2)See reports against me\n3)Exit");
        int temp=input.nextInt();
        switch (temp){
            case 1:{

            }
            case 2:{

            }
            case 3:{
                start();
            }
        }
    }
    public static void showAdmin(int ID){
        System.out.println("Hello, "+name+"! Select one option:\n1)Check my schedule\n2)See reports against me\n3)Exit");
        int temp=input.nextInt();
        switch (temp){
            case 1:{

            }
            case 2:{

            }
        }
    }
    public static void printTable(){

    }
}