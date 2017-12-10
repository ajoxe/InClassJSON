package nyc.c4q.jsoninclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jsoninclass.controller.MessageAdapter;
import nyc.c4q.jsoninclass.model.Message;

public class MainActivity extends AppCompatActivity {



    RecyclerView messageRecyclerView;
    private List<String> jsonMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        messageRecyclerView = (RecyclerView)  findViewById(R.id.recycler_view);
        JSONObject inClassOne = setJson();
        JSONArray exerciseJsonArray = getJsonArray(inClassOne);
        jsonMessageList = convertJSONArrayToList(exerciseJsonArray);
        List<Message> messageList = new ArrayList<>();
        for (int i= 0; i< jsonMessageList.size(); i++){
            messageList.add(new Message(jsonMessageList.get(i)));
            System.out.println( "json message" + jsonMessageList.get(i));
        }


        MessageAdapter messageAdapter = new MessageAdapter(messageList);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        messageRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        messageRecyclerView.setHasFixedSize(true);
        messageRecyclerView.setAdapter(messageAdapter);










    }

    private List<String> convertJSONArrayToList(JSONArray jsonArray){
        List<String> jsonArrayToList = new ArrayList<>();
        for (int i = 0; i< jsonArray.length(); i++){
            try{
                String oneMessage = String.valueOf(jsonArray.getString(i));
                jsonArrayToList.add(oneMessage);
                Log.d("messageLog", oneMessage);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return jsonArrayToList;
    }

    private JSONObject setJson(){
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject("{\"status\":\"success\",\"message\":[\"affenpinscher\",\"african\",\"airedale\",\"akita\",\"appenzeller\",\"basenji\",\"beagle\",\"bluetick\",\"borzoi\",\"bouvier\",\"boxer\",\"brabancon\",\"briard\",\"bulldog\",\"bullterrier\",\"cairn\",\"chihuahua\",\"chow\",\"clumber\",\"collie\",\"coonhound\",\"corgi\",\"dachshund\",\"dane\",\"deerhound\",\"dhole\",\"dingo\",\"doberman\",\"elkhound\",\"entlebucher\",\"eskimo\",\"germanshepherd\",\"greyhound\",\"groenendael\",\"hound\",\"husky\",\"keeshond\",\"kelpie\",\"komondor\",\"kuvasz\",\"labrador\",\"leonberg\",\"lhasa\",\"malamute\",\"malinois\",\"maltese\",\"mastiff\",\"mexicanhairless\",\"mountain\",\"newfoundland\",\"otterhound\",\"papillon\",\"pekinese\",\"pembroke\",\"pinscher\",\"pointer\",\"pomeranian\",\"poodle\",\"pug\",\"pyrenees\",\"redbone\",\"retriever\",\"ridgeback\",\"rottweiler\",\"saluki\",\"samoyed\",\"schipperke\",\"schnauzer\",\"setter\",\"sheepdog\",\"shiba\",\"shihtzu\",\"spaniel\",\"springer\",\"stbernard\",\"terrier\",\"vizsla\",\"weimaraner\",\"whippet\",\"wolfhound\"]}");

        }catch(JSONException e){
            e.printStackTrace();
        }

        return jsonObject;


    }

    private JSONArray getJsonArray(JSONObject jsonObject){

        JSONArray jsonArray = new JSONArray();

        try {
            jsonArray  = jsonObject.getJSONArray("message");

        }catch(JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }
}
