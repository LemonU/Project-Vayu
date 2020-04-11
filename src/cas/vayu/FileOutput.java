package cas.vayu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cas.vayu.disasterarea.builder.DisasterArea;

public class FileOutput {
	public static void main(String[] args){

		ArrayList<DisasterPoint> list = new ArrayList<DisasterPoint>();
		DisasterPoint x1 = new DisasterPoint(1234);
		x1.setLat(3);
		x1.setLon(4);
		x1.setYear(1999);
		x1.settype(WeatherTypeEnum.Avalanche);
		DisasterPoint x2 = new DisasterPoint(12345);
		x2.setLat(23);
		x2.setLon(24);
		x2.setYear(1456);


		list.add(x1);
		list.add(x2);


		FileOutput outtry = new FileOutput();
		outtry.writeData("test1", list);
		//outtry.writeData("testone", );
	}

	public FileOutput() {

	}
	public void writeData(String fileName, ArrayList<DisasterPoint> nodelist){
		try {
			int linesize = 21*7;
			String sep = "";
			for (int i =0; i< linesize; i++ ) {
				sep = sep + "-";
			}
			sep = sep + "\n";
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

	public void writeArea(String fileName, ArrayList<DisasterArea> areas){
		try {
			FileWriter out = new FileWriter(fileName);
			out.write("");
			for (DisasterArea i : areas) {
				out.append(i.convexHull);
			}
			out.close();
		}catch(IOException e){
			System.out.println("error in file creation");
		}
		
	}
		
}
