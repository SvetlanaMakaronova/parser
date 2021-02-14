import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;

public class TextWriter {

    Document document = null;
    String file = Config.outputFile;

    public boolean addTextInFile(String url) {

        try {
            if (isValidURL(url)) {
                document = Jsoup.connect(url).get();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

                out.write(document.text());
                return true;
            }
        } catch (FileNotFoundException e) {
            Logger.LOGGER.log(Level.WARNING, file + " not found: " + e);
        } catch (IOException e) {
            Logger.LOGGER.log(Level.WARNING, "Failed with:  " + e);
        }

        return false;
    }

    public boolean isValidURL(String url) {

        URL u = null;

        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            Logger.LOGGER.log(Level.WARNING, "URL is malformed: " + e);
            return false;
        }

        try {
            u.toURI();
        } catch (URISyntaxException e) {
            Logger.LOGGER.log(Level.WARNING, "URL Syntax Exception: " + e);
            return false;
        }

        try {
            u.getContent();
        } catch (UnknownHostException e){
            Logger.LOGGER.log(Level.WARNING, "Host not available: " + e.getLocalizedMessage());
            return false;
        } catch (IOException e) {
            Logger.LOGGER.log(Level.WARNING, "Unknown IOException: " + e.getLocalizedMessage());
        }


        return true;
    }

}
