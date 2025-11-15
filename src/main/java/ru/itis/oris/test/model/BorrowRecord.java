package ru.itis.oris.test.model;

import java.time.LocalDate;

public class BorrowRecord {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String bookTitle;
    private String userName;

    public BorrowRecord() {}

    public BorrowRecord(Integer id, Integer bookId, Integer userId, LocalDate borrowDate,
                        LocalDate returnDate, String bookTitle, String userName) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.bookTitle = bookTitle;
        this.userName = userName;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public static BorrowRecordBuilder builder() {
        return new BorrowRecordBuilder();
    }

    public static class BorrowRecordBuilder {
        private Integer id;
        private Integer bookId;
        private Integer userId;
        private LocalDate borrowDate;
        private LocalDate returnDate;
        private String bookTitle;
        private String userName;

        public BorrowRecordBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public BorrowRecordBuilder bookId(Integer bookId) {
            this.bookId = bookId;
            return this;
        }

        public BorrowRecordBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public BorrowRecordBuilder borrowDate(LocalDate borrowDate) {
            this.borrowDate = borrowDate;
            return this;
        }

        public BorrowRecordBuilder returnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public BorrowRecordBuilder bookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
            return this;
        }

        public BorrowRecordBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public BorrowRecord build() {
            return new BorrowRecord(id, bookId, userId, borrowDate, returnDate, bookTitle, userName);
        }
    }
}