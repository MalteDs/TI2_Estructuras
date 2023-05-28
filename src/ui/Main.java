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
        System.out.println(printMenu());
        option = reader.nextInt();
        return option;
    }
    public void executeOption(int option) throws IOException {

        switch(option){

            case 1:
                    print(controller.print());
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                break;
            case 6:

            case 0:
                break;

            default :
                System.out.println("Su eleccion no es correcta");
                break;
        }
    }
    public String printMenu(){
        return
                "\n" +
                        "<< ------------------------------------------------ >>\n" +
                        "<<                 Airplane System                   >> \n"+
                        "<< ------------------------------------------------ >>\n"+
                        "1. Mirar Ciudades \n"+
                        "2. Iniciar Viaje \n"+
                        "3. Conccer distancia\n"+
                        "0. Exit";
    }







}
