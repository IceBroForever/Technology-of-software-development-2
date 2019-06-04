package com.pasha1ruh.client.entities;

import com.pasha1ruh.server.SquareEquationSolverServer;
import com.pasha1ruh.square_equation.SquareEquation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RemoteSquareEquationSolver extends SquareEquation {
    public RemoteSquareEquationSolver(double a, double b, double c) {
        super(a, b, c);
    }

    @Override
    public double[] solve() throws IllegalStateException {
        JSONObject responce = null;

        Socket socket = null;
        try {
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            socket = new Socket(addr, SquareEquationSolverServer.PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            JSONObject request = new JSONObject();
            request.put("a", a);
            request.put("b", b);
            request.put("c", c);

            out.println(request.toString());

            responce = new JSONObject(in.readLine());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) { System.out.println("Error while closing socket: " + e.getMessage()); }
            if (responce == null) throw new IllegalStateException("Can not be calculated");
        }

        if (responce.has("error")) {
            String error = responce.getString("error");

            switch (error) {
                case "bad_format": {
                    System.out.println("Bad format: " + responce.getString("message"));
                    throw new IllegalStateException("Can not be calculated");
                }
                case "illegal_state": {
                    throw new IllegalStateException(responce.getString("message"));
                }
            }
        }

        ArrayList<Double> roots = new ArrayList<>();
        JSONArray arr = responce.getJSONArray("roots");
        for (int i = 0; i < arr.length(); i++)
            roots.add(arr.getDouble(i));

        return roots.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
