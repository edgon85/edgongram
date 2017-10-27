package com.edgon.firebaseauthentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.edgon.firebaseauthentication.model.Artist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnAddArtist;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private List<String> artistNames;
    private List<Artist> artists;

    private static final String ARTIST_NODE = "Artists";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cuando no hay internet y se vuelve a conctar
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        btnAddArtist = (Button) findViewById(R.id.btn_add_artist);
        listView = (ListView) findViewById(R.id.list_view);

        databaseReference = FirebaseDatabase.getInstance().getReference(); //obtiene una referencia de https://edgongram.firebaseio.com/


        artistNames = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,artistNames);
        artists = new ArrayList<>();

        listView.setAdapter(arrayAdapter);

        leerDatos();

        btnAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createArtist();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                deleteData(position);
                Toast.makeText(MainActivity.this," Eliminado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void leerDatos() {
        databaseReference.child(ARTIST_NODE)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        artistNames.clear();
                        artists.clear();

                        if (dataSnapshot.exists()){
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                Artist artist = snapshot.getValue(Artist.class);
                                Log.w(TAG,"Artist name: " + artist.getName());
                                artistNames.add(artist.getName());
                                artists.add(artist);
                            }
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void createArtist(){
        Artist artist = new Artist(databaseReference.push().getKey(),"Garbage","Rock");

        databaseReference.child(ARTIST_NODE)
                .child(artist.getId())
                .setValue(artist);

        Toast.makeText(this, "Artista agregado", Toast.LENGTH_SHORT).show();
    }

    public void deleteData(int position){
        String idArtist = artists.get(position).getId();
        artists.remove(position);
        artistNames.remove(position);
        databaseReference.child(ARTIST_NODE).child(idArtist).removeValue();
    }


}
