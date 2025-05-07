public class primHeap {

    void heapify(int arr[][], int n, int i){
        int left = 2*i + 1;
        int right = 2*i + 2;
        int largest = i;
        largest = (left < n && arr[left][1] > arr[largest][1]) ? left : largest;
        largest = (right < n && arr[right][1] > arr[largest][1]) ? right : largest;
        if(largest != i){
            int temp = arr[largest][1];
            arr[largest][1] = arr[i][1];
            arr[i][1] = temp;
            heapify(arr, n, largest);
        }
    }

    int build

    int minIndex(int currLowest[][], boolean inMST[]){
        int min = -1, minVal =  Integer.MAX_VALUE;
        for(int i=0;i<currLowest.length;i++){
            if(minVal > currLowest[i] && inMST[i]==false){
                min = i;
                minVal = currLowest[i];
            }
        }
        return min;
    }

    void printMST(int parent[], int graph[][]){
        System.out.println("\nMinimum Spanning Tree\nEdge\tWeight");
        for(int i=0;i<graph.length;i++){
            System.out.println(parent[i]+"-"+i+"\t"+graph[parent[i]][i]);
        }
    }

    void minSpanTree(int graph[][]){
        boolean inMST[] = new boolean[graph.length];// the name says is, fking read
        int currLowest[][] = new int[graph.length][2]; // current lowest after each insertion in MST
        int parent[] = new int[graph.length]; // i : node, parent[i] : parent of i in MST

        for(int i=0;i<graph.length;i++){
            inMST[i] = false;
            currLowest[i] = new int[]{i, Integer.MAX_VALUE };
        }

        currLowest[0][1] = 0; // Starting node - assuming 0

        for(int i=0;i<graph.length;i++){
            int currMin = minIndex(currLowest, inMST);
            inMST[currMin] = true;

            for(int j=0;j<graph.length;j++){ // Like BFS of i
                if( 
                    graph[currMin][j] != 0 && // for unconnected nodes
                    graph[currMin][j] < currLowest[j][1] &&
                    inMST[j] == false
                 ){
                    parent[j] = currMin;
                    currLowest[j][1] = graph[currMin][j];
                 }
            }
        }
        printMST(parent, graph);
    }

    public static void main(String args[]){
        primHeap ob = new primHeap();
        int graph[][] = new int[][] { { 0, 7, 2, 3, 0, 0 },
                                      { 7, 0, 0, 0, 8, 2 },
                                      { 2, 0, 0, 8, 6, 0 },
                                      { 3, 0, 8, 0, 0, 6 },
                                      { 0, 8, 6, 0, 0, 5 },
                                      { 0, 2, 0, 6, 5, 0 } };
        ob.minSpanTree(graph);
    }
}
