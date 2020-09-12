import java.sql.*;
import java.util.Scanner;

public class PersonCURD {
    public static void main(String args[]){
        String url ="jdbc:mysql://localhost:3306/namedDB";
        String userName = "root";
        String password = "root123@";

        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            System.out.println("Connection is sucessfull");
            System.out.println("Enter the CURD value: ");
            Scanner scanner = new Scanner(System.in);
            String CURD_value = scanner.next();
            CURD_value = CURD_value.toUpperCase();

            switch (CURD_value){
                case "C":
                    System.out.println("Entered option is to create new PERSON");
                    String insertQuery = "Insert into PERSON(firstname,secondname) VALUES(?,?)";
                    PreparedStatement statement = connection.prepareStatement(insertQuery);
                    System.out.println("Enter the first Name: ");
                    String firstname = scanner.next();
                    System.out.println("Enter the second Name: ");
                    String secondname = scanner.next();
                    statement.setString(1, firstname);
                    statement.setString(2, secondname);
                    int successs = statement.executeUpdate();
                    if (successs > 0) {
                        System.out.print("Person inserted");
                    }
                    else
                    {
                        System.out.print("Person insert unsucessfull");
                    }
                break;

                case "U":
                    System.out.println("Entered option is to update PERSON");
                    System.out.println("Enter PERSON id to update: ");
                    int updateid = scanner.nextInt();
                    System.out.println("Enter the firstname: ");
                    String ufirstname = scanner.next();
                    System.out.println("Enter the secondname: ");
                    String usecondname = scanner.next();
                    String updatePerson = "UPDATE PERSON set firstName =?,secondname =? where id=?";
                    PreparedStatement statement2 = connection.prepareStatement(updatePerson);
                    statement2.setString(1,ufirstname);
                    statement2.setString(2,usecondname);
                    statement2.setInt(3,updateid);
                    int suc = statement2.executeUpdate();
                    if (suc > 0) {
                        System.out.print(" Person updated sucessfully ");
                    }
                    else{
                        System.out.print("Person updated unsucessfully");
                    }
                break;

                case "R":
                    System.out.println("Entered option is to retrive PERSON Details");
                    String retrivePerson = "SELECT * from PERSON";
                    Statement statement1 = connection.createStatement();
                    ResultSet resultSet = statement1.executeQuery(retrivePerson);
                    int count = 0;
                    while (resultSet != null && resultSet.next()){
                        String firstName = resultSet.getString("firstName");
                        String lastName = resultSet.getString("secondname");
                        int id = resultSet.getInt("id");
                        count++;
                        //Display values
                        System.out.print("ID: " + id);
                        System.out.print(", firstname: " + firstName);
                        System.out.println(", secondname: " + lastName);
                    }
                break;

                case "D":
                    System.out.println("Entered option is to delete PERSON");
                    System.out.println("Enter id to delete: ");
                    String deleteid = scanner.next();
                    String deleteperson = "DELETE FROM PERSON " + "WHERE id = " + deleteid;
                    PreparedStatement statement3 = connection.prepareStatement(deleteperson);
                    int successs2 = statement3.executeUpdate();
                    if (successs2 > 0) {
                        System.out.print(" Person deleted sucessfuly ");
                    }
                    else{
                        System.out.print("Person with Entered ID not present in the DB");
                    }
                break;

                default: System.out.println("Entered value is not correct");
            }

            connection.close();
        } catch (SQLException throwables) {
            System.out.print("Connection not established or CURD operation unsucessfull");
            throwables.printStackTrace();
        }
    }
}
