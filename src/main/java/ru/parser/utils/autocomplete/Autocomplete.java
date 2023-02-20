package ru.parser.utils.autocomplete;

import ru.parser.utils.autocomplete.container.TernarySearchTree;

import java.util.Queue;

/**
 * Класс, предоставляющий функцию автозаполнения (autocomplete). Использует {@link TernarySearchTree тернарное дерево поиска}
 * в качестве контейнера данных.
 *
 * @param <V> тип значения, которое будет закреплено за ключом.
 */
public class Autocomplete<V>
{
    private final TernarySearchTree<V> container;

    public Autocomplete()
    {
        this.container = new TernarySearchTree<>();
    }

    public void insert(String key, V value)
    {
        container.put(key, value);
    }

    public Queue<String> keys()
    {
        return container.keys();
    }

    public Queue<String> keysWithPrefix(String prefix)
    {
        return container.keysWithPrefix(prefix);
    }

    public V getValueByKey(String key)
    {
        return container.get(key);
    }
}
