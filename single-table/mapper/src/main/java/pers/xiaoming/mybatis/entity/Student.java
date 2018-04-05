package pers.xiaoming.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private double score;

    public Student(String name) {
        this.name = name;
    }

    public Student(double score) {
        this.score = score;
    }

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }
}
