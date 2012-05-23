package com.croquis.list.sample15;

import com.croquis.list.CroquisListActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class CroquisListViewSample15Activity extends CroquisListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String[] values = new String[]
		{
			"Item1", "Item2", "Item3", "Item4",
			"Item5", "Item6", "Item7", "Item8",
			"Item9", "Item10", "Item11", "Item12",
			"Item13", "Item14", "Item15", "Item16",
			"Item17", "Item18", "Item19", "Item20",
			"Item21", "Item22", "Item23", "Item24",
			"Item25", "Item26", "Item27", "Item28",
			"Item29", "Item30"
		};
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));
	}
}
