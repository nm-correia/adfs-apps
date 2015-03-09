import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws Exception {
		//gen();
		adapt();
	}
	
	
	public static void gen() throws Exception {
		File f = new File("log1.txt");
		PrintWriter writer = new PrintWriter("log1.txt", "UTF-8");
		
		int nodes = 100;
		Random gen = new Random();
		int n = 5000000;
		
		while(f.length() < n) {
			
			writer.print(gen.nextInt(10000000));
			writer.print('\t');
			
			int nodea = gen.nextInt(nodes);
			int nodeb = gen.nextInt(nodes);
			while(nodea == nodeb) nodeb = gen.nextInt(nodes);
			
			writer.print(nodea);
			writer.print('\t');
			writer.print(nodeb);
			writer.print('\t');
			writer.print(System.currentTimeMillis());
			writer.println();
		}
		writer.close();
	}
	
	
	
	public static void adapt() throws Exception {
		
		Map<Integer, String> m = new HashMap<Integer, String>();
		
		FileReader fr = new FileReader("/home/nuno/Documents/PROJ/OTHER/web-Stanford.txt");
        BufferedReader br = new BufferedReader(fr);
        
		PrintWriter writer = new PrintWriter("log2.txt", "UTF-8");
		
		Random gen = new Random();

        String line;

        while ((line = br.readLine()) != null) {
            String[] result = line.split("\t");
            
            int nodea = Integer.parseInt(result[0]);
            int nodeb = Integer.parseInt(result[1]);
            
            if(!m.containsKey(nodea))
            	m.put(nodea, new BigInteger(130, gen).toString(32));
            
            if(!m.containsKey(nodeb))
            	m.put(nodea, new BigInteger(130, gen).toString(32));
            
            writer.print(gen.nextInt(10000000));
			writer.print('\t');			
			writer.print(nodea);
			writer.print('\t');
			writer.print(nodeb);
			writer.print('\t');
			writer.print(System.currentTimeMillis());
			writer.println();
        }
        writer.close();
        br.close();
        
        writer = new PrintWriter("lognodes.txt", "UTF-8");
        for(Entry<Integer, String> e: m.entrySet())
        	writer.println(e.getKey() + " " + e.getValue());
        writer.close();
	}
	
}
