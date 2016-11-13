package pl.pyda;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.pyda.model.Client;
import pl.pyda.model.Country;
import pl.pyda.model.Currency;
import pl.pyda.model.Order;
import pl.pyda.repository.ClientRepository;
import pl.pyda.repository.CountryRepository;
import pl.pyda.repository.CurrencyRepository;
import pl.pyda.service.CalculationService;
import pl.pyda.service.InputService;
import pl.pyda.service.OrderService;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by jakubpyda on 11/11/16.
 */
@Slf4j
@Transactional
@Component
public class Initializer {

	private final InputService inputService;
	private final OrderService orderService;
	private final CurrencyRepository currencyRepository;
	private final CountryRepository countryRepository;
	private final ClientRepository clientRepository;
	private final CalculationService calculationService;

	public Initializer(InputService inputService, OrderService orderService, CurrencyRepository currencyRepository, CountryRepository countryRepository, ClientRepository clientRepository, CalculationService calculationService) {
		this.inputService = inputService;
		this.orderService = orderService;
		this.currencyRepository = currencyRepository;
		this.countryRepository = countryRepository;
		this.clientRepository = clientRepository;
		this.calculationService = calculationService;
	}


	@EventListener(classes = {ContextRefreshedEvent.class})
	public void initialize() throws Exception {
		initDB();
		String fileName = "/Users/jakubpyda/Desktop/test/plik.txt";

		BufferedReader br = inputService.getBufferedReaderFromFile(fileName);
		List<Order> orders = orderService.parseToOrders(br);
//		orders.forEach(System.out::println);
		List<Order> calculatedOrders = calculationService.calculateOrders(orders);
		calculatedOrders.forEach(order -> System.out.println("Zamówienie numer = " + order.getOrderNumber()
				+ "\nPłace przez internet kwotę " + order.getPrice() + " " + order.getCurrency().getName()));
	}

	@Transactional
	private void initDB() {
		log.info("Init Database");
		List<Currency> currencies = initCurrencies();
		List<Country> countries = initCountries();
		List<Client> clients = initClients();
	}

	@Transactional
	private List<Client> initClients() {
		List<Client> clients = Lists.newArrayList();
		clients.add(Client.builder()
				.firstName("Marcin")
				.lastName("Tokar")
				.country(countryRepository.findByShortName("PL"))
				.build());
		clients.add(Client.builder()
				.firstName("Piotr")
				.lastName("Omiotek")
				.country(countryRepository.findByShortName("PL"))
				.build());
		clients.add(Client.builder()
				.firstName("John")
				.lastName("Smith")
				.country(countryRepository.findByShortName("GB"))
				.build());
		return clientRepository.save(clients);
	}

	@Transactional
	private List<Country> initCountries() {
		List<Country> countries = Lists.newArrayList();
		countries.add(Country.builder()
				.name("Polska")
				.shortName("PL")
				.currency(currencyRepository.findByName("PLN"))
				.build());
		countries.add(Country.builder()
				.name("Wielka Brytania")
				.shortName("GB")
				.currency(currencyRepository.findByName("GBP"))
				.build());
		return countryRepository.save(countries);
	}

	@Transactional
	private List<Currency> initCurrencies() {
		List<Currency> currencies = Lists.newArrayList();
		currencies.add(Currency.builder()
				.name("PLN")
				.exchangeRate(0.0)
				.build());
		currencies.add(Currency.builder()
				.name("GBP")
				.exchangeRate(6.0)
				.build());
		return currencyRepository.save(currencies);
	}

}