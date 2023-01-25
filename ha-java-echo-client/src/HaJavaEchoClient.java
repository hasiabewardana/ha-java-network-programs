import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HaJavaEchoClient {
    public static void main(String[] args) {
        System.out.println("Welcome to HaJavaEchoClient!");

        try(Socket socket = new Socket("127.0.0.1",5000)){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter strings to be echoed: ");
                echoString = scanner.nextLine();

                printWriter.println(echoString);

                if (!echoString.equals("exit")){
                    response = bufferedReader.readLine();
                    System.out.println(response);
                }
            }while (!echoString.equals("exit"));
        }catch (IOException exception){
            System.out.println("Client error: " + exception.getMessage());
        }
    }
}