package com.recovery.fun;

import com.recovery.fun.repository.ClinicalHistoryRepository;
import com.recovery.fun.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class SpringClinicaRecoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClinicaRecoveryApplication.class, args);
	}

	@Autowired
	private ClinicalHistoryRepository clinicalHistoryRepository;

	@Autowired
	private QuoteRepository quoteRepository;

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String formattedDate = sdf.format(new Date());*/
//			clinicalHistoryRepository.getClinicalHistoryByPatientIdPacientOOrderByFecha(1L).forEach(System.out::println);
//			BigDecimal l = quoteRepository.calculateGrossMonthlyIncome(formattedDate);
		/*	quoteRepository.getQuotesByDate("20240815").forEach(x ->{
				Long cantidad = x.getCantidad();
				String name = x.getName();
				System.out.println("name: "+name+" cantidad: "+cantidad);
			});*/
//			System.out.println(l);
		};
	}

}
