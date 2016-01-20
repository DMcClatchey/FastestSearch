import java.util.*;
import java.util.Arrays;

public class search {

	public static void main(String[] args) {
		//creates array size from user input, user forced to enter integer>100
		Scanner s = new Scanner(System.in);
        System.out.println("How long are your three arrays? Please enter a number greater than 100");
        int arraysize = s.nextInt();
        while(arraysize<=100){
        	System.out.println("Please enter an integer greater than 100.");
        	arraysize = s.nextInt();
        }
   
        //creates arrays with random numbers
        int[] array1 = new int[arraysize];
        int[] array2 = new int[arraysize];
        int[] array3 = new int[arraysize];
        for(int i=0; i<arraysize; i++){
        	array1[i] = (int)((Math.random()*99)+1); 
        }
        for(int i=0; i<arraysize; i++){
      	  	array2[i] = (int)(Math.random()*100); 
        }
        for(int i=0; i<arraysize; i++){
      	  	array3[i] = (int)(Math.random()*100); 
        }
   
        //Next three blocks are sequential searches
        System.out.println();
        long timestart = System.nanoTime();
        System.out.print("For Array 1, here are all indexes at which the number 1 is found before it is sorted: ");
        Seq_search(array1, 1);
        long timeend = System.nanoTime();
        long seqtime1 = timeend-timestart;
        
        timestart = System.nanoTime();
        System.out.println("");
        System.out.print("For Array 2, here are all indexes at which the number 50 is found before it is sorted: ");
        Seq_search(array2, 50);
        timeend = System.nanoTime();
        long seqtime50 = timeend-timestart;
        
        timestart = System.nanoTime();
        System.out.println("");
        System.out.print("For Array 3, here are all indexes at which the number 100 is found before it is sorted: ");
        Seq_search(array3, 100);
        System.out.println();
        timeend = System.nanoTime();
        long seqtime100 = timeend-timestart;
        
        //Measures the time it takes to sort array 1 to be used in binary search method
        timestart = System.nanoTime();
        Arrays.sort(array1);
        timeend = System.nanoTime();
        long sorttime = timeend-timestart;
        System.out.println();
        //System.out.println("Here is Array 1 in sorted order: " + Arrays.toString(array1));
        
        //Next three blocks are binary search method.
        timestart = System.nanoTime();
        Bin_search(array1, 1);
        timeend = System.nanoTime();
        long bintime1 = timeend-timestart+sorttime;
        
        timestart = System.nanoTime();
        Bin_search(array1, 50);
        timeend = System.nanoTime();
        long bintime50 = timeend-timestart+sorttime;
        
        timestart = System.nanoTime();
        Bin_search(array1, 100);        
        timeend = System.nanoTime();
        long bintime100 = timeend-timestart+sorttime;
        
        //Measures the time it takes to sort array3 to be used in recursive binary search method
        timestart = System.nanoTime();
        Arrays.sort(array3);
        timeend = System.nanoTime();
        long sorttime2 = timeend-timestart;
        System.out.println();
        //System.out.println("Here is Array 3 in sorted order: " + Arrays.toString(array3));
        
        //Next three blocks are recursive binary search
        timestart = System.nanoTime();
        BinRe_search(array3,1);
        timeend = System.nanoTime();
        long binretime1 = timeend-timestart+sorttime2;
        
        timestart = System.nanoTime();
        BinRe_search(array3,50);
        timeend = System.nanoTime();
        long binretime50 = timeend-timestart+sorttime2;
        
        BinRe_search(array3,100);
        timeend = System.nanoTime();
        long binretime100 = timeend-timestart+sorttime2;
        
        //Three sets of three if statements each determining which search was fastest for given target number
        if(seqtime1<bintime1 && seqtime1<binretime1){
        	System.out.println("The fastest search for the number 1 was a sequential search with a time of " + seqtime1 + " nanoseconds");
        }
        else if(bintime1<seqtime1 && bintime1<binretime1){
        	System.out.println("The fastest search for the number 1 was a looped binary search with a time of " + bintime1+ " nanoseconds");
        }
        else if(binretime1<seqtime1 && binretime1<bintime1){
        	System.out.println("The fastest search for the number 1 was a recursive binary search with a time of " + binretime1+ " nanoseconds");
        }
        
        if(seqtime50<bintime50 && seqtime50<binretime50){
        	System.out.println("The fastest search for the number 50 was a sequential search with a time of " + seqtime50+ " nanoseconds");
        }
        else if(bintime50<seqtime50 && bintime50<binretime50){
        	System.out.println("The fastest search for the number 50 was a looped binary search with a time of " + bintime50+ " nanoseconds");
        }
        else if(binretime50<seqtime50 && binretime1<bintime50){
        	System.out.println("The fastest search for the number 50 was a recursive binary search with a time of " + bintime50+ " nanoseconds");
        }
        
        if(seqtime100<bintime100 && seqtime50<binretime100){
        	System.out.println("The fastest search for the number 100 was a sequential search with a time of " + seqtime100+ " nanoseconds");
        }
        else if(bintime50<seqtime100 && bintime50<binretime100){
        	System.out.println("The fastest search for the number 100 was a looped binary search with a time of " + bintime100+ " nanoseconds");
        }
        else if(binretime50<seqtime100 && binretime1<bintime100){
        	System.out.println("The fastest search for the number 100 was a recursive binary search with a time of " + bintime100+ " nanoseconds");
        }
        
        
	}
	
