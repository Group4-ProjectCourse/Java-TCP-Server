package Data;

import Model.RegisterUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*

we can use a singleton to  allows many threads to easily share a global instance.
But we can change it to whatever pattern will suit us best
This class will be used for the database queries
Each method should handle a different query.



*/
public class DataBaseService {

    // singleton database class
    private static DataBaseService dataBaseService;
    Connection con = null;


    private DataBaseService() {

        // here you can add the remote Database url
        String url = "jdbc:mysql://localhost:3306/SmartHouse";
        String userName = "";
        String password = " ";
        try {
            Class.forName("com.mysql.jdbc.Driver");


            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }
    private DataBaseService getInstance() {
        if (dataBaseService == null)
            dataBaseService= new DataBaseService();

        return dataBaseService;
    }


    // get all users
    public List<RegisterUser> getUsers() {
        List<RegisterUser> registerUsers = new ArrayList<RegisterUser>();
        try {
            // the select statement should be changed to the database you are using
            String sql = "select * from user ";

            Statement st = con.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                RegisterUser r = new RegisterUser();


                //r.setemail(resultSet.getInt(1));
                //r.setaddress(resultSet.getString(2));
               // r.phoneNumber(resultSet.getInt(3));

                registerUsers.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return registerUsers;
    }


    // get user by email
    public RegisterUser getUserByEmail(String email) {
        List<RegisterUser> registerUsers = new ArrayList<RegisterUser>();
        RegisterUser r = new RegisterUser();
        try {

            // we can either get user bay email or it can be cahnged to username
            // it should always be unique for each user
            String sql = "select * from user where email = " + email;

            Statement st = con.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            if (resultSet.next()) {

                // here we use the model classes to read the selected user
                //
                //a.getEmail(resultSet.getString(1));
              //  a.setName(resultSet.getString(2));



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return r;
    }



}
