package com.xf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Order {
    String gname;
    String typename;
    String gphone;
    String indate;
    String outdate;
    Integer id;

    public Order(String gname, String typename, String gphone, String indate, String outdate) {
        this.gname = gname;
        this.typename = typename;
        this.gphone = gphone;
        this.indate = indate;
        this.outdate = outdate;
        this.id = null;
    }
}
