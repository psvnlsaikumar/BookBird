package bookbird.bookbird;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import bookbird.bookbird.Interface.ItemClickListener;
import bookbird.bookbird.Model.Book;
import bookbird.bookbird.ViewHolder.BookViewHolder;

public class Books extends AppCompatActivity {
	
	RecyclerView recyclerView;
	RecyclerView.LayoutManager layoutManager;
	
	FirebaseDatabase database;
	DatabaseReference bookList;
	
	String categoryId="";
	
	FirebaseRecyclerAdapter<Book,BookViewHolder> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books);
		
		//FIrebase Init
		database = FirebaseDatabase.getInstance();
		bookList = database.getReference("Books");
		
		recyclerView = (RecyclerView)findViewById(R.id.recycler_book);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		
		//new intent
		
		if(getIntent() != null)
			categoryId = getIntent().getStringExtra("CategoryId");
		if (!categoryId.isEmpty() && categoryId != null) {
			LoadListBooks(categoryId);
		}
		}
	
	private void LoadListBooks(String categoryId) {
		adapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(Book.class,R.layout.book_item,BookViewHolder.class,
		bookList.orderByChild("MenuId").equalTo(categoryId)) {
			@Override
			protected void populateViewHolder(BookViewHolder viewHolder, Book model, int position) {
				
				viewHolder.book_name.setText(model.getName());
				Picasso.with(getBaseContext()).load(model.getImage())
						.into(viewHolder.book_image);
				
				final Book local = model;
				viewHolder.setItemClickListener(new ItemClickListener() {
					@Override
					public void onClick(View view, int position, boolean isLongClick) {
						Intent bookDetail = new Intent(Books.this,BookDetail.class);
						bookDetail.putExtra("BookId",adapter.getRef(position).getKey());
						startActivity(bookDetail);
					}
				});
				
			}
		};
		
		//setting adapter
		Log.d("TAG",""+adapter.getItemCount());
		recyclerView.setAdapter(adapter);
	}
	
	
	
}