	//Sequential search method
	public static void Seq_search(int arr[], int target){
		int j=0;
		for(int i=0; i<arr.length; i++){
			if(arr[i]==target){
				System.out.print(i + ", ");
				j++;//counter so that, if target not found, j=0 and the next if statement executes.
			}
		}
		if(j==0){ 
			System.out.print("the number " + target + " does not exist in this array.");
		}
	}
	
	//Binary search method
	public static void Bin_search(int arr[], int target){
		int start = 0;
		int end = (arr.length)-1;
		int middle = (end+start)/2;
		
		while (target!=arr[middle] && middle!=start && middle!=end){
			if(target<arr[middle]){
				end = middle-1;
				middle = (end+start)/2;
			}
			if(target>arr[middle]){
				start=middle+1;
				middle = start + (end-start)/2;
			}
		}
		
		if(target==arr[middle]){
			System.out.println("Here is an index in Array 1 where the number " + target + " occurs: " + middle);
		}
		//Need the following two if statements for when the array's length is two.
		else if(arr.length<=2 && target==arr[start]){
			System.out.println("Here is an index in Array 1 where the number " + target + " occurs: " + start);
		}
		else if(arr.length<=2 && target==arr[end]){
			System.out.println("Here is an index in Array 1 where the number " + target + " occurs: " + end);
		}
		else
			System.out.println("The number " + target + " does not exist in Array 1.");
	}
	
	//Recursive binary search method
	public static void BinRe_search(int arr[], int target){
		int start = 0;
		int end = (arr.length)-1;
		int middle = (end)/2;
		//System.out.println(Arrays.toString(arr));
		
		if(target==arr[middle]){
			System.out.println("The number " + target + " exists in Array 3.");
		}
		//Need the following two if statements for when the array's length is two or less.
		else if(arr.length<=2 && (target==arr[0] || target==arr[1])){
			System.out.println("The number " + target + " exists in Array 3.");
		}
			
		else if(arr.length<=2 && (target!=arr[0]) && (target!=arr[1])){
			System.out.println("The number " +target + " does not exist in Array 3.");
		}
		
		else if(target<arr[middle]){
			int newend = middle;
			int[] looparray = Arrays.copyOfRange(arr, 0, newend+1);
			BinRe_search(looparray, target);
			}
		
		else if(target>arr[middle]){
			int newstart=middle;
			int[] looparray = Arrays.copyOfRange(arr, newstart, (end+1));
			BinRe_search(looparray, target);
			}
		
	}

}
