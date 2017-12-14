package nyc.c4q.jsoninclass;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
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

       // QuestionOne questionOne = new QuestionOne();
        //questionOne.businessLogic();
        //QuestionTwo questionTwo = new QuestionTwo();
        //questionTwo.businessLogic();
        QuestionThree questionThree = new QuestionThree();
        questionThree.setRecyclerView();




        /*Question 3
        Open up a new Android Studio Project, create your own JSONObjects and JSONArrays, and parse them into a List in your app's MainActivity.

        When done, use that list in a Linear Layout RecyclerView. Add additional logic so that, when an item is clicked, the user is moved to another activity, where their selection's data is displayed.

        Push to a Github Repo, and paste the link below:*/


    }

    private class QuestionThree{

        public void setRecyclerView(){
             View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(MainActivity.this, QuestionThreeDetailActivity.class);
                    startActivity(detailIntent);
                }
            };
            RecyclerView termsRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            TermData termData = new TermData();
            termData = termData.setTermsList();
            TermAdapter termAdapter = new TermAdapter(termData, onClickListener);
            termsRecyclerView.setAdapter(termAdapter);
            termsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }



        public class TermData extends ArrayList<Term>{
            private TermData termData;

            private TermData(){

            }
            private TermData setTermsList() {
                Type collectionType = new TypeToken<Collection<Term>>() {
                }.getType();
                Gson gs = new Gson();
                Collection<Term> terms = null;
                InputStream is = getApplicationContext().getResources().openRawResource(R.raw.basicandroidterms);
                InputStreamReader isr = new InputStreamReader(is);
                terms = gs.fromJson(isr, collectionType);
                termData = new TermData();
                termData.addAll(terms);
                return termData;
            }

        }


        public class TermViewHolder extends RecyclerView.ViewHolder{
            TextView termView;
            TextView definition;
            public TermViewHolder(View itemView) {
                super(itemView);
                termView = (TextView) itemView.findViewById(R.id.term_text_view);
                definition = (TextView) itemView.findViewById(R.id.definition_text_view);
            }

            public void onBind (Term term){
                termView.setText(term.term);
                definition.setText(term.definition);
            }
        }

        public class TermAdapter extends RecyclerView.Adapter<TermViewHolder> {
            private TermData termDataList;
            private View.OnClickListener termClickListener;

            public TermAdapter(TermData termDataList, View.OnClickListener termClickListener){
                this.termDataList = termDataList;
                this.termClickListener = termClickListener;
            }

            @Override
            public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_item_view, parent, false);
                return new TermViewHolder(childView);
            }

            @Override
            public void onBindViewHolder(TermViewHolder holder, int position) {

                Term term = termDataList.get(position);
                holder.onBind(term);
                holder.itemView.setOnClickListener(termClickListener);
            }

            @Override
            public int getItemCount() {
                return termDataList.size();
            }
        }


        private class Term implements Serializable{
            String term;
            String definition;
        }


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



    /*Question 4
    Open up a new  Android Studio Project, copy the following string, and parse it into a List in your app's MainActivity:

        {
        "description": "Birds of Antarctica, grouped by family",
        "source": "https://en.wikipedia.org/wiki/List_of_birds_of_Antarctica",
        "birds": [
        {
        "family": "Albatrosses",
        "members": [
        "Wandering albatross",
        "Grey-headed albatross",
        "Black-browed albatross",
        "Sooty albatross",
        "Light-mantled albatross"
        ]
        },
        {
        "family": "Cormorants",
        "members": [
        "Antarctic shag",
        "Imperial shag",
        "Crozet shag"
        ]
        },
        {
        "family": "Diving petrels",
        "members": [
        "South Georgia diving petrel",
        "Common diving petrel"
        ]
        },
        {
        "family": "Ducks, geese and swans",
        "members": [
        "Yellow-billed pintail"
        ]
        },
        {
        "family": "Gulls",
        "members": [
        "Kelp gull"
        ]
        },
        {
        "family": "Penguins",
        "members": [
        "King penguin",
        "Emperor penguin",
        "Gentoo penguin",
        "Adelie penguin",
        "Chinstrap penguin",
        "Rockhopper penguin",
        "Macaroni penguin"
        ]
        },
        {
        "family": "Shearwaters and petrels",
        "members": [
        "Antarctic giant petrel",
        "Hall's giant petrel",
        "Southern fulmar",
        "Antarctic petrel",
        "Cape petrel",
        "Snow petrel",
        "Great-winged petrel",
        "White-headed petrel",
        "Blue petrel",
        "Broad-billed prion",
        "Salvin's prion",
        "Antarctic prion",
        "Slender-billed prion",
        "Fairy prion",
        "Grey petrel",
        "White-chinned petrel",
        "Kerguelen petrel",
        "Sooty shearwater"
        ]
        },
        {
        "family": "Sheathbills",
        "members": [
        "Snowy sheathbill"
        ]
        },
        {
        "family": "Skuas and jaegers",
        "members": [
        "South polar skua",
        "Brown skua"
        ]
        },
        {
        "family": "Storm petrels",
        "members": [
        "Grey-backed storm petrel",
        "Wilson's storm petrel",
        "Black-bellied storm petrel"
        ]
        },
        {
        "family": "Terns",
        "members": [
        "Arctic tern",
        "Antarctic tern"
        ]
        }
        ]
        }
        Using a Linear Layout RecyclerView, display this data using CardViews.

        Push to a Github Repo, and paste the link below:*/
