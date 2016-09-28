package homework; /**
 * Created by Anatoly on 17.08.2016.
 */
import java.util.List;

public interface MultiMap<K,V> {
    List<V> get(K type);
    void put(K type, V value);
}
