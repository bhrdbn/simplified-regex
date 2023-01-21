import java.io.*;
import java.net.URL;
import java.util.*;

public class main {


    public static void main(String[] args) throws IOException {
        // File path is passed as parameter
        URL firstUrl;
        ArrayList<ArrayList<SuffixTree> >allTrees=new ArrayList<>();

        firstUrl = main.class.getResource("input.txt");
        File file1 = new File(firstUrl.getPath());
        BufferedReader br = new BufferedReader(new FileReader(file1));
        int n = Integer.parseInt(br.readLine());
        String[] patterns = new String[n];
        String[] whole=new String[10];

        for (int i = 0; i < n; i++) {
            patterns[i] = br.readLine();
        }

//

        for (int d = 0; d < 10; d++) {
            URL url;
            if (d != 9) {
                url = main.class.getResource("doc0" + (d + 1) + ".txt");
            } else {
                url = main.class.getResource("doc10.txt");
            }
            File file = new File(url.getPath());
            br = new BufferedReader(new FileReader(file));
            String st = br.readLine();
            st=st.toLowerCase();
            StringBuilder sb = new StringBuilder();

            for (int c = 0; c < st.length(); c++) {
                if (Character.isAlphabetic(st.charAt(c)) || st.charAt(c) == ' ') {
                    sb.append(st.charAt(c));
                }
            }
            whole[d] = sb.toString();
        }
        for (int i = 0; i < 10; i++) {
            ArrayList<SuffixTree>tree=new ArrayList<>();
            String[] words = whole[i].split(" ");
            for (String word : words)
                tree.add(new SuffixTree(word));
            allTrees.add(tree);
        }


        BufferedWriter f_writer = new BufferedWriter(new FileWriter("./" + "result.txt"));
        // code to make your model and read inputs
        long startTime = System.nanoTime();
        // code to find the result of queries


        for (String p:patterns) {

            String res="";
            HashMap<Integer, Integer> fileCount = new HashMap<>();
            for(int i=0;i<10;i++){
                int count=0;
                for (SuffixTree st:allTrees.get(i)){
                    if (st.searchText(p)){
                        count++;
                    }
                }
                if (count!=0)fileCount.put(i+1,count);
            }
            if (!fileCount.isEmpty()) {
                List<Map.Entry<Integer, Integer>> l = new ArrayList<Map.Entry<Integer, Integer>>(fileCount.entrySet());
                Collections.sort(l, new ValueThenKeyComparator<Integer, Integer>());
                for (Map.Entry<Integer, Integer>m:l) {
                    res+=m.getKey()+" ";
                }

            }
            else {
                res="-1";
            }
            res+="\n";

            f_writer.write(res);
            f_writer.flush();

        }
        f_writer.close();
        long endTime = System.nanoTime();
        double duration = (endTime - startTime)/1000000.0;
        f_writer = new BufferedWriter(new FileWriter("./" + "time.txt"));
        f_writer.write(String.valueOf(duration));



        }
//        StringBuilder stringBuilder=new StringBuilder();
//        String nq = scanner.nextLine();
//        int n = Integer.parseInt(nq.split(" ")[0]);
//        int q = Integer.parseInt(nq.split(" ")[1]);
//        String[] words=scanner.nextLine().split(" ");
//        int count=0;
//        for (int i =0 ; i<q ; i++){
//            StringBuilder nums= new StringBuilder();
//            String pattern=scanner.nextLine();
//            for (String word:words){
//                SuffixTree suffixTree = new SuffixTree(word);
//                if (suffixTree.searchText(pattern)) {
//                    count++;
//                    nums.append(word).append(" ");
//                }
//            }
//            stringBuilder.append(count).append(" ").append(nums).append("\n");
//            count=0;
//        }
//        System.out.print(stringBuilder);
//    }
    }


