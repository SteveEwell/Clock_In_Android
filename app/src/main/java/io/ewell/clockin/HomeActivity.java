package io.ewell.clockin;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.ewell.barcodegen.*;


public class HomeActivity extends ActionBarActivity {

    private TextView mEmployeeNumber;
    private TextView mUPC;
    private TextView mBinary;
    private View mBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mEmployeeNumber = (TextView)findViewById(R.id.employee_number);
        mUPC = (TextView)findViewById(R.id.upc_number);
        mBinary = (TextView)findViewById(R.id.binary_number);
        mBarcode = (View)findViewById(R.id.barcode_view);
        BarcodeGenerator barcodeGen = new BarcodeGenerator("478112");
        mEmployeeNumber.setText(barcodeGen.getEmployeeNumber());
        mUPC.setText(barcodeGen.getUpc());
        mBinary.setText(barcodeGen.getBinary());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
