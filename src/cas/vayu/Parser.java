package cas.vayu;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Parser {
	
	private static Hashtable<Integer,Integer> lookup = new Hashtable<Integer,Integer>(); // id to index 
	
	
	public static void main(String[] args) throws IOException {
		Parser par = new Parser();
//		System.out.println(nodelist.get(69));
//		System.out.println(nodelist.get(420));
//		System.out.println(lookup.get(nodelist.get(69).getID()));

	}

	public static ArrayList<DisasterPoint> nodelist;
	private static ArrayList<String> locationFiles;
	
	
	
	
	public Parser() {
		nodelist = new ArrayList<DisasterPoint>();
//		locationFiles = new ArrayList<String>();
//
//		locationFiles.add("StormEvents_locations-ftp_v1.0_d2019_c20200317");
//		locationParser();
		//getfiles();

	}
	private void getfiles() {
		File currentDir = new File("./data/eventDetails"); // current directory
		getfiles(currentDir);
	}
	private void getfiles(File dir) {

		try {
			File[] files = dir.listFiles();
			
			
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					getfiles(file);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				
					System.out.println("File name:" + file.getName());
					String [] test = file.getName().split("-");
					//System.out.println(test[0]);
					String filetype =  test[0];
					
					
					if (filetype.equalsIgnoreCase("StormEvents_locations")) {
						System.out.println(file.getAbsolutePath());
						
					}
	
					
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void locationParser()  {


		//colunms order is YearMonth, EPISODE_ID, Event_ID, Location_index, Range, AZIMUTH, Location , Latitude, longtidue ,lat2, lon2
		//split columns  :   0      ,      1    ,    2     ,        3     ,  4   ,    5   ,    6     ,   7     ,    8      , 9 , 10   
		//		for (String i : locationFiles) {// for all files later
		//			
		//		}

		try {
			File myObj = new File("StormEvents_locations-ftp_v1.0_d2019_c20200317.csv");
			Scanner myReader = new Scanner(myObj);

			myReader.nextLine();//skip the heading
			while (myReader.hasNextLine()) {

				String data = myReader.nextLine();
				String[] line = data.split(",");//spliting the columns up
				int id = Integer.parseInt(line[2]);
				

				
				DisasterPoint temp ;
				
				
				if (  lookup.get(id) == null) {
					temp = new DisasterPoint(id);
					lookup.put(id, nodelist.size());
					nodelist.add(temp);
					
					
				
				}else {
					temp = nodelist.get(lookup.get(id));
					
				}

				
				
				int year = Integer.parseInt ( line[0].substring(0, 4));
				temp.setYear(year);
				temp.setLat(Double.parseDouble(line[7]));
				temp.setLon(Double.parseDouble(line[8]));

				
				
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public ArrayList<DisasterPoint> getNodelist(){
		return nodelist;
	}
	
	 
   
}
