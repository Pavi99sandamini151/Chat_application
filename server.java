import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamreader = null;
        OutputStreamWriter outputStreamwriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(1234);

        while(true){
            try{
                socket = serverSocket.accept();
                inputStreamreader = new InputStreamReader(socket.getInputStream());
                outputStreamwriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamreader);
                bufferedWriter = new BufferedWriter(outputStreamwriter);

                while(true){
                    String msgfromCLient = bufferedReader.readLine();
                    System.out.println("client"+ msgfromCLient);
                    bufferedWriter.write("MSG recieved");

                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if(msgfromCLient.equalsIgnoreCase("BYE"))
                        break;
                }
                socket.close();
                inputStreamreader.close();
                outputStreamwriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
