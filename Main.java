import java.io.*;
import java.util.*;

public class Main {

    public String replace(String content, String old, String replacement){
        while(content.con(old)){
            
        }
    }

    public static void main(String[] args) throws IOException {
        if(args[0].equals("-h")|| args[0].equals("-help" )){
            System.out.println("Das Programm entfernt sämtliche in Latex gebräuchliche $-Symbole der Math-Umgebung und" +
                    "ersetzt diese durch die äquivalenten \\(, \\), \\[, \\] Klammerungen die in Mathjax genutzt werden." +
                    "\\" +
                    "Befehle:\\ Main.java -h : Hilfe" +
                    "\\" +
                    "Start: Main.java <Dateiname.txt>");
        }
        String filename = args[0];
        List<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/maxim/Desktop/" + filename));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            //System.out.println(everything);
            String[] strings = everything.split("");
            ArrayList<String> characterArrayList = new ArrayList<String>(10);
            Boolean openCurved = false;
            Boolean openBracket = false;
            String current = " ";
            String prior = " ";
            for(int i = 1; i < strings.length; i++){
                current = strings[i];
                prior = strings[i-1];
                //System.out.println(current + "   " + prior);
                if(prior.equals("$") && current.equals("$")){ // check for $$
                    if(!openBracket){
                        System.out.println("[");
                        result.add("\\");
                        result.add("[");
                        openBracket = true;
                        i++;
                    } else {
                        System.out.println("]");
                        result.add("\\");
                        result.add("]");
                        openBracket = false;
                        i++;
                    }
                    continue;
                }
                else if(prior.equals("$") && !current.equals("$")){
                    if(!openCurved){
                        System.out.println("(");
                        result.add("\\");
                        result.add("(");
                        openCurved = true;
                    } else {
                        System.out.println(")");
                        result.add("\\");
                        result.add(")");
                        openCurved = false;
                    }
                    continue;
                } else {
                    if(i == 1){
                        result.add(strings[0]);
                    } else if(i == strings.length-1){
                        result.add(strings[i]);
                    } else {
                        result.add(prior);
                    }
                }
            }
        } finally {
            br.close();
        }
        String add = "";
        for (String s:result){
            add += s;
        }

        PrintWriter out = new PrintWriter("C:\\Users\\maxim\\Desktop\\edited" + filename);
        out.write(add);
        out.close();
    }
}
