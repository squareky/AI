import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class addressComments {
	//Open Files and store content in the type of string
	 String[] OpenFile()throws IOException{
		FileReader reader = new FileReader("graph500_50052.txt");
    	BufferedReader textReader = new BufferedReader(reader);
    	int numberOfLines = readLines();
    	String[] textData = new String[numberOfLines];
    	int i=0;
    	//for(i = 0; i < numberOfLines;i++) {
    	//	textData[i] = textReader.readLine();  
    	//}
    	while(i< numberOfLines) {
    		textData[i] = textReader.readLine();
    		while((textData[i].contains("#")||textData[i].contains("Vertices")||textData[i].contains("Edges"))) {
    			textData[i]=textReader.readLine();
    	}
    		i++;
    	}
    	textReader.close();
		return textData;
    }  
	//Computing the number of lines
    int readLines() throws IOException{
    	FileReader reader = new FileReader("graph500_50052.txt");
    	BufferedReader textReader = new BufferedReader(reader);
    	String line;
    	int numberOfLines = 0;
    	while((line=textReader.readLine())!=null) {
    		//I tried this:
    		if(line.contains("#")||line.contains("Vertices")||line.contains("Edges")) {
    			continue;
    		}
    		numberOfLines++;
    	}
    	reader.close();
    	return numberOfLines;
    }
    

}
