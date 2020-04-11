package cas.vayu.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


/**
 * 
 * @author Christina Korsman
 *
 */
public class Parser {

	private static Hashtable<Integer,Integer> lookup = new Hashtable<Integer,Integer>(); // id to index 
	public static ArrayList<DisasterPoint> nodelist;//list of all nodes in all files
	
	public static void main(String[] args) throws IOException {//testing 
		Parser par = new Parser();
		System.out.println(par.getNodeList());
	}


	/**
	 * When class is called reads through all the files and inputs them into the nodelist
	 */
	public Parser() {
		nodelist = new ArrayList<DisasterPoint>();

		parse();

	}

	/**
	 * Sets the base dir for all files to be read 
	 */
	private void parse() {
		File currentDir = new File("./data/eventDetails"); // current directory
		getfiles(currentDir);
	}

	/**
	 * 
	 * @param dir the directory for the files to be read
	 */
	private void getfiles(File dir) {

		try {
			File[] files = dir.listFiles();

			for (File file : files) {
				if (file.isDirectory()) {

					getfiles(file);
				} else {
					detailsParser(file.getCanonicalPath());

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * A method to perform parsing on a single file
	 * @param file the file to be read and node inputed into nodelist
	 */
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

				WeatherTypeEnum type = WeatherTypeEnum.fromString(line[12].replaceAll("\"", ""));


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
					//set all the field for the node
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

	/**
	 * Takes the casualties column  and return the sum of them
	 * @param cast1 casualties column 1 
	 * @param cast2 casualties column 2 
	 * @param cast3 casualties column 3
	 * @param cast4 casualties column 4 
	 * @return the sum of the  casualties columns
	 */
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

	/**
	 * Take the damages columns and returns the sum of them 
	 * @param dam1 damages column 1
	 * @param dam2 damages column 2
	 * @return the sum of the damages columns
	 */
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
	
	/**
	 * Returns the nodelist
	 * @return the nodelist
	 */
	public ArrayList<DisasterPoint> getNodeList(){
		return nodelist;
	}
	public Hashtable<Integer,Integer> getTable(){
		return lookup;
	}



}
