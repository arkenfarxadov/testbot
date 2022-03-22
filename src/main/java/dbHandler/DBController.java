package dbHandler;

import mailing.Mailing;
import messageHandler.MessageHandler;
import rssParser.News;
import java.sql.*;
import java.util.ArrayList;


public class DBController {
    private static final String URL= "jdbc:postgresql://localhost:5432/NewsDB";
    private static final String NAME ="postgres";
    private static final String PASSWORD = "111";
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection=DriverManager.getConnection(URL,NAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void saveSubs(String id, String username, String firstname){
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO subscribers VALUES('" + id+ "', '"+ username+"','"+firstname+"')";
            statement.executeUpdate(SQL);
            Mailing.subs.add(id);
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.subscription(id);
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.subExceptionExecution(id);
        }
    }

    public static void deleteSubs(String id){
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM subscribers WHERE id='"+id+"'";
            statement.executeUpdate(SQL);
            Mailing.subs.add(id);
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.unsub(id);
        }
        catch (SQLException throwables) {
             throwables.printStackTrace();
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.subDeleteExceptionExecution(id);
        }
    }
    public static ArrayList<String> getSubs(){
        ArrayList<String>subs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL= "SELECT * FROM  subscribers";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                subs.add(resultSet.getString("id").toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subs;
    }
    public static void save(News news,int index){

        try {
            Statement statement = connection.createStatement();
            String SQL=null;

            if(index==0) {
                SQL = "INSERT INTO news VALUES('" + news.getPubDate() + "','" + news.getTitle() + "', '" + news.getLink() + "')";
            }
            if(index==1) {
                SQL = "INSERT INTO earningnews VALUES('" + news.getPubDate() + "','" + news.getTitle() + "','" + news.getLink() + "')";
            }
            if(index==2) {
                SQL = "INSERT INTO financenews VALUES('" + news.getPubDate() + "','" + news.getTitle() + "','" + news.getLink() + "')";
            }
                statement.executeUpdate(SQL);

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static void saveMailingNews(News news,int index){
        try {
            Statement statement = connection.createStatement();
            String SQL=null;
            if(index==0) {
                SQL = "INSERT INTO news VALUES('" + news.getPubDate() + "','" +news.getTitle() + "', '" + news.getLink() + "')";
            }
            if(index==1) {
                SQL = "INSERT INTO earningnews VALUES('" + news.getPubDate() + "','" + news.getTitle() + "','" + news.getLink() + "')";

            }
            if(index==2) {
                SQL = "INSERT INTO financenews VALUES('" + news.getPubDate() + "','" + news.getTitle() + "','" + news.getLink() + "')";

            }
            statement.executeUpdate(SQL);
            Mailing.mailingNews.add(news);

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static ArrayList<String> getAll(int index){
        ArrayList<String>list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL=null;
            if (index==0)
                SQL = "SELECT link FROM news";
            if (index ==1)
                SQL = "SELECT link FROM earningnews";
            if(index==2)
                SQL = "SELECT link FROM financenews";

            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
               /* News news = new News();
                news.setPubDate(resultSet.getString("pubDate"));
                news.setTitle(resultSet.getString("title"));
                news.setLink(resultSet.getString("link"));
                list.add(news);*/
                list.add(resultSet.getString("link"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }



}
