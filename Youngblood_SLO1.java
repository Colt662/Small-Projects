import java.util.Arrays;
import java.util.PriorityQueue;



public class Youngblood_SLO1 {
    public static void main(String[] args) {
        //make and fill the qs with values
        String[] q1Values = {"George", "Jim", "John", "Blake", "Kevin", "Michael", "Walter", "Angel"};
        String[] q2Values = {"George", "Katie", "Kevin", "Michelle", "Ryan", "Angel"};
        PriorityQueue<String> q1 = new PriorityQueue<String>(Arrays.asList(q1Values));
        PriorityQueue<String> q2 = new PriorityQueue<String>(Arrays.asList(q2Values));


        printSet(q1, "A");
        printSet(q2, "B");
        printUnion(q1,q2, "A", "B");
        printDifference(q1,q2, "A", "B");
        printDifference(q2,q1, "B", "A");
        printSymmetricDifference(q1,q2, "A", "B");
        printIntersection(q1,q2, "A", "B");
    }

    public static void printSet(PriorityQueue<String> q, String name){
        PriorityQueue<String> result = new PriorityQueue<String>(q);

        System.out.println("Set " + name + ":");
        while(!result.isEmpty()){
            System.out.print(result.poll()+ " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printUnion(PriorityQueue<String> q1, PriorityQueue<String> q2, String name1, String name2){
        //Union - elements that are in either set A or set B
        PriorityQueue<String> result = new PriorityQueue<String>(q1);

        for(String item: q2){
            if(!result.contains(item)){
                result.add(item);
            }
        }

        System.out.println("The union of set " + name1 + " and set " + name2 + ":");
        while(!result.isEmpty()){
            System.out.print(result.poll()+ " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printDifference(PriorityQueue<String> q1, PriorityQueue<String> q2, String name1, String name2){
        //difference - items in and only in set A
        PriorityQueue<String> result = new PriorityQueue<String>(q1);

        for(String item: q2){
            result.remove(item);
        }

        System.out.println("The difference of set " + name1 + " and set " + name2 + ":");
        while(!result.isEmpty()){
            System.out.print(result.poll()+ " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printSymmetricDifference(PriorityQueue<String> q1, PriorityQueue<String> q2, String name1, String name2){
        //symmetric difference - items in only one of set A or B
        PriorityQueue<String> result = new PriorityQueue<String>(q1);

        for(String item: q2){
            if(!result.remove(item)){
                result.add(item);
            }
        }

        System.out.println("The symmetric difference of set " + name1 + " and set " + name2 + ":");
        while(!result.isEmpty()){
            System.out.print(result.poll()+ " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printIntersection(PriorityQueue<String> q1, PriorityQueue<String> q2, String name1, String name2){
        //intersection - only the items that are in both sets a and b
        PriorityQueue<String> result = new PriorityQueue<String>();

        for(String item: q1){
            if(q2.contains(item)){
                result.add(item);
            }
        }

        System.out.println("The intersection of set " + name1 + " and set " + name2 + ":");
        while(!result.isEmpty()){
            System.out.print(result.poll()+ " ");
        }
        System.out.println();
        System.out.println();
    }
}