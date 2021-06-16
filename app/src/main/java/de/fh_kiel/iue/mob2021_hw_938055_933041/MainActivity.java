package de.fh_kiel.iue.mob2021_hw_938055_933041;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements Adapter.OnNoteListener {

    ArrayList<CryptoData> cryptodata;
    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    Adapter adapter;
    DetailsFragment detailsFragment;
    static final String API_DATA = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,USDC,CRO,NEO,MKR,ETH,XRP,XTC,ETC,ATOM,MIOTA,HT,LINK,XMR,LEO,ADA,TRX,DASH,XLM,BSV,USDT,BNB,BCH,LTC,EOS&tsyms=EUR";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.asyncTaskMenuItem:
                Toast.makeText(this, getString(R.string.asyncTaskToast), Toast.LENGTH_SHORT).show();
                cryptodata.clear();
                onStartAsyncTask();
                findViewById(R.id.your_placeholder).setVisibility(View.INVISIBLE);
                return true;
            case R.id.internetMenuItem:

                Toast.makeText(this, getString(R.string.refreshedToast), Toast.LENGTH_SHORT).show();
                cryptodata.clear();
                loadData();
                findViewById(R.id.your_placeholder).setVisibility(View.INVISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onStartAsyncTask() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.GONE);
        ConcurrencyTask ConT = new ConcurrencyTask(progressBar);
        ConT.execute();
    }

    protected class ConcurrencyTask extends AsyncTask<String, Integer, Boolean> {
        private ProgressBar progressBar;

        ConcurrencyTask(ProgressBar pb) {
            progressBar = pb;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
            progressBar.setProgress(0);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            for (int i = 0; i < 50; i++) {
                publishProgress((i * 100) / 50);
                try {
                    cryptodata.add(new CryptoData(getString(R.string.syntheticCryptoName) + i, getString(R.string.syntheticCryptoValue)));
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            adapter.notifyDataSetChanged();
            progressBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            Toast.makeText(MainActivity.this, getString(R.string.finishToast), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(ProgressBar.GONE);
        }
    }

    /**
     * requests the cryptodata from min-api.cryptocompare.com and adds them to the ArrayList cryptodata.
     */
    private void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String shortEuro = getString(R.string.shortEuro);
                            cryptodata.add(new CryptoData("BTC", jsonObject.getJSONObject("BTC").getString(shortEuro)));
                            cryptodata.add(new CryptoData("USDC", jsonObject.getJSONObject("USDC").getString(shortEuro)));
                            cryptodata.add(new CryptoData("CRO", jsonObject.getJSONObject("CRO").getString(shortEuro)));
                            cryptodata.add(new CryptoData("NEO", jsonObject.getJSONObject("NEO").getString(shortEuro)));
                            cryptodata.add(new CryptoData("MKR", jsonObject.getJSONObject("MKR").getString(shortEuro)));
                            cryptodata.add(new CryptoData("ETH", jsonObject.getJSONObject("ETH").getString(shortEuro)));
                            cryptodata.add(new CryptoData("XRP", jsonObject.getJSONObject("XRP").getString(shortEuro)));
                            cryptodata.add(new CryptoData("XTC", jsonObject.getJSONObject("XTC").getString(shortEuro)));
                            cryptodata.add(new CryptoData("ETC", jsonObject.getJSONObject("ETC").getString(shortEuro)));
                            cryptodata.add(new CryptoData("ATOM", jsonObject.getJSONObject("ATOM").getString(shortEuro)));
                            cryptodata.add(new CryptoData("MIOTA", jsonObject.getJSONObject("MIOTA").getString(shortEuro)));
                            cryptodata.add(new CryptoData("HT", jsonObject.getJSONObject("HT").getString(shortEuro)));
                            cryptodata.add(new CryptoData("LINK", jsonObject.getJSONObject("LINK").getString(shortEuro)));
                            cryptodata.add(new CryptoData("XMR", jsonObject.getJSONObject("XMR").getString(shortEuro)));
                            cryptodata.add(new CryptoData("LEO", jsonObject.getJSONObject("LEO").getString(shortEuro)));
                            cryptodata.add(new CryptoData("ADA", jsonObject.getJSONObject("ADA").getString(shortEuro)));
                            cryptodata.add(new CryptoData("TRX", jsonObject.getJSONObject("TRX").getString(shortEuro)));
                            cryptodata.add(new CryptoData("DASH", jsonObject.getJSONObject("DASH").getString(shortEuro)));
                            cryptodata.add(new CryptoData("XLM", jsonObject.getJSONObject("XLM").getString(shortEuro)));
                            cryptodata.add(new CryptoData("BSV", jsonObject.getJSONObject("BSV").getString(shortEuro)));
                            cryptodata.add(new CryptoData("USDT", jsonObject.getJSONObject("USDT").getString(shortEuro)));
                            cryptodata.add(new CryptoData("BNB", jsonObject.getJSONObject("BNB").getString(shortEuro)));
                            cryptodata.add(new CryptoData("BCH", jsonObject.getJSONObject("BCH").getString(shortEuro)));
                            cryptodata.add(new CryptoData("LTC", jsonObject.getJSONObject("LTC").getString(shortEuro)));
                            cryptodata.add(new CryptoData("EOS", jsonObject.getJSONObject("EOS").getString(shortEuro)));
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

    /**
     * shows the details to the clioked List element.
     * @param position
     */
    @Override
    public void onNoteClick(int position) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.your_placeholder).setVisibility(View.VISIBLE);
            Toast.makeText(this, getString(R.string.detailsToast), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DetailsActivity.class);
            DetailsFragment.data = cryptodata.get(position).getName();
            DetailsFragment.data1 = cryptodata.get(position).getEuro();
            startActivity(intent);
        } else {
            findViewById(R.id.your_placeholder).setVisibility(View.VISIBLE);
            DetailsFragment.data = cryptodata.get(position).getName();
            DetailsFragment.data1 = cryptodata.get(position).getEuro();
            Fragment detailsFragment = new DetailsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, detailsFragment).addToBackStack(null).commit();
        }
    }
}