package pers.xiaoming.mybatis.entity.n_to_m;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
// to avoid stack overflow
@ToString(exclude = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    private Integer id;
    private String name;
    private Set<Customer> customers;

    public Store(String name) {
        this.name = name;
        this.customers = new HashSet<>();
    }

    public Store(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id)
                && Objects.equals(name, store.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
