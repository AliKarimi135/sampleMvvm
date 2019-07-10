package ir.aliprogramer.learnrxjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;



import ir.aliprogramer.learnrxjava.R;
import ir.aliprogramer.learnrxjava.model.Personal;
import ir.aliprogramer.learnrxjava.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    TextView title;
    EditText name,family;
    AppCompatButton btnAdd;
    RecyclerView recyclerView;
    MainViewModel mainViewModel;
    PersonAdapter personAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        title=toolbar.findViewById(R.id.title);
        name=findViewById(R.id.name);
        family=findViewById(R.id.family);
        btnAdd=findViewById(R.id.add);
        recyclerView=findViewById(R.id.recycler_view);
        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,new LinearLayoutManager(recyclerView.getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.setInputLiveData(new Personal(name.getText().toString().trim(),family.getText().toString().trim()));
                clearInput();
            }
        });

        mainViewModel.getPersonalMutableLiveData().observe(this, new Observer<List<Personal>>() {
            @Override
            public void onChanged(List<Personal> personals) {
                personAdapter=new PersonAdapter(personals,mainViewModel);
                recyclerView.setAdapter(personAdapter);
                personAdapter.notifyDataSetChanged();
            }
        });
        mainViewModel.getInputLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                title.setText(s);
            }
        });
    }
public void fillRecycle(List<Personal>personalList){
    personAdapter=new PersonAdapter(personalList,mainViewModel);
    recyclerView.setAdapter(personAdapter);
    personAdapter.notifyDataSetChanged();
}
    private void clearInput() {
        name.setText("");
        family.setText("");
    }


}
