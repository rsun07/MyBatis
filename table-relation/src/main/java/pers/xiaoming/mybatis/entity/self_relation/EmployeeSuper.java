package pers.xiaoming.mybatis.entity.self_relation;


import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class EmployeeSuper {
    private Integer id;
    private String name;
    private String title;
    private EmployeeSuper manager;
    private Set<EmployeeSuper> subordinators;

    public EmployeeSuper() {
        this.subordinators = new HashSet<>();
    }

    public EmployeeSuper(String name, String title) {
        this.name = name;
        this.title = title;
        this.subordinators = new HashSet<>();
    }

    public EmployeeSuper(Integer id, String name, String title, EmployeeSuper manager, Set<EmployeeSuper> subordinators) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.manager = manager;
        this.subordinators = subordinators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeSuper employee = (EmployeeSuper) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(title, employee.title) &&
                Objects.equals(manager, employee.manager) &&
                Objects.equals(subordinators, employee.subordinators);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, title, manager, subordinators);
    }
}
