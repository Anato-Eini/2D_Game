package org.example.game2d;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SocketClient implements Runnable {
    private static Socket socket;
//    Gson gson;
    ResponseListener responseListener;

    public ResponseListener getResponseListener() {
        return responseListener;
    }

    public void closeEverything(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Connecting to server");
            socket = new Socket("provided-in.gl.at.ply.gg", 42572);
//            socket = new Socket("180.190.180.195", 5050);
//            System.out.println(socket);

//            register("asd", "asd", "asd", "asd");
//            loginAsync("asd", "asd");

            String messageFromServer;
            while (socket.isConnected()){
                byte[] dataFromClient = readBytes();

                if(dataFromClient == null) continue;

                messageFromServer = new String(dataFromClient, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            closeEverything(socket);
        }
    }

    public static void sendBytes(byte[] myByteArray) throws IOException {
        sendBytes(myByteArray, 0, myByteArray.length);
    }

    public static void sendBytes(byte[] myByteArray, int start, int len) throws IOException {
        if (len < 0)
            throw new IllegalArgumentException("Negative length not allowed");
        if (start < 0 || start >= myByteArray.length)
            throw new IndexOutOfBoundsException("Out of bounds: " + start);
        // Other checks if needed.

        // May be better to save the streams in the support class;
        // just like the socket variable.
        OutputStream out = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeInt(len);
        if (len > 0) {
            dos.write(myByteArray, start, len);
        }
    }

    public byte[] readBytes() throws IOException {
        InputStream in = socket.getInputStream();
        DataInputStream dis = new DataInputStream(in);

        int len = dis.readInt();
        byte[] data = new byte[len];
        if (len > 0) {
            dis.readFully(data);
        }
        return data;
    }

    public static void moveAsync(int id, String direction){
        new Thread(() -> {
            try {
                Map<String, Object> data= new HashMap<>();
                data.put("id", id);
                data.put("direction", direction);

                sendBytes(writeRequest("login", data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static byte[] writeRequest(String operation, Map<String, Object> data){
//        Gson gson = new Gson();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("operation", operation);
        dataMap.put("data", data);
//        String jsonData = gson.toJson(dataMap);
        String requestData = dataMap.toString();
        return requestData.getBytes();
    }

    public interface ResponseListener{
        void onSuccess(String message);
    }
}