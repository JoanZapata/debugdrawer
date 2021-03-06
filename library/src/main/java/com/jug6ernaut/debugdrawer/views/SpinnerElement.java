package com.jug6ernaut.debugdrawer.views;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.jug6ernaut.debugdrawer.R;

/**
 * Created by williamwebb on 6/28/14.
 */
public abstract class SpinnerElement extends DebugElement {
	private final String name;
	private final String[] elements;

	public SpinnerElement(String name, String[] elements) {
		this.name = name;
		this.elements = elements;
	}
	public abstract void onItemSelect(String item);

	@Override
	public View onCreateView(DebugModule parent, LayoutInflater inflater, ViewGroup root) {
		Context context = root.getContext();
		Spinner spinner = (Spinner) inflater.inflate(R.layout.debug_template_spinner, null);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, elements); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setGravity(Gravity.START | Gravity.END | Gravity.CENTER_VERTICAL); // "start|end|center_vertical"
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelect(elements[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
	    TextView nameView = new TextView(context);
		nameView.setText(name);
        return TextElement.createDefaultLayout(nameView,spinner);
    }

}
