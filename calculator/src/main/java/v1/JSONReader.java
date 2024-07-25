package v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONReader {

    public static String Read(Reader re) throws IOException { // class Declaration
        StringBuilder str = new StringBuilder(); // To Store Url Data In String.
        int temp;
        do {
          temp = re.read(); // reading Charcter By Chracter.
          str.append((char) temp);
      
        } while (temp != -1);
        //  re.read() return -1 when there is end of buffer , data or end of file.
        return str.toString();
      }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException, URISyntaxException {
        InputStream input = new URI(url).toURL().openStream();

        try { // try catch for checked exception
            BufferedReader re = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            // Buffer Reading In UTF-8
            String Text = Read(re); // Handy Method To Read Data From BufferReader
            JSONArray json = new JSONArray(Text); // Creating A JSON
            return json; // Returning JSON
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            input.close();
        }
  }
}
