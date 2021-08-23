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
            host = "ec2-54-205-232-84.compute-1.amazonaws.com";
            userName= "mixpkrjhrdgrxj";
            password = "bd9be143a2056efa51b7a9f9546478d783667151515a126be2b01d294aa50a9a";

            sql2o = new Sql2o("jdbc:postgresql://" + host + ":5432/dbdiho906h1ffa",userName,password);
            ;
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
