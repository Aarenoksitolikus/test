package ru.itis.oris.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowRecord {

    private Integer id;

    private Integer bookId;

    private Integer userId;

    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

}
