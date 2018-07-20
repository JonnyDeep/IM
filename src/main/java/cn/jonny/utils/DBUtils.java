package cn.jonny.utils;

        import cn.jonny.entity.User;


        import java.sql.*;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class DBUtils {
    private final static String DRIVER="com.mysql.cj.jdbc.Driver";

    //add serverTimezon=GMT if your environment is jdk1.8
    private final static String URL = "jdbc:mysql://localhost:3306/IMDB?serverTimezone=GMT";
    private final static String USERNAME = "root";
    private final static String PASSWORD= "123456";

    private static Connection con = null;

    public static Connection getCon() throws ClassNotFoundException,SQLException
    {
        Class.forName(DRIVER);
        con = (Connection)DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return con;
    }

    public static void close() throws SQLException
    {
        if(con!=null)
        {
            con.close();
        }
    }

    public static List<User> Select(String sql,Connection con) throws SQLException {
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<User> ls = new ArrayList<User>();
        while(rs.next())
        {
            User user = new User();
            user.setId(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            ls.add(user);
        }

        rs.close();
        pstm.close();
        return ls;
    }

    public static List<String> SelectList(String sql,Connection con) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ResultSetMetaData rsdm = rs.getMetaData();



        int cloumnNum = rsdm.getColumnCount();
        String[] cloumnName=new String[cloumnNum];
        String[] cloumnType = new String[cloumnNum];
        for (int i = 0; i < cloumnNum; i++) {
            cloumnName[i]=rsdm.getColumnName(i+1);
            cloumnType[i] = rsdm.getColumnTypeName(i+1);
        }

        List<String> ls = new ArrayList<String>();
        while ( rs.next())
        {
            StringBuilder sbd = new StringBuilder();
            sbd.append("{");
            for (int i = 0; i < cloumnNum; i++) {
                if("VARCHAR".equals(cloumnType[i]))
                {
                    sbd.append(cloumnName[i].toLowerCase()+":"+"\""+rs.getString(i+1)+"\",");
                }else{
                    sbd.append(cloumnName[i].toLowerCase()+":"+rs.getString(i+1)+",");
                }

            }
            sbd.deleteCharAt(sbd.length()-1);
            sbd.append("}");
            ls.add(String.valueOf(sbd));
            sbd.delete(0,sbd.length());
        }


        rs.close();
        pstm.close();
        return ls;
    }


}
