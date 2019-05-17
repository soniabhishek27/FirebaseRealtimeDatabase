package com.example.therock;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rootRef=db.getReference();
    DatabaseReference gameref=rootRef.child("game");

    TextView textView;
    Button Rock,Paper,Scissor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // rootRef.setValue("cool");
       // rootRef.child("Users").child("c").setValue("fcuk");

        textView=findViewById(R.id.textView);
        Rock=findViewById(R.id.Rock);
        Paper=findViewById(R.id.Paper);
        Scissor=findViewById(R.id.Scissors);

        Rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameref.setValue("Rock");

            }
        });
        Paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameref.setValue("Paper");
            }
        });
        Scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameref.setValue("Scrissor");

            }
        });






    }

    @Override
    protected void onStart() {
        super.onStart();

            gameref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String text=dataSnapshot.getValue().toString();
                    textView.setText(text);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();

                }
            });

    }
}
