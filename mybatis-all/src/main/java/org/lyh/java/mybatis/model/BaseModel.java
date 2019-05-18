package org.lyh.java.mybatis.model;

import org.lyh.java.mybatis.annotation.JsonField;
import org.lyh.java.mybatis.annotation.NonTableFiled;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/12 22:40
 */
@SuppressWarnings("unused")
public class BaseModel {

    public Map<String,Object> jsonValues ;

    protected Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String,String> getFieldMap(){
        Map<String,String> fieldMap = new HashMap<String,String>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields){
            if(field.getAnnotation(NonTableFiled.class) == null){
                fieldMap.put(
                        // table field -- snake
                        field.getName().replaceAll("([A-Za-z])([A-Z])","$1_$2").toLowerCase(),
                        // bean field -- hump
                        field.getName()
                );
            }
        }
        return fieldMap;
    }

    public List<Field> getJsonFields(){
        Field fields[] = this.getClass().getDeclaredFields();
        List<Field> jsonFields = new ArrayList<Field>();
        for(Field field : fields){
            JsonField jsonField = field.getAnnotation(JsonField.class);
            if(jsonField == null){
                continue;
            }
            jsonFields.add(field);
        }
        return jsonFields;
    }

    public Map<String,Object>  getJsonValues(){
        if(jsonValues != null){
            return jsonValues;
        }
        jsonValues = new HashMap<String, Object>();
        List<Field> fields = getJsonFields();
        for (Field field : fields){
            field.setAccessible(true);
            JsonField jsonField = field.getAnnotation(JsonField.class);
            try {
                jsonValues.put(jsonField.value(),field.get(this));
            } catch (IllegalAccessException e) {
                //
            } finally {
                field.setAccessible(false);
            }
        }
        return jsonValues;
    }
}
