package com.example.hixam.trainwakeup;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hixam.trainwakeup.clases.Station;
import com.example.hixam.trainwakeup.dao.StationsDAO;

import java.util.ArrayList;
import java.util.List;

public class testActivity extends ListActivity {

    private String[] arraySpinner;
    private StationsDAO datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        datasource = new StationsDAO(this);
        datasource.open();
        int count = datasource.getAllComments().size();
        this.arraySpinner = new String[count];
        int i =0;

       // ArrayAdapter<Station> adapter = (ArrayAdapter<Station>) getListAdapter();
        Station station = null;
        List<Station> myList = new ArrayList<Station>();
        myList = getStations();

        for(Station st : myList) {
            // save the new station to the database
            station = new Station();
            station = datasource.createStation(st);
            //adapter2.add(station);
        }
        for(Station s : datasource.getAllComments())
        {
            if(count>0)
            arraySpinner[count-1] = s.getStation();
            count--;
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        Spinner s = (Spinner) findViewById(R.id.spinner);

        s.setAdapter(adapter2);
    }

    public List<Station> getStations()
    {
        List<Station> list = new ArrayList<>();

        Station st1 = new Station();
        st1.setStation("Castelldefels");
        st1.setLongt("1.979089");
        st1.setLat("41.278758");
        list.add(st1);

        Station st2 = new Station();
        st2.setStation("Gava");
        st2.setLongt("2.010439");
        st2.setLat("41.303417");
        list.add(st2);


        Station st3 = new Station();
        st3.setStation("Viladecans");
        st3.setLongt("2.027402");
        st3.setLat("41.309445");
        list.add(st3);

        Station st4 = new Station();
        st4.setStation("Prat de llobregat");
        st4.setLongt("2.089909");
        st4.setLat("41.330623");
        list.add(st4);

        Station st5 = new Station();
        st5.setStation("Bellvitge");
        st5.setLongt("2.115284");
        st5.setLat("41.354901");
        list.add(st5);


        return list;
    }
}
