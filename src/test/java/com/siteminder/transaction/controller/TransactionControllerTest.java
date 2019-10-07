//package com.siteminder.transaction.controller;
//
//import static org.junit.Assert.assertEquals;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.siteminder.transaction.controller.TransactionController;
//import com.siteminder.transaction.model.TransactionEntity;
//import com.siteminder.transaction.services.TransactionService;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = TransactionController.class, secure = false)
//public class TransactionControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private TransactionService transactionService;
//
//	TransactionEntity transactionEntity = new TransactionEntity(Long.parseLong(String.valueOf(1)),
//			BigDecimal.valueOf(47.43), new Date(System.currentTimeMillis()));
//
////	Statistics mockStats = new Statistics(BigDecimal.valueOf(175).setScale(2, BigDecimal.ROUND_HALF_UP), 
////			BigDecimal.valueOf(43.75).setScale(2, BigDecimal.ROUND_HALF_UP),
////			BigDecimal.valueOf(66).setScale(2, BigDecimal.ROUND_HALF_UP),
////			BigDecimal.valueOf(17).setScale(2, BigDecimal.ROUND_HALF_UP),4);
//	
//	String mockTransactionJson = "{\"amount\":66.125,\"timestamp\":\"2019-06-30T13:40:18.312Z\"}";
//
//	
//	@Test
//	public void displayStats() throws Exception {
//
////		Mockito.when(
////				transactionService.retrieveStats()).thenReturn(mockStats);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/statistics").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		System.out.println(result.getResponse());
//		String expected = "{sum:175,avg:43.75,max:66,min:17,count:4}";
//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), false);
//	}
//	
//	
//	@Test
//	public void registerTransactions_status204() throws Exception {
//		TransactionEntity mockTransaction = new TransactionEntity(Long.parseLong(String.valueOf(1)),
//				BigDecimal.valueOf(47.43), new Date(System.currentTimeMillis()));
//
//		// transactionService.addTransactions to respond back with mockTransaction
//		Mockito.when(
//				transactionService.addTransactions(
//						Mockito.any(TransactionEntity.class))).thenReturn(mockTransaction);
//
//		// Send new transaction as body to /transactions
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/transactions")
//				.accept(MediaType.APPLICATION_JSON).content(mockTransactionJson)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
//
//	}
//	
////	@Test
////	public void registerTransactions_status422() throws Exception {
////		TransactionEntity mockTransaction = new TransactionEntity(Long.parseLong(String.valueOf(1)),
////				BigDecimal.valueOf(47.43), new Date(System.currentTimeMillis()));
////		
////		mockTransactionJson = "{\"amount\":66.125,\"timestamp\":\"2019-07-30T13:40:18.312Z\"}";
////		// transactionService.addTransactions to respond back with mockTransaction
////		Mockito.when(
////				transactionService.addTransactions(
////						Mockito.any(TransactionEntity.class))).thenReturn(mockTransaction);
////
////		// Send new transaction as body to /transactions
////		RequestBuilder requestBuilder = MockMvcRequestBuilders
////				.post("/transactions")
////				.accept(MediaType.APPLICATION_JSON).content(mockTransactionJson)
////				.contentType(MediaType.APPLICATION_JSON);
////
////		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////
////		MockHttpServletResponse response = result.getResponse();
////
////		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatus());
////
////	}
//	
//	@Test
//	public void registerTransactions_status400() throws Exception {
//		TransactionEntity mockTransaction = new TransactionEntity(Long.parseLong(String.valueOf(1)),
//				BigDecimal.valueOf(47.43), new Date(System.currentTimeMillis()));
//		
//		mockTransactionJson = "{\"amount\":-66.125,\"timestamp\":\"2019-07-30T13:40:18.312Z\"}";
//		// transactionService.addTransactions to respond back with mockTransaction
//		Mockito.when(
//				transactionService.addTransactions(
//						Mockito.any(TransactionEntity.class))).thenReturn(mockTransaction);
//
//		// Send new transaction as body to /transactions
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/transactions")
//				.accept(MediaType.APPLICATION_JSON).content(mockTransactionJson)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
//
//	}
//	
//	@Test
//	public void registerTransactions_status201() throws Exception {
//		TransactionEntity mockTransaction = new TransactionEntity(Long.parseLong(String.valueOf(1)),
//				BigDecimal.valueOf(47.43), new Date(System.currentTimeMillis()));
//		
//		Instant instant = new Date(System.currentTimeMillis()).toInstant();
//		mockTransactionJson = "{\"amount\":66.125,\"timestamp\":\""+instant.toString()+"\"}";
//		// transactionService.addTransactions to respond back with mockTransaction
//		Mockito.when(
//				transactionService.addTransactions(
//						Mockito.any(TransactionEntity.class))).thenReturn(mockTransaction);
//
//		// Send new transaction as body to /transactions
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/transactions")
//				.accept(MediaType.APPLICATION_JSON).content(mockTransactionJson)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//
//	}
//	
//	@Test
//	public void deleteTransactions() throws Exception {
//		
//		List<TransactionEntity> transactionList = Collections.synchronizedList(new ArrayList<TransactionEntity>());
//
//		Mockito.when(
//				transactionService.removeTransactions()).thenReturn(transactionList);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.delete("/transactions")
//				.accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
//	}
//	
//
//}
