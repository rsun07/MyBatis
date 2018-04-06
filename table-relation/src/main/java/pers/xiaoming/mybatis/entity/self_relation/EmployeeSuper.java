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
    private Set<EmployeeSuper> subordinators;

    public EmployeeSuper() {
        this.subordinators = new HashSet<>();
    }

    public EmployeeSuper(String name, String title) {
        this.name = name;
        this.title = title;
        this.subordinators = new HashSet<>();
    }

    public EmployeeSuper(Integer id, String name, String title, Set<EmployeeSuper> subordinators) {
        this.id = id;
        this.name = name;
        this.title = title;
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
                subEquals(this.subordinators, employee.subordinators);
    }

    private boolean subEquals(Set<EmployeeSuper> origin, Set<EmployeeSuper> candidate) {
        if (origin == candidate) return true;

        if (candidate == null) return false;
        if (origin.size() != candidate.size()) return false;
        return origin.containsAll(candidate);
    }

    @Override
    public int hashCode() {
        // if include subordinators in the hash, it will cause the diff
        // when call contains method in HashSet();
        // even the subordinators.size() cannot be here

        // this might because the subordinator set is initialized in different way
        // in test origin and the db query result.
        return Objects.hash(id, name, title);
    }
}
