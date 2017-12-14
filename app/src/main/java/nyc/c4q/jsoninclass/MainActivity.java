package nyc.c4q.jsoninclass;

import android.support.annotation.Nullable;
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
import java.util.Arrays;
import java.util.List;

import nyc.c4q.jsoninclass.controller.MessageAdapter;
import nyc.c4q.jsoninclass.model.Message;

public class MainActivity extends AppCompatActivity {


    RecyclerView messageRecyclerView;
    private List<String> jsonMessageList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuestionOne questionOne = new QuestionOne();
        questionOne.businessLogic();
        QuestionTwo questionTwo = new QuestionTwo();
        questionTwo.businessLogic();
    }

    private class QuestionTwo {

        public void businessLogic() {
            String jSonString = setJSonString();
            JSONObject exercise2 = setJSon(jSonString);
            String keyName = "message";
            JSONArray exercise2JSonArray = getJsonArray(exercise2, keyName);
            List<String> messageKeys = getMessageKeys(exercise2JSonArray);
            logKeys(messageKeys, exercise2JSonArray);

        }

        @Nullable//google what that means
        private JSONObject setJSon(String jSonString) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jSonString);
            } catch (JSONException e) {

            }
            return jsonObject;
        }

        private JSONArray getJsonArray(JSONObject jsonObject, String keyName) {
            JSONArray jsonArray = new JSONArray();
            try {
                jsonArray = jsonObject.getJSONArray(keyName);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonArray;
        }

        private List<String> getMessageKeys(JSONArray jsonArray) {


            List<String> messagekeys = new ArrayList<>();
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    messagekeys.add(String.valueOf(jsonArray.getString(i)));
                }
            } catch (JSONException e) {

            }

            return messagekeys;

        }

        public void logKeys(List<String> keyList, JSONArray jsonArray) {
            for (int i = 0; i < keyList.size(); i++) {
                Log.d("Dog Breeds: ", keyList.get(i));


                String key = keyList.get(i);
                try {
                    JSONObject jsonArrayObject = jsonArray.getJSONObject(i);
                    List<String> innerArrayStrings = new ArrayList<>();
                    JSONArray innerJSonArray = jsonArrayObject.getJSONArray(key);
                    for (int j = 0; j < innerJSonArray.length(); j++) {
                        innerArrayStrings.add(innerJSonArray.getString(i));
                    }
                    if (!innerArrayStrings.isEmpty()) {
                        for (String s : innerArrayStrings) {
                            Log.d(TAG, "Dog Breed Types:" + s);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

        public String setJSonString() {
            String jSonString = "{\"status\":\"success\",\"message\":{\"affenpinscher\":[],\"african\":[],\"airedale\":[],\"akita\":[],\"appenzeller\":[],\"basenji\":[],\"beagle\":[],\"bluetick\":[],\"borzoi\":[],\"bouvier\":[],\"boxer\":[],\"brabancon\":[],\"briard\":[],\"bulldog\":[\"boston\",\"french\"],\"bullterrier\":[\"staffordshire\"],\"cairn\":[],\"chihuahua\":[],\"chow\":[],\"clumber\":[],\"collie\":[\"border\"],\"coonhound\":[],\"corgi\":[\"cardigan\"],\"dachshund\":[],\"dane\":[\"great\"],\"deerhound\":[\"scottish\"],\"dhole\":[],\"dingo\":[],\"doberman\":[],\"elkhound\":[\"norwegian\"],\"entlebucher\":[],\"eskimo\":[],\"germanshepherd\":[],\"greyhound\":[\"italian\"],\"groenendael\":[],\"hound\":[\"Ibizan\",\"afghan\",\"basset\",\"blood\",\"english\",\"walker\"],\"husky\":[],\"keeshond\":[],\"kelpie\":[],\"komondor\":[],\"kuvasz\":[],\"labrador\":[],\"leonberg\":[],\"lhasa\":[],\"malamute\":[],\"malinois\":[],\"maltese\":[],\"mastiff\":[\"bull\",\"tibetan\"],\"mexicanhairless\":[],\"mountain\":[\"bernese\",\"swiss\"],\"newfoundland\":[],\"otterhound\":[],\"papillon\":[],\"pekinese\":[],\"pembroke\":[],\"pinscher\":[\"miniature\"],\"pointer\":[\"german\"],\"pomeranian\":[],\"poodle\":[\"miniature\",\"standard\",\"toy\"],\"pug\":[],\"pyrenees\":[],\"redbone\":[],\"retriever\":[\"chesapeake\",\"curly\",\"flatcoated\",\"golden\"],\"ridgeback\":[\"rhodesian\"],\"rottweiler\":[],\"saluki\":[],\"samoyed\":[],\"schipperke\":[],\"schnauzer\":[\"giant\",\"miniature\"],\"setter\":[\"english\",\"gordon\",\"irish\"],\"sheepdog\":[\"english\",\"shetland\"],\"shiba\":[],\"shihtzu\":[],\"spaniel\":[\"blenheim\",\"brittany\",\"cocker\",\"irish\",\"japanese\",\"sussex\",\"welsh\"],\"springer\":[\"english\"],\"stbernard\":[],\"terrier\":[\"american\",\"australian\",\"bedlington\",\"border\",\"dandie\",\"fox\",\"irish\",\"kerryblue\",\"lakeland\",\"norfolk\",\"norwich\",\"patterdale\",\"scottish\",\"sealyham\",\"silky\",\"tibetan\",\"toy\",\"westhighland\",\"wheaten\",\"yorkshire\"],\"vizsla\":[],\"weimaraner\":[],\"whippet\":[],\"wolfhound\":[\"irish\"]}}";
            return jSonString;
        }

    }

    public class QuestionOne {
        public void businessLogic() {
            messageRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            JSONObject inClassOne = setJson();
            JSONArray exerciseJsonArray = getJsonArray(inClassOne);
            jsonMessageList = convertJSONArrayToList(exerciseJsonArray);
            List<Message> messageList = new ArrayList<>();
            for (int i = 0; i < jsonMessageList.size(); i++) {
                messageList.add(new Message(jsonMessageList.get(i)));
                System.out.println("json message" + jsonMessageList.get(i));
            }
            MessageAdapter messageAdapter = new MessageAdapter(messageList);

            messageRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            messageRecyclerView.setHasFixedSize(true);
            messageRecyclerView.setAdapter(messageAdapter);

        }

        private List<String> convertJSONArrayToList(JSONArray jsonArray) {
            List<String> jsonArrayToList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    String oneMessage = String.valueOf(jsonArray.getString(i));
                    jsonArrayToList.add(oneMessage);
                    Log.d("messageLog", oneMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return jsonArrayToList;
        }

        private JSONObject setJson() {
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject("{\"status\":\"success\",\"message\":[\"affenpinscher\",\"african\",\"airedale\",\"akita\",\"appenzeller\",\"basenji\",\"beagle\",\"bluetick\",\"borzoi\",\"bouvier\",\"boxer\",\"brabancon\",\"briard\",\"bulldog\",\"bullterrier\",\"cairn\",\"chihuahua\",\"chow\",\"clumber\",\"collie\",\"coonhound\",\"corgi\",\"dachshund\",\"dane\",\"deerhound\",\"dhole\",\"dingo\",\"doberman\",\"elkhound\",\"entlebucher\",\"eskimo\",\"germanshepherd\",\"greyhound\",\"groenendael\",\"hound\",\"husky\",\"keeshond\",\"kelpie\",\"komondor\",\"kuvasz\",\"labrador\",\"leonberg\",\"lhasa\",\"malamute\",\"malinois\",\"maltese\",\"mastiff\",\"mexicanhairless\",\"mountain\",\"newfoundland\",\"otterhound\",\"papillon\",\"pekinese\",\"pembroke\",\"pinscher\",\"pointer\",\"pomeranian\",\"poodle\",\"pug\",\"pyrenees\",\"redbone\",\"retriever\",\"ridgeback\",\"rottweiler\",\"saluki\",\"samoyed\",\"schipperke\",\"schnauzer\",\"setter\",\"sheepdog\",\"shiba\",\"shihtzu\",\"spaniel\",\"springer\",\"stbernard\",\"terrier\",\"vizsla\",\"weimaraner\",\"whippet\",\"wolfhound\"]}");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonObject;


        }

        private JSONArray getJsonArray(JSONObject jsonObject) {

            JSONArray jsonArray = new JSONArray();

            try {
                jsonArray = jsonObject.getJSONArray("message");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonArray;
        }
    }

}
