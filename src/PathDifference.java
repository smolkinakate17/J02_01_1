import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathDifference {
    public static List<PathDifferenceStatus> difference(Path path1,Path path2) throws IOException {
        List<PathDifferenceStatus> result=new ArrayList<>();
        if(notExist(path1,path2)) result.add(PathDifferenceStatus.NotExists);
        if(sameFile(path1,path2)) result.add(PathDifferenceStatus.SameFile);
        if(biggerFile(path1,path2)) result.add(PathDifferenceStatus.BiggerFile);
        if(smallerFile(path1,path2)) result.add(PathDifferenceStatus.SmallerFile);
        if(sameSize(path1,path2)) result.add(PathDifferenceStatus.SameSizeFile);
        if(sameDirectory(path1,path2)) result.add(PathDifferenceStatus.SameDirectory);
        if(sameAbsoluteNameDepth(path1,path2)) result.add(PathDifferenceStatus.SameAbsoluteNameDepth);
        if(samePrefix(path1,path2)) result.add(PathDifferenceStatus.SamePrefix);
        if(sameRoot(path1,path2)) result.add(PathDifferenceStatus.SameRoot);
        if(subpath(path1,path2)) result.add(PathDifferenceStatus.Subpath);
        if(parentPath(path1,path2)) result.add(PathDifferenceStatus.ParentPath);
        return result;
    }
    private static boolean notExist(Path path1,Path path2){
        if(Files.notExists(path1)||Files.notExists(path2)){
            return true;
        }
        return false;
    }
    private static boolean bothExist(Path path1,Path path2){
        return Files.exists(path1)&& Files.exists(path2);
    }
    private static boolean sameFile(Path path1,Path path2) throws IOException {
        if(bothExist(path1,path2)){
            return Files.isSameFile(path1,path2);
        }
        return false;
    }
    private static boolean biggerFile(Path path1,Path path2) throws IOException {
        if(bothExist(path1,path2)){
            return Files.size(path1)>Files.size(path2);
        }
        return false;
    }
    private static boolean smallerFile(Path path1,Path path2) throws IOException{
        if(bothExist(path1,path2)){
            return Files.size(path1)<Files.size(path2);
        }
        return false;
    }
    private static boolean sameSize(Path path1,Path path2)throws IOException{
        if(bothExist(path1,path2)){
            return Files.size(path1)==Files.size(path2);
        }
        return false;
    }
    private static boolean sameDirectory(Path path1,Path path2){

        return path1.getParent().equals(path2.getParent());

    }
    private static boolean sameAbsoluteNameDepth(Path path1,Path path2) {

        return path1.getNameCount() == path2.getNameCount();
    }
    private static boolean samePrefix(Path path1,Path path2){
        return path1.getName(0).equals(path2.getName(0));
    }
    private static boolean sameRoot(Path path1,Path path2){
        return path1.getRoot().equals(path2.getRoot());
    }
    private static boolean subpath(Path path1,Path path2) throws IOException {
        if(bothExist(path1,path2)){
            path1=path1.toRealPath();
            path2=path2.toRealPath();
            return path1.startsWith(path2);
        }
        return false;
    }
    private static boolean parentPath(Path path1,Path path2) throws IOException {
        return subpath(path2,path1);
    }
}
