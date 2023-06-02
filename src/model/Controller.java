package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Controller<T> {
    public static int  DISTANCE=0;
    private File data;
    private ArrayList<Vertex> cities;
    private  ArrayList<Vertex> conection;
    private WeightedDirectedGraphMatrix matrix;
    private Gson gson;

    public Controller() throws IOException {
        this.cities= new ArrayList<Vertex>(50);
        this.conection= new ArrayList<Vertex>(50);
        this.matrix= new WeightedDirectedGraphMatrix<>(50);
        gson = new Gson();
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir.getAbsolutePath() + "/data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        data = new File(dataDirectory, "result.json");
        uploadData();
        createConnection();
    }
    private void uploadData(){
            try{
                FileInputStream fis = new FileInputStream(data);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                StringBuilder msj = new StringBuilder();
                while((line = reader.readLine())!=null){
                    msj.append(line);
                }
                fis.close();
                Gson gson = new Gson();
                Vertex[] array = gson.fromJson(msj.toString(),Vertex[].class);
                for(Vertex vertex : array){
                    this.matrix.addVertex(vertex.getValue());
                    this.cities.add(vertex);
                }
            }catch(Exception ex){
                ex.printStackTrace();
                //Ya que creamos el archivo antes, nunca va a pasar que no lo encuentra;
            }
    }



    public void createConnection() {
        this.matrix.addEdge("Armenia", "Barranquilla", 600);
        this.matrix.addEdge("Armenia", "Cali", 320);
        this.matrix.addEdge("Armenia", "Bogotá", 260);
        this.matrix.addEdge("Cúcuta", "Bucaramanga", 120);
        this.matrix.addEdge("Cúcuta", "Tunja", 350);
        this.matrix.addEdge("Cúcuta", "Calarcá", 450);
        this.matrix.addEdge("Bucaramanga", "Cali", 520);
        this.matrix.addEdge("Bucaramanga", "Medellín", 420);
        this.matrix.addEdge("Bucaramanga", "Cartagena", 780);
        this.matrix.addEdge("Ibagué", "Pereira", 230);
        this.matrix.addEdge("Ibagué", "Bogotá", 180);
        this.matrix.addEdge("Ibagué", "Manizales", 290);
        this.matrix.addEdge("Villavicencio", "Neiva", 150);
        this.matrix.addEdge("Villavicencio", "Bogotá", 100);
        this.matrix.addEdge("Villavicencio", "Bucaramanga", 420);
        this.matrix.addEdge("Neiva", "Popayán", 370);
        this.matrix.addEdge("Neiva", "Barranquilla", 440);
        this.matrix.addEdge("Neiva", "Cali", 280);
        this.matrix.addEdge("Pasto", "Popayán", 90);
        this.matrix.addEdge("Pasto", "Tumaco", 190);
        this.matrix.addEdge("Pasto","Totoro",60);
        this.matrix.addEdge("Popayán", "Cali", 120);
        this.matrix.addEdge("Popayán", "Bogotá", 350);
        this.matrix.addEdge("Sincelejo", "Cartagena", 110);
        this.matrix.addEdge("Sincelejo", "Barranquilla", 130);
        this.matrix.addEdge("Sincelejo", "Montería", 220);
        this.matrix.addEdge("Valledupar", "Barranquilla", 180);
        this.matrix.addEdge("Valledupar", "Santa Marta", 290);
        this.matrix.addEdge("Valledupar", "Bogotá", 480);
        this.matrix.addEdge("Tunja", "Bogotá", 130);
        this.matrix.addEdge("Tunja", "Manizales", 380);
        this.matrix.addEdge("Tunja", "Cali", 550);
        this.matrix.addEdge("Riohacha", "Santa Marta", 130);
        this.matrix.addEdge("Riohacha", "Valledupar", 180);
        this.matrix.addEdge("Riohacha", "Barranquilla", 210);
        this.matrix.addEdge("Montería", "Cartagena", 160);
        this.matrix.addEdge("Montería", "Barranquilla", 190);
        this.matrix.addEdge("Montería", "Santa Marta", 320);
        this.matrix.addEdge("Montería","Totoro", 356);
        this.matrix.addEdge("Quibdó", "Medellín", 280);
        this.matrix.addEdge("Quibdó", "Bogotá", 320);
        this.matrix.addEdge("Quibdó", "Cartagena", 420);
        this.matrix.addEdge("Florencia", "Neiva", 310);
        this.matrix.addEdge("Florencia", "Bogotá", 390);
        this.matrix.addEdge("Florencia", "Cali", 520);
        this.matrix.addEdge("Florencia","Totoro",450);
        this.matrix.addEdge("Yopal", "Bucaramanga", 380);
        this.matrix.addEdge("Yopal", "Villavicencio", 270);
        this.matrix.addEdge("Yopal", "Bogotá", 320);
        this.matrix.addEdge("Villanueva", "Barranquilla", 240);
        this.matrix.addEdge("Villanueva", "Cartagena", 280);
        this.matrix.addEdge("Villanueva", "Valledupar", 220);
        this.matrix.addEdge("Villanueva","Totoro",250);
        this.matrix.addEdge("San Andrés", "Cartagena", 230);
        this.matrix.addEdge("San Andrés", "Barranquilla", 280);
        this.matrix.addEdge("San Andrés", "Santa Marta", 320);
        this.matrix.addEdge("Leticia", "Bogotá", 900);
        this.matrix.addEdge("Leticia", "Medellín", 850);
        this.matrix.addEdge("Leticia", "Cali", 930);
        this.matrix.addEdge("Inírida", "Bogotá", 730);
        this.matrix.addEdge("Inírida", "Medellín", 670);
        this.matrix.addEdge("Inírida", "Barranquilla", 820);
        this.matrix.addEdge("Mitu", "Bogotá", 800);
        this.matrix.addEdge("Mitu", "Medellín", 750);
        this.matrix.addEdge("Mitu", "Cali", 830);
        this.matrix.addEdge("Puerto Carreño", "Bogotá", 810);
        this.matrix.addEdge("Puerto Carreño", "La Dorada", 760);
        this.matrix.addEdge("Puerto Carreño", "Barranquilla", 910);
        this.matrix.addEdge("Arauca", "Bogotá", 520);
        this.matrix.addEdge("Arauca", "Bucaramanga", 440);
        this.matrix.addEdge("Arauca", "Calarcá", 590);
        this.matrix.addEdge("Barrancabermeja", "Bucaramanga", 90);
        this.matrix.addEdge("Barrancabermeja", "Cali", 520);
        this.matrix.addEdge("Barrancabermeja", "Bogotá", 390);
        this.matrix.addEdge("Tumaco", "Cali", 270);
        this.matrix.addEdge("Tumaco", "Bogotá", 650);
        this.matrix.addEdge("Tumaco", "Cartagena", 450);
        this.matrix.addEdge("Turbo", "Medellín", 190);
        this.matrix.addEdge("Turbo", "Santander de Quilichao", 230);
        this.matrix.addEdge("Turbo", "Santa Marta", 340);
        this.matrix.addEdge("Magangué", "Cartagena", 90);
        this.matrix.addEdge("Magangué", "Barranquilla", 120);
        this.matrix.addEdge("Magangué", "Sincelejo", 80);
        this.matrix.addEdge("Girardot", "Bogotá", 130);
        this.matrix.addEdge("Girardot", "Manizales", 380);
        this.matrix.addEdge("Girardot", "Cali", 550);
        this.matrix.addEdge("Sogamoso", "Bucaramanga", 180);
        this.matrix.addEdge("Sogamoso", "Tunja", 70);
        this.matrix.addEdge("Sogamoso", "Calarcá", 300);
        this.matrix.addEdge("Duitama", "Bucaramanga", 220);
        this.matrix.addEdge("Duitama", "Tunja", 50);
        this.matrix.addEdge("Duitama", "Calarcá", 280);
        this.matrix.addEdge("Tulua", "Cali", 40);
        this.matrix.addEdge("Tulua", "Pereira", 100);
        this.matrix.addEdge("Tulua", "Calarcá", 250);
        this.matrix.addEdge("Cúcuta", "Barranquilla", 120);
        this.matrix.addEdge("Cúcuta", "Bogotá", 350);
        this.matrix.addEdge("Ibagué", "Neiva", 230);
        this.matrix.addEdge("Ibagué", "Cali", 180);
        this.matrix.addEdge("Pasto", "Cali", 90);
        this.matrix.addEdge("Pasto","Bello",116);
        this.matrix.addEdge("Pasto","Bogotá",400);
        this.matrix.addEdge("Quibdó", "Medellín", 280);
        this.matrix.addEdge("Quibdó", "Bogotá", 320);
        this.matrix.addEdge("Quibdó", "Cartagena", 420);
        this.matrix.addEdge("Bogotá", "Armenia", 260);
        this.matrix.addEdge("Bogotá", "Pitalito", 350);
        this.matrix.addEdge("Bogotá", "Cali", 320);
        this.matrix.addEdge("Bogotá", "Barranquilla", 600);
        this.matrix.addEdge("Bogotá", "Bello", 670);
        this.matrix.addEdge("Bogotá", "Santa Marta", 700);
        this.matrix.addEdge("Bogotá", "Manizales", 210);
        this.matrix.addEdge("Bogotá", "Pereira", 260);
        this.matrix.addEdge("Medellín", "Armenia", 330);
        this.matrix.addEdge("Medellín", "Cúcuta", 310);
        this.matrix.addEdge("Medellín", "Cali", 420);
        this.matrix.addEdge("Medellín", "Buenaventura", 700);
        this.matrix.addEdge("Medellín", "Cartagena", 650);
        this.matrix.addEdge("Medellín", "Ipiales", 670);
        this.matrix.addEdge("Medellín", "Manizales", 100);
        this.matrix.addEdge("Medellín", "Pereira", 130);
        this.matrix.addEdge("Cali", "Armenia", 320);
        this.matrix.addEdge("Cali", "Cúcuta", 520);
        this.matrix.addEdge("Cali", "Mocoa", 320);
        this.matrix.addEdge("Cali", "Barranquilla", 800);
        this.matrix.addEdge("Cali", "La Dorada", 760);
        this.matrix.addEdge("Cali", "Santa Marta", 790);
        this.matrix.addEdge("Cali", "Manizales", 230);
        this.matrix.addEdge("Cali", "Pereira", 180);
        this.matrix.addEdge("Barranquilla", "Armenia", 600);
        this.matrix.addEdge("Barranquilla", "Cúcuta", 430);
        this.matrix.addEdge("Barranquilla", "Bogotá", 600);
        this.matrix.addEdge("Barranquilla", "Cali", 800);
        this.matrix.addEdge("Cartagena", "Armenia", 620);
        this.matrix.addEdge("Cartagena", "Cúcuta", 570);
        this.matrix.addEdge("Cartagena", "Bogotá", 670);
        this.matrix.addEdge("Cartagena", "Mocoa", 760);
        this.matrix.addEdge("Santa Marta", "Armenia", 610);
        this.matrix.addEdge("Santa Marta", "Cúcuta", 480);
        this.matrix.addEdge("Santa Marta", "Santander de Quilichao", 700);
        this.matrix.addEdge("Santa Marta", "Cali", 790);
        this.matrix.addEdge("Manizales", "Armenia", 130);
        this.matrix.addEdge("Manizales", "Pitalito", 360);
        this.matrix.addEdge("Manizales", "Bogotá", 210);
        this.matrix.addEdge("Manizales", "Cali", 230);
        this.matrix.addEdge("Pereira", "Ipiales", 450);
        this.matrix.addEdge("Pereira", "Cúcuta", 290);
        this.matrix.addEdge("Pereira", "Bogotá", 260);
        this.matrix.addEdge("Pereira", "Cali", 180);
        this.matrix.addEdge("Totoro","Cali", 200);
        this.matrix.addEdge("Totoro","Santa Marta", 650);
        this.matrix.addEdge("Totoro","Sogamoso", 850);
        this.matrix.addEdge("Ipiales","Pasto", 180);
        this.matrix.addEdge("Ipiales","Mitu", 400);
        this.matrix.addEdge("Ipiales","Yopal", 700);
        this.matrix.addEdge("Pitalito","Ipiales", 460);
        this.matrix.addEdge("Pitalito","Calarcá", 650);
        this.matrix.addEdge("Santander de Quilichao","Cali", 30);
        this.matrix.addEdge("Santander de Quilichao","Sogamoso", 30);
        this.matrix.addEdge("Santander de Quilichao","Armenia", 250);
        this.matrix.addEdge("Mocoa","Valledupar", 800);
        this.matrix.addEdge("Mocoa","Popayán", 350);
        this.matrix.addEdge("La Dorada","Barranquilla", 450);
        this.matrix.addEdge("La Dorada","Neiva", 200);
        this.matrix.addEdge("Buenaventura","Puerto Carreño", 300);
        this.matrix.addEdge("Buenaventura","Quibdó", 300);
        this.matrix.addEdge("Buenaventura","Tunja", 300);
        this.matrix.addEdge("Bello","Cali",680);
        this.matrix.addEdge("Bello","Armenia",500);
        this.matrix.addEdge("Bello","Ipiales",420);
        this.matrix.addEdge("Ciénaga","Calarcá",590);
        this.matrix.addEdge("Ciénaga","Popayán",650);
        this.matrix.addEdge("Ciénaga","Pitalito",480);
        this.matrix.addEdge("Calarcá","Ocaña",358);
        this.matrix.addEdge("Calarcá","Neiva",358);
        this.matrix.addEdge("Calarcá","Duitama",320);
        this.matrix.addEdge("Ocaña","Mocoa",320);
        this.matrix.addEdge("Ocaña","Bello",320);
        this.matrix.addEdge("Ocaña","Sogamoso",320);
    }
    public String print(){
        return this.matrix.printVertices();
    }
    private void saveList() throws IOException {
        FileOutputStream fos = new FileOutputStream(data);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        String data = gson.toJson(cities);
        writer.write(data);
        writer.flush();
        fos.close();
    }
    public void distanceBeteenCities(String start,String dest){
       matrix.bfs((T) start).toString();
       DISTANCE=matrix.getVertex((T) dest).getTime();
    }


    public String startTravel(String start, String dest) {
        return matrix.dijkstra((T)start,(T) dest).toString();
    }
}