package cas.vayu.fileio;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cas.vayu.disasterarea.builder.DisasterArea;
/**
 * 
 * @author Christina Korsman
 *
 */
public class FileOutput {
	
	String sep;//the line separated the nodes
	int linesize ; // the size of sep
	/**
	 * Initialized the FileOutput and sets up the linesize
	 */
	public FileOutput() {
		//setting up sep size 
		int lineSize = 21*7;
		sep = "";
		for (int i =0; i< lineSize; i++ ) {
			sep = sep + "-";
		}
		sep = sep + "\n";
	}

	/**
	 * @brief write a file format to be easy to read with the given arraylist of nodes
	 * @param fileName  the output file name
	 * @param nodelist	the arraylist of nodes
	 */
	public void writeData(String fileName, ArrayList<DisasterPoint> nodelist){
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
			for (DisasterPoint i : nodelist) {
				out.append(String.format("%-20d|", i.getID()));
				out.append(String.format("%-20d|", i.getYear()));
				
				if (i.getweatherType() == null) {// not showing the user changing it to blank instead
					out.append((String.format("%-20s|","")));
				}else {
					out.append(String.format("%-20s|", i.getweatherType()));
				}
				
				out.append(String.format("%-20s|", i.getLat()));
				out.append(String.format("%-20s|", i.getLon()));
				out.append(String.format("%-20s|", i.getproperty_damage()));
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
	 * @brief write a file formated to be easy to read given the arraylist of disaster areas
	 * @param fileName the output file name
	 * @param areas the arraylist of area to be outputed
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
					out.append(String.format("%-20d|", x.getID()));
					out.append(String.format("%-20d|", x.getYear()));
					if (x.getweatherType() == null) {// not showing the user changing it to blank instead
						out.append((String.format("%-20s|","")));
					}else {
						out.append(String.format("%-20s|", x.getweatherType()));
					}
					out.append(String.format("%-20s|", x.getLat()));
					out.append(String.format("%-20s|", x.getLon()));
					out.append(String.format("%-20s|", x.getproperty_damage()));
					out.append(String.format("%-20s|", x.getCasulaties()));

					out.append("\n");
					out.append(sep);

				}
				out.append("\n");
			}
			out.close();
		}catch(IOException e){
			System.out.println("error in file creation");
		}



	}
}
