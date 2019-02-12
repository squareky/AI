import java.io.IOException;

 class convert {
	  String[] convertStringToMatrixVertice() throws IOException{
     	addressComments result = new addressComments();
		    int Length = result.readLines();
		    String[] re = result.OpenFile();
		    //partition re into vertices set and edges set and use stringToInt to store them.
		    int number = 0;//because we have 520 lines for edges, 
		    //therefore, partitioning each line could generate 3 numbers.
		    int edgeNumber = 0;
		    int j = 0;
		    String[] vertices = new String[500];
	        String[] edges = new String[50052];
	        int[] stringToIntEdge = new int[50052*3];
	        int[] stringToIntVertice =  new int[500*3];
	        String[] detailsEdge=new String[50052*3];
	        String[] detailsVertice = new String[500*3];

		    //use 2-dimensional array to store the cost. we have 100 vertices, so the matrix could be 100*100
	        int[][] tempEdge = new int[500][500];
	        int[] tempVertice = new int[500];
	        //store the vertices into array vertices and the edges into array edges 
	        for(int i = 0;i < Length;i++) {
	        	if(i<500) {
	        		vertices[i]= re[i];
	        	}
	        	else {
	        		edges[j]= re[i];
	        		j++;
	        	}
	        }
	        //Convert the string into the type of int for vertices
	       for(int i=0; i < 500;i++) {
	    	  detailsVertice = vertices[i].split(",");
	           for(String b:detailsVertice) {
	        	  stringToIntVertice[number] = Integer.parseInt(b);
	        	   //System.out.println(b);
	        	   edgeNumber++;
	           }
	        }
	      
	       int index=0;
	        for(int i=0; i < 1500;i=i+3) {
	        	tempVertice[index] = 10*stringToIntVertice[i+1]+stringToIntVertice[i+2];
	        	index++;
	        }

	       // we need to store the costs in terms of their 
	        //corresponding distance between two vertices.
	        
     	return vertices;
     }
      int[][] convertStringToMatrixEdge()throws IOException{
   	  addressComments result = new addressComments();
		  int Length = result.readLines();
		    String[] re = result.OpenFile();
		    //partition re into vertices set and edges set and use stringToInt to store them.
		  int number = 0;//because we have 520 lines, 
		    //therefore, partitioning each line could generate 3 numbers.
		    int j = 0;
		    String[] vertices = new String[500];
	        String[] edges = new String[50052];
	        int[] stringToInt = new int[50052*3+1];
	        String[] details;

		    //use 2-dimensional array to store the cost. we have 100 vertices, so the matrix could be 100*100
	        int[][] temp = new int[500][500];
	        //store the vertices into array vertices and the edges into array edges 
	        for(int i = 0;i < Length;i++) {
	        	if(i<500) {
	        		vertices[i]= re[i];
	        	}
	        	else {
	        		edges[j]= re[i];
	        		j++;
	        	}
	        }
	       //Convert the string into the type of int
	        for(int i=0; i < 50052;i++) {
	           details = edges[i].split(",");
	           for(String b:details) {
	        	   stringToInt[number] = Integer.parseInt(b);
	        	   //System.out.println(b);
	        	   number++;
	           }
	        }
	       // System.out.println(number);
	       // we need to store the costs in terms of their 
	        //corresponding distance between two vertices.
	        int k = 0;
	        for(int i = 0;i < 499;i++) {
	        	for(j = i+1;j < 500;j++) {
	        		if(i==stringToInt[k] && j==stringToInt[k+1]) {
	        			temp[i][j]=stringToInt[k+2];
	        			temp[j][i] = stringToInt[k+2];
	        			k = k+3;
	        		}
	        		else
	        		   temp[i][j]=100000;//Because not all vertices have connections
	        		//in order to compute, I assign a very large value to them.
	            //	System.out.println(temp[i][j]);
	        	}
	       }
	        return temp;
     } 

}
