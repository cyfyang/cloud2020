
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        System.out.println(list.size());

        if(CollectionUtils.isEmpty(list)){
            System.out.println("nimabi");
        }else {
            System.out.println("23123123");
        }

    }
}
