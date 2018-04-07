package pers.xiaoming.mybatis.entity.self_relation;


import lombok.Data;

import java.util.Objects;

@Data
public class EmployeeSub {
    private Integer id;
    private String name;
    private String title;
    private EmployeeSub supervisor;

    public EmployeeSub() {
        this.supervisor = null;
    }

    public EmployeeSub(String name, String title) {
        this.name = name;
        this.title = title;
        this.supervisor = null;
    }

    public EmployeeSub(Integer id, String name, String title, EmployeeSub supervisor) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeSub employee = (EmployeeSub) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(title, employee.title) &&
                Objects.equals(supervisor, employee.supervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, supervisor);
    }

    @Override
    public String toString() {
        return "EmployeeSub{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", supervisor=" + supervisor +
                '}';
    }
}
