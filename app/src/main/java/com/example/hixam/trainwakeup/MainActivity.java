package com.example.hixam.trainwakeup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.hixam.trainwakeup.clases.Station;
import com.example.hixam.trainwakeup.dao.StationsDAO;

public class MainActivity extends ListActivity {

        private StationsDAO datasource;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            datasource = new StationsDAO(this);
            datasource.open();

            List<Station> values = datasource.getAllComments();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            ArrayAdapter<Station> adapter = new ArrayAdapter<Station>(this,
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);
        }

        // Will be called via the onClick attribute
        // of the buttons in main.xml
        public void onClick(View view) {
            @SuppressWarnings("unchecked")
            ArrayAdapter<Station> adapter = (ArrayAdapter<Station>) getListAdapter();
            Station station = null;
            switch (view.getId()) {
                case R.id.add:
                    List<Station> myList = new ArrayList<Station>();
                    myList = getStations();

                    for(Station st : myList) {
                        // save the new station to the database
                        station = datasource.createStation(st);
                        adapter.add(station);
                    }
                    break;
                case R.id.delete:
                    if (getListAdapter().getCount() > 0) {
                        station = (Station) getListAdapter().getItem(0);
                        datasource.deleteComment(station);
                        adapter.remove(station);
                    }
                    break;
                case R.id.testbtn:
                    Intent t = new Intent(this, testActivity.class);
                    startActivity(t);
                    break;
            }
            adapter.notifyDataSetChanged();
        }


        public List<Station> getStations()
        {
            List<Station> list = new ArrayList<>();

            Station st1 = new Station();
            st1.setStation("Castelldefels");
            st1.setLongt("123654456");
            st1.setLat("321564654");
            list.add(st1);

            Station st2 = new Station();
            st2.setStation("Gava");
            st2.setLongt("123654456");
            st2.setLat("321564654");
            list.add(st2);

            Station st3 = new Station();
            st3.setStation("Viladecans");
            st3.setLongt("123654456");
            st3.setLat("321564654");
            list.add(st3);

            Station st4 = new Station();
            st4.setStation("Prat de llobregat");
            st4.setLongt("123654456");
            st4.setLat("321564654");
            list.add(st4);

            Station st5 = new Station();
            st5.setStation("Belvitge");
            st5.setLongt("123654456");
            st5.setLat("321564654");
            list.add(st5);



            return list;
        }


        @Override
        protected void onResume() {
            datasource.open();
            super.onResume();
        }

        @Override
        protected void onPause() {
            datasource.close();
            super.onPause();
        }

    }
