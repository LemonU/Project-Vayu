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

		//		File file = new File("./data/eventDetails/StormEvents_details-ftp_v1.0_d2019_c20200317.csv"); // current directory
		//		nodelist = new ArrayList<DisasterPoint>();
		//		detailsParser(file.getAbsolutePath());
		//		System.out.println(nodelist.size());

		Parser par = new Parser();


	}

	public static ArrayList<DisasterPoint> nodelist;
	private static ArrayList<String> locationFiles;


	public Parser() {
		nodelist = new ArrayList<DisasterPoint>();

		getfiles();
		System.out.print(nodelist.size());

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
					detailsParser(file.getCanonicalPath());
					//					
					//					System.out.println("File name:" + file.getName());
					//					String [] test = file.getName().split("-");
					//					//System.out.println(test[0]);
					//					String filetype =  test[0];
					//
					//
					//					if (filetype.equalsIgnoreCase("StormEvents_locations")) {
					//						System.out.println(file.getAbsolutePath());
					//
					//					}
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

	public static  void detailsParser(String file ) {
		try {
			File myObj = new File(file);
			Scanner myReader = new Scanner(myObj);

			myReader.nextLine();//skip the heading
			while (myReader.hasNextLine()) {

				//parsing the line into the correct data types
				String data = myReader.nextLine();
				String[] line = data.split(",", -1);

				int id = Integer.parseInt(line[7]);
				int year = Integer.parseInt ( line[0].substring(0, 4));
				int cast = castparse(line[20].replaceAll("\"", "") , line[21].replaceAll("\"", ""), line[22].replaceAll("\"", ""), line[23].replaceAll("\"", "")   );

				int dam = damparse(line[24].replaceAll("\"", ""), line[25].replaceAll("\"", ""));

				Weather_Type_Enum type = Weather_Type_Enum.fromString(line[12].replaceAll("\"", ""));


				double lat = 0.0;

				try {
					lat = Double.parseDouble(line[44].replaceAll("\"", ""));

				}catch (NumberFormatException e ){
					lat =0;
				}

				double lon =0.0;
				try {
					lon = Double.parseDouble(line[45].replaceAll("\"", ""));

				}catch (NumberFormatException e ){
					lon =0;
				}

				//void any points that do not have a lat or long
				if(!(Math.round(lat) == 0 || Math.round(lon) == 0)) {
					DisasterPoint temp ; // creating point checking if it exist and update it other wise make a new  point base on the unique id
					if (  lookup.get(id) == null) {
						temp = new DisasterPoint(id);
						lookup.put(id, nodelist.size());
						nodelist.add(temp);

					}else {
						temp = nodelist.get(lookup.get(id));

					}
					temp.setYear(year);
					temp.setLat(lat);
					temp.setLon(lon);
					temp.settype(type);
					temp.setCas(cast);
				}



			}
		}
		catch(FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

	private static int castparse(String cast1, String cast2, String cast3, String cast4) {
		int cast =0;
		try {
			cast += Integer.parseInt(cast1.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		try {
			cast += Integer.parseInt(cast2.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		try {
			cast += Integer.parseInt(cast3.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		try {
			cast += Integer.parseInt(cast4.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		return cast;
	}

	private static int damparse(String dam1, String dam2) {
		Double dam = 0.0;

		if (dam1.contains("K")) {
			String [] out = dam1.split("K");
			if(out.length !=0) {
			dam  +=( Double.parseDouble(out[0]))*1000;}

		}
		if (dam1.contains("M")) {
			String [] out = dam1.split("M");
			if(out.length !=0) {
			dam  +=( Double.parseDouble(out[0]))*1000000;}

		}


		if (dam2.contains("K")) {
			String [] out = dam2.split("K");
			if(out.length !=0) {
			dam  +=( Double.parseDouble(out[0]))*1000;}


		}
		if (dam2.contains("M")) {
			String [] out = dam2.split("M");
			if(out.length !=0) {
			dam  +=( Double.parseDouble(out[0]))*1000000;}

		}


		return (int) Math.round(dam);
	}

	public ArrayList<DisasterPoint> getNodelist(){
		return nodelist;
	}



}
