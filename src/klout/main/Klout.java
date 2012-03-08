package klout.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import klout.types.KloutException;
import klout.types.User;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * @author aidanc
 * @email aidanchurch@gmail.com
 *
 */

/**
 * Klout class. Contains all Klout functions.
 * @author aidan
 *
 */
public class Klout {

	private static String url = "http://api.klout.com/1/klout.json";
	private static Gson gson;
	
	/**
	 * Constructor. 
	 * @param key Your API key.
	 */
	public Klout(String key){
		url+="?key="+key;
		gson = new Gson();
	}
	
	/**
	 * Returns a list of Users and their Klout Scores.
	 * @param users The Users to find.
	 * @return List of Users with their scores.
	 * @throws IOException 
	 * @throws KloutException
	 */
	public List<User> getUsersScores(List<String> users) throws IOException, KloutException{
		URL link = new URL(this.addParams(users, url));
		HttpURLConnection con = (HttpURLConnection) link.openConnection();
		if(con.getResponseCode() == 200){
			return this.makeUsers(con, users);
		}else{
			throw new KloutException("Klout Error: " + con.getResponseMessage() + " USING URL: " + link.toString());
		}
	}
	
	/**
	 * Appends Parameters onto the URL
	 * @param users users to add
	 * @param url the URL to begin with
	 * @return The finished URL.
	 */
	private String addParams(List<String> users, String url){
		url += "&users=";
		for(String user : users){
			url += user+",";
		}
		return url;
	}
	
	/**
	 * Creates users from the Json Response.
	 * @param con HTTP connection.
	 * @param users User names
	 * @return Users and their klout scores.
	 * @throws IOException
	 */
	private List<User> makeUsers(HttpURLConnection con, List<String> users) throws IOException{
		List<User> Users = new ArrayList<User>();
		InputStream response = con.getInputStream();
		BufferedReader buff = new BufferedReader(new InputStreamReader(response, Charset.forName("UTF-8")));
	    String SearchResult = readAll(buff);
	    JsonObject jsonO = new JsonParser().parse(SearchResult).getAsJsonObject();
		JsonArray array = (JsonArray)new JsonParser().parse(jsonO.get("users").toString());
		
		for(int i=0; i< array.size() && i<users.size(); ++i){
			User u = gson.fromJson(array.get(i), User.class);
			u.setUname(users.get(i));
			Users.add(u);
		}
		return Users;
	}
	
	/**
	 * Reads all Strings from the Input into one.
	 * @param rd
	 * @return
	 * @throws IOException
	 */
	private String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
