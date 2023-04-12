package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {
    @DataProvider //cw_18
    public Iterator<Object[]> loginModelDto() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder().email("test@mail.ru").password("Qwer1234$").build()});
        list.add(new Object[]{User.builder().email("test@mail.ru").password("Qwer1234$").build()});
        list.add(new Object[]{User.builder().email("test@mail.ru").password("Qwer1234$").build()});

        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> registrationCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));


        String line = reader.readLine();//переменная- пробросить throws IOException

        while (line != null) {
            String[] split = line.split(",");//получили массив и обернуть в while
            list.add(new Object[] { new User()
                    .withName(split[0])//index element
                    .withLastName(split[1])
                    .withEmail(split[2])
                    .withPassword(split[3])
            });
            line = reader.readLine();//циклом пройти по строкам по строка не станет null

    }
      return list.iterator();
    }
}