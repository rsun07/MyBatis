package pers.xiaoming.mybatis.entity.n_to_m;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Store {
    private Integer id;
    private String name;
    private Set<Customer> customers;

    public Store(String name, String address) {
        this.name = name;
        this.customers = new HashSet<>();
    }
}
