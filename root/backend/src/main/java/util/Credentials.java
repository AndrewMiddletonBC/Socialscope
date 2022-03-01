
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.language.v1.LanguageServiceSettings;

public class Credentials {

    private static final String DEFAULT_CREDENTIALS_FILE = "private/credentials.json";
    private static final String DEFAULT_CREDENTIALS_GOOGLEFILE ="private/socialsentanalysis.json";

    private String redditAppId;
    private String redditAppSecret;  // private
    private String redditUserAgent;

    private String twitterAppId;
    private String twitterAppSecret;  // private
    private String twitterUserAgent;
    private String youtubeUserAgent;
    private String youtubeApiKey;  // private
       // private
    public Credentials() {
        readCredentialsFromFile(DEFAULT_CREDENTIALS_FILE);
    }

    public LanguageServiceSettings readGoogleCredentials() throws IOException {
    	LanguageServiceSettings languageServiceSettings =
            LanguageServiceSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream(DEFAULT_CREDENTIALS_GOOGLEFILE))))
                .build();
        return languageServiceSettings;
        }

    private void readCredentialsFromFile(String fp) {
        BufferedReader reader;
        try {
        	String currPath = new java.io.File(".").getCanonicalPath();
        	System.out.println(currPath);
            reader = new BufferedReader(new FileReader(fp));
            StringBuilder sb = new StringBuilder();
            reader.lines().forEach((String line) -> sb.append(line));
            reader.close();
            JSONObject jo = new JSONObject(sb.toString());
            this.redditAppId = jo.getString("redditAppId");
            this.redditAppSecret = jo.getString("redditAppSecret");
            this.redditUserAgent = jo.getString("redditUserAgent");
            this.twitterAppId = jo.getString("twitterAppId");
            System.out.println(this.twitterAppId);
            this.twitterAppSecret = jo.getString("twitterAppSecret");
            System.out.println(this.twitterAppSecret);
            this.twitterUserAgent = jo.getString("twitterUserAgent");
            this.youtubeUserAgent = jo.getString("youtubeUserAgent");
            this.youtubeApiKey = jo.getString("youtubeApiKey");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void writeCredentialsToFile(String fp) {
        // debugging method to write initial data
        JSONObject jo = new JSONObject();
        try {
            jo.put("redditAppId", redditAppId);
            jo.put("redditAppSecret", redditAppSecret);
            jo.put("redditUserAgent", redditUserAgent);
            jo.put("twitterAppId", twitterAppId);
            jo.put("twitterAppSecret", twitterAppSecret);
            jo.put("twitterUserAgent", twitterUserAgent);
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fp));
            writer.write(jo.toString());
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // reddit credentials
    public String getRedditAppId() {
        return redditAppId;
    }

    public String getRedditAppSecret() {
        return redditAppSecret;
    }

    public String getRedditAppUserAgent() {
        return redditUserAgent;
    }

    // twitter credentials
    public String getTwitterAppId() {
        return twitterAppId;
    }

    public String getTwitterAppSecret() {
        return twitterAppSecret;
    }

    public String getTwitterAppUserAgent() {
        return twitterAppSecret;
    }

    // youtube credentials
    public String getYoutubeAppUserAgent() {
        return youtubeUserAgent;
    }
    public String getYoutubeApiKey() {
        return youtubeApiKey ;
    }
}



