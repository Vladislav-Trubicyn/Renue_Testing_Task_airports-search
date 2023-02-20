package ru.parser;

import ru.parser.utils.data.Data;

import java.io.IOException;
import java.util.Scanner;

/**
 * Основной стартовый класс программы.
 */
public class StartApplication
{
    private static final String CSV_FILE_PATH = "src/main/resources/csv/airports.csv";

    public static void main(String[] args)
    {
        try
        {
            Data data = new Data();
            Scanner scanner = new Scanner(System.in);

            data.buildAutocomplete(CSV_FILE_PATH, Integer.parseInt(args[0]));
            //data.buildAutocomplete(CSV_FILE_PATH, 2);

            System.out.print("Введите строку: ");
            String getStr = scanner.nextLine();

            while (!getStr.equals("!quit"))
            {
                Long startSearchTime = System.currentTimeMillis();
                Iterable<String> allMatches = data.getKeysByPrefix(getStr);
                Long finishSearchTime = System.currentTimeMillis();

                int countSearchStr = 0;

                for (String match : allMatches)
                {
                    System.out.println("'" + match + "'" + "[" + data.getValueByKey(match) + "]");
                    countSearchStr++;
                }

                System.out.println(String.format("Количество найденных строк: %s поиск %s мс", countSearchStr, (finishSearchTime - startSearchTime)));

                System.out.print("Введите строку: ");
                getStr = scanner.nextLine();
            }
        }

        catch (NumberFormatException ex)
        {
            System.out.println("В качестве полученного аргумента, указан символ, а не число:\n" + ex);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("Неверно указан индекс:\n" + ex);
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Неверно введена строка для поиска:\n" + ex);
        }
        catch (IOException ex)
        {
            System.out.println("Ошибка при чтении файла:\n" + ex);
        }
        catch (Exception ex)
        {
            System.out.println("Ошибка приложения:\n" + ex);
        }
    }
}
