package Interface;
import java.io.Serializable;
import java.util.ArrayList;
public class Path implements Serializable {
    private static final long serialVersionUID = 1L;
    public String path;
    public int id;
    public ArrayList<String> trees = new ArrayList<>();
	public ArrayList<String> afds = new ArrayList<>();
	public ArrayList<String> afns = new ArrayList<>();
	public ArrayList<String> nexts = new ArrayList<>();
	public ArrayList<String> transitions = new ArrayList<>();
    public Path(int id,String path) {
        this.id = id;
        this.path = path;
    }
}