import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path1=Path.of("C:\\Users\\Ekaterina\\Downloads\\Smolina_uml_DictionaryManager_and_PaymentManager.pdf");
        Path path2=Path.of("C:\\Users\\Ekaterina\\Downloads\\Smolkina_uml_MainManager.pdf");
        List<PathDifferenceStatus>list=PathDifference.difference(path1,path2);
        for(PathDifferenceStatus status: list){
            System.out.println(status.toString());
        }
    }
}