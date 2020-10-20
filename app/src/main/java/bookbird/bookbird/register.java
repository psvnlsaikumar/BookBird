package bookbird.bookbird;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import bookbird.bookbird.Model.User;

public class register extends AppCompatActivity {
	MaterialEditText edtPhone,edtName,edtPassword;
	Button btnSignup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		edtName = (MaterialEditText)findViewById(R.id.edtName);
		edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
		edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
		btnSignup = (Button)findViewById(R.id.btnSignup);
		
		//init firebase
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		final DatabaseReference table_user = database.getReference("User");
		
		btnSignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final ProgressDialog mDialog = new ProgressDialog(register.this);
				mDialog.setMessage("Please Wait....");
				mDialog.show();
				
				table_user.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						//Check if user's phone already exists in the database
						if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
						mDialog.dismiss();
							Toast.makeText(register.this, "Phone number has already been registered", Toast.LENGTH_SHORT).show();
						}
						else {
							mDialog.dismiss();
							User user = new User(edtName.getText().toString(),edtPassword.getText().toString());
							table_user.child(edtPhone.getText().toString()).setValue(user);
							Toast.makeText(register.this, "New phone number has been registered successfully", Toast.LENGTH_SHORT).show();
							finish();
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
