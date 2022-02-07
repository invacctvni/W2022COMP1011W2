package com.example.w2022comp1011w2;

import java.sql.*;

//Update DB Utility Class.
public class DBUtility {
    private static String user = "Jiaqi200477892";
    private static String password ="UeRfRiM5fr";
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
        try(
                //connect to the db
                Connection conn = DriverManager.getConnection(connectURL, user, password);
                //use this statement to protect. Create a statement object - ps
                PreparedStatement ps = conn.prepareStatement(sql,new String[] {"cameraID"})
                )
        {
//            ? get replaced by the following
//            configure the prepared statement to prevent sql injection attacks
            ps.setString(1,camera.getMake());
            ps.setString(2,camera.getModel());
            ps.setInt(3,camera.getResolution());
            ps.setBoolean(4,camera.isSlr());
            ps.setDouble(5,camera.getPrice());
            //run the command into the DB. equal to run the command in mysql
            ps.executeUpdate();

            //get the cameraID
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                cameraID = resultSet.getInt(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(resultSet !=null) {
                resultSet.close();
            }
        }

        return cameraID;
    }
}
