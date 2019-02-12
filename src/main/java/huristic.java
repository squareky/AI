import java.io.IOException;


class huristic {
	private convert vertToString = new convert();
 	private String[] verticeStore;
	private int k = 0;//
	private int difference = 3;
	private String[] verticeDetail1= new String[500*3];
	private int[] verticeDetail2= new int[500*3];

	private int[][] functionCost=new int[500][500];
	//int[] startVertice = new int[2];
	//int[] endVertice = new int[2];
	private int startVerX;
	private int startVerY;
	private int endVerX;
	private int endVerY;

      int[][] computeHust() throws IOException {
    	 verticeStore = vertToString.convertStringToMatrixVertice();
    	 for(int i = 0;i < 500;i++) {
 			verticeDetail1=verticeStore[i].split(",");
 			for(String b: verticeDetail1) {
 				verticeDetail2[k] = Integer.parseInt(b);
 				k++;
			}
    	 }
    	 
    	 for(int i = 0;i < 499;i++) {
    		 for(int j = i+1; j < 500;j++) {
    			//partition start vertice
    			 startVerX = verticeDetail2[1+3*i];
    			 startVerY = verticeDetail2[2+3*i];
    		    
    			
    			//partition end vertice
    			//verticeDetail2=verticeStore[j].split(",");
    			 endVerX = verticeDetail2[1+3*j];
    			 endVerY = verticeDetail2[2+3*j];
    			if(startVerX==endVerX&&startVerY==endVerY) {
    				//This is the way in which I define h(x)
    			    functionCost[i][j] =(int)(100*Math.sqrt( Math.abs(endVerX-startVerX)*Math.abs(endVerX-startVerX)+Math.abs(endVerY-startVerY)*Math.abs(endVerY-startVerY)));
    			    functionCost[j][i] = functionCost[i][j];
    			}//h(x) = distance
    			else {
    			    functionCost[i][j] =(int) (100*(Math.sqrt((Math.abs(endVerX-startVerX)-1)*(Math.abs(endVerX-startVerX)-1)+(Math.abs(endVerY-startVerY)-1)*(Math.abs(endVerY-startVerY)-1))));
    			    functionCost[j][i] =functionCost[i][j];

    			    // System.out.println(functionCost[i][j]);
    			}
    	 }
    	 }
    	 return functionCost;
     }
}
