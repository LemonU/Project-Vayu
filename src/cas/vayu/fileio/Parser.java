package cas.vayu.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


/**
 * Parser which reads input files and constructs DisasterPoints
 * from the DisasterPoints
 * @author Christina Korsman
 *
 */
public class Parser {

	private Hashtable<Integer,Integer> lookup = new Hashtable<Integer,Integer>(); // id to index 
	public static ArrayList<DisasterPoint> nodelist;//list of all nodes in all files

	/**
	 * Initializes parsing of the input files and creation of DisasterPoints
	 */
	public Parser() {
		nodelist = new ArrayList<DisasterPoint>();

		parse();
		System.out.println("Parsing successful. Number of nodes: " + nodelist.size());

	}

	/**
	 * Sets the base directory for all the files to be parsed
	 */
	private void parse() {
		File currentDir = new File("./data/eventDetails"); // current directory
		getFiles(currentDir);
	}

	/**
	 * Gets all the files in the given directory dir and runs the detailsParser
	 * on files that are not directories. If a directory is contained in dir, it
	 * recursively searches by called getFiles on that directory.
	 * @param dir the directory for the files to be read
	 */
	private void getFiles(File dir) {
		try {
			File[] files = dir.listFiles();

			for (File file : files) {
				if (file.isDirectory()) {

					getFiles(file);
				} else {
					detailsParser(file.getCanonicalPath());

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Parses given file with name fileName, and creates DisasterPoints from data.
	 * Adds all the created DisasterPoints to a the nodeList
	 * @param fileName Name of file to be read to parse data for DisasterPoints from
	 */
	public  void detailsParser(String fileName ) {
		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);

			myReader.nextLine();//skip the heading
			while (myReader.hasNextLine()) {

				//parsing the line into the correct data types
				String data = myReader.nextLine();
				String[] line = data.split(",", -1);
				if(line.length < 45) {
					continue;
				}
				int id = Integer.parseInt(line[7]);
				int year = Integer.parseInt ( line[0].substring(0, 4));
				
				int cast = sumCasualties(line[20].replaceAll("\"", "") , line[21].replaceAll("\"", ""), line[22].replaceAll("\"", ""), line[23].replaceAll("\"", "")   );

				int dam = damageParse(line[24].replaceAll("\"", ""), line[25].replaceAll("\"", ""));

				
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

				//void any points that do not have a lat or lon
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
					temp.setPropertyDam(dam);
				}



			}
		}
		catch(FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

	/**
	 * Takes 4 strings which are each representing integers for injuries and deaths,
	 * converts them to integer, and returns the sum of all the integers.
	 * @param injuryD String of integer direct injuries 
	 * @param injuryI String of integer indirect injuries
	 * @param deathD String of integer direct deaths
	 * @param deathI String of integer indirect deaths   
	 * @return the sum of the the injuries and deaths to represent casualties
	 */
	private static int sumCasualties(String injuryD, String injuryI, String deathD, String deathI) {
		int cast =0;
		try {
			cast += Integer.parseInt(injuryD.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		try {
			cast += Integer.parseInt(injuryI.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		try {
			cast += Integer.parseInt(deathD.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		try {
			cast += Integer.parseInt(deathI.replaceAll("\"", ""));

		}catch (NumberFormatException e){
			cast +=0;
		}

		return cast;
	}

	/**
	 * Takes string representation of  property damage and crop damage and 
	 * converts to Double, then returns their sum
	 * @param pDamage String of property damage in USD
	 * @param cDamage String of crop damage in USD
	 * @return the sum of the property and crop damage
	 */
	private static int damageParse(String pDamage, String cDamage) {
		Double dam = 0.0;

		if (pDamage.contains("K")) {
			String [] out = pDamage.split("K");
			if(out.length !=0) {
				dam  +=( Double.parseDouble(out[0]))*1000;}

		}
		if (pDamage.contains("M")) {
			String [] out = pDamage.split("M");
			if(out.length !=0) {
				dam  +=( Double.parseDouble(out[0]))*1000000;}

		}


		if (cDamage.contains("K")) {
			String [] out = cDamage.split("K");
			if(out.length !=0) {
				dam  +=( Double.parseDouble(out[0]))*1000;}


		}
		if (cDamage.contains("M")) {
			String [] out = cDamage.split("M");
			if(out.length !=0) {
				dam  +=( Double.parseDouble(out[0]))*1000000;}

		}


		return (int) Math.round(dam);
	}
	
	/**
	 * Returns the ArrayList of DisasterPoints created and stored by the parser
	 * @return ArrayList of DisasterPoints created by parser
	 */
	public ArrayList<DisasterPoint> getData(){
		return nodelist;
	}
	/**
	 * Getter for the hash table constructed by parser to map id of Disasterpoint
	 * to its index
	 * @return hash table which maps the id of a DisasterPoint to the point's
	 * ArrayList index
	 */
	public Hashtable<Integer,Integer> getTable(){
		return lookup;
	}



}
