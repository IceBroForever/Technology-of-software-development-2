package com.pasha1ruh.server;

import com.pasha1ruh.square_equation.SquareEquationSolver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SquareEquationSolverServer {
    public static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started: " + s);

        while (true) {
            Socket socket = null;
            try {
                socket = s.accept();
                System.out.println("Connection accepted: " + socket);
                PrintWriter out = null;
                try {
                    BufferedReader in =
                            new BufferedReader(
                                    new InputStreamReader(
                                            socket.getInputStream()));

                    out = new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream())), true);

                    String requestStr = in.readLine();

                    System.out.println("Received request: " + requestStr);

                    JSONObject request = new JSONObject(requestStr);
                    SquareEquationSolver squareEquation = new SquareEquationSolver(
                            request.getDouble("a"),
                            request.getDouble("b"),
                            request.getDouble("c")
                    );

                    System.out.println("Calculation equation: " + squareEquation);

                    double[] roots = squareEquation.solve();

                    System.out.println("Calculated roots: " + roots);

                    JSONObject response = new JSONObject();
                    response.put("roots", new JSONArray(roots));

                    System.out.println("Sending response: " + response.toString());
                    out.println(response.toString());
                } catch (IOException e) {
                    System.out.println("Error while handling request: " + e.getMessage());
                } catch (JSONException e) {
                    System.out.println("Error in request: " + e.getMessage());
                    JSONObject response = new JSONObject();
                    response.put("error", "bad_format");
                    response.put("message", e.getMessage());
                    System.out.println("Sending response: " + response.toString());
                    out.println(response.toString());
                } catch (IllegalStateException e) {
                    System.out.println("Error while calculate: " + e.getMessage());
                    JSONObject response = new JSONObject();
                    response.put("error", "illegal_state");
                    response.put("message", e.getMessage());
                    System.out.println("Sending response: " + response.toString());
                    out.println(response.toString());
                }
            } catch (IOException e) {
                System.out.println("Error while accepting request: " + e.getMessage());
            } finally {
                if (socket != null) socket.close();
            }
        }
    }
}
