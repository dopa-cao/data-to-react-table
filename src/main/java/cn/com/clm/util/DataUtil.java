package cn.com.clm.util;

import cn.com.clm.bean.Dog;
import cn.com.clm.bean.People;
import cn.com.clm.bean.Phone;
import com.fasterxml.uuid.Generators;

import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
public class DataUtil {

    private static Date date = new Date();


    /**
     * get people list
     * @return list
     */
    public static List<People> getPeopleData() {
        List<People> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            People people = new People();
            people.setId(i + 1L);
            people.setBirth(getDate(date, i));
            people.setName("ct" + i);
            people.setSchool("school" + i);
            people.setDesc("desc" + i);
            list.add(people);
        }
        return list;
    }

    /**
     * git dog list
     * @return list
     */
    public static List<Dog> getDogData() {
        List<Dog> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Dog dog = new Dog();
            dog.setId(i +1L);
            dog.setColor("color" + 1);
            dog.setHeight(50d + i);
            dog.setName("dog" + i);
            dog.setDesc("desc" + i);
            list.add(dog);
        }
        return list;
    }

    /**
     * get phone data
     * @return list
     */
    public static List<Phone> getPhoneData() {
        List<Phone> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Phone phone = new Phone();
            phone.setId(i + 1L);
            phone.setColor("color" + i);
            phone.setType("type" + i);
            phone.setDesc("desc" + i);
            list.add(phone);
        }
        return list;
    }




    /**
     * get date
     *
     * @param date  date
     * @param count count
     * @return      result
     */
    private static Date getDate(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, - count);
        return calendar.getTime();
    }


    /**
     * create uuid
     *
     * @return  result
     */
    public static String uuid() {
        return  Generators.timeBasedGenerator().generate().toString();
    }


}
