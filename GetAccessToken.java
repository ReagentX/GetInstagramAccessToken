import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
 
public class GetAccessToken {
	public static void main(String[] varargs) {
		try {
			GetAccessToken task = new GetAccessToken(new URL("https://api.instagram.com/oauth/access_token"));
			
			//set your app's information here:
			
			task.setParam("client_id", "");
			task.setParam("client_secret", "");
			task.setParam("grant_type", "authorization_code");
			task.setParam("redirect_uri", "");
			task.setParam("code", "");
			String[] response = task.runTask();
		} catch (Throwable t) {
		}
	}
	
	private static final String userAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7";
	private final URL url;
	private HashMap<String, String> params;
	public GetAccessToken(URL target) {
		url = target;
		params = new HashMap<String, String>();
	}
 
	public void setParam(String key, String value) {
		this.params.put(key, value);
	}
 
	public String[] runTask() throws IOException {
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setRequestProperty("User-Agent", userAgent);
		if (params.size() > 0) {
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			DataOutputStream postStream = new DataOutputStream(
					connection.getOutputStream());
			StringBuilder postBlob = new StringBuilder();
			Iterator<String> paramskeys = params.keySet().iterator();
			while (paramskeys.hasNext()) {
				String key = paramskeys.next();
				postBlob.append(key).append("=");
				postBlob.append(URLEncoder.encode(params.get(key), "utf-8"));
				if (paramskeys.hasNext())
					postBlob.append("&");
			}
			String chunk = postBlob.toString();
			System.out.println(String.format("POST to server: %s", chunk));
			postStream.writeBytes(chunk);
			postStream.close();
		}
		System.out.println("If execution stops here the code is not valid anymore. \n");
		DataInputStream getStream = new DataInputStream(connection.getInputStream());
		ArrayList<String> result = new ArrayList<String>();
		String str;
		while (null != (str = getStream.readLine())){
			System.out.println("Access code: " + str.substring(16, 68));
			result.add(str);
		}
		getStream.close();
		return result.toArray(new String[0]);
		}
}
