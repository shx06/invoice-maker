package com.wayyesy.invoicemaker.invoice;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.wayyesy.invoicemaker.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentHeader extends Fragment {

    TextInputEditText invoiceName, invoiceNo, invoiceCreatedDate, invoiceDueDate, invoicePO;
    Spinner invoiceDueTerms;
    int day, month, year;

    public FragmentHeader() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_header, container, false);

        invoiceName = view.findViewById(R.id.invoice_name);
        invoiceNo = view.findViewById(R.id.invoice_no);
        invoiceCreatedDate = view.findViewById(R.id.invoice_created_date);
        invoiceDueTerms = view.findViewById(R.id.invoice_due_terms);
        invoiceDueDate = view.findViewById(R.id.invoice_due_date);
        invoicePO = view.findViewById(R.id.p_o);

        Calendar calendar = Calendar.getInstance();

        invoiceCreatedDate.setOnClickListener(v -> {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view12, year, month, dayOfMonth) ->
                    invoiceCreatedDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime())), year, month, day);
            datePickerDialog.show();
        });

        invoiceDueDate.setOnClickListener(v -> {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, month, dayOfMonth) -> invoiceDueDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime())), year, month, day);
            datePickerDialog.show();
        });

        String[] optionsList = getResources().getStringArray(R.array.due_date);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, optionsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        invoiceDueTerms.setAdapter(adapter);

        invoiceDueTerms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), optionsList[position], Toast.LENGTH_SHORT).show();
                if (optionsList[position].equals("Custom")) {
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                    month = calendar.get(Calendar.MONTH);
                    year = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (v, year, month, dayOfMonth) -> {
                        }, year, month, day);
                    datePickerDialog.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        return view;
    }
}