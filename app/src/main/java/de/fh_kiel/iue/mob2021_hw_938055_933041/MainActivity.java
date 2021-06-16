package de.fh_kiel.iue.mob2021_hw_938055_933041;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.opengl.ETC1;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnNoteListener{

    ArrayList<CryptoData>cryptodata;

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;;
    Adapter adapter;
    DetailsFragment detailsFragment;
    static String API_DATA = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,USDC,CRO,NEO,MKR,ETH,XRP,XTC,ETC,ATOM,MIOTA,HT,LINK,XMR,LEO,ADA,TRX,DASH,XLM,BSV,USDT,BNB,BCH,LTC,EOS&tsyms=EUR";


    @Override


    public  boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.item1:

                Toast.makeText(this, "AsyncTask", Toast.LENGTH_SHORT).show();
                cryptodata.clear();
                onStartAsyncTask();

                return true;

            case R.id.item2:

                Toast.makeText(this, "Aktuallisiert", Toast.LENGTH_SHORT).show();
                cryptodata.clear();
                loadData();
                return true;

            default:return super.onOptionsItemSelected(item);
        }

    }

    public void onStartAsyncTask(){
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(progressBar.GONE);
            ConcurrencyTask ConT = new ConcurrencyTask(progressBar);
            ConT.execute();
        }

    protected class ConcurrencyTask extends AsyncTask<String, Integer, Boolean> {

        private  ProgressBar pbar;
        ConcurrencyTask(ProgressBar pb){ pbar=pb; }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbar.setVisibility(ProgressBar.VISIBLE);
            pbar.setProgress(0);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            for (int i =0; i < 50; i++)
            {
                publishProgress((i*100)/50);
                try{
                    cryptodata.add(new CryptoData("Test" + i,"30"));
                    Thread.sleep(50);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            adapter.notifyDataSetChanged();
            pbar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            Toast.makeText(MainActivity.this, "Fertig", Toast.LENGTH_SHORT).show();
            pbar.setVisibility(ProgressBar.GONE);
        }
    }

 private void loadData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API_DATA,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        //progressDialog.dismiss();

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            cryptodata.add(new CryptoData("BTC",jsonObject.getJSONObject("BTC").getString("EUR")));
                            cryptodata.add(new CryptoData("USDC",jsonObject.getJSONObject("USDC").getString("EUR")));
                            cryptodata.add(new CryptoData("CRO",jsonObject.getJSONObject("CRO").getString("EUR")));
                            cryptodata.add(new CryptoData("NEO",jsonObject.getJSONObject("NEO").getString("EUR")));
                            cryptodata.add(new CryptoData("MKR",jsonObject.getJSONObject("MKR").getString("EUR")));
                            cryptodata.add(new CryptoData("ETH",jsonObject.getJSONObject("ETH").getString("EUR")));
                            cryptodata.add(new CryptoData("XRP",jsonObject.getJSONObject("XRP").getString("EUR")));
                            cryptodata.add(new CryptoData("XTC",jsonObject.getJSONObject("XTC").getString("EUR")));
                            cryptodata.add(new CryptoData("ETC",jsonObject.getJSONObject("ETC").getString("EUR")));
                            cryptodata.add(new CryptoData("ATOM",jsonObject.getJSONObject("ATOM").getString("EUR")));
                            cryptodata.add(new CryptoData("MIOTA",jsonObject.getJSONObject("MIOTA").getString("EUR")));
                            cryptodata.add(new CryptoData("HT",jsonObject.getJSONObject("HT").getString("EUR")));
                            cryptodata.add(new CryptoData("LINK",jsonObject.getJSONObject("LINK").getString("EUR")));
                            cryptodata.add(new CryptoData("XMR",jsonObject.getJSONObject("XMR").getString("EUR")));
                            cryptodata.add(new CryptoData("LEO",jsonObject.getJSONObject("LEO").getString("EUR")));
                            cryptodata.add(new CryptoData("ADA",jsonObject.getJSONObject("ADA").getString("EUR")));
                            cryptodata.add(new CryptoData("TRX",jsonObject.getJSONObject("TRX").getString("EUR")));
                            cryptodata.add(new CryptoData("DASH",jsonObject.getJSONObject("DASH").getString("EUR")));
                            cryptodata.add(new CryptoData("XLM",jsonObject.getJSONObject("XLM").getString("EUR")));
                            cryptodata.add(new CryptoData("BSV",jsonObject.getJSONObject("BSV").getString("EUR")));
                            cryptodata.add(new CryptoData("USDT",jsonObject.getJSONObject("USDT").getString("EUR")));
                            cryptodata.add(new CryptoData("BNB",jsonObject.getJSONObject("BNB").getString("EUR")));
                            cryptodata.add(new CryptoData("BCH",jsonObject.getJSONObject("BCH").getString("EUR")));
                            cryptodata.add(new CryptoData("LTC",jsonObject.getJSONObject("LTC").getString("EUR")));
                            cryptodata.add(new CryptoData("EOS",jsonObject.getJSONObject("EOS").getString("EUR")));
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

 },
                 new Response.ErrorListener() {

                     @Override

                    public void onErrorResponse(VolleyError error) {

       }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewModel myviewmodel = new ViewModelProvider(this).get(MyViewModel.class);
        myviewmodel.getUsers().observe(this,users ->{



        });




        setdata();
        loadData();
        initRecyclerView();
        detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fgDetailsFragment);
    }


    private void setdata() {


        cryptodata = new ArrayList<>();


    }

    private void initRecyclerView() {
        mrecyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(cryptodata, this);
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onNoteClick(int position) {

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "Details", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DetailsActivity.class);
            DetailsFragment.data = cryptodata.get(position).getName() ;
            DetailsFragment.data1 = cryptodata.get(position).getEuro();
            startActivity(intent);
        }
        else {
            DetailsFragment.data = cryptodata.get(position).getName();
            DetailsFragment.data1 = cryptodata.get(position).getEuro();
            Fragment detailsFragment = new DetailsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, detailsFragment).addToBackStack(null).commit();


        }

    }
}