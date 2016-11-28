package cvut.fit.di;

import cvut.fit.di.entity.Person;
import cvut.fit.di.reflextion.Wired;

import java.lang.reflect.Field;


public class Application {

    public static void main(String[] args) {

        Class clazz = Person.class;


        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if(field.isAnnotationPresent(Wired.class))
            System.out.println(field.getDeclaringClass().getName());
        }
    }

}
