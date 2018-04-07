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
@ToString(exclude = "stores")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String name;
    private Set<Store> stores;

    public Customer(String name) {
        this.name = name;
        this.stores = new HashSet<>();
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.stores = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
