# Приложение для автозаполнения

Консольное приложение, позволяющее быстро искать данные аэропортов по вводимому пользователем тексту.

## Руководство пользователя

После корректного запуска приложения на консоли отобразится сообщение:
```
Введите строку:
```
Необходимо ввести строку для поиска всех строк, которые начинаются на заданное значение.

Пример:
```
Введите строку: Win
'Windham Airport'[8282,"Windham Airport","Willimantic","United States","IJD","KIJD",41.74399948120117,-72.1802978515625,247,-5,"A","America/New_York","airport","OurAirports"]
'Windom Municipal Airport'[8928,"Windom Municipal Airport","Windom","United States","MWM","KMWM",43.91339874267578,-95.1093978881836,1410,-6,"A","America/Chicago","airport","OurAirports"]
'Windorah Airport'[6333,"Windorah Airport","Windorah","Australia","WNR","YWDH",-25.41309928894043,142.66700744628906,452,10,"O","Australia/Brisbane","airport","OurAirports"]
'Windsor Airport'[113,"Windsor Airport","Windsor","Canada","YQG","CYQG",42.27560043334961,-82.95559692382812,622,-5,"A","America/Toronto","airport","OurAirports"]
'Wings Field'[8504,"Wings Field","Philadelphia","United States","BBX","KLOM",40.1375007629,-75.2650985718,302,-5,"A","America/New_York","airport","OurAirports"]
'Winkler County Airport'[3686,"Winkler County Airport","Wink","United States","INK","KINK",31.779600143399996,-103.200996399,2822,-6,"A","America/Chicago","airport","OurAirports"]
'Winnemucca Municipal Airport'[11864,"Winnemucca Municipal Airport","Winnemucca","United States","WMC","KWMC",40.8965988159,-117.805999756,4308,\N,\N,\N,"airport","OurAirports"]
'Winnipeg / James Armstrong Richardson International Airport'[160,"Winnipeg / James Armstrong Richardson International Airport","Winnipeg","Canada","YWG","CYWG",49.909999847399995,-97.2398986816,783,-6,"A","America/Winnipeg","airport","OurAirports"]
'Winnipeg / St. Andrews Airport'[22,"Winnipeg / St. Andrews Airport","Winnipeg","Canada","YAV","CYAV",50.0564002991,-97.03250122070001,760,-6,"A","America/Winnipeg","airport","OurAirports"]
'Winslow Lindbergh Regional Airport'[7587,"Winslow Lindbergh Regional Airport","Winslow","United States","INW","KINW",35.021900177,-110.722999573,4941,-7,"N","America/Phoenix","airport","OurAirports"]
'Winter Haven Municipal Airport - Gilbert Field'[8508,"Winter Haven Municipal Airport - Gilbert Field","Winter Haven","United States","GIF","KGIF",28.06290054,-81.75330353,145,-5,"A","America/New_York","airport","OurAirports"]
'Winton Airport'[6337,"Winton Airport","Winton","Australia","WIN","YWTN",-22.36359977722168,143.08599853515625,638,10,"O","Australia/Brisbane","airport","OurAirports"]
Количество найденных строк: 12 поиск 14 мс
```
> Регистр букв не имеет значения, введенная строка ***Win***, ***WIN*** и т.п. выведет один тот же результат.
> Также в результате поиска сохраняется исходный регистр строк.

## Закрытие приложения

Чтобы закрыть приложение, необходимо ввести ```!quit```.

## Ограничивующие моменты

В тексте тестового задания указаны следующие нефункциональные требования:

1. Перечитывать все строки файла при каждом поиске нельзя 
(в том числе читать только определенную колонку у каждой строки).
2. Создавать новые файлы или редактировать текущий нельзя
(в том числе использовать СУБД).
3. Хранить весь файл в памяти нельзя
(не только в качестве массива байт, но и в структуре, которая так или иначе содержит все
данные из файла).
4. Для корректной работы программе требуется не более 7 МБ памяти
(все запуски java –jar должны выполняться с jvm флагом ) -Xmx7m.

Исходя из всех ограничений выше, мною было предпринято решение о реализовывании сжатия строк с помощью **GZIP** стандартной библиотеки java (java.util.zip)
и позаимственного тернарного дерева поиска. В данном случае все данные хранятся в сжатом виде и распаковываются в момент востребованности.