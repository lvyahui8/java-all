package org.lyh.annotation.simple;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/11/27 21:09
 */
@SourceAnnotation("appClass")
public class App {
    @UserAnnotation(id=10,name = "lvyahui",age = 18,gender = "M")
    private Object obj;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        Field field = App.class.getDeclaredField("obj");
        UserAnnotation ua = field.getAnnotation(UserAnnotation.class);

        System.out.println(ua);

        App app = new App();
        field.set(app,new User(ua.id(),ua.name(),ua.age(),ua.gender()));

        // 注解获取自己本身的注解
        Target target = ua.annotationType().getAnnotation(Target.class);
        ElementType[] values = target.value();
        System.out.println(Arrays.toString(values));

        Method method = app.getClass().getDeclaredMethod("method");
        Todo todo = method.getAnnotation(Todo.class);
        System.out.println(todo);

        SourceAnnotation sourceAnnotation = App.class.getAnnotation(SourceAnnotation.class);
        System.out.println(sourceAnnotation);
    }

    @Todo(priority = Todo.Priority.MEDIUM, author = "lvyahui", status = Todo.Status.STARTED)
    public void method(){

    }
}
