package com.jaimedantas.greenlac.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/greenlac/v1")
public class Stats {

    @RequestMapping(value = "/stats/history", method = RequestMethod.GET, produces = "text/csv")
    public ResponseEntity exportCSVHistory() throws IOException {

        File path = new File("history.csv");
        return getResponseEntity(path);
    }

    @RequestMapping(value = "/stats/uri", method = RequestMethod.GET, produces = "text/csv")
    public ResponseEntity exportCSVUri() throws IOException {

        File path = new File("uri.csv");
        return getResponseEntity(path);
    }

    private ResponseEntity getResponseEntity(File path) throws IOException {
        FileInputStream fl = new FileInputStream(path);
        byte[] arr = new byte[(int) path.length()];
        fl.read(arr);
        fl.close();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "history.csv");
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(arr, httpHeaders, HttpStatus.OK);
    }


}
