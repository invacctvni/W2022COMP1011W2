package com.example.w2022comp1011w2;

import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//Update DB Utility Class.
public class DBUtility {
    private static String user = "Jiaqi200477892";
    private static String password = "UeRfRiM5fr";
    //    connection string hostname / dbname
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/Jiaqi200477892";

    /**
     * This method will send a Camera object into the DB and return the cameraID.
     */
//    return id
    public static int insertCameraIntoDB(Camera camera) throws SQLException {
//get -1 back something went wrong
        int cameraID = -1;
        //what's returned from the DB when we query it.
        ResultSet resultSet = null;

//        placeholder, protect from attack Prepare statement to ensure
        String sql = "INSERT INTO cameras (make,model,resolution,slr,price) VALUES (?,?,?,?,?);";

        //this is called a "try with resources" block. It will auto-close anything in the ()
        try (
                //connect to the db
                Connection conn = DriverManager.getConnection(connectURL, user, password);
                //use this statement to protect. Create a statement object - ps
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"cameraID"})
        ) {
//            ? get replaced by the following
//            configure the prepared statement to prevent sql injection attacks
            ps.setString(1, camera.getMake());
            ps.setString(2, camera.getModel());
            ps.setInt(3, camera.getResolution());
            ps.setBoolean(4, camera.isSlr());
            ps.setDouble(5, camera.getPrice());
            //run the command into the DB. equal to run the command in mysql
            ps.executeUpdate();

            //get the cameraID
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cameraID = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return cameraID;
    }

    /**
     * This method will return a list of all Camera's and their associated number of sales
     */
    public static ArrayList<Camera> getCamerasFromDB() {
        ArrayList<Camera> cameras = new ArrayList<>();

        //query the DB and create Camera objects / add them to the List
        String sql = "SELECT cameras.cameraID, make, model, resolution, price, slr, COUNT(salesId) AS 'units sold'\n" +
                "FROM cameras INNER JOIN camerasales \n" +
                "ON cameras.cameraID = camerasales. cameraId\n" +
                "GROUP BY cameras.cameraID";
        try (
                Connection conn = DriverManager.getConnection(connectURL, user, password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                int cameraID = resultSet.getInt("cameraId");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int resolution = resultSet.getInt("resolution");
                double price = resultSet.getDouble("price");
                boolean slr = resultSet.getBoolean("slr");
                int unitsSold = resultSet.getInt("units sold");
                Camera newCamera = new Camera(cameraID, make, model, resolution, slr, price, unitsSold);
                cameras.add(newCamera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cameras;
    }

    public static XYChart.Series<String, Integer> getUnitsSold() {
        XYChart.Series<String,Integer> unitsSold = new XYChart.Series<>();
        unitsSold.setName("2022");
        //get a list of cameras from db
        ArrayList<Camera> cameras = getCamerasFromDB();
        //loop over each camera and add it to chart series
//        unitsSold.getData().add(new XYChart.Data<>("Jaret's cool camera", 20));
//        unitsSold.getData().add(new XYChart.Data<>("Matheson's cool camera", 30));
        for (Camera camera : cameras) {
            unitsSold.getData().add(new XYChart.Data<>(camera.getMakeAndModel(),camera.getUnitsSold()));
            System.out.println(camera.toString());
        }
        return unitsSold;
    }
}
