#конвертировать файл с кодировкой UTF-16LE в кодировку windows-1251
/usr/lib/jvm/jdk-22-oracle-x64/bin/java -cp "/home/usr/code/java" checkencoding.Checker -if /home/usr/code/java/checkencoding/СписокГРЗ.csv -of /home/usr/code/java/checkencoding/test.csv -ic UTF-16LE -oc windows-1251 --convert
