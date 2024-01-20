package com.wayyeasy.invoicemaker.payment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.wayyeasy.invoicemaker.R;

import org.json.JSONObject;

public class RazorpayActivity extends AppCompatActivity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozorpay);


        // pre load payment resources of RazorPay
        Checkout.preload(getApplicationContext());


        findViewById(R.id.razorpay_payment_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }


        });


    }

    public void startPayment() {

        Checkout checkout = new Checkout();
        //  checkout.setImage(R.drawable.logo);
        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "Invoice Maker");
            options.put("description", "subscription charge");
            // options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            // options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "100");//4900 pass amount in currency subunits
            //options.put("prefill.email", "");
            //options.put("prefill.contact", "");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }


    }


    @Override
    public void onPaymentSuccess(String s) {

        try {
            Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            Log.d(TAG, "Exception in onPaymentSuccess");
        }


    }

    @Override
    public void onPaymentError(int i, String s) {

        try {
            Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            Log.d(TAG, "Exception in onPaymentSuccess");
        }

    }


}