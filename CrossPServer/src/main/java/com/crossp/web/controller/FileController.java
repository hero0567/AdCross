package com.crossp.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	@RequestMapping("/download/{name:.+}")
	public HttpEntity<byte[]> download(@PathVariable("name") String name) throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		Path path = Paths.get("download/" + name);
		byte[] data = Files.readAllBytes(path);
		return new ResponseEntity<byte[]>(data, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public void upload(@RequestParam(value="file", required=true) MultipartFile file) throws Exception {		
		FileCopyUtils.copy(file.getBytes(),new File("/tmp/"+file.getOriginalFilename())); 		
	}
}
