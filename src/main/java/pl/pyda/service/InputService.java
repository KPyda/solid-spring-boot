package pl.pyda.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jakubpyda on 09/11/16.
 */
@Service
public class InputService {

	public BufferedReader getBufferedReaderFromFile(String fileName) throws IOException {
		return Files.newBufferedReader(Paths.get(fileName));
	}
}
