import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {

    public static void main(String[] args){
        Socket socket  = null;
        InputStreamReader inputStreamreader = null;
        OutputStreamWriter outputStreamwriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);
            inputStreamreader = new InputStreamReader(socket.getInputStream());
            outputStreamwriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamreader);
            bufferedWriter = new BufferedWriter(outputStreamwriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                //send msg to server
                String msgToSend = scanner.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                //reply from the server
                System.out.println(" server: " + bufferedReader.readLine());
                if (msgToSend.equalsIgnoreCase("BYE"))
                    break;

            }


        }catch (IOException e){
            e.printStackTrace();
        }finally {

            try{
                if(socket != null)
                    socket.close();
                if(inputStreamreader != null)
                    inputStreamreader.close();
                if(outputStreamwriter != null)
                    outputStreamwriter.close();
                if(bufferedReader != null)
                    bufferedReader.close();
                if(bufferedWriter != null)
                    bufferedWriter.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
