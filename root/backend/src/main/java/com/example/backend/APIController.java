package com.example.backend;

import handlers.IApiHandler;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import util.Credentials;

import java.util.List;
import java.util.Map;

@RestController
public class APIController {
    @CrossOrigin
    @GetMapping("/api/")
    public Map<String, Object> api(@RequestParam String keyword, @RequestParam boolean twitterChoose, @RequestParam boolean redditChoose, @RequestParam boolean youtubeChoose, @RequestParam String maxResults, @RequestParam String start, @RequestParam String end) {
        Credentials cred = new Credentials();
        List<IApiHandler> inputAPI = BackendApplication.initializeApiHandlers(cred, twitterChoose, youtubeChoose, redditChoose);
        System.out.println("Executing search...");
        JSONObject results = BackendApplication.executeSearch(keyword, inputAPI, maxResults, start, end);
        System.out.println(results.toString());
        return results.toMap();
    }
}
