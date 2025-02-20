package checkencoding;

import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.nio.charset.Charset;
import java.util.SortedMap;


public class Checker {

    private static String iFilePath; // путь входящего файла
    private static String oFilePath; // путь выходящего файла
    private static String iCharset; // кодировка входного файла
    private static String oCharset; // кодировка выходного файла
    public static void main(String[] args) throws Exception {
        
        // считываем аргументы команды
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if( args[i].equals("-if") || args[i].equals("--input-file") ) {
                    iFilePath = args[++i];
                    System.out.println("inputFilePath " + iFilePath);
                } 
                else if ( args[i].equals("-of") || args[i].equals("--output-file") ) {
                    oFilePath = args[++i];
                    System.out.println("outputFilePath " + oFilePath);
                }
                else if ( args[i].equals("-ic") || args[i].equals("--input-charset") ) {
                    iCharset = args[++i];
                    System.out.println("inputCharset " + iCharset);
                }
                else if ( args[i].equals("-oc") || args[i].equals("--output-charset") ) {
                    oCharset = args[++i];
                    System.out.println("outputCharset " + oCharset);
                }
                else if ( args[i].equals("--info") ) {
                    System.out.println(iFilePath);
                    System.out.println( getInputCharset(iFilePath) );
                }
                else if ( args[i].equals("--convert") ) {
                    System.out.println(iFilePath);
                    System.out.println( getInputCharset(iFilePath) );
                    convert(iFilePath, oFilePath, iCharset, oCharset);
                }
                else if ( args[i].equals("-l") ) {
                    SortedMap<String,Charset> sm = Charset.availableCharsets();

                    for (Map.Entry<String, Charset> entry : sm.entrySet()) {
                        String key = entry.getKey();
                        Charset value = entry.getValue();
                        System.out.println("Key: " + key + ", Value: " + value.toString());
                    }
                }
            }   

        } else {
            System.out.println("checker 9.0");
        }
    }

    public static String getInputCharset(String iFilepath) throws Exception {
        File ifile = new File(iFilepath);
        FileReader ifReader = new FileReader(ifile);
        return ifReader.getEncoding();
    }



    public static void convert(String iFilePath, String oFilePath, String iCharsetName, String oCharsetName) throws Exception {
        File ifile = new File(iFilePath);
        Charset iCharset = Charset.forName(iCharsetName);
        Charset oCharset = Charset.forName(oCharsetName);
        //System.out.println(ch.toString()); 

        System.out.println();

        byte[] iByteArray = new byte[(int) ifile.length()];
        try (FileInputStream inputStream = new FileInputStream(ifile)) {
            inputStream.read(iByteArray);
        }
        System.out.println (new String(iByteArray, iCharset));

        // переводим в новую кодировку
        byte[] oByteArray = new String(iByteArray, iCharset).getBytes(oCharset);
        
        FileOutputStream outputStream = new FileOutputStream(oFilePath);
        outputStream.write(oByteArray);
        //CharsetEncoder chEnc = new CharsetEncoder(oCharset);
        //chEnc.encode();
    }
}