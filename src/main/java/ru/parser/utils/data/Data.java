package ru.parser.utils.data;

import ru.parser.utils.autocomplete.Autocomplete;
import ru.parser.utils.compressor.CompressedString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Класс-обертка, предоставляющий более удобный доступ работы с ({@link Autocomplete автозаполнение},
 * {@link CompressedString сжатые строк}), а также отвечающий за обработку исходных данных.
 */
public class Data
{
    private final Autocomplete<String> autocomplete;
    private final Map<String, CompressedString> stringTable;

    public Data()
    {
        this.autocomplete = new Autocomplete<>();
        this.stringTable = new HashMap<>();
    }

    public void buildAutocomplete(String path, int columnNumber) throws IOException
    {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(path)))
        {
            String inputLine;

            while ((inputLine = fileReader.readLine()) != null)
            {
                String selectedColumn = removeDoubleQuotes(inputLine.split(",")[columnNumber - 1]);
                autocomplete.insert(selectedColumn.toLowerCase(), selectedColumn);
                stringTable.put(selectedColumn, new CompressedString(inputLine));
            }
        }
    }

    public List<String> getKeysByPrefix(String prefix)
    {
        Queue<String> matches = autocomplete.keysWithPrefix(prefix.toLowerCase());
        List<String> result = new ArrayList<>(matches.size());

        for (String match : autocomplete.keysWithPrefix(prefix.toLowerCase()))
        {
            result.add(autocomplete.getValueByKey(match));
        }

        return result;
    }

    public String getValueByKey(String key) throws IOException
    {
        return stringTable.get(key).unzip();
    }

    private static String removeDoubleQuotes(final String value)
    {
        if (value.charAt(0) == '\"' && value.charAt(value.length() - 1) == '\"')
        {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}
