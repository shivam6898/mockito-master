package com.hubberspot.mockito.spies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookManagerTest {

	@InjectMocks
	private BookManager bookManager;


	//when there is need to Mock one method and Actually normal call  the method then  use @Spy
	@Spy
	private BookService bookService;
	
	@Test
	public void testMockitoSpy(){
		// We need to mock findBook because it is communicating to database or not implemented
		// We need to call getAppliedDiscount to calculate the discounted price
		Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		//with spied Object  use doReturn.when syntax
		doReturn(book).when(bookService).findBook("1234");
		//  it  will  actually  call  the findBook method
//		when(bookService.findBook("1234")).thenReturn(book);
		int actualDiscount = bookManager.applyDiscountOnBook("1234", 10);
		assertEquals(450, actualDiscount);
	}
	
}
