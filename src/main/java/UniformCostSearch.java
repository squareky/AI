import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class UniformCostSearch {
	//a edge set
	private PriorityQueue<EdgeSetElement> edgeSet = new PriorityQueue<EdgeSetElement>();
	private int vertexNum;//the number of vertices
	private int[][] matrix;//undierected graph
	public UniformCostSearch() {}
	public UniformCostSearch(int vertexNum) {
		this.vertexNum = vertexNum;
		matrix = new int[vertexNum][vertexNum];
	}
	 UniformCostSearch(int vertexNum, int[][] matrix) {
		this.vertexNum = vertexNum;
		this.matrix = matrix;
	}
	/**
	 * search
	 * @param startVertex-beginning node
	 * @param endVertex-end node
	 * @return
	 */
	 List<EdgeSetElement> search(int startVertex, int endVertex) {
		//priority queue store edge set.
		/*PriorityQueue<EdgeSetElement> edgeSet;*/
		//initialized edge set
		for(int i = 0; i < vertexNum; i++) {
			if(matrix[startVertex][i] > 0) {
				EdgeSetElement e = new EdgeSetElement(i, matrix[startVertex][i], startVertex+"->"+i);
				edgeSet.add(e);
			}
		}
		//System.out.println("initialized edgeSet:");
		//edgeSet.forEach((edge)->System.out.println("node,cost:"+edge.nodeIndex+","+edge.cost));
		//cannot reach
		if(edgeSet.size() == 0) {
			return null;
		}
		List<EdgeSetElement> results = new ArrayList<EdgeSetElement>();
		//Use ArrayList to store the parent node of the deleted node
		while(true) {
			//chose a node that achieve the least path cost as an exploded node
			//if it is the end node, we find the least cost path
			EdgeSetElement exploded = edgeSet.poll();
			if(exploded.nodeIndex == endVertex) {
				results.add(exploded);
				//There may exists different path
				EdgeSetElement node = edgeSet.peek();
				while(node!=null && node.nodeIndex == endVertex&&node.cost == exploded.cost) {
					results.add(edgeSet.poll());
					node = edgeSet.peek();
				}
				return results;
			}
			//System.out.println("exploded node:index,cost:"+exploded.nodeIndex+","+exploded.cost);
			//update edge set
			updateEdgeSet(exploded);
			
		}
		
	}
	/**
	 * update edge set
	 * @param exploded
	 */
	 void updateEdgeSet(EdgeSetElement exploded) {
		//find the succeeding nodes of the exploded node and add them into the edge set
		for(int i = 0; i < vertexNum; i++) {
			if(matrix[exploded.nodeIndex][i] > 0) {
				EdgeSetElement e = new EdgeSetElement(i, exploded.cost + matrix[exploded.nodeIndex][i], exploded.path+"->"+i);
				edgeSet.add(e);
			}
		}
		//System.out.println("After updating the edgeSet:");
		//edgeSet.forEach((edge)->System.out.println("node,cost:"+edge.nodeIndex+","+edge.cost));
	}
	/**
	 * Print path
	 * @param start 
	 * @param end 
	 */
	 void test(int start, int end) {
		List<EdgeSetElement> results = this.search(start, end);
		for(EdgeSetElement result : results) {
			System.out.println("It costs:"+result.cost+",Path:"+result.path);
		}
	}
	public static void main(String[] args) throws IOException {
		int[][] temp;
		//int[][] temp1;
		int[][] totalCostFunc = new int[500][500];
		convert matrix = new convert();
		temp=matrix.convertStringToMatrixEdge();
		System.out.println("Test for Uniform Cost Search:");
		UniformCostSearch uniformCostSearch1 = new UniformCostSearch(500, temp);
		long startTime = System.currentTimeMillis(); //timing
		//input start and end points
		uniformCostSearch1.test(0, 498);
		long endTime   = System.currentTimeMillis();
		long TotalTime = endTime - startTime;
		System.out.println("it takes "+TotalTime+" miliseconds");
		huristic computeFunc = new huristic();
		//temp1 = computeFunc.computeHust();
		
		
	/* //These pieces of codes are to place huristic
	//function into a file So that we are able to complete complicated function first.
        String filename = "hx.txt";
        
		File file =new File(filename);
		 if (!file.exists())   
	        {       
	            file.createNewFile();      
	        }      
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filename),"utf-8");      
        BufferedWriter writer=new BufferedWriter(write); 
        String s;
        int k = 0;
        for(int i = 0;i < temp1.length; i++) {
        	for(int j = 0; j < temp1[0].length;j++) {
        	// System.out.println(huriCost[i][j]);	
        	 s = String.valueOf(temp1[i][j]); 
        	 k++;
        	 writer.write(s);	
        	 writer.newLine();
        	}
        	
        }
        System.out.println(k);
        writer.close();*/
		
		
     //Read the huristic value h(x) from a file that was the values that were computed before.
		FileReader reader = new FileReader("hx.txt");
    	BufferedReader textReader = new BufferedReader(reader);
    	
    	String[] textData = new String[250000];
    	
    	String line;
    	int[][] temp2 = new int[500][500];
		for(int i = 0;i < 250000; i++) {
    		
    			line = textReader.readLine();
    			textData[i] = line;
    			//System.out.println(temp2[i][j]);
    	}
    	textReader.close();
        // Read the file line by line, because the content is a type of string,
		//so we need to transform them into integer.
				int k = 0;
	    for(int i=0;i<500;i++) {
            for(int j=0;j < 500;j++) {
            	temp2[i][j] =Integer.parseInt(textData[k]);
            	
            	k++;
            }	    	
	    	
	    }

		
        for(int i = 0; i < 499;i++) {
        	for(int j = 0;j < 500;j++) {
        		//A* search using g(x)+h(x)
        		totalCostFunc[i][j] = temp[i][j] + temp2[i][j];
        	}
        }
		System.out.println("Test for A* Search:");
        UniformCostSearch informedSearch = new UniformCostSearch(500, totalCostFunc);
        long startTime1 = System.currentTimeMillis(); //timing
        informedSearch.test(0, 498);
		long endTime1   = System.currentTimeMillis();
		long TotalTime1 = endTime - startTime;
		System.out.println("it takes "+TotalTime1+" miliseconds");
			}
}
