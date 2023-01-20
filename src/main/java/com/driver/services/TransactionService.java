package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BookRepository;
import com.driver.repositories.CardRepository;
import com.driver.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.driver.models.TransactionStatus.SUCCESSFUL;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository5;

    @Autowired
    CardRepository cardRepository5;

    @Autowired
    TransactionRepository transactionRepository5;

    @Value("${books.max_allowed}")
    public int max_allowed_books;

    @Value("${books.max_allowed_days}")
    public int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    public int fine_per_day;

    public String issueBook(int cardId, int bookId) throws Exception {
        //check whether bookId and cardId already exist
        //conditions required for successful transaction of issue book:
        //1. book is present and available
        // If it fails: throw new Exception("Book is either unavailable or not present");
        //2. card is present and activated
        // If it fails: throw new Exception("Card is invalid");
        //3. number of books issued against the card is strictly less than max_allowed_books
        // If it fails: throw new Exception("Book limit has reached for this card");
        //If the transaction is successful, save the transaction to the list of transactions and return the id

        //Note that the error message should match exactly in all cases

        Book book= bookRepository5.findById(bookId).get();
        Card card= cardRepository5.findById(cardId).get();

        if(book!=null && card!=null)
        {
            if(book.isAvailable())
            {
                if(card.getCardStatus()== CardStatus.ACTIVATED)
                {
                    if(card.getBooks().size()+1<=max_allowed_books)
                    {
                        Transaction transaction= new Transaction();
                        transaction.setBook(book);
                        transaction.setCard(card);
                        transaction.setIssueOperation(true);
                        transaction.setTransactionStatus(SUCCESSFUL);
                        transactionRepository5.save(transaction);

                        book.setAvailable(false);

                        List<Transaction> txnList= book.getTransactions();
                        txnList.add(transaction);
                        book.setTransactions(txnList);
                        bookRepository5.save(book);

                        return transaction.getTransactionId();
                    }
                    else
                        throw new Exception("Book limit has reached for this card");
                }
                else
                    throw new Exception("Card is invalid");
            }
            else
                throw new Exception("Book is either unavailable or not present");

        }
       return null; //return transactionId instead
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception{

        List<Transaction> transactions = transactionRepository5.find(cardId, bookId, SUCCESSFUL, true);
        Transaction transaction = transactions.get(transactions.size() - 1);

        //for the given transaction calculate the fine amount considering the book has been returned exactly when this function is called
        //make the book available for other users
        //make a new transaction for return book which contains the fine amount as well

        Book book= bookRepository5.findById(bookId).get();
        Card card= cardRepository5.findById(cardId).get();
        book.setAvailable(true);
        bookRepository5.save(book);

        Transaction returnBookTransaction  = new Transaction();
        returnBookTransaction.setIssueOperation(false);
        returnBookTransaction.setBook(book);
        returnBookTransaction.setTransactionStatus(SUCCESSFUL);
        returnBookTransaction.setCard(card);
        returnBookTransaction.setFineAmount(0);     //how to set fineAmount?? ***
        transactionRepository5.save(returnBookTransaction);
        return returnBookTransaction; //return the transaction after updating all details
    }
}
