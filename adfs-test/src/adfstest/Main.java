package adfstest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws Exception {
		int nops = 50;
		float reads = Float.parseFloat(args[0]);
		
		Random gen = new Random();
		
		float prob;
		int nreads = 0;
		int nwrites = 0;
		
		for(int i = 0; i < nops; i++) {
			prob = gen.nextFloat();
			System.out.println("!!!!!! i = " + i);
			if(prob < reads) {
				System.out.println(">>>>>>>>>>>READ<<<<<<<<<<<<");
				while(true) {
					try {
						FileReader fr = new FileReader("/local_home/a41841/adfs-client/adfs/adfs/nuno/wc.txt");
				        BufferedReader br = new BufferedReader(fr);
				        br.readLine();
				        br.close();
				        nreads++;
				        break;
					} catch(Exception e) {
						System.out.println("CANNOT OPEN FOR READ");
					}
					Thread.sleep(1000);
				}
			}
			else {
				System.out.println(">>>>>>>>>>>WRITE<<<<<<<<<<<<");
				while(true) {
					try {
						FileWriter fw = new FileWriter("/local_home/a41841/adfs-client/adfs/adfs/nuno/nuno.txt", true);
						fw.append(" a ");
						fw.close();
						nwrites++;
						break;
					} catch(Exception e) {
						System.out.println("CANNOT OPEN FOR WRITE");
					}
					Thread.sleep(1000);
				}

			}
			
		}
		
		System.out.println("READS: " + nreads);
		System.out.println("WRITES: " + nwrites);
	}

}
