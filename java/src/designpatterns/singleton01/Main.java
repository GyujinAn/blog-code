package designpatterns.singleton01;

import java.sql.*;

/**
 * @author agj017@gmail.com
 * @since 2022/04/16
 */
public class Main {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SingletonDataSource dataSource = SingletonDataSource.getInstance();

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(
                    dataSource.getUrl(),
                    dataSource.getUsername(),
                    dataSource.getPassword());

            pstmt = con.prepareStatement("select * from tablename");

            rs = pstmt.executeQuery();

            while(rs.next()) {
                //.
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }

                if(pstmt != null) {
                    pstmt.close();
                }

                if(con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
