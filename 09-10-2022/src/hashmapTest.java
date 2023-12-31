import java.util.HashMap;
import java.util.Map;
public class hashmapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> m = new HashMap();
		m.put("test", 1);
		m.put("test2", 2);
		
		System.out.println(m.get("test2"));
		m.entrySet().toArray();

	}

}
