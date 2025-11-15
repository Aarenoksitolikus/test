package ru.itis.oris.test.model;

import lombok.*;

@Getter
@Setter
@Builder
public class BorrowRecordEntity {
    private Long id;
    private int book_id;
    private int user_id;
    private String borrow_date;
    private String return_date;
}
