package bookbird.bookbird;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import bookbird.bookbird.Common.Common;
import bookbird.bookbird.Database.Database;
import bookbird.bookbird.Model.User;

public class Sign_in extends AppCompatActivity {
	EditText edtPhone,edtPassword;
	Button btnSignin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		
		edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
		edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
		btnSignin = (Button)findViewById(R.id.btnSignin);
		
		//init firebase
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		final DatabaseReference table_user = database.getReference("User");
		
		btnSignin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				final ProgressDialog mDialog = new ProgressDialog(Sign_in.this);
				mDialog.setMessage("Please Wait....");
				mDialog.show();
				
				table_user.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						
						//Check if user exists in database
						if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
							//Get User Information
							mDialog.dismiss();
							User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
							user.setPhone(edtPhone.getText().toString());  //set phone
							if (user.getPassword().equals(edtPassword.getText().toString())) {
								Intent homeIntent = new Intent(Sign_in.this,Home.class);
								Common.CurrentUser = user;
								
								new Database(getBaseContext()).cleanCart();
								
								startActivity(homeIntent);
								finish();
							} else {
								Toast.makeText(Sign_in.this, "RIP", Toast.LENGTH_SHORT).show();
							}
						}
						else {
							mDialog.dismiss();
							Toast.makeText(Sign_in.this,"User does not exist",Toast.LENGTH_SHORT).show();
						     }
							
					}
					
					@Override
					public void onCancelled(DatabaseError databaseError) {
						
					}
				});
			}
		});
	}
}
