package freeway;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class Executor {
    static void execute(String dataSourceURL , String migrationDirectoryPath) throws SQLException, IOException {
        Connection connection = App.getConnection(dataSourceURL);
        for (File file: getOrderedMigrations(migrationDirectoryPath)) {
            System.out.println(file.getName());
            List<String> lines = Files.readAllLines(file.toPath());
            Statement statement = connection.createStatement();
            statement.execute(String.join("\n", lines));
        }
    }

    private static File[] getOrderedMigrations(String migrationDirectoryPath) {
        File migrationDirectory = new File(migrationDirectoryPath);
        File[] files = migrationDirectory.listFiles();
        assert files != null;
        Arrays.sort(files, new VersionComparator());
        return files;
    }
}
