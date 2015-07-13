package cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V>
{
    private int cacheSize;

    public LRUCache(int cacheSize)
    {
        super(cacheSize, 1.0f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
    {
        return size() >= cacheSize;
    }
}
