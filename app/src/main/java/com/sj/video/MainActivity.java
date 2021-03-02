package com.sj.video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);

        String strJson="{ \n" +
                "\"MediaItems\":\n" +
                " [{ \n" +
                "\"Title\": \"Anupam Kher in conversation with Johnny Lever\",\n" +
                "\"Image\":\"https://img.republicworld.com/republic-prod/shows/promolarge/xxhdpi/tr:w-425,h-233/150375812059a18728c0d9b.jpeg\",\n" +
                " \"Url\": \"http://uds.ak.o.brightcove.com/5384493731001/5384493731001_5552873278001_5552856451001.mp4?pubId=5384493731001&videoId=5552856451001\"\n" +
                "}, \n" +
                "{ \n" +
                "\"Title\": \"In Conversation with Chef Vikas Khanna\", \n" +
                "\"Image\":\"https://img.republicworld.com/republic-prod/shows/promolarge/xxhdpi/tr:w-425,h-233/150497447759b4168d9557d.jpeg\", \n" +
                "\"Url\": \"http://uds.ak.o.brightcove.com/5384493731001/5384493731001_5552873278001_5552856451001.mp4?pubId=5384493731001&videoId=5552856451001\"\n" +
                "}, \n" +
                "{ \n" +
                "\"Title\": \"Nation Wants To Know: Union minister Rajnath Singh Speaks To Bharat\",\n" +
                "\"Image\":\"https://img.republicworld.com/republic-prod/shows/promolarge/xxhdpi/tr:w-425,h-233/15509400745c7177aa10252.jpeg\", \n" +
                "\"Url\": \"http://uds.ak.o.brightcove.com/5384493731001/5384493731001_5552873278001_5552856451001.mp4?pubId=5384493731001&videoId=5552856451001\"\n" +
                "},\n" +
                " {\n" +
                " \"Title\": \"Nation Wants To Know: Gautam Gambhir Speaks To Arnab\", \n" +
                "\"Image\":\"https://img.republicworld.com/republic-prod/shows/promolarge/xxhdpi/tr:w-425,h-233/15467004525c30c6a42b547.jpeg\", \n" +
                "\"Url\": \"http://uds.ak.o.brightcove.com/5384493731001/5384493731001_5552873278001_5552856451001.mp4?pubId=5384493731001&videoId=5552856451001\"\n" +
                "}, \n" +
                "{ \n" +
                "\"Title\": \"Nation Wants To Know: Union minister Col Rajyavardhan Rathore Speaks To Arnab\",\n" +
                "\"Image\":\"https://img.republicworld.com/republic-prod/shows/promolarge/xxhdpi/tr:w-425,h-233/15473142655c3a2459d525a.jpeg\", \n" +
                "\"Url\": \"http://uds.ak.o.brightcove.com/5384493731001/5384493731001_5552873278001_5552856451001.mp4?pubId=5384493731001&videoId=5552856451001\"\n" +
                "}, \n" +
                "{\n" +
                " \"Title\": \"#ISROChiefSpeaksToArnab\",\n" +
                "\"Image\":\"https://img.republicworld.com/republic-prod/shows/promolarge/xxhdpi/tr:w-425,h-233/15944845855f09e769daee4.jpeg\", \n" +
                "\"Url\": \"http://uds.ak.o.brightcove.com/5384493731001/5384493731001_5552873278001_5552856451001.mp4?pubId=5384493731001&videoId=5552856451001\"\n" +
                "}] \n" +
                "} \n";
        String data = "";
        try {
            // Create the root JSONObject from the JSON string.
            JSONObject  jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("MediaItems");

            //Iterate the jsonArray and print the info of JSONObjects
            String[] Title=new String[jsonArray.length()];
            String[] Image=new String[jsonArray.length()];
            String[] Url=new String[jsonArray.length()];
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Title[i] = jsonObject.optString("Title").toString();
                Image[i] = jsonObject.optString("Image").toString();
                Url[i] = jsonObject.optString("Url").toString();

            }
            final CustomView customListView = new CustomView(MainActivity.this, Title,Image,Url);
            listview.setAdapter(customListView);

        } catch (JSONException e) {e.printStackTrace();}
    }
}
