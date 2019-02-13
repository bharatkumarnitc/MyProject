package online.nitcalicut.myproject.Control2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;


public class E32_GridViewGalleryText extends AppCompatActivity {
    GridView grid;
    String[] arTitle = {
            "Google",
            "Github",
            "Instagram",
            "Facebook",
            "Flickr",
            "Pinterest",
            "Quora",
            "Twitter"

    };
    int[] arImage = {
            R.drawable.a1,
            R.drawable.a2,

            R.drawable.a3,
            R.drawable.a4,

            R.drawable.a5,
            R.drawable.a6,

            R.drawable.a7,
            R.drawable.a8

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e32__grid_view_gallery_text);

        grid = (GridView) findViewById(R.id.E32_grid);

        CustomGrid adapter = new CustomGrid(E32_GridViewGalleryText.this, arTitle, arImage);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(E32_GridViewGalleryText.this, "You Clicked at " + arTitle[+position], Toast.LENGTH_SHORT).show();

            }
        });

    }

    public class CustomGrid extends BaseAdapter {
        Context mContext;
        String[] mTitle;
        int[] mImage;

        public CustomGrid(Context c, String[] web, int[] Imageid) {
            mContext = c;
            this.mImage = Imageid;
            this.mTitle = web;
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder viewHolder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.activity_e32__row, parent, false);
                viewHolder=new ViewHolder(row);
                row.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) row.getTag();
            }

            viewHolder.img.setImageResource(mImage[position]);
            viewHolder.txt.setText(mTitle[position]);
            return grid;
        }
    }

    public class ViewHolder {
        TextView txt;
        ImageView img;

        public ViewHolder(View v) {
            txt = (TextView) findViewById(R.id.E32_grid_text);
            img = (ImageView) findViewById(R.id.E32_grid_image);

        }
    }
}

