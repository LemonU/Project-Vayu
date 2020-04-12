package cas.vayu.fileio;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cas.vayu.disasterarea.builder.DisasterArea;
/**
 * Module which handles file outputting of lists of DisasterPoint and lists of
 * DisasterAreas
 * @author Christina Korsman
 *
 */
public class FileOutput {
	
	private String sep;//the line separated the DisasterPoints 
	private static final int numOfFields = 7;
	private static final int sizeOfField = 21;
	/**
	 * Initializes the FileOutput and creates the line separator which
	 * separates the DisasterPoints when being printed
	 */
	public FileOutput() {
		//setting up sep size 
		int lineSize = numOfFields*sizeOfField;
		sep = "";
		for (int i =0; i< lineSize; i++ ) {
			sep = sep + "-";
		}
		sep = sep + "\n";
	}

	/**
	 * Writes the DisasterPoints found in the pointList into a file with the name
	 * fileName
	 * @param fileName  the output file name
	 * @param pointList	the ArrayList of DisasterPoint to be written
	 */
	public void writeData(String fileName, ArrayList<DisasterPoint> pointList){
		try {
			
			FileWriter out = new FileWriter(fileName);
			//title
			out.write (String.format("%-20s|", "ID"));
			out.append(String.format("%-20s|", "Year"));
			out.append(String.format("%-20s|", "Weather Type"));
			out.append(String.format("%-20s|", "Latitude"));
			out.append(String.format("%-20s|", "Longitude"));
			out.append(String.format("%-20s|", "Property Damage"));
			out.append(String.format("%-20s|", "Casualties"));
			out.append("\n");
			out.append(sep);




			//give all items 20 spaces
			//append all the node item in the correct format
			for (DisasterPoint i : pointList) {
				out.append(String.format("%-20d|", i.getId()));
				out.append(String.format("%-20d|", i.getYear()));
				
				if (i.getWeatherType() == null) {// not showing the user changing it to blank instead
					out.append((String.format("%-20s|","")));
				}else {
					out.append(String.format("%-20s|", i.getWeatherType()));
				}
				
				out.append(String.format("%-20s|", i.getLat()));
				out.append(String.format("%-20s|", i.getLon()));
				out.append(String.format("%-20s|", i.getPropertyDamage()));
				out.append(String.format("%-20s|", i.getCasulaties()));

				out.append("\n");
				out.append(sep);
			}
			out.close();

		}catch(IOException e){
			System.out.println("error in file creation");
		}
	}

	/**
	 * Write the DisasterAreas found in areas to a file with name fileName
	 * @param fileName the output file name
	 * @param areas the ArrayList of areas to be written
	 */
	public void writeArea(String fileName, ArrayList<DisasterArea> areas){
		try {
			FileWriter out = new FileWriter(fileName);
			out.write("");//overwrite the previous if it exists 

			//output the convex not all the nodes 
			for (DisasterArea i : areas) {
				//the type fo the area 
				out.append(String.format("%-20s\n", i.getType()));
				
				//title for each area 
				out.write (String.format("%-20s|", "ID"));
				out.append(String.format("%-20s|", "Year"));
				out.append(String.format("%-20s|", "Weather Type"));
				out.append(String.format("%-20s|", "Latitude"));
				out.append(String.format("%-20s|", "Longitude"));
				out.append(String.format("%-20s|", "Property Damage"));
				out.append(String.format("%-20s|", "Casualties"));
				out.append("\n");
				out.append(sep);
				for (DisasterPoint x: i.getHull()) {

					
					//outputting all the contents 
					out.append(String.format("%-20d|", x.getId()));
					out.append(String.format("%-20d|", x.getYear()));
					if (x.getWeatherType() == null) {// not showing the user changing it to blank instead
						out.append((String.format("%-20s|","")));
					}else {
						out.append(String.format("%-20s|", x.getWeatherType()));
					}
					out.append(String.format("%-20s|", x.getLat()));
					out.append(String.format("%-20s|", x.getLon()));
					out.append(String.format("%-20s|", x.getPropertyDamage()));
					out.append(String.format("%-20s|", x.getCasulaties()));

					out.append("\n");
					out.append(sep);

				}
				out.append("\n");
			}
			out.close();
		}catch(IOException e){
			System.out.println("error in file creation");
		} catch(NullPointerException e) {
			System.out.println("Area does not exist");
		}



	}
}
