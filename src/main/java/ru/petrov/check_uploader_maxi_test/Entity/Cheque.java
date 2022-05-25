package ru.petrov.check_uploader_maxi_test.Entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "cheque")
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long card_number;

    @CreatedDate
    private Calendar date;

    @OneToMany(mappedBy = "cheque_id")
    private List<Sale> sales;


}
