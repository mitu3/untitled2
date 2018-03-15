import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;


public class test {

        public static void main(String [] args){
            try{
//            打开英文文档读取文档内容  目前支持TXT格式

                InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\test.docx");

                InputStreamReader reader = new InputStreamReader(is, "UTF-8");
                StringBuffer sb = new StringBuffer();
                while (reader.ready()) {
                    sb.append((char) reader.read());
                }

//            将文档中的英文提取出来并添加到 list

                String  str = sb.toString();
                String s = "\\d+.\\d+|\\w+";
                Pattern  pattern=Pattern.compile(s);
                Matcher  ma=pattern.matcher(str);

                List<String> list = new ArrayList<>();
                while(ma.find()){
                    list.add(ma.group());

                }

//          将所有单词转换为小写并添加到list2

                List<String> list2 = new ArrayList<>();
                for(int i=0 ;i<list.size();i++){
                    String strs = list.get(i).toString();
                    String str2 = strs.toLowerCase();
                    list2.add(str2);
                }

//
//            System.out.println(list2);
                Map<String, Integer> map = new HashMap<String, Integer>();
                for (String str3 : list2) {
                    if (map.containsKey(str3)) {
                        map.put(str3, map.get(str3) + 1);
                    } else {
                        map.put(str3, 1);
                    }
                }
//          根据value升序排序
//             这里将map.entrySet()转换成list
                List<Map.Entry<String, Integer>> list6 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//                然后通过比较器来实现排序
                Collections.sort(list6, new Comparator<Map.Entry<String, Integer>>() {
                    //升序排序.
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                String str6 = list6.get(list6.size()-1).toString();
                String [] list7 = str6.split("=");
                System.out.println("文章中最多的英文单词为：  "+list7[0]+"    单词数量为： "+ list7[1]);
//            for (Map.Entry<String, Integer> mapping : list6) {
//                System.out.println(mapping.getKey() + ":" + mapping.getValue());
//            }



                is.close();
                reader.close();

            }catch(Exception e){
                System.out.print(e);
            }



        }
    }



