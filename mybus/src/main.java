import java.sql.ResultSet;
import java.util.Scanner;

public class main {
    public static void main(String [] args){
        System.out.println("Hello! This is Smart Bus system terminal.");
        start();
    }
    static String name;
    public static void start(){
        System.out.println("You are\n1)User\n2)Driver\n3)Administrator");
        Scanner input = new Scanner(System.in);
        int temp=input.nextInt();
        System.out.print("Please insert your name:");
        name = input.next();
        System.out.print("Password:");
        String pw = input.next();
        int ID;
        switch (temp){
            case 1:{
                ID=sql.authentication(1,name,pw);
                if (ID<0) {
                    System.out.println("Wrong username or password.");
                    start();
                }else{
                    showUser(ID);
                }
                break;
            }
            case 2:{
                ID=sql.authentication(2,name,pw);
                if (ID<0) {
                    System.out.println("Wrong username or password.");
                    start();
                }else{
                    showDriver(ID);
                }
                break;
            }
            case 3:{
                ID=sql.authentication(3,name,pw);
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
        System.out.println("Hello, "+name+"! Select one option:\n1)View record\n2)Plan my route\n3)Check balance");
        Scanner input=new Scanner(System.in);
        int temp=input.nextInt();
        switch (temp){
            case 1:{
                String sql="";

            }
            case 2:{
                String sql="";
            }
            case 3:{
                String sql="select balance from user where uid="+ID;
                ResultSet rs=mysql.getResult(sql);
            }
        }
    }
    public static void showDriver(int ID){

    }
    public static void showAdmin(int ID){

    }
    public static void printTable(){

    }
}