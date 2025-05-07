public class kruskal {

    void swap(int arr[][], int i, int j){
        int temp[] = arr[i].clone();
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void heapify(int arr[][], int n, int i){
        int largest = i, left = 2*i+1, right = 2*i + 2;
        largest = (left < n && arr[left][2] > arr[largest][2]) ? left : largest;
        largest = (right < n && arr[right][2] > arr[largest][2]) ? right : largest;
        if(largest != i){
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    void buildheap(int arr[][]){
        for(int i=(arr.length-1)/2; i>=0; i--){
            heapify(arr, arr.length, i);
        }
    }

    void heapSort(int arr[][]){
        buildheap(arr);
        for(int i=arr.length-1;i>=0;i--){
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
        System.out.print("\nSorted array : ");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i][0]+" "+arr[i][1]+" "+arr[i][2]+" ");
        }
    }

    int find(int parent[], int a){
        if(parent[a] != a){
            parent[a] = find(parent, parent[a]);
            return parent[a];
        }
        return a;
    }

    int value(int graph[][], int a, int b){
        for(int i=0;i<graph.length;i++){
            if(
                (graph[i][0] == a && graph[i][1] == b) ||
                (graph[i][1] == a && graph[i][0] == b)
            )
                return graph[i][2];
        }
        return -1;
    }

    void printMST(int parent[], int graph[][]){
        System.out.println("\nThe Kruskal MST:\nEdge\tValue");
        for(int i=0;i<parent.length;i++){
            System.out.println(find(parent, i)+"-"+i+"\t"+value(graph, find(parent, i), i));
        }
    }
    
    void minSpanTree(int graph[][], int n){
        int parent[] = new int[n];
        int count = 0;
        
        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        heapSort(graph);

        System.out.println("\nThe Kruskal MST:\nEdge\tValue");

        for(int i=0;i<graph.length;i++){
            int root1 = find(parent, graph[i][0]);
            int root2 = find(parent, graph[i][1]);
            if(root1 != root2){
                System.out.println(graph[i][0]+"-"+graph[i][1]+"\t"+graph[i][2]);
                if(root1 < root2){ parent[root2] = root1; }
                else{ parent[root1] = root2; }
                if(++count == n-1) break;
            }
        }

    }

    public static void main(String args[]){
        kruskal ob = new kruskal();

        int graph[][] = new int[][] {
            {0, 1, 7},
            {0, 2, 2},
            {0, 3, 3},
            {1, 4, 8},
            {1, 5, 2},
            {2, 3, 8},
            {2, 4, 6},
            {3, 5, 6},
            {4, 5, 5}
        };

        ob.minSpanTree(graph, 6);
    }
}
