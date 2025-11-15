package ru.itis.oris.test.service;


import ru.itis.oris.test.model.BorrowRecord;
import ru.itis.oris.test.dao.BorrowRecordDao;
import ru.itis.oris.test.dao.BookDao;
import java.util.List;

public class BorrowService {
    private BorrowRecordDao borrowRecordDao = new BorrowRecordDao();
    private BookDao bookDao = new BookDao();

    public boolean borrowBook(Integer bookId, Integer userId) {
        var bookOpt = bookDao.findById(bookId);
        if (bookOpt.isEmpty() || !bookOpt.get().getAvailable()) {
            return false;
        }

        if (borrowRecordDao.isBookBorrowed(bookId)) {
            return false;
        }

        borrowRecordDao.borrowBook(bookId, userId);
        return true;
    }

    public void returnBook(Integer recordId) {
        borrowRecordDao.returnBook(recordId);
    }

    public List<BorrowRecord> getUserBorrowedBooks(Integer userId) {
        return borrowRecordDao.findActiveByUserId(userId);
    }

    public List<BorrowRecord> getAllBorrowedBooks() {
        return borrowRecordDao.findAllActive();
    }
}