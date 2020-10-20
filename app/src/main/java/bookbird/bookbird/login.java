package bookbird.bookbird;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class login extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button log_main=(Button)findViewById(R.id.login);
		Button reg_main=(Button)findViewById(R.id.register);
		
		
		BottomNavigationView botttomNavigationView = (BottomNavigationView)findViewById(R.id.Bottom_Navigation);
		botttomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId())
				{
					case R.id.search:
						Toast.makeText(login.this, "Action Search Clicked", Toast.LENGTH_SHORT).show();
						break;
					case R.id.home:
						Intent home = new Intent(login.this,Home.class);
						startActivity(home);
						
						break;
				    case R.id.settings:
						Toast.makeText(login.this, "Action Settings Clicked", Toast.LENGTH_SHORT).show();
						break;
					case R.id.orders:
						Intent orderIntent = new Intent(login.this,OrderStatus.class);
						startActivity(orderIntent);
						
					case R.id.cart:
						Intent cart = new Intent(login.this,Cart.class);
						startActivity(cart);
						break;
				
				}
				return true;
			}
		});
		log_main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent login=new Intent(login.this,Sign_in.class);
				startActivity(login);
			}
		});
		reg_main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent register = new Intent(login.this,register.class);
				startActivity(register);
			}
		});
		
	}
	}

