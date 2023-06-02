package ui;
import java.io.IOException;
import java.util.Scanner;


import model.*;
public class Main {
    private Scanner reader ;
    private Controller controller;
    public Main() throws IOException {
        this.reader = new Scanner(System.in);
        this.controller=new Controller();
    }
    private void print(Object o){System.out.println(o);}
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        int option = -1;
        do{
            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);
    }

    public int getOptionShowMenu(){
        int option = 0;
        print(printMenu());
        option = reader.nextInt();
        return option;
    }
    public void executeOption(int option) throws IOException {
        String dest="";
        String a="";
        switch(option){


            case 1:
                printCities();
                break;
            case 2:
                printCities();
                print("what is the city where you are going to start?");
                a=reader.nextLine();
                String start=reader.nextLine();
                print("what is the destination?");
                dest= reader.nextLine();
                startTravel(start,dest);

                break;
            case 3:
                printCities();
                print("what is the city where you are going to start?");
                a=reader.nextLine();
                start=reader.nextLine();
                print("what is the destination?");
                dest= reader.nextLine();
                distanceBeteenCities(start, dest);

                break;

            case 0:
                break;

            default :
                print("invalid option");
                break;
        }
    }
    public String printMenu(){
        return
                "\n" +
                        "<< ------------------------------------------------ >>\n" +
                        "<<                 Transport terminal               >> \n"+
                        "<< ------------------------------------------------ >>\n"+
                        "1. Show  cities \n"+
                        "2. Start a travel \n"+
                        "3. look at the distance between cities \n"+
                        "0. Exit";
    }
    public void printCities(){
        String msg= controller.print();
        print( msg);
    }
    public void distanceBeteenCities(String start,String dest ){
        controller.distanceBeteenCities(start,dest);
        print (" The distance between cities from "+ start +" to "+dest+ " is "+Controller.DISTANCE +" cities");
    }
    public void startTravel(String start , String dest){
        String msg= controller.startTravel(start,dest);
        if(msg==null){
            print( "the cities you chose do not exist");
        }else{
            print("The cities you need to visit to reach your destination with the shortest distance are:");
            print( msg);
        }

    }