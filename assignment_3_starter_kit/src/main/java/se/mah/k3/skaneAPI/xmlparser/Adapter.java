package se.mah.k3.skaneAPI.xmlparser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import se.mah.k3.skaneAPI.R;
import se.mah.k3.skaneAPI.model.Journey;
import se.mah.k3.skaneAPI.model.Journeys;
import se.mah.k3.skaneAPI.model.Station;


public class Adapter extends BaseExpandableListAdapter {
        private ArrayList<Journey> MyJourney;
        private Context c;

        public Adapter(Context c,ArrayList<Journey> MyJourney){
            this.c=c;
            this.MyJourney = MyJourney;
        }

      @Override
        public int getGroupCount() {
            return MyJourney.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.group_layout,null);




            TextView Avgang = (TextView) convertView.findViewById(R.id.Avgang);
            String startStation = MyJourney.get(groupPosition).getStartStation().getStationName();
            Avgang.setText(startStation);

            TextView startTime =(TextView) convertView.findViewById(R.id.startTime);
            String depDateTime = MyJourney.get(groupPosition).getTimeToDeparture()+" min";
            startTime.setText(depDateTime);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.child_layout,null);

            TextView Departure = (TextView) convertView.findViewById(R.id.departure);
            String DepStation = MyJourney.get(groupPosition).getStartStation().getStationName();
            Departure.setText(DepStation);

            TextView arrival = (TextView) convertView.findViewById(R.id.arrival);
            String Arrival = MyJourney.get(groupPosition).getEndStation().getStationName();
            arrival.setText(Arrival);




            TextView startTime = (TextView) convertView.findViewById(R.id.timeToDeparture);
            String timeToDeparture = MyJourney.get(groupPosition).getTimeToDeparture()+" min";
            startTime.setText(timeToDeparture);

            ImageView error = (ImageView) convertView.findViewById(R.id.error);
            if (Integer.parseInt(MyJourney.getTimeToDeparture()) < 5) {      /* ERROR */
                Log.i("tiden", "fungerar");
                error.setVisibility(View.VISIBLE);
            }else{
                error.setVisibility(View.INVISIBLE);

            }

            TextView travelMinutes = (TextView) convertView.findViewById(R.id.travelMinutes);
            String Minutes = MyJourney.get(groupPosition).getTravelMinutes()+ " min restid";
            travelMinutes.setText(Minutes);
            return convertView;

            }
    @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }



        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

