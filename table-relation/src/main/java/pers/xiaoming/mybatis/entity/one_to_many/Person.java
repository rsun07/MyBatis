package pers.xiaoming.mybatis.entity.one_to_many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Integer id;
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
