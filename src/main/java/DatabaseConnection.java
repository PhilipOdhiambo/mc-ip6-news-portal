import org.sql2o.Sql2o;

public class DatabaseConnection {

    public static Sql2o sql2o;
    public static String host;
    public static String userName;
    public static String password;

    public static int getPortNumber() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {

            // Heroku connection
            host = "ec2-35-153-114-74.compute-1.amazonaws.com";
            userName= "apuajtilynanbk";
            password = "4e201ca47300a64c3ddeaa14acd967c0aec930cad7a7d59e2941b884b6755c8c";

            sql2o = new Sql2o("jdbc:postgresql://" + host + ":5432/d7pbbvj5h1733r",userName,password);
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        // Return Local H2 connection

        String connectionString = "jdbc:h2:~/news-portal.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        sql2o = new Sql2o(connectionString, "", "");

       // sql2o = new Sql2o("jdbc:postgresql://" + host + ":5432/wildlife_tracker",userName,password);

        return 4567;
    }


    public static String getUserName() {
        return userName;
    }

    public static String getUserPass() {
        return userName;
    }

    public static String getDbHost() {
        return host;
    }
}
