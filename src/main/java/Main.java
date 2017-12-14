import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        String s = args[0];
        replaceSelected("**/*.c**/*.c **/*.cc **/*.cp **/*.cpp **/*.cxx **/*.c++ **/*.h **/*.gem **/*.tgz **/*.dll **/*.js **/*.py **/*.jar **/*.rb **/*.js **/*.php **/*.rpm **/*.java**/*nupkg**/*.zip","1", s);

    }


    public static void replaceSelected(String replaceWith, String type, String arg) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("whitesource-fs-agent"));
            String line;

            StringBuffer inputBuffer = new StringBuffer();

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            String inputStr = inputBuffer.toString();

            file.close();

            System.out.println(inputStr); // check that it's inputted right

            // this if structure determines whether or not to replace "0" or "1"
            if (Integer.parseInt(type) == 0) {
                inputStr = inputStr.replace(replaceWith + "1", replaceWith + "0");
            } else if (Integer.parseInt(type) == 1) {
                inputStr = inputStr.replace(replaceWith, arg);
            }

            // check if the new input is right
            System.out.println("----------------------------------\n" + inputStr);

            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("whitesource-fs-agent");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }


}
