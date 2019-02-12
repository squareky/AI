

public class EdgeSetElement implements Comparable<EdgeSetElement>{
	//node index
	int nodeIndex;
	//current cost of the path
	int cost;
	//path way
	String path;
	
	EdgeSetElement(int nodeIndex, int cost, String path) {
		this.nodeIndex = nodeIndex;
		this.cost = cost;
		this.path = path;
	}
 

	public int compareTo(EdgeSetElement o) {
		//when adding it in and assume we don't see it repeated
		//allow nodeIndexes that are same
		if(this.cost < o.cost) {
			return -1;
		} else{
			return 1;
		} 
	}
	
	@Override
	 public boolean equals(Object obj) {
		EdgeSetElement e = (EdgeSetElement) obj;
		if(e.nodeIndex == this.nodeIndex && e.cost == this.cost) {
			return true;
		}
		return false;
	}
	
}