package com.ty_yun.default_sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Button insertBtn, updateBtn, deleteBtn, selectBtn, selectAllBtn;
    EditText itemKeyEt, item1Et, item2Et, item3Et, item4Et;
    TextView result;

    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBtn = (Button) findViewById(R.id.insert_btn);
        updateBtn = (Button) findViewById(R.id.update_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);
        selectBtn = (Button) findViewById(R.id.select_btn);
        selectAllBtn = (Button) findViewById(R.id.select_all_btn);

        itemKeyEt = (EditText) findViewById(R.id.item_key);
        item1Et = (EditText) findViewById(R.id.item_1);
        item2Et = (EditText) findViewById(R.id.item_2);
        item3Et = (EditText) findViewById(R.id.item_3);
        item4Et = (EditText) findViewById(R.id.item_4);

        result = (TextView) findViewById(R.id.result);

        helper = Helper.getInstance(this);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.insert(item1Et.getText().toString(), Integer.parseInt(item2Et.getText().toString()), item3Et.getText().toString(), item4Et.getText().toString());
                result.setText("INSERT 성공!");
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.update(Integer.parseInt(itemKeyEt.getText().toString()), item1Et.getText().toString());
                result.setText("UPDATE 성공!");
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.delete(Integer.parseInt(itemKeyEt.getText().toString()));
                result.setText("DELETE 성공!");
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = helper.select(Integer.parseInt(itemKeyEt.getText().toString()));
                result.setText(""+item.getItemKey()+" / "+item.getItem1()+" / "+item.getItem2()+" / "+item.getItem3()+" / "+item.getItem4());
            }
        });

        selectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Item> item = helper.selectAll();
                String resultStr = "";
                for (int i=0; i<item.size(); i++) {
                    resultStr += "\n\n";
                    resultStr += ""+item.get(i).getItemKey()+
                            " / "+item.get(i).getItem1()+
                            " / "+item.get(i).getItem2()+
                            " / "+item.get(i).getItem3()+
                            " / "+item.get(i).getItem4();
                }
                result.setText(resultStr);
            }
        });
    }
}